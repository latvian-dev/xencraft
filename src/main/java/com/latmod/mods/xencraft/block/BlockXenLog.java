package com.latmod.mods.xencraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * @author LatvianModder
 */
public class BlockXenLog extends Block
{
	public BlockXenLog()
	{
		super(Material.WOOD, MapColor.WOOD);
		setHardness(2.0F);
		setSoundType(SoundType.WOOD);
	}

	@Override
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean isWood(net.minecraft.world.IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, net.minecraft.entity.EntityLiving.SpawnPlacementType type)
	{
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
}