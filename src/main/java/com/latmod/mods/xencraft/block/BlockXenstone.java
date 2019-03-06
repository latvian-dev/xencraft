package com.latmod.mods.xencraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * @author LatvianModder
 */
public class BlockXenstone extends Block
{
	public BlockXenstone()
	{
		super(Material.ROCK, MapColor.BLACK);
		setHardness(1F);
	}
}