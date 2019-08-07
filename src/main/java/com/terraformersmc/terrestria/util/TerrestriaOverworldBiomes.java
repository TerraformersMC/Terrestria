package com.terraformersmc.terrestria.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import net.minecraft.world.biome.Biome;

public class TerrestriaOverworldBiomes {
	
	private TerrestriaOverworldBiomes() {
	}
	
	private static final Map<Biome, List<PredicatedBiomeEntry>> PREDICATED_BORDER_MAP = new HashMap<>();
	
	public static final List<PredicatedBiomeEntry> getPredicatedBorders(Biome biome) {
		return PREDICATED_BORDER_MAP.getOrDefault(biome, new ArrayList<>());
	}
	
	public static void addPredicatedBorderBiome(Biome biomeBase, Biome biomeBorder, Predicate<Biome> predicate) {
		PREDICATED_BORDER_MAP.computeIfAbsent(biomeBase, biome -> new ArrayList<>()).add(new PredicatedBiomeEntry(biomeBorder, predicate));
	}
	
	public static final class PredicatedBiomeEntry {
		public final Biome biome;
		public final Predicate<Biome> predicate;
		
		PredicatedBiomeEntry(Biome b, Predicate<Biome> p) {
			biome = b;
			predicate = p;
		}
	}
	
}
