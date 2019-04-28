package com.latmod.mods.xencraft.block;

import com.google.common.collect.Lists;
import com.latmod.mods.xencraft.item.XenCraftItems;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * @author LatvianModder
 */
public class BlockXenLeaves extends BlockLeaves
{
	public BlockXenLeaves()
	{
		setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, false).withProperty(DECAYABLE, true));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
	}

	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int meta = 0;

		if (!state.getValue(DECAYABLE))
		{
			meta |= 4;
		}

		if (state.getValue(CHECK_DECAY))
		{
			meta |= 8;
		}

		return meta;
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	@Deprecated
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		//leavesFancy = true;
		//return super.shouldSideBeRendered(blockState, world, pos, side);
		return true;
	}

	@Override
	protected int getSaplingDropChance(IBlockState state)
	{
		return 25;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return XenCraftItems.XEN_SAPLING;
	}

	@Override
	protected void dropApple(World world, BlockPos pos, IBlockState state, int chance)
	{
		if (world.rand.nextInt(chance) == 0)
		{
			spawnAsEntity(world, pos, new ItemStack(XenCraftItems.XEN_GEM));
		}
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state)
	{
		return new ItemStack(this);
	}

	@Override
	public BlockPlanks.EnumType getWoodType(int meta)
	{
		return BlockPlanks.EnumType.DARK_OAK;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		IBlockState state = world.getBlockState(pos);
		return Lists.newArrayList(getSilkTouchDrop(state));
	}

	@Override
	public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
}