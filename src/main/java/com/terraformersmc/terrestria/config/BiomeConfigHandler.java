package com.terraformersmc.terrestria.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class BiomeConfigHandler {

	private static File CONFIG_FILE;
	private static BiomeConfig CONFIG;

	private BiomeConfigHandler() {}

	public static BiomeConfig getBiomeConfig() {
		if(CONFIG != null) {
			return CONFIG;
		}

		CONFIG = new BiomeConfig( false);

		File configDirectory = new File(FabricLoader.getInstance().getConfigDirectory(), "terrestria");
		CONFIG_FILE = new File(configDirectory, "biomes.json");

		if (!configDirectory.exists()) {
			configDirectory.mkdir();
		}

		loadConfig();

		return CONFIG;
	}

	private static void loadConfig() {
		try {
			if (CONFIG_FILE.exists()) {
				Gson gson = new Gson();
				BufferedReader br = new BufferedReader(new FileReader(CONFIG_FILE));

				CONFIG = gson.fromJson(br, BiomeConfig.class);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't load Terrestria config file");
			e.printStackTrace();
		}
	}

	public static void save() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(CONFIG);

		try (FileWriter fileWriter = new FileWriter(CONFIG_FILE)) {
			fileWriter.write(jsonString);
		}  catch (IOException e) {
			System.err.println("Couldn't save Terrestria config file");
			e.printStackTrace();
		}
	}
}
