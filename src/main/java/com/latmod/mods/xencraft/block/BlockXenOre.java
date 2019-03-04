package com.latmod.mods.xencraft.block;

import com.latmod.mods.xencraft.XenCraft;
import com.latmod.mods.xencraft.item.XenCraftItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * @author LatvianModder
 */
public class BlockXenOre extends Block implements IXenBlock
{
	public BlockXenOre()
	{
		super(Material.ROCK);
		setHardness(2F);
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return XenCraftItems.XEN_GEM;
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return quantityDropped(random) + random.nextInt(fortune + 1);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
	{
		if (getItemDropped(state, RANDOM, fortune) != Item.getItemFromBlock(this))
		{
			return 1 + RANDOM.nextInt(7);
		}

		return 0;
	}

	@Override
	@Deprecated
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(XenCraftItems.XEN_ORE);
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, net.minecraft.entity.EntityLiving.SpawnPlacementType type)
	{
		return false;
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
	{
		return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.CUTOUT;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return XenCraft.PROXY.getXenLightValue(world, getXenLayer());
	}

	@Override
	public BlockRenderLayer getXenLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	@Deprecated
	@SideOnly(Side.CLIENT)
	public float getAmbientOcclusionLightValue(IBlockState state)
	{
		return 0F;
	}
}