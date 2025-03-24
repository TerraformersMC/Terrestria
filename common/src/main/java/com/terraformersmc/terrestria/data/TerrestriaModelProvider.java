package com.terraformersmc.terrestria.data;

import com.mojang.datafixers.util.Pair;
import com.terraformersmc.terraform.dirt.api.DirtBlocks;
import com.terraformersmc.terraform.leaves.api.data.LeavesModels;
import com.terraformersmc.terraform.wood.api.block.BareSmallLogBlock;
import com.terraformersmc.terraform.wood.api.block.QuarterLogBlock;
import com.terraformersmc.terraform.wood.api.block.SmallLogBlock;
import com.terraformersmc.terraform.wood.api.data.WoodModels;
import com.terraformersmc.terrestria.init.TerrestriaBlockFamilies;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.tint.GrassTintSource;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.FoliageColors;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TerrestriaModelProvider extends FabricModelProvider {
	public static final List<Pair<Property<Boolean>, Function<Identifier, BlockStateVariant>>> SMALL_LOG_VARIANT_FUNCTIONS = List.of(
			Pair.of(
					Properties.NORTH,
					model -> BlockStateVariant.create()
							.put(VariantSettings.MODEL, model)
							.put(VariantSettings.X, VariantSettings.Rotation.R90)
			),
			Pair.of(
					Properties.EAST,
					model -> BlockStateVariant.create()
							.put(VariantSettings.MODEL, model)
							.put(VariantSettings.X, VariantSettings.Rotation.R90)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
			),
			Pair.of(
					Properties.SOUTH,
					model -> BlockStateVariant.create()
							.put(VariantSettings.MODEL, model)
							.put(VariantSettings.X, VariantSettings.Rotation.R90)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
			),
			Pair.of(
					Properties.WEST,
					model -> BlockStateVariant.create()
							.put(VariantSettings.MODEL, model)
							.put(VariantSettings.X, VariantSettings.Rotation.R90)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
			),
			Pair.of(
					Properties.UP,
					model -> BlockStateVariant.create()
							.put(VariantSettings.MODEL, model)
			),
			Pair.of(
					Properties.DOWN,
					model -> BlockStateVariant.create()
							.put(VariantSettings.MODEL, model)
							.put(VariantSettings.X, VariantSettings.Rotation.R180)
			)
	);

	public TerrestriaModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator generator) {
		// WoodBlocks
		this.registerWoodBlocks(generator, TerrestriaBlocks.CYPRESS, TerrestriaBlockFamilies.CYPRESS, TerrestriaBlocks.CYPRESS_SAPLING, TerrestriaBlocks.POTTED_CYPRESS_SAPLING);
		this.registerWoodBlocks(generator, TerrestriaBlocks.HEMLOCK, TerrestriaBlockFamilies.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING, TerrestriaBlocks.POTTED_HEMLOCK_SAPLING);
		this.registerWoodBlocks(generator, TerrestriaBlocks.JAPANESE_MAPLE, TerrestriaBlockFamilies.JAPANESE_MAPLE, TerrestriaBlocks.JAPANESE_MAPLE_SAPLING, TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SAPLING);
		this.registerWoodBlocks(generator, TerrestriaBlocks.RAINBOW_EUCALYPTUS, TerrestriaBlockFamilies.RAINBOW_EUCALYPTUS, TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING, TerrestriaBlocks.POTTED_RAINBOW_EUCALYPTUS_SAPLING);
		this.registerWoodBlocks(generator, TerrestriaBlocks.REDWOOD, TerrestriaBlockFamilies.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING, TerrestriaBlocks.POTTED_REDWOOD_SAPLING);
		this.registerWoodBlocks(generator, TerrestriaBlocks.RUBBER, TerrestriaBlockFamilies.RUBBER, TerrestriaBlocks.RUBBER_SAPLING, TerrestriaBlocks.POTTED_RUBBER_SAPLING);
		this.registerWoodBlocks(generator, TerrestriaBlocks.SAKURA, TerrestriaBlockFamilies.SAKURA, TerrestriaBlocks.SAKURA_SAPLING, TerrestriaBlocks.POTTED_SAKURA_SAPLING);
		this.registerWoodBlocks(generator, TerrestriaBlocks.WILLOW, TerrestriaBlockFamilies.WILLOW, TerrestriaBlocks.WILLOW_SAPLING, TerrestriaBlocks.POTTED_WILLOW_SAPLING);
		this.registerWoodBlocks(generator, TerrestriaBlocks.YUCCA_PALM, TerrestriaBlockFamilies.YUCCA_PALM, TerrestriaBlocks.YUCCA_PALM_SAPLING, TerrestriaBlocks.POTTED_YUCCA_PALM_SAPLING);

		// StoneBlocks
		this.registerStoneBlockFamily(generator, TerrestriaBlockFamilies.VOLCANIC_ROCK);
		this.registerStoneBlockFamily(generator, TerrestriaBlockFamilies.VOLCANIC_ROCK_BRICK);
		this.registerStoneBlockFamily(generator, TerrestriaBlockFamilies.VOLCANIC_MOSSY_ROCK_BRICK);
		this.registerStoneBlockFamily(generator, TerrestriaBlockFamilies.VOLCANIC_COBBLESTONE);
		this.registerStoneBlockFamily(generator, TerrestriaBlockFamilies.VOLCANIC_MOSSY_COBBLESTONE);
		this.registerSmoothStone(generator, TerrestriaBlocks.VOLCANIC_ROCK);

		// DirtBlocks
		this.registerDirtBlocks(generator, TerrestriaBlocks.ANDISOL);

		// Non-WoodBlocks small trunks
		this.registerSmallLog(generator, TerrestriaBlocks.SAGUARO_CACTUS, null);
		this.registerSmallLog(generator, TerrestriaBlocks.SMALL_OAK_LOG, Blocks.OAK_LOG, Blocks.OAK_LEAVES);
		this.registerSmallLog(generator, TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG, Blocks.STRIPPED_OAK_LOG, Blocks.OAK_LEAVES);
		// Log item registration for small logs is in the item registration

		// Wood odds and ends
		generator.registerFlowerPotPlantAndItem(TerrestriaBlocks.BRYCE_SAPLING, TerrestriaBlocks.POTTED_BRYCE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
		generator.registerSingleton(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES, TexturedModel.LEAVES);
		this.registerBlockItemModel(generator, TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES);
		generator.registerFlowerPotPlantAndItem(TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING, TerrestriaBlocks.POTTED_DARK_JAPANESE_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
		generator.registerTintedBlockAndItem(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES, TexturedModel.LEAVES, FoliageColors.DEFAULT);
		generator.registerFlowerPotPlantAndItem(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING, TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SHRUB_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
		generator.registerSingleton(TerrestriaBlocks.JUNGLE_PALM_LEAVES, TexturedModel.LEAVES);
		this.registerBlockItemModel(generator, TerrestriaBlocks.JUNGLE_PALM_LEAVES);
		generator.registerFlowerPotPlantAndItem(TerrestriaBlocks.JUNGLE_PALM_SAPLING, TerrestriaBlocks.POTTED_JUNGLE_PALM_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);

		// Stone odds and ends
		generator.registerRotatable(TerrestriaBlocks.VOLCANIC_SAND);
		this.registerBlockItemModel(generator, TerrestriaBlocks.VOLCANIC_SAND);

		// Misc. vegetation
		generator.registerFlowerPotPlantAndItem(TerrestriaBlocks.AGAVE, TerrestriaBlocks.POTTED_AGAVE, BlockStateModelGenerator.CrossType.NOT_TINTED);
		generator.registerFlowerPotPlantAndItem(TerrestriaBlocks.ALOE_VERA, TerrestriaBlocks.POTTED_ALOE_VERA, BlockStateModelGenerator.CrossType.NOT_TINTED);
		generator.registerSingleton(TerrestriaBlocks.CATTAIL, TexturedModel.TEMPLATE_SEAGRASS);
		this.registerBlockItemModel(generator, TerrestriaBlocks.CATTAIL);
		generator.registerTintableCrossBlockState(TerrestriaBlocks.DEAD_GRASS, BlockStateModelGenerator.CrossType.NOT_TINTED);
		this.registerBlockItemModel(generator, TerrestriaBlocks.DEAD_GRASS);
		generator.registerFlowerPotPlantAndItem(TerrestriaBlocks.INDIAN_PAINTBRUSH, TerrestriaBlocks.POTTED_INDIAN_PAINTBRUSH, BlockStateModelGenerator.CrossType.NOT_TINTED);
		generator.registerFlowerPotPlantAndItem(TerrestriaBlocks.MONSTERAS, TerrestriaBlocks.POTTED_MONSTERAS, BlockStateModelGenerator.CrossType.NOT_TINTED);
		generator.registerFlowerPotPlantAndItem(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING, TerrestriaBlocks.POTTED_SAGUARO_CACTUS_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
		Identifier cattailTop = generator.createSubModel(TerrestriaBlocks.TALL_CATTAIL, "_top", Models.TEMPLATE_SEAGRASS, TextureMap::texture);
		Identifier cattailBottom = generator.createSubModel(TerrestriaBlocks.TALL_CATTAIL, "_bottom", Models.TEMPLATE_SEAGRASS, TextureMap::texture);
		generator.registerDoubleBlock(TerrestriaBlocks.TALL_CATTAIL, cattailTop, cattailBottom);
		generator.registerFlowerPotPlantAndItem(TerrestriaBlocks.TINY_CACTUS, TerrestriaBlocks.POTTED_TINY_CACTUS, BlockStateModelGenerator.CrossType.NOT_TINTED);
	}

	@Override
	public void generateItemModels(ItemModelGenerator generator) {
		// Small logs
		this.registerSmallLogItemModels(generator, TerrestriaBlocks.SAKURA);
		this.registerSmallLogItemModels(generator, TerrestriaBlocks.YUCCA_PALM);

		this.registerSmallLogItemModel(generator, TerrestriaBlocks.SAGUARO_CACTUS);
		this.registerSmallLogItemModel(generator, TerrestriaBlocks.SMALL_OAK_LOG, Blocks.OAK_LOG);
		this.registerSmallLogItemModel(generator, TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG, Blocks.STRIPPED_OAK_LOG);

		// Boats
		this.registerBoatItemModels(generator, TerrestriaItems.CYPRESS);
		this.registerBoatItemModels(generator, TerrestriaItems.HEMLOCK);
		this.registerBoatItemModels(generator, TerrestriaItems.JAPANESE_MAPLE);
		this.registerBoatItemModels(generator, TerrestriaItems.RAINBOW_EUCALYPTUS);
		this.registerBoatItemModels(generator, TerrestriaItems.REDWOOD);
		this.registerBoatItemModels(generator, TerrestriaItems.RUBBER);
		this.registerBoatItemModels(generator, TerrestriaItems.SAKURA);
		this.registerBoatItemModels(generator, TerrestriaItems.WILLOW);
		this.registerBoatItemModels(generator, TerrestriaItems.YUCCA_PALM);

		// Hand tools
		generator.register(TerrestriaItems.LOG_TURNER, Models.HANDHELD_ROD);
	}


	/*
	 * Shorthand for registering just the item model of a block item which uses its block's model.
	 */
	private void registerBlockItemModel(BlockStateModelGenerator generator, Block block) {
		generator.registerParentedItemModel(block, ModelIds.getBlockModelId(block));
	}

	private void registerLeafPile(BlockStateModelGenerator generator, Block leafPile, Block leaves) {
		// Bottom half of registerWoolAndCarpet
		Identifier carpetModel = TexturedModel.CARPET.get(leaves)
				.upload(leafPile, generator.modelCollector);
		generator.blockStateCollector.accept(
				BlockStateModelGenerator.createSingletonBlockState(leafPile, carpetModel));

		this.registerBlockItemModel(generator, leafPile);
	}

	private void registerSmallLog(BlockStateModelGenerator generator, Block log, @Nullable Block leaves) {
		registerSmallLog(generator, log, null, leaves);
	}

	private void registerSmallLog(BlockStateModelGenerator generator, Block log, @Nullable Block texture, @Nullable Block leaves) {
		if (!(log instanceof BareSmallLogBlock)) {
			throw new IllegalArgumentException("Attempt to register non-SmallLog via registerSmallLog: " + log);
		}
		TextureKey leavesKey = TextureKey.of("leaves");
		TextureMap logMap = TextureMap.sideAndEndForTop(texture == null ? log : texture);
		TextureMap leafyMap = logMap
				.copyAndAdd(leavesKey, TextureMap.getId(leaves == null ? Blocks.OAK_LEAVES : leaves))
				.copy(leavesKey, TextureKey.PARTICLE);
		Model logModel = new Model(
				Optional.of(WoodModels.BLOCK_SMALL_LOG),
				Optional.empty(),
				TextureKey.PARTICLE, TextureKey.SIDE);
		Model branchModel = new Model(
				Optional.of(WoodModels.BLOCK_SMALL_LOG_BRANCH),
				Optional.of("_branch"),
				TextureKey.PARTICLE, TextureKey.END, TextureKey.SIDE);
		Model leavesModel = new Model(
				Optional.of(WoodModels.BLOCK_SMALL_LOG_LEAVES),
				Optional.of("_leaves"),
				TextureKey.PARTICLE, leavesKey);
		Model cutoutModel = new Model(
				Optional.of(WoodModels.BLOCK_SMALL_LOG_LEAVES_CUTOUT),
				Optional.of("_leaves_cutout"),
				TextureKey.PARTICLE, leavesKey);
		Identifier texturedLogModel = TexturedModel.makeFactory(block -> logMap, logModel)
				.upload(log, generator.modelCollector);
		Identifier texturedBranchModel = TexturedModel.makeFactory(block -> logMap, branchModel)
				.upload(log, generator.modelCollector);
		Identifier texturedLeavesModel = TexturedModel.makeFactory(block -> leafyMap, leavesModel)
				.upload(log, generator.modelCollector);
		Identifier texturedCutoutModel = TexturedModel.makeFactory(block -> leafyMap, cutoutModel)
				.upload(log, generator.modelCollector);
		MultipartBlockStateSupplier multipartBlockStateSupplier = MultipartBlockStateSupplier.create(log);

		SMALL_LOG_VARIANT_FUNCTIONS.forEach(pair -> {
			multipartBlockStateSupplier.with(
					When.create().set(pair.getFirst(), true),
					pair.getSecond().apply(texturedBranchModel));
			multipartBlockStateSupplier.with(
					When.create().set(pair.getFirst(), false),
					pair.getSecond().apply(texturedLogModel));
			if (leaves != null) {
				multipartBlockStateSupplier.with(
						When.allOf(When.create().set(SmallLogBlock.HAS_LEAVES, true), When.create().set(pair.getFirst(), true)),
						pair.getSecond().apply(texturedCutoutModel));
				multipartBlockStateSupplier.with(
						When.allOf(When.create().set(SmallLogBlock.HAS_LEAVES, true), When.create().set(pair.getFirst(), false)),
						pair.getSecond().apply(texturedLeavesModel));
			}
		});

		generator.blockStateCollector.accept(multipartBlockStateSupplier);
	}

	private void registerQuarterLog(BlockStateModelGenerator generator, Block quarterLog, Block log) {
		// Quarter Logs have specifically rotated textures on all sides:
		// * an inside texture to the North and East
		// * a side texture to the South and West
		// * a top (end) texture on the top and bottom
		TextureMap textureMap = new TextureMap()
				.put(TextureKey.INSIDE, TextureMap.getId(quarterLog))
				.put(TextureKey.SIDE, TextureMap.getId(log))
				.put(TextureKey.END, TextureMap.getSubId(quarterLog, "_top"))
				.copy(TextureKey.SIDE, TextureKey.PARTICLE);
		Model model = new Model(
				Optional.of(WoodModels.BLOCK_QUARTER_LOG),
				Optional.empty(),
				TextureKey.PARTICLE, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.END);
		Identifier texturedModel = TexturedModel.makeFactory(block -> textureMap, model)
				.upload(quarterLog, generator.modelCollector);

		generator.blockStateCollector.accept(
				VariantsBlockStateSupplier.create(quarterLog)
						.coordinate(
								BlockStateVariantMap.create(Properties.AXIS, QuarterLogBlock.BARK_SIDE)
										.registerVariants((axis, barkSide) -> switch (axis) {
											case X -> switch (barkSide) {
												case SOUTHWEST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel)
														.put(VariantSettings.X, VariantSettings.Rotation.R90)
														.put(VariantSettings.Y, VariantSettings.Rotation.R270));
												case NORTHWEST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel)
														.put(VariantSettings.X, VariantSettings.Rotation.R90)
														.put(VariantSettings.Y, VariantSettings.Rotation.R90));
												case NORTHEAST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel)
														.put(VariantSettings.X, VariantSettings.Rotation.R270)
														.put(VariantSettings.Y, VariantSettings.Rotation.R90));
												case SOUTHEAST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel)
														.put(VariantSettings.X, VariantSettings.Rotation.R270)
														.put(VariantSettings.Y, VariantSettings.Rotation.R270));
											};
											case Y -> switch (barkSide) {
												case SOUTHWEST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel));
												case NORTHWEST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel)
														.put(VariantSettings.Y, VariantSettings.Rotation.R90));
												case NORTHEAST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel)
														.put(VariantSettings.Y, VariantSettings.Rotation.R180));
												case SOUTHEAST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel)
														.put(VariantSettings.Y, VariantSettings.Rotation.R270));
											};
											case Z -> switch (barkSide) {
												case SOUTHWEST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel)
														.put(VariantSettings.X, VariantSettings.Rotation.R90));
												case NORTHWEST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel)
														.put(VariantSettings.X, VariantSettings.Rotation.R270));
												case NORTHEAST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel)
														.put(VariantSettings.X, VariantSettings.Rotation.R270)
														.put(VariantSettings.Y, VariantSettings.Rotation.R180));
												case SOUTHEAST -> List.of(BlockStateVariant.create()
														.put(VariantSettings.MODEL, texturedModel)
														.put(VariantSettings.X, VariantSettings.Rotation.R90)
														.put(VariantSettings.Y, VariantSettings.Rotation.R180));
											};
										})
						)
		);

		this.registerBlockItemModel(generator, quarterLog);
	}

	private void registerWoodBlocks(BlockStateModelGenerator generator, WoodBlocks woodBlocks, BlockFamily blockFamily, Block sapling, Block pottedSapling) {
		// Vanilla part of WoodBlocks
		generator.registerCubeAllModelTexturePool(blockFamily.getBaseBlock()).family(blockFamily);
		this.registerBlockItemModel(generator, woodBlocks.fenceGate);
		this.registerBlockItemModel(generator, woodBlocks.planks);
		this.registerBlockItemModel(generator, woodBlocks.pressurePlate);

		if (woodBlocks.getSize().equals(WoodBlocks.LogSize.SMALL)) {
			// This case is for small log trees that do have a full WoodBlocks
			this.registerSmallLog(generator, woodBlocks.log, woodBlocks.leaves);
			this.registerSmallLog(generator, woodBlocks.strippedLog, woodBlocks.leaves);

			// Log item registration for small logs is in the item registration
		} else {
			// This is what vanilla's BlockFamily-based code does
			if (woodBlocks.hasWood()) {
				generator.registerLog(woodBlocks.log).log(woodBlocks.log).wood(woodBlocks.wood);
				generator.registerLog(woodBlocks.strippedLog).log(woodBlocks.strippedLog).wood(woodBlocks.strippedWood);

				this.registerBlockItemModel(generator, woodBlocks.wood);
				this.registerBlockItemModel(generator, woodBlocks.strippedWood);
			} else {
				generator.registerLog(woodBlocks.log).log(woodBlocks.log);
				generator.registerLog(woodBlocks.strippedLog).log(woodBlocks.strippedLog);
			}

			this.registerBlockItemModel(generator, woodBlocks.log);
			this.registerBlockItemModel(generator, woodBlocks.strippedLog);
		}
		generator.registerHangingSign(blockFamily.getBaseBlock(), woodBlocks.hangingSign, woodBlocks.wallHangingSign);
		if (sapling != null) {
			generator.registerFlowerPotPlantAndItem(sapling, pottedSapling, BlockStateModelGenerator.CrossType.NOT_TINTED);
		}
		if ("willow".equals(woodBlocks.getName())) {
			// TODO: generalize this special case?
			TextureMap textureMap = new TextureMap()
					.put(TextureKey.SIDE, TextureMap.getId(woodBlocks.leaves))
					.put(TextureKey.END, TextureMap.getSubId(woodBlocks.leaves, "_top"))
					.copy(TextureKey.SIDE, TextureKey.PARTICLE);
			Model model = new Model(
					Optional.of(LeavesModels.BLOCK_PILLAR_LEAVES),
					Optional.empty(),
					TextureKey.PARTICLE, TextureKey.SIDE, TextureKey.END);
			TexturedModel.Factory texturedModel = TexturedModel.makeFactory(block -> textureMap, model);

			generator.registerTintedBlockAndItem(woodBlocks.leaves, texturedModel, FoliageColors.DEFAULT);
		} else if (woodBlocks.isTintable()) {
			generator.registerTintedBlockAndItem(woodBlocks.leaves, TexturedModel.LEAVES, FoliageColors.DEFAULT);
		} else {
			generator.registerSingleton(woodBlocks.leaves, TexturedModel.LEAVES);
			this.registerBlockItemModel(generator, woodBlocks.leaves);
		}

		// Extra parts of WoodBlocks
		if (woodBlocks.hasQuarterLog()) {
			this.registerQuarterLog(generator, woodBlocks.quarterLog, woodBlocks.log);
			this.registerQuarterLog(generator, woodBlocks.strippedQuarterLog, woodBlocks.strippedLog);
		}
		if (woodBlocks.hasLeafPile()) {
			this.registerLeafPile(generator, woodBlocks.leafPile, woodBlocks.leaves);
		}
	}


	private void registerStoneBlockFamily(BlockStateModelGenerator generator, BlockFamily blockFamily) {
		// Vanilla part of stone BlockFamily
		generator.registerCubeAllModelTexturePool(blockFamily.getBaseBlock()).family(blockFamily);

		this.registerBlockItemModel(generator, blockFamily.getBaseBlock());
		if (blockFamily.getVariants().containsKey(BlockFamily.Variant.CHISELED)) {
			this.registerBlockItemModel(generator, blockFamily.getVariant(BlockFamily.Variant.CHISELED));
		}
		if (blockFamily.getVariants().containsKey(BlockFamily.Variant.CRACKED)) {
			this.registerBlockItemModel(generator, blockFamily.getVariant(BlockFamily.Variant.CRACKED));
		}
		if (blockFamily.getVariants().containsKey(BlockFamily.Variant.PRESSURE_PLATE)) {
			this.registerBlockItemModel(generator, blockFamily.getVariant(BlockFamily.Variant.PRESSURE_PLATE));
		}
	}

	private void registerSmoothStone(BlockStateModelGenerator generator, StoneBlocks stoneBlocks) {
		// Vanilla part
		// Non-hard-coded version of BlockStateModelGenerator.registerSmoothStone()
		TextureMap fullTexture = TextureMap.all(stoneBlocks.smooth.full);
		TextureMap slabTexture = TextureMap.sideEnd(TextureMap.getSubId(stoneBlocks.smooth.slab, "_side"), fullTexture.getTexture(TextureKey.TOP));
		Identifier slabId = Models.SLAB.upload(stoneBlocks.smooth.slab, slabTexture, generator.modelCollector);
		Identifier topSlabId = Models.SLAB_TOP.upload(stoneBlocks.smooth.slab, slabTexture, generator.modelCollector);
		Identifier doubleSlabId = Models.CUBE_COLUMN.uploadWithoutVariant(stoneBlocks.smooth.slab, "_double", slabTexture, generator.modelCollector);
		generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(stoneBlocks.smooth.slab, slabId, topSlabId, doubleSlabId));
		BlockStateModelGenerator.BlockTexturePool textures = generator.registerCubeAllModelTexturePool(stoneBlocks.smooth.full);

		this.registerBlockItemModel(generator, stoneBlocks.smooth.full);
		this.registerBlockItemModel(generator, stoneBlocks.smooth.slab);

		// Extra parts
		if (stoneBlocks.smooth.stairs != null) {
			textures.stairs(stoneBlocks.smooth.stairs);
		}
		if (stoneBlocks.smooth.wall != null) {
			textures.wall(stoneBlocks.smooth.wall);
		}
	}

	private void registerDirtBlocks(BlockStateModelGenerator generator, DirtBlocks dirtBlocks) {
		// Dirt basic block
		generator.registerRotatable(dirtBlocks.getDirt());
		Identifier dirtTextureId = TextureMap.getId(dirtBlocks.getDirt());

		// Dirt Path based on vanilla model and the partial code in BlockStateModelGenerator.registerDirtPath()
		TextureMap pathTexture = TextureMap.sideAndEndForTop(dirtBlocks.getDirtPath())
				.put(TextureKey.BOTTOM, dirtTextureId)
				.inherit(TextureKey.BOTTOM, TextureKey.PARTICLE)
				.put(TextureKey.TOP, TextureMap.getSubId(Blocks.DIRT_PATH,"_top"))
				.put(TextureKey.SIDE, TextureMap.getSubId(dirtBlocks.getDirtPath(), "_side"));
		Identifier pathTextureId = new Model(
				Optional.of(ModelIds.getBlockModelId(Blocks.DIRT_PATH)),
				Optional.empty(),
				TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP
		).upload(dirtBlocks.getDirtPath(), pathTexture, generator.modelCollector);
		generator.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(
				dirtBlocks.getDirtPath(), pathTextureId));

		// Grass, Mycelium, and Podzol based roughly on BlockStateModelGenerator.registerTopSoils()
		// grass
		TextureMap grassTexture = new TextureMap()
				.put(TextureKey.BOTTOM, dirtTextureId)
				.inherit(TextureKey.BOTTOM, TextureKey.PARTICLE)
				.put(TextureKey.TOP, TextureMap.getSubId(Blocks.GRASS_BLOCK, "_top"))
				.put(TextureKey.SIDE, TextureMap.getSubId(dirtBlocks.getGrassBlock(), "_side"))
				.put(TextureKey.of("overlay"), TextureMap.getSubId(Blocks.GRASS_BLOCK, "_side_overlay"));
		Identifier grassTextureId = new Model(
				Optional.of(ModelIds.getBlockModelId(Blocks.GRASS_BLOCK)),
				Optional.empty(),
				TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP
		).upload(dirtBlocks.getGrassBlock(), grassTexture, generator.modelCollector);
		TextureMap snowTexture = new TextureMap()
				.put(TextureKey.BOTTOM, dirtTextureId)
				.inherit(TextureKey.BOTTOM, TextureKey.PARTICLE)
				.put(TextureKey.TOP, TextureMap.getSubId(Blocks.GRASS_BLOCK, "_top"))
				.put(TextureKey.SIDE, TextureMap.getSubId(dirtBlocks.getGrassBlock(), "_snow"));
		BlockStateVariant snowStateVariant = BlockStateVariant.create()
				.put(VariantSettings.MODEL, Models.CUBE_BOTTOM_TOP.upload(
						dirtBlocks.getGrassBlock(), "_snow", snowTexture, generator.modelCollector));
		generator.registerTopSoil(dirtBlocks.getGrassBlock(), grassTextureId, snowStateVariant);
		generator.registerTintedItemModel(dirtBlocks.getGrassBlock(), grassTextureId, new GrassTintSource());
		/* TODO: future mycelium feature?
		// mycelium
		Identifier myceliumTexture = TexturedModel.CUBE_BOTTOM_TOP
				.get(dirtBlocks.getMycelium())
				.textures(textures -> textures
						.put(TextureKey.BOTTOM, dirtTextureId)
						.put(TextureKey.TOP, TextureMap.getSubId(Blocks.MYCELIUM, "_top")))
				.upload(dirtBlocks.getMycelium(), generator.modelCollector);
		generator.registerTopSoil(dirtBlocks.getMycelium(), myceliumTexture, blockStateVariant);
		*/
		// podzol
		Identifier podzolTexture = TexturedModel.CUBE_BOTTOM_TOP
				.get(dirtBlocks.getPodzol())
				.textures(textures -> textures
						.put(TextureKey.BOTTOM, dirtTextureId)
						.put(TextureKey.TOP, TextureMap.getSubId(Blocks.PODZOL, "_top")))
				.upload(dirtBlocks.getPodzol(), generator.modelCollector);
		generator.registerTopSoil(dirtBlocks.getPodzol(), podzolTexture, snowStateVariant);

		// Farmland from BlockStateModelGenerator.registerFarmland()
		TextureMap farmlandTexture = new TextureMap()
				.put(TextureKey.DIRT, TextureMap.getId(dirtBlocks.getDirt()))
				.put(TextureKey.TOP, TextureMap.getId(dirtBlocks.getFarmland()));
		TextureMap moistFarmlandTexture = new TextureMap()
				.put(TextureKey.DIRT, TextureMap.getId(dirtBlocks.getDirt()))
				.put(TextureKey.TOP, TextureMap.getSubId(dirtBlocks.getFarmland(), "_moist"));
		Identifier farmlandModel = Models.TEMPLATE_FARMLAND.upload(dirtBlocks.getFarmland(), farmlandTexture, generator.modelCollector);
		Identifier moistFarmlandModel = Models.TEMPLATE_FARMLAND.upload(TextureMap.getSubId(dirtBlocks.getFarmland(), "_moist"), moistFarmlandTexture, generator.modelCollector);
		generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(dirtBlocks.getFarmland())
				.coordinate(BlockStateModelGenerator
						.createValueFencedModelMap(Properties.MOISTURE, 7, moistFarmlandModel, farmlandModel)));

		this.registerBlockItemModel(generator, dirtBlocks.getDirt());
		this.registerBlockItemModel(generator, dirtBlocks.getDirtPath());
		this.registerBlockItemModel(generator, dirtBlocks.getFarmland());
		this.registerBlockItemModel(generator, dirtBlocks.getPodzol());
	}

	private void registerSmallLogItemModel(ItemModelGenerator generator, Block block, @Nullable Block texture) {
		new Model(
				Optional.of(WoodModels.ITEM_SMALL_LOG),
				Optional.empty(),
				TextureKey.ALL
		).upload(
				block.asItem(),
				TextureMap.all(TextureMap.getId(texture == null ? block : texture))
						.inherit(TextureKey.ALL, TextureKey.PARTICLE),
				generator.modelCollector
		);

		generator.register(block.asItem());
	}

	private void registerSmallLogItemModel(ItemModelGenerator generator, Block block) {
		registerSmallLogItemModel(generator, block, null);
	}

	private void registerSmallLogItemModels(ItemModelGenerator generator, WoodBlocks woodBlocks) {
		registerSmallLogItemModel(generator, woodBlocks.log, null);
		registerSmallLogItemModel(generator, woodBlocks.strippedLog, null);
	}

	private void registerBoatItemModels(ItemModelGenerator generator, WoodItems woodItems) {
		if (woodItems.hasBoat()) {
			generator.register(woodItems.boat, Models.GENERATED);
			generator.register(woodItems.chestBoat, Models.GENERATED);
		}
	}

	@Override
	public String getName() {
		return "Terrestria Models";
	}
}
