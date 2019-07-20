package net.coderbot.terrestria.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.coderbot.terrestria.Terrestria;

import java.io.*;
import java.net.URISyntaxException;

public class BiomeConfigHandler {

	private static String CONFIG_PATH;
	private static File CONFIG_FILE;
	private static BiomeConfigHandler biomeConfigHandler;
	private static BiomeConfig config;
	private static final short CURRENT_BIOME_CONFIG_VERSION = 1;

	public BiomeConfigHandler() {
		biomeConfigHandler = this;
		try {
			CONFIG_PATH = new File(Terrestria.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath() + File.separator + "config" + File.separator;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		CONFIG_FILE = new File(CONFIG_PATH + "biomes.json");

		System.out.println("TERRESTRIA CONFIG:" + CONFIG_PATH);

		File configDir = new File(CONFIG_PATH);
		if (!configDir.exists()) {
			configDir.mkdir();
		}

		if (!loadConfig()) {
			createNewConfig();
		}
	}

	private static boolean loadConfig() {
		try {
			if (CONFIG_FILE.exists()) {
				Gson gson = new Gson();
				BufferedReader br = new BufferedReader(new FileReader(CONFIG_FILE));

				BiomeConfig config = gson.fromJson(br, BiomeConfig.class);
				store(config);
				return true;
			} else {
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	private void createNewConfig() {
		BiomeConfig config = new BiomeConfig();

		config.setVersion(CURRENT_BIOME_CONFIG_VERSION);

		config.addNode("cypress_forest", new BiomeConfigNode(true, 1.0f, 0.33f));

		store(config);
		save();
	}

	private static void save() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(config);
		try (FileWriter fileWriter = new FileWriter(CONFIG_FILE)) {
			fileWriter.write(jsonString);
		}  catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void store(BiomeConfig cf) {
		config = cf;
	}

	public static BiomeConfig getConfig() {
		return config;
	}
}
