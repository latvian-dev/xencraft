package com.latmod.mods.xencraft.item;

import com.latmod.mods.xencraft.block.EnumXenPattern;
import com.latmod.mods.xencraft.block.EnumXenType;

/**
 * @author LatvianModder
 */
public class ItemBlockXen extends ItemBlockXenBase
{
	public final EnumXenType type;
	public final EnumXenPattern pattern;

	public ItemBlockXen(EnumXenType t, EnumXenPattern p)
	{
		super(p.blocks[t.ordinal()]);
		type = t;
		pattern = p;
	}
}