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
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@NullMarked
public class TerrestriaModelProvider extends FabricModelProvider {
	public static final List<Pair<BooleanProperty, Function<MultiVariant, MultiVariant>>> SMALL_LOG_VARIANT_FUNCTIONS = List.of(
			Pair.of(BlockStateProperties.NORTH, (model) -> model
					.with(BlockModelGenerators.X_ROT_90)
			),
			Pair.of(BlockStateProperties.EAST, (model) -> model
					.with(BlockModelGenerators.X_ROT_90)
					.with(BlockModelGenerators.Y_ROT_90)
			),
			Pair.of(BlockStateProperties.SOUTH, (model) -> model
					.with(BlockModelGenerators.X_ROT_90)
					.with(BlockModelGenerators.Y_ROT_180)
			),
			Pair.of(BlockStateProperties.WEST, (model) -> model
					.with(BlockModelGenerators.X_ROT_90)
					.with(BlockModelGenerators.Y_ROT_270)
			),
			Pair.of(BlockStateProperties.UP, (model) -> model
			),
			Pair.of(BlockStateProperties.DOWN, (model) -> model
					.with(BlockModelGenerators.X_ROT_180)
			)
	);

	public TerrestriaModelProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators generator) {
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
		generator.createPlantWithDefaultItem(TerrestriaBlocks.BRYCE_SAPLING, TerrestriaBlocks.POTTED_BRYCE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createTrivialBlock(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES, TexturedModel.LEAVES);
		this.registerBlockItemModel(generator, TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES);
		generator.createPlantWithDefaultItem(TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING, TerrestriaBlocks.POTTED_DARK_JAPANESE_MAPLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createTintedLeaves(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_DEFAULT);
		generator.createPlantWithDefaultItem(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING, TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SHRUB_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createTrivialBlock(TerrestriaBlocks.JUNGLE_PALM_LEAVES, TexturedModel.LEAVES);
		this.registerBlockItemModel(generator, TerrestriaBlocks.JUNGLE_PALM_LEAVES);
		generator.createPlantWithDefaultItem(TerrestriaBlocks.JUNGLE_PALM_SAPLING, TerrestriaBlocks.POTTED_JUNGLE_PALM_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

		// Stone odds and ends
		generator.createRotatedVariantBlock(TerrestriaBlocks.VOLCANIC_SAND);
		this.registerBlockItemModel(generator, TerrestriaBlocks.VOLCANIC_SAND);

		// Misc. vegetation
		generator.createPlantWithDefaultItem(TerrestriaBlocks.AGAVE, TerrestriaBlocks.POTTED_AGAVE, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(TerrestriaBlocks.ALOE_VERA, TerrestriaBlocks.POTTED_ALOE_VERA, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createTrivialBlock(TerrestriaBlocks.CATTAIL, TexturedModel.SEAGRASS);
		this.registerBlockItemModel(generator, TerrestriaBlocks.CATTAIL);
		generator.createCrossBlock(TerrestriaBlocks.DEAD_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);
		this.registerBlockItemModel(generator, TerrestriaBlocks.DEAD_GRASS);
		generator.createPlantWithDefaultItem(TerrestriaBlocks.INDIAN_PAINTBRUSH, TerrestriaBlocks.POTTED_INDIAN_PAINTBRUSH, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(TerrestriaBlocks.MONSTERAS, TerrestriaBlocks.POTTED_MONSTERAS, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING, TerrestriaBlocks.POTTED_SAGUARO_CACTUS_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
		Identifier cattailTopId = generator.createSuffixedVariant(TerrestriaBlocks.TALL_CATTAIL, "_top", ModelTemplates.SEAGRASS, TextureMapping::defaultTexture);
		Identifier cattailBottomId = generator.createSuffixedVariant(TerrestriaBlocks.TALL_CATTAIL, "_bottom", ModelTemplates.SEAGRASS, TextureMapping::defaultTexture);
		MultiVariant cattailTop = BlockModelGenerators.plainVariant(cattailTopId);
		MultiVariant cattailBottom = BlockModelGenerators.plainVariant(cattailBottomId);
		generator.createDoubleBlock(TerrestriaBlocks.TALL_CATTAIL, cattailTop, cattailBottom);
		generator.createPlantWithDefaultItem(TerrestriaBlocks.TINY_CACTUS, TerrestriaBlocks.POTTED_TINY_CACTUS, BlockModelGenerators.PlantType.NOT_TINTED);
	}

	@Override
	public void generateItemModels(ItemModelGenerators generator) {
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
		generator.generateFlatItem(TerrestriaItems.LOG_TURNER, ModelTemplates.FLAT_HANDHELD_ROD_ITEM);
	}


	/*
	 * Shorthand for registering just the item model of a block item which uses its block's model.
	 */
	private void registerBlockItemModel(BlockModelGenerators generator, Block block) {
		generator.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
	}

	private void registerLeafPile(BlockModelGenerators generator, Block leafPile, Block leaves) {
		// Bottom half of registerWoolAndCarpet
		Identifier carpetModelId = TexturedModel.CARPET.get(leaves).create(leafPile, generator.modelOutput);
		MultiVariant carpetModel = BlockModelGenerators.plainVariant(carpetModelId);
		generator.blockStateOutput.accept(
				BlockModelGenerators.createSimpleBlock(leafPile, carpetModel));

		this.registerBlockItemModel(generator, leafPile);
	}

	private void registerSmallLog(BlockModelGenerators generator, Block log, @Nullable Block leaves) {
		registerSmallLog(generator, log, null, leaves);
	}

	private void registerSmallLog(BlockModelGenerators generator, Block log, @Nullable Block texture, @Nullable Block leaves) {
		if (!(log instanceof BareSmallLogBlock)) {
			throw new IllegalArgumentException("Attempt to register non-SmallLog via registerSmallLog: " + log);
		}
		TextureSlot leavesKey = TextureSlot.create("leaves");
		TextureMapping logMap = TextureMapping.logColumn(texture == null ? log : texture);
		TextureMapping leafyMap = logMap
				.copyAndUpdate(leavesKey, TextureMapping.getBlockTexture(leaves == null ? Blocks.OAK_LEAVES : leaves))
				.copySlot(leavesKey, TextureSlot.PARTICLE);
		ModelTemplate logModel = new ModelTemplate(
				Optional.of(WoodModels.BLOCK_SMALL_LOG),
				Optional.empty(),
				TextureSlot.PARTICLE, TextureSlot.SIDE);
		ModelTemplate branchModel = new ModelTemplate(
				Optional.of(WoodModels.BLOCK_SMALL_LOG_BRANCH),
				Optional.of("_branch"),
				TextureSlot.PARTICLE, TextureSlot.END, TextureSlot.SIDE);
		ModelTemplate leavesModel = new ModelTemplate(
				Optional.of(WoodModels.BLOCK_SMALL_LOG_LEAVES),
				Optional.of("_leaves"),
				TextureSlot.PARTICLE, leavesKey);
		ModelTemplate cutoutModel = new ModelTemplate(
				Optional.of(WoodModels.BLOCK_SMALL_LOG_LEAVES_CUTOUT),
				Optional.of("_leaves_cutout"),
				TextureSlot.PARTICLE, leavesKey);
		Identifier texturedLogModelId = TexturedModel.createDefault(block -> logMap, logModel)
				.create(log, generator.modelOutput);
		Identifier texturedBranchModelId = TexturedModel.createDefault(block -> logMap, branchModel)
				.create(log, generator.modelOutput);
		Identifier texturedLeavesModelId = TexturedModel.createDefault(block -> leafyMap, leavesModel)
				.create(log, generator.modelOutput);
		Identifier texturedCutoutModelId = TexturedModel.createDefault(block -> leafyMap, cutoutModel)
				.create(log, generator.modelOutput);
		MultiVariant texturedLog = BlockModelGenerators.plainVariant(texturedLogModelId);
		MultiVariant texturedBranch = BlockModelGenerators.plainVariant(texturedBranchModelId);
		MultiVariant texturedLeaves = BlockModelGenerators.plainVariant(texturedLeavesModelId);
		MultiVariant texturedCutout = BlockModelGenerators.plainVariant(texturedCutoutModelId);
		MultiPartGenerator multipartBlockModelDefinitionCreator =
				MultiPartGenerator.multiPart(log);

		SMALL_LOG_VARIANT_FUNCTIONS.forEach(pair -> {
			multipartBlockModelDefinitionCreator.with(
					new ConditionBuilder().term(pair.getFirst(), true),
					pair.getSecond().apply(texturedBranch));
			multipartBlockModelDefinitionCreator.with(
					new ConditionBuilder().term(pair.getFirst(), false),
					pair.getSecond().apply(texturedLog));
			if (leaves != null) {
				multipartBlockModelDefinitionCreator.with(
						new ConditionBuilder()
								.term(SmallLogBlock.HAS_LEAVES, true)
								.term(pair.getFirst(), true),
						pair.getSecond().apply(texturedCutout));
				multipartBlockModelDefinitionCreator.with(
						new ConditionBuilder()
								.term(SmallLogBlock.HAS_LEAVES, true)
								.term(pair.getFirst(), false),
						pair.getSecond().apply(texturedLeaves));
			}
		});

		generator.blockStateOutput.accept(multipartBlockModelDefinitionCreator);
	}

	private void registerQuarterLog(BlockModelGenerators generator, Block quarterLog, Block log) {
		// Quarter Logs have specifically rotated textures on all sides:
		// * an inside texture to the North and East
		// * a side texture to the South and West
		// * a top (end) texture on the top and bottom
		TextureMapping textureMap = new TextureMapping()
				.put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(quarterLog))
				.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log))
				.put(TextureSlot.END, TextureMapping.getBlockTexture(quarterLog, "_top"))
				.copySlot(TextureSlot.SIDE, TextureSlot.PARTICLE);
		ModelTemplate model = new ModelTemplate(
				Optional.of(WoodModels.BLOCK_QUARTER_LOG),
				Optional.empty(),
				TextureSlot.PARTICLE, TextureSlot.INSIDE, TextureSlot.SIDE, TextureSlot.END);
		Identifier texturedModelId = TexturedModel.createDefault(block -> textureMap, model)
				.create(quarterLog, generator.modelOutput);
		MultiVariant texturedModel = BlockModelGenerators.plainVariant(texturedModelId);

		generator.blockStateOutput.accept(
				MultiVariantGenerator.dispatch(quarterLog)
						.with(
								PropertyDispatch.initial(BlockStateProperties.AXIS, QuarterLogBlock.BARK_SIDE)
										.generate((axis, barkSide) -> switch (axis) {
											case X -> switch (barkSide) {
												case SOUTHWEST -> texturedModel
														.with(BlockModelGenerators.X_ROT_90)
														.with(BlockModelGenerators.Y_ROT_270);
												case NORTHWEST -> texturedModel
														.with(BlockModelGenerators.X_ROT_90)
														.with(BlockModelGenerators.Y_ROT_90);
												case NORTHEAST -> texturedModel
														.with(BlockModelGenerators.X_ROT_270)
														.with(BlockModelGenerators.Y_ROT_90);
												case SOUTHEAST -> texturedModel
														.with(BlockModelGenerators.X_ROT_270)
														.with(BlockModelGenerators.Y_ROT_270);
											};
											case Y -> switch (barkSide) {
												case SOUTHWEST -> texturedModel;
												case NORTHWEST -> texturedModel
														.with(BlockModelGenerators.Y_ROT_90);
												case NORTHEAST -> texturedModel
														.with(BlockModelGenerators.Y_ROT_180);
												case SOUTHEAST -> texturedModel
														.with(BlockModelGenerators.Y_ROT_270);
											};
											case Z -> switch (barkSide) {
												case SOUTHWEST -> texturedModel
														.with(BlockModelGenerators.X_ROT_90);
												case NORTHWEST -> texturedModel
														.with(BlockModelGenerators.X_ROT_270);
												case NORTHEAST -> texturedModel
														.with(BlockModelGenerators.X_ROT_270)
														.with(BlockModelGenerators.Y_ROT_180);
												case SOUTHEAST -> texturedModel
														.with(BlockModelGenerators.X_ROT_90)
														.with(BlockModelGenerators.Y_ROT_180);
											};
										})
						)
		);

		this.registerBlockItemModel(generator, quarterLog);
	}

	private void registerWoodBlocks(BlockModelGenerators generator, WoodBlocks woodBlocks, BlockFamily blockFamily, @Nullable Block sapling, Block pottedSapling) {
		// Vanilla part of WoodBlocks
		generator.family(blockFamily.getBaseBlock()).generateFor(blockFamily);
		generator.createShelf(woodBlocks.shelf, woodBlocks.strippedLog);
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
				assert woodBlocks.wood != null && woodBlocks.strippedWood != null;
				generator.woodProvider(woodBlocks.log).logWithHorizontal(woodBlocks.log).wood(woodBlocks.wood);
				generator.woodProvider(woodBlocks.strippedLog).logWithHorizontal(woodBlocks.strippedLog).wood(woodBlocks.strippedWood);
			} else {
				generator.woodProvider(woodBlocks.log).logWithHorizontal(woodBlocks.log);
				generator.woodProvider(woodBlocks.strippedLog).logWithHorizontal(woodBlocks.strippedLog);
			}
		}
		if (sapling != null) {
			generator.createPlantWithDefaultItem(sapling, pottedSapling, BlockModelGenerators.PlantType.NOT_TINTED);
		}
		if ("willow".equals(woodBlocks.getName())) {
			// TODO: generalize this special case?
			TextureMapping textureMap = new TextureMapping()
					.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(woodBlocks.leaves))
					.put(TextureSlot.END, TextureMapping.getBlockTexture(woodBlocks.leaves, "_top"))
					.copySlot(TextureSlot.SIDE, TextureSlot.PARTICLE);
			ModelTemplate model = new ModelTemplate(
					Optional.of(LeavesModels.BLOCK_PILLAR_LEAVES),
					Optional.empty(),
					TextureSlot.PARTICLE, TextureSlot.SIDE, TextureSlot.END);
			TexturedModel.Provider texturedModel = TexturedModel.createDefault(block -> textureMap, model);

			generator.createTintedLeaves(woodBlocks.leaves, texturedModel, FoliageColor.FOLIAGE_DEFAULT);
		} else if (woodBlocks.isTintable()) {
			generator.createTintedLeaves(woodBlocks.leaves, TexturedModel.LEAVES, FoliageColor.FOLIAGE_DEFAULT);
		} else {
			generator.createTrivialBlock(woodBlocks.leaves, TexturedModel.LEAVES);
			this.registerBlockItemModel(generator, woodBlocks.leaves);
		}

		// Extra parts of WoodBlocks
		if (woodBlocks.hasQuarterLog()) {
			assert woodBlocks.quarterLog != null && woodBlocks.strippedQuarterLog != null;
			this.registerQuarterLog(generator, woodBlocks.quarterLog, woodBlocks.log);
			this.registerQuarterLog(generator, woodBlocks.strippedQuarterLog, woodBlocks.strippedLog);
		}
		if (woodBlocks.hasLeafPile()) {
			assert woodBlocks.leafPile != null;
			this.registerLeafPile(generator, woodBlocks.leafPile, woodBlocks.leaves);
		}
	}


	private void registerStoneBlockFamily(BlockModelGenerators generator, BlockFamily blockFamily) {
		// Vanilla part of stone BlockFamily
		generator.family(blockFamily.getBaseBlock()).generateFor(blockFamily);

		this.registerBlockItemModel(generator, blockFamily.getBaseBlock());
		if (blockFamily.getVariants().containsKey(net.minecraft.data.BlockFamily.Variant.CHISELED)) {
			this.registerBlockItemModel(generator, blockFamily.get(net.minecraft.data.BlockFamily.Variant.CHISELED));
		}
		if (blockFamily.getVariants().containsKey(net.minecraft.data.BlockFamily.Variant.CRACKED)) {
			this.registerBlockItemModel(generator, blockFamily.get(net.minecraft.data.BlockFamily.Variant.CRACKED));
		}
		if (blockFamily.getVariants().containsKey(net.minecraft.data.BlockFamily.Variant.PRESSURE_PLATE)) {
			this.registerBlockItemModel(generator, blockFamily.get(net.minecraft.data.BlockFamily.Variant.PRESSURE_PLATE));
		}
	}

	private void registerSmoothStone(BlockModelGenerators generator, StoneBlocks stoneBlocks) {
		// Vanilla part
		// Non-hard-coded version of BlockStateModelGenerator.registerSmoothStone()
		TextureMapping fullTexture = TextureMapping.cube(stoneBlocks.smooth.full);
		TextureMapping slabTexture = TextureMapping.column(TextureMapping.getBlockTexture(stoneBlocks.smooth.slab, "_side"), fullTexture.get(TextureSlot.TOP));
		Identifier slabId = ModelTemplates.SLAB_BOTTOM.create(stoneBlocks.smooth.slab, slabTexture, generator.modelOutput);
		Identifier topSlabId = ModelTemplates.SLAB_TOP.create(stoneBlocks.smooth.slab, slabTexture, generator.modelOutput);
		Identifier doubleSlabId = ModelTemplates.CUBE_COLUMN.createWithOverride(stoneBlocks.smooth.slab, "_double", slabTexture, generator.modelOutput);
		MultiVariant slab = BlockModelGenerators.plainVariant(slabId);
		MultiVariant topSlab = BlockModelGenerators.plainVariant(topSlabId);
		MultiVariant doubleSlab = BlockModelGenerators.plainVariant(doubleSlabId);
		generator.blockStateOutput.accept(BlockModelGenerators.createSlab(stoneBlocks.smooth.slab, slab, topSlab, doubleSlab));
		BlockModelGenerators.BlockFamilyProvider textures = generator.family(stoneBlocks.smooth.full);

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

	private void registerDirtBlocks(BlockModelGenerators generator, DirtBlocks dirtBlocks) {
		// Dirt basic block
		generator.createRotatedVariantBlock(dirtBlocks.dirtBlock());
		Material dirtTextureId = TextureMapping.getBlockTexture(dirtBlocks.dirtBlock());

		// Dirt Path based on vanilla model and the partial code in BlockStateModelGenerator.registerDirtPath()
		TextureMapping pathTexture = TextureMapping.logColumn(Objects.requireNonNull(dirtBlocks.dirtPathBlock()))
				.put(TextureSlot.BOTTOM, dirtTextureId)
				.copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
				.put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.DIRT_PATH,"_top"))
				.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(dirtBlocks.dirtPathBlock(), "_side"));
		Identifier pathModelId = new ModelTemplate(
				Optional.of(ModelLocationUtils.getModelLocation(Blocks.DIRT_PATH)),
				Optional.empty(),
				TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.TOP
		).create(dirtBlocks.dirtPathBlock(), pathTexture, generator.modelOutput);
		Variant pathModel = BlockModelGenerators.plainModel(pathModelId);

		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(dirtBlocks.dirtPathBlock(),
				BlockModelGenerators.createRotatedVariants(pathModel)));

		// Grass, Mycelium, and Podzol based roughly on BlockStateModelGenerator.registerTopSoils()
		// grass
		TextureMapping grassTextureMap = new TextureMapping()
				.put(TextureSlot.BOTTOM, dirtTextureId)
				.copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
				.put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.GRASS_BLOCK, "_top"))
				.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Objects.requireNonNull(dirtBlocks.grassBlock()), "_side"))
				.put(TextureSlot.create("overlay"), TextureMapping.getBlockTexture(Blocks.GRASS_BLOCK, "_side_overlay"));
		Identifier grassTextureId = new ModelTemplate(
				Optional.of(ModelLocationUtils.getModelLocation(Blocks.GRASS_BLOCK)),
				Optional.empty(),
				TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.TOP
		).create(dirtBlocks.grassBlock(), grassTextureMap, generator.modelOutput);
		MultiVariant grassTexture = BlockModelGenerators.plainVariant(grassTextureId);
		TextureMapping snowTexture = new TextureMapping()
				.put(TextureSlot.BOTTOM, dirtTextureId)
				.copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
				.put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.GRASS_BLOCK, "_top"))
				.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(dirtBlocks.grassBlock(), "_snow"));
		MultiVariant snowStateVariant = BlockModelGenerators
				.plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(
						dirtBlocks.grassBlock(), "_snow", snowTexture, generator.modelOutput));
		generator.createGrassLikeBlock(dirtBlocks.grassBlock(), grassTexture, snowStateVariant);
		generator.registerSimpleTintedItemModel(dirtBlocks.grassBlock(), grassTextureId, new GrassColorSource());
		/* TODO: future mycelium feature?
		// mycelium
		Identifier myceliumTexture = TexturedModel.CUBE_BOTTOM_TOP
				.get(dirtBlocks.myceliumBlock())
				.textures(textures -> textures
						.put(TextureKey.BOTTOM, dirtTextureId)
						.put(TextureKey.TOP, TextureMap.getSubId(Blocks.MYCELIUM, "_top")))
				.upload(dirtBlocks.myceliumBlock(), generator.modelCollector);
		generator.registerTopSoil(dirtBlocks.myceliumBlock(), myceliumTexture, blockStateVariant);
		*/
		// podzol
		Identifier podzolTextureId = TexturedModel.CUBE_TOP_BOTTOM
				.get(Objects.requireNonNull(dirtBlocks.podzolBlock()))
				.updateTextures(textures -> textures
						.put(TextureSlot.BOTTOM, dirtTextureId)
						.put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.PODZOL, "_top")))
				.create(dirtBlocks.podzolBlock(), generator.modelOutput);
		MultiVariant podzolTexture = BlockModelGenerators.plainVariant(podzolTextureId);
		generator.createGrassLikeBlock(dirtBlocks.podzolBlock(), podzolTexture, snowStateVariant);

		// Farmland from BlockStateModelGenerator.registerFarmland()
		TextureMapping farmlandTexture = new TextureMapping()
				.put(TextureSlot.DIRT, TextureMapping.getBlockTexture(dirtBlocks.dirtBlock()))
				.put(TextureSlot.TOP, TextureMapping.getBlockTexture(Objects.requireNonNull(dirtBlocks.farmBlock())));
		TextureMapping moistFarmlandTexture = new TextureMapping()
				.put(TextureSlot.DIRT, TextureMapping.getBlockTexture(dirtBlocks.dirtBlock()))
				.put(TextureSlot.TOP, TextureMapping.getBlockTexture(dirtBlocks.farmBlock(), "_moist"));
		Identifier farmlandModelId = ModelTemplates.FARMLAND.create(dirtBlocks.farmBlock(), farmlandTexture, generator.modelOutput);
		Identifier moistFarmlandModelId = ModelTemplates.FARMLAND.create(TextureMapping.getBlockTexture(dirtBlocks.farmBlock(), "_moist").sprite(), moistFarmlandTexture, generator.modelOutput);
		MultiVariant farmlandModel = BlockModelGenerators.plainVariant(farmlandModelId);
		MultiVariant moistFarmlandModel = BlockModelGenerators.plainVariant(moistFarmlandModelId);
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(dirtBlocks.farmBlock())
				.with(BlockModelGenerators
						.createEmptyOrFullDispatch(BlockStateProperties.MOISTURE, 7, moistFarmlandModel, farmlandModel)));

		this.registerBlockItemModel(generator, dirtBlocks.dirtBlock());
		this.registerBlockItemModel(generator, dirtBlocks.dirtPathBlock());
		this.registerBlockItemModel(generator, dirtBlocks.farmBlock());
		this.registerBlockItemModel(generator, dirtBlocks.podzolBlock());
	}

	private void registerSmallLogItemModel(ItemModelGenerators generator, Block block, @Nullable Block texture) {
		new ModelTemplate(
				Optional.of(WoodModels.ITEM_SMALL_LOG),
				Optional.empty(),
				TextureSlot.ALL
		).create(
				block.asItem(),
				TextureMapping.cube(TextureMapping.getBlockTexture(texture == null ? block : texture))
						.copyForced(TextureSlot.ALL, TextureSlot.PARTICLE),
				generator.modelOutput
		);

		generator.declareCustomModelItem(block.asItem());
	}

	private void registerSmallLogItemModel(ItemModelGenerators generator, Block block) {
		registerSmallLogItemModel(generator, block, null);
	}

	private void registerSmallLogItemModels(ItemModelGenerators generator, WoodBlocks woodBlocks) {
		registerSmallLogItemModel(generator, woodBlocks.log, null);
		registerSmallLogItemModel(generator, woodBlocks.strippedLog, null);
	}

	private void registerBoatItemModels(ItemModelGenerators generator, WoodItems woodItems) {
		if (woodItems.hasBoat()) {
			generator.generateFlatItem(woodItems.boat, ModelTemplates.FLAT_ITEM);
			generator.generateFlatItem(woodItems.chestBoat, ModelTemplates.FLAT_ITEM);
		}
	}

	@Override
	public String getName() {
		return "Terrestria Models";
	}
}
