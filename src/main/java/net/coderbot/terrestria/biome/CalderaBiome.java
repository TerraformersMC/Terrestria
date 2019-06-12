package net.coderbot.terrestria.biome;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;
import java.util.function.Function;

public class CalderaBiome extends Biome {

	public CalderaBiome(Settings settings) {
		super(settings);

		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		this.addStructureFeature(Feature.STRONGHOLD, FeatureConfig.DEFAULT);

		DefaultBiomeFeatures.addLandCarvers(this);
		DefaultBiomeFeatures.addDefaultStructures(this);
		DefaultBiomeFeatures.addDefaultLakes(this);
		DefaultBiomeFeatures.addDungeons(this);
		DefaultBiomeFeatures.addMineables(this);
		DefaultBiomeFeatures.addDefaultOres(this);
		DefaultBiomeFeatures.addDefaultDisks(this);

		DefaultBiomeFeatures.addExtraDefaultFlowers(this);
		DefaultBiomeFeatures.addDefaultGrass(this);
		DefaultBiomeFeatures.addDefaultMushrooms(this);
		DefaultBiomeFeatures.addDefaultVegetation(this);
		DefaultBiomeFeatures.addSprings(this);
		DefaultBiomeFeatures.addFrozenTopLayer(this);

		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(EntityType.SHEEP, 12, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(EntityType.PIG, 10, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(EntityType.COW, 8, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(EntityType.LLAMA, 5, 4, 6));
		this.addSpawn(EntityCategory.AMBIENT, new SpawnEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.WITCH, 5, 1, 1));
	}

	public static class CalderaSurfaceBuilder extends DefaultSurfaceBuilder {
		public CalderaSurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> function) {
			super(function);
		}

		protected void generate(Random random, Chunk chunk, Biome biome, int x, int z, int unknown3, double unknown4, BlockState stone, BlockState water, BlockState top, BlockState filler, BlockState underwater, int unknown5) {
			BlockPos.Mutable pos = new BlockPos.Mutable(x & 15, 99, z & 15);

			int height = chunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG).get(pos.getX(), pos.getZ()) - 1;

			if(height >= 100 && height <= 102) {
				pos.set(pos.getX(), height, pos.getZ());

				while(pos.getY() > 97) {
					chunk.setBlockState(pos, Blocks.SAND.getDefaultState(), false);

					pos.setOffset(Direction.DOWN);
				}
			} else if(height > 102) {
				top = Blocks.GRASS_BLOCK.getDefaultState();
				filler = Blocks.DIRT.getDefaultState();
			} else {
				while(chunk.getBlockState(pos).isAir()) {
					chunk.setBlockState(pos, water, false);

					pos.setOffset(Direction.DOWN);
				}

				while(pos.getY() > 97) {
					chunk.setBlockState(pos, Blocks.SAND.getDefaultState(), false);

					pos.setOffset(Direction.DOWN);
				}
			}

			// Sea Level = 100
			super.generate(random, chunk, biome, x, z, unknown3, unknown4, stone, water, top, filler, underwater, 100);
		}
	}
}
