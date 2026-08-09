package com.terraformersmc.terrestria.tag;

import com.terraformersmc.terrestria.Terrestria;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.TagKey;

public final class TerrestriaBlockItemTags {
	// Conventional tags provided by Fabric (but we want them as BlockItemTags)
	public static final BlockItemTagId COBBLESTONES = from(ConventionalBlockTags.COBBLESTONES);
	public static final BlockItemTagId STONES = from(ConventionalBlockTags.STONES);
	public static final BlockItemTagId STRIPPED_LOGS = from(ConventionalBlockTags.STRIPPED_LOGS);
	public static final BlockItemTagId STRIPPED_WOODS = from(ConventionalBlockTags.STRIPPED_WOODS);

	// Conventional tags not provided by Fabric
	public static final BlockItemTagId BASALTS = create(Identifier.fromNamespaceAndPath("c", "basalts"));
	public static final BlockItemTagId BLACK_SANDS = create(Identifier.fromNamespaceAndPath("c", "black_sands"));
	public static final BlockItemTagId DIRTS = create(Identifier.fromNamespaceAndPath("c", "dirts"));
	public static final BlockItemTagId PLANKS_THAT_BURN = create(Identifier.fromNamespaceAndPath("c", "planks_that_burn"));
	public static final BlockItemTagId PODZOLS = create(Identifier.fromNamespaceAndPath("c", "podzols"));
	public static final BlockItemTagId SANDS = create(Identifier.fromNamespaceAndPath("c", "sands"));

	// Unconventional tags
	public static final BlockItemTagId CYPRESS_LOGS = create("cypress_logs");
	public static final BlockItemTagId HEMLOCK_LOGS = create("hemlock_logs");
	public static final BlockItemTagId JAPANESE_MAPLE_LOGS = create("japanese_maple_logs");
	public static final BlockItemTagId RAINBOW_EUCALYPTUS_LOGS = create("rainbow_eucalyptus_logs");
	public static final BlockItemTagId REDWOOD_LOGS = create("redwood_logs");
	public static final BlockItemTagId RUBBER_LOGS = create("rubber_logs");
	public static final BlockItemTagId SAKURA_LOGS = create("sakura_logs");
	public static final BlockItemTagId SMALL_OAK_LOGS = create("small_oak_logs");
	public static final BlockItemTagId WILLOW_LOGS = create("willow_logs");
	public static final BlockItemTagId YUCCA_PALM_LOGS = create("yucca_palm_logs");

	@SuppressWarnings("UnnecessaryReturnStatement")
	private TerrestriaBlockItemTags() {
		return;
	}

	private static BlockItemTagId create(String path) {
		return create(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path));
	}

	private static BlockItemTagId create(Identifier id) {
		return BlockItemTagId.create(id, id);
	}

	private static BlockItemTagId from(TagKey<?> key) {
		return create(key.location());
	}
}
