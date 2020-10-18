package com.terraformersmc.terrestria.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.terraformersmc.terrestria.Terrestria;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class TerrestriaConfigManager {
	private static File directory;
	private static File clientFile;
	private static File generalFile;
	private static TerrestriaClientConfig clientConfig;
	private static TerrestriaGeneralConfig generalConfig;
	public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

	static {
		// Make sure the configuration is ready
		initializeGeneralConfig();
	}

	private static void prepareFiles() {
		if (directory == null) {
			directory = new File(FabricLoader.getInstance().getConfigDirectory(), Terrestria.MOD_ID);
			if (!directory.exists()) {
				directory.mkdir();
			}
		}
		if (clientFile == null && FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			clientFile = new File(directory, "client.json");
		}
		if (generalFile == null) {
			generalFile = new File(directory, "general.json");
		}
	}

	public static TerrestriaClientConfig initializeClientConfig() {
		if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) {
			throw new UnsupportedOperationException("Cannot initialize the Terrestria client configuration when not on the client.");
		}
		if (clientConfig != null) {
			return clientConfig;
		}

		clientConfig = new TerrestriaClientConfig();
		loadClient();
		return clientConfig;
	}

	public static TerrestriaGeneralConfig initializeGeneralConfig() {
		if (generalConfig != null) {
			return generalConfig;
		}

		generalConfig = new TerrestriaGeneralConfig();
		loadGeneral();

		return generalConfig;
	}

	private static void loadClient() {
		if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) {
			throw new UnsupportedOperationException("Cannot load the Terrestria client configuration when not on the client.");
		}
		prepareFiles();

		try {
			if (!clientFile.exists()) {
				saveClient();
			}
			if (clientFile.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(clientFile));

				clientConfig = GSON.fromJson(br, TerrestriaClientConfig.class);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't load Terrestria client configuration file; reverting to defaults");
			e.printStackTrace();
		}
	}

	private static void loadGeneral() {
		prepareFiles();

		try {
			if (!generalFile.exists()) {
				saveGeneral();
			}
			if (generalFile.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(generalFile));

				generalConfig = GSON.fromJson(br, TerrestriaGeneralConfig.class);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't load Terrestria general configuration file; reverting to defaults");
			e.printStackTrace();
		}
	}

	public static void saveClient() {
		if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) {
			throw new UnsupportedOperationException("Cannot save the Terrestria client configuration when not on the client.");
		}
		prepareFiles();

		String jsonString = GSON.toJson(clientConfig);

		try (FileWriter fileWriter = new FileWriter(clientFile)) {
			fileWriter.write(jsonString);
		} catch (IOException e) {
			System.err.println("Couldn't save Terrestria client configuration file");
			e.printStackTrace();
		}
	}

	public static void saveGeneral() {
		prepareFiles();

		String jsonString = GSON.toJson(generalConfig);

		try (FileWriter fileWriter = new FileWriter(generalFile)) {
			fileWriter.write(jsonString);
		} catch (IOException e) {
			System.err.println("Couldn't save Terrestria general configuration file");
			e.printStackTrace();
		}
	}

	public static TerrestriaClientConfig getClientConfig() {
		if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) {
			throw new UnsupportedOperationException("Cannot get the Terrestria client configuration when not on the client.");
		}
		return clientConfig;
	}

	public static TerrestriaGeneralConfig getGeneralConfig() {
		return generalConfig;
	}
}
