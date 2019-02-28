package com.latmod.mods.xencraft.block;

/**
 * @author LatvianModder
 */
public class BlockXen extends BlockXenBase
{
	public final EnumXenType type;
	public final EnumXenPattern pattern;

	public BlockXen(EnumXenType t, EnumXenPattern p)
	{
		type = t;
		pattern = p;
	}
}