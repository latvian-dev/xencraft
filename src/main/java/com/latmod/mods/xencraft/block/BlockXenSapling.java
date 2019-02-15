package com.latmod.mods.xencraft.block;

import com.latmod.mods.xencraft.XenCraft;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * @author LatvianModder
 */
public class BlockXenSapling extends BlockBush implements IGrowable
{
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	public static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

	public BlockXenSapling()
	{
		super(Material.PLANTS);
		setDefaultState(blockState.getBaseState().withProperty(STAGE, 0));
		setHardness(0F);
		setSoundType(SoundType.PLANT);
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return SAPLING_AABB;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!world.isRemote)
		{
			super.updateTick(world, pos, state, rand);

			if (!world.isAreaLoaded(pos, 1))
			{
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light
			}
			if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
			{
				grow(world, pos, state, rand);
			}
		}
	}

	public void grow(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (state.getValue(STAGE) == 0)
		{
			world.setBlockState(pos, state.cycleProperty(STAGE), 4);
		}
		else
		{
			generateTree(world, pos, state, rand);
		}
	}

	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, rand, pos))
		{
			return;
		}

		WorldGenerator worldgenerator = new WorldGenTrees(true, 4, XenCraftBlocks.XEN_LOG.getDefaultState(), XenCraftBlocks.XEN_LEAVES.getDefaultState(), false);
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

		if (!worldgenerator.generate(world, rand, pos))
		{
			world.setBlockState(pos, state, 4);
		}
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
	{
		return true;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
	{
		return (double) world.rand.nextFloat() < 0.45D;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state)
	{
		grow(world, pos, state, rand);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(STAGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(STAGE);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, STAGE);
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
	{
		return layer == BlockRenderLayer.CUTOUT || layer == BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	@Deprecated
	public int getLightValue(IBlockState state)
	{
		return XenCraft.PROXY.getXenLightValue(BlockRenderLayer.CUTOUT);
	}
}