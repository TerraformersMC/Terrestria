package com.terraformersmc.terrestria.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.terraformersmc.terrestria.Terrestria;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TerrestriaConfigManager {
	private final Path biomeConfigPath;
	private final Path biomeConfigBackupPath;
	private final Path clientConfigPath;
	private final Path clientConfigBackupPath;
	private final Path generalConfigPath;
	private final Path generalConfigBackupPath;
	private TerrestriaBiomeConfig biomeConfig;
	private TerrestriaClientConfig clientConfig;
	private TerrestriaGeneralConfig generalConfig;

	private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

	public TerrestriaConfigManager() {
		Path configDirectory = FabricLoader.getInstance().getConfigDir().resolve(Terrestria.MOD_ID);

		try {
			Files.createDirectories(configDirectory);
		} catch (IOException e) {
			Terrestria.LOGGER.error("Failed to create config directory at " + configDirectory, e);
		}

		biomeConfigPath = configDirectory.resolve("biome.json");
		biomeConfigBackupPath = configDirectory.resolve("biome-invalid-syntax.json");

		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			clientConfigPath = configDirectory.resolve("client.json");
			clientConfigBackupPath = configDirectory.resolve("client-invalid-syntax.json");
		} else {
			clientConfigPath = null;
			clientConfigBackupPath = null;
		}

		generalConfigPath = configDirectory.resolve("general.json");
		generalConfigBackupPath = configDirectory.resolve("general-invalid-syntax.json");
	}

	private static <T> T loadConfig(Path configPath, T defaults, Class<T> clazz, Path backupPath) {
		if (!Files.exists(configPath)) {
			if (!saveConfig(configPath, defaults)) {
				Terrestria.LOGGER.error("Unable to save default configuration values for " + configPath);

				// Not much else to do at this point.
				return defaults;
			}
		}

		String content;

		try {
			// Load the entire file first, so that we don't get any weird IO errors halfway through.
			content = Files.readString(configPath);
		} catch (IOException e) {
			Terrestria.LOGGER.error("Failed to load Terrestria configuration file at " + configPath, e);
			Terrestria.LOGGER.error("This shouldn't happen under normal conditions, ensure that you have the correct permissions");
			Terrestria.LOGGER.error("Reverting to default configuration");

			return defaults;
		}

		try {
			return GSON.fromJson(content, clazz);
		} catch (JsonSyntaxException e) {
			Terrestria.LOGGER.error("Failed to parse Terrestria configuration file at " + configPath, e);

			// Revert the config so that the user has a fresh start if they need it.
			// It's also possible for the user to delete the config to recreate it, but that seems a bit unintuitive.
			Terrestria.LOGGER.error("Reverting to default configuration, ensure that your file has correct syntax");
			saveConfig(configPath, defaults);

			// There are a few websites like this, but this was the first result that came up.
			Terrestria.LOGGER.error("In the future, consider using something like https://jsonchecker.com/ to check your syntax");

			// It would be quite annoying if a user just spent 10 minutes editing the file, only for it to be wiped away.
			// Save a backup so they can review the errors and fix them.
			Terrestria.LOGGER.error("The previous configuration file content has been written to " + backupPath);

			try {
				Files.writeString(backupPath, content);
			} catch (IOException ioe) {
				Terrestria.LOGGER.error("Couldn't save previous configuration file content at " + backupPath, ioe);
				Terrestria.LOGGER.error("This shouldn't happen under normal conditions, ensure that you have the correct permissions and that your disk isn't full!");
			}

			return defaults;
		}
	}

	private static <T> boolean saveConfig(Path configPath, T instance) {
		String jsonString = GSON.toJson(instance);

		try {
			Files.writeString(configPath, jsonString);

			return true;
		} catch (IOException e) {
			Terrestria.LOGGER.error("Couldn't save Terrestria configuration file at " + configPath, e);
			Terrestria.LOGGER.error("This shouldn't happen under normal conditions, ensure that you have the correct permissions and that your disk isn't full!");

			return false;
		}
	}

	public TerrestriaBiomeConfig getBiomeConfig() {
		if (biomeConfig == null) {
			biomeConfig = loadConfig(biomeConfigPath, new TerrestriaBiomeConfig(), TerrestriaBiomeConfig.class, biomeConfigBackupPath);
		}

		return biomeConfig;
	}

	public TerrestriaClientConfig getClientConfig() {
		if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) {
			throw new UnsupportedOperationException("Cannot get the Terrestria client configuration when not on the client.");
		}

		if (clientConfig == null) {
			clientConfig = loadConfig(clientConfigPath, new TerrestriaClientConfig(), TerrestriaClientConfig.class, clientConfigBackupPath);
		}

		return clientConfig;
	}

	public TerrestriaGeneralConfig getGeneralConfig() {
		if (generalConfig == null) {
			generalConfig = loadConfig(generalConfigPath, new TerrestriaGeneralConfig(), TerrestriaGeneralConfig.class, generalConfigBackupPath);
		}

		return generalConfig;
	}
}
