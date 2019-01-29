package com.latmod.mods.xencraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * @author LatvianModder
 */
public class ItemBlockXen extends ItemBlock
{
	public ItemBlockXen(Block block)
	{
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
}