package com.latmod.mods.xencraft.item;

import com.latmod.mods.xencraft.block.EnumXenPattern;

/**
 * @author LatvianModder
 */
public class ItemBlockXen extends ItemBlockXenBase
{
	public final EnumXenPattern pattern;

	public ItemBlockXen(EnumXenPattern p)
	{
		super(p.block);
		pattern = p;
	}
}