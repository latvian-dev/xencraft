package com.latmod.mods.xencraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * @author LatvianModder
 */
public class BlockXenstone extends Block
{
	public final boolean dark;

	public BlockXenstone(boolean d)
	{
		super(Material.ROCK, d ? MapColor.BLACK : MapColor.CLOTH);
		setHardness(1F);
		dark = d;
	}
}