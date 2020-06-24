package com.terraformersmc.terrestria.feature.trees;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;

import net.minecraft.block.AbstractBlock.AbstractBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.Structure;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.BitSetVoxelSet;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public abstract class AbstractTreeFeature<T extends TreeFeatureConfig> extends Feature<T> {
    public AbstractTreeFeature(Codec<T> codec) {
        super(codec);
    }

    protected static boolean canTreeReplace(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            Block block = state.getBlock();
            return state.isAir() || state.isIn(BlockTags.LEAVES) || isDirt(block) || block.isIn(BlockTags.LOGS) || block.isIn(BlockTags.SAPLINGS) || block == Blocks.VINE;
        });
    }

    public static boolean isAir(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, AbstractBlockState::isAir);
    }

    protected static boolean isNaturalDirt(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            Block block = state.getBlock();
            return isDirt(block) && block != Blocks.GRASS_BLOCK && block != Blocks.MYCELIUM;
        });
    }

    protected static boolean isVine(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            return state.getBlock() == Blocks.VINE;
        });
    }

    public static boolean isWater(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            return state.getBlock() == Blocks.WATER;
        });
    }

    public static boolean isAirOrLeaves(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            return state.isAir() || state.isIn(BlockTags.LEAVES);
        });
    }

    public static boolean isNaturalDirtOrGrass(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            return isDirt(state.getBlock());
        });
    }

    protected static boolean isDirtOrGrass(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            Block block = state.getBlock();
            return isDirt(block) || block == Blocks.FARMLAND;
        });
    }

    public static boolean isReplaceablePlant(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            Material material = state.getMaterial();
            return material == Material.REPLACEABLE_PLANT;
        });
    }

    protected void setToDirt(ModifiableTestableWorld world, BlockPos pos) {
        if (!isNaturalDirt(world, pos)) {
            this.setBlockState(world, pos, Blocks.DIRT.getDefaultState());
        }

    }

    public static boolean setLogBlockState(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> trunkPositions, BlockBox box, TreeFeatureConfig config) {
        if (!isAirOrLeaves(world, pos) && !isReplaceablePlant(world, pos) && !isWater(world, pos)) {
            return false;
        } else {
            setBlockState(world, pos, config.trunkProvider.getBlockState(random, pos), box);
            trunkPositions.add(pos.toImmutable());
            return true;
        }
    }

    protected boolean setLeavesBlockState(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> leavesPositions, BlockBox box, TreeFeatureConfig config) {
        if (!isAirOrLeaves(world, pos) && !isReplaceablePlant(world, pos) && !isWater(world, pos)) {
            return false;
        } else {
            setBlockState(world, pos, config.leavesProvider.getBlockState(random, pos), box);
            leavesPositions.add(pos.toImmutable());
            return true;
        }
    }

    protected void setBlockState(ModifiableWorld world, BlockPos pos, BlockState state) {
        setBlockStateWithoutUpdatingNeighbors(world, pos, state);
    }

    protected static final void setBlockState(ModifiableWorld world, BlockPos pos, BlockState state, BlockBox box) {
        setBlockStateWithoutUpdatingNeighbors(world, pos, state);
        box.encompass(new BlockBox(pos, pos));
    }

    private static void setBlockStateWithoutUpdatingNeighbors(ModifiableWorld world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state, 19);
    }

    public final boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, T treeFeatureConfig) {
        Set<BlockPos> set = Sets.newHashSet();
        Set<BlockPos> set2 = Sets.newHashSet();
        Set<BlockPos> set3 = Sets.newHashSet();
        BlockBox blockBox = BlockBox.empty();
        boolean bl = this.generate(world, random, blockPos, set, set2, blockBox, treeFeatureConfig);
        if (blockBox.minX <= blockBox.maxX && bl && !set.isEmpty()) {
            if (!treeFeatureConfig.decorators.isEmpty()) {
                List<BlockPos> list = Lists.newArrayList(set);
                List<BlockPos> list2 = Lists.newArrayList(set2);
                list.sort(Comparator.comparingInt(Vec3i::getY));
                list2.sort(Comparator.comparingInt(Vec3i::getY));
                treeFeatureConfig.decorators.forEach((decorator) -> {
                    decorator.generate(world, random, list, list2, set3, blockBox);
                });
            }

            VoxelSet voxelSet = this.placeLogsAndLeaves(world, blockBox, set, set3);
            Structure.updateCorner(world, 3, voxelSet, blockBox.minX, blockBox.minY, blockBox.minZ);
            return true;
        } else {
            return false;
        }
    }

    private VoxelSet placeLogsAndLeaves(WorldAccess world, BlockBox box, Set<BlockPos> logs, Set<BlockPos> leaves) {
        List<Set<BlockPos>> list = Lists.newArrayList();
        VoxelSet voxelSet = new BitSetVoxelSet(box.getBlockCountX(), box.getBlockCountY(), box.getBlockCountZ());

        for(int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }

        Mutable mutable = new Mutable();
        Iterator var9 = Lists.newArrayList(leaves).iterator();

        BlockPos blockPos2;
        while(var9.hasNext()) {
            blockPos2 = (BlockPos)var9.next();
            if (box.contains(blockPos2)) {
                voxelSet.set(blockPos2.getX() - box.minX, blockPos2.getY() - box.minY, blockPos2.getZ() - box.minZ, true, true);
            }
        }

        var9 = Lists.newArrayList(logs).iterator();

        while(var9.hasNext()) {
            blockPos2 = (BlockPos)var9.next();
            if (box.contains(blockPos2)) {
                voxelSet.set(blockPos2.getX() - box.minX, blockPos2.getY() - box.minY, blockPos2.getZ() - box.minZ, true, true);
            }

            Direction[] var11 = Direction.values();
            int var12 = var11.length;

            for(int var13 = 0; var13 < var12; ++var13) {
                Direction direction = var11[var13];
                mutable.set(blockPos2, direction);
                if (!logs.contains(mutable)) {
                    BlockState blockState = world.getBlockState(mutable);
                    if (blockState.contains(Properties.DISTANCE_1_7)) {
                        ((Set)list.get(0)).add(mutable.toImmutable());
                        setBlockStateWithoutUpdatingNeighbors(world, mutable, (BlockState)blockState.with(Properties.DISTANCE_1_7, 1));
                        if (box.contains(mutable)) {
                            voxelSet.set(mutable.getX() - box.minX, mutable.getY() - box.minY, mutable.getZ() - box.minZ, true, true);
                        }
                    }
                }
            }
        }

        for(int k = 1; k < 6; ++k) {
            Set<BlockPos> set = (Set)list.get(k - 1);
            Set<BlockPos> set2 = (Set)list.get(k);
            Iterator var25 = set.iterator();

            while(var25.hasNext()) {
                BlockPos blockPos3 = (BlockPos)var25.next();
                if (box.contains(blockPos3)) {
                    voxelSet.set(blockPos3.getX() - box.minX, blockPos3.getY() - box.minY, blockPos3.getZ() - box.minZ, true, true);
                }

                Direction[] var27 = Direction.values();
                int var28 = var27.length;

                for(int var16 = 0; var16 < var28; ++var16) {
                    Direction direction2 = var27[var16];
                    mutable.set(blockPos3, direction2);
                    if (!set.contains(mutable) && !set2.contains(mutable)) {
                        BlockState blockState2 = world.getBlockState(mutable);
                        if (blockState2.contains(Properties.DISTANCE_1_7)) {
                            int l = (Integer)blockState2.get(Properties.DISTANCE_1_7);
                            if (l > k + 1) {
                                BlockState blockState3 = (BlockState)blockState2.with(Properties.DISTANCE_1_7, k + 1);
                                setBlockStateWithoutUpdatingNeighbors(world, mutable, blockState3);
                                if (box.contains(mutable)) {
                                    voxelSet.set(mutable.getX() - box.minX, mutable.getY() - box.minY, mutable.getZ() - box.minZ, true, true);
                                }

                                set2.add(mutable.toImmutable());
                            }
                        }
                    }
                }
            }
        }

        return voxelSet;
    }

    protected abstract boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, T config);
}
