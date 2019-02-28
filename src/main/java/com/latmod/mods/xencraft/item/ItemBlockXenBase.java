package com.latmod.mods.xencraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * @author LatvianModder
 */
public class ItemBlockXenBase extends ItemBlock
{
	public ItemBlockXenBase(Block block)
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