package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneVariantBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;

public class TerrestriaBlockFamilies {
	// Wood
	public static final BlockFamily CYPRESS = fromWoodBlocks(TerrestriaBlocks.CYPRESS);
	public static final BlockFamily HEMLOCK = fromWoodBlocks(TerrestriaBlocks.HEMLOCK);
	public static final BlockFamily JAPANESE_MAPLE = fromWoodBlocks(TerrestriaBlocks.JAPANESE_MAPLE);
	public static final BlockFamily RAINBOW_EUCALYPTUS = fromWoodBlocks(TerrestriaBlocks.RAINBOW_EUCALYPTUS);
	public static final BlockFamily REDWOOD = fromWoodBlocks(TerrestriaBlocks.REDWOOD);
	public static final BlockFamily RUBBER = fromWoodBlocks(TerrestriaBlocks.RUBBER);
	public static final BlockFamily SAKURA = fromWoodBlocks(TerrestriaBlocks.SAKURA);
	public static final BlockFamily WILLOW = fromWoodBlocks(TerrestriaBlocks.WILLOW);
	public static final BlockFamily YUCCA_PALM = fromWoodBlocks(TerrestriaBlocks.YUCCA_PALM);

	// Stone
	public static final BlockFamily VOLCANIC_ROCK = plainFromStoneBlocks(TerrestriaBlocks.VOLCANIC_ROCK);
	public static final BlockFamily VOLCANIC_ROCK_BRICK = brickFromStoneBlocks(TerrestriaBlocks.VOLCANIC_ROCK);
	public static final BlockFamily VOLCANIC_MOSSY_ROCK_BRICK = fromStoneVariantBlocks(TerrestriaBlocks.VOLCANIC_ROCK.mossyBricks);
	public static final BlockFamily VOLCANIC_COBBLESTONE = fromStoneVariantBlocks(TerrestriaBlocks.VOLCANIC_ROCK.cobblestone);
	public static final BlockFamily VOLCANIC_MOSSY_COBBLESTONE = fromStoneVariantBlocks(TerrestriaBlocks.VOLCANIC_ROCK.mossyCobblestone);


	private static BlockFamily fromWoodBlocks(WoodBlocks woodBlocks) {
		return BlockFamilies.familyBuilder(woodBlocks.planks)
				.button(woodBlocks.button)
				.fence(woodBlocks.fence)
				.fenceGate(woodBlocks.fenceGate)
				.pressurePlate(woodBlocks.pressurePlate)
				.sign(woodBlocks.sign, woodBlocks.wallSign)
				.slab(woodBlocks.slab)
				.stairs(woodBlocks.stairs)
				.door(woodBlocks.door)
				.trapdoor(woodBlocks.trapdoor)
				.recipeGroupPrefix("wooden")
				.recipeUnlockedBy("has_planks")
				.getFamily();
	}

	private static BlockFamily plainFromStoneBlocks(StoneBlocks stoneBlocks) {
		return BlockFamilies.familyBuilder(stoneBlocks.plain.full)
				.button(stoneBlocks.button)
				.slab(stoneBlocks.plain.slab)
				.stairs(stoneBlocks.plain.stairs)
				.wall(stoneBlocks.plain.wall)
				.pressurePlate(stoneBlocks.pressurePlate)
				.getFamily();
	}

	private static BlockFamily brickFromStoneBlocks(StoneBlocks stoneBlocks) {
		return BlockFamilies.familyBuilder(stoneBlocks.bricks.full)
				.chiseled(stoneBlocks.chiseledBricks)
				.cracked(stoneBlocks.crackedBricks)
				.slab(stoneBlocks.bricks.slab)
				.stairs(stoneBlocks.bricks.stairs)
				.wall(stoneBlocks.bricks.wall)
				.getFamily();
	}

	private static BlockFamily fromStoneVariantBlocks(StoneVariantBlocks stoneVarientBlocks) {
		return BlockFamilies.familyBuilder(stoneVarientBlocks.full)
				.slab(stoneVarientBlocks.slab)
				.stairs(stoneVarientBlocks.stairs)
				.wall(stoneVarientBlocks.wall)
				.getFamily();
	}
}
