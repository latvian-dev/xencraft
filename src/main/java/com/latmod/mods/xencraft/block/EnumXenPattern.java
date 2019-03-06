package com.latmod.mods.xencraft.block;

import com.latmod.mods.xencraft.item.ItemBlockXen;
import net.minecraft.util.IStringSerializable;

/**
 * @author LatvianModder
 */
public enum EnumXenPattern implements IStringSerializable
{
	BLOCK("block"),
	BRICKS("bricks"),
	SMALL_BRICKS("small_bricks"),
	PLATE("plate"),
	TILES("tiles"),
	SMALL_TILES("small_tiles"),
	HEX("hex");

	public static final EnumXenPattern[] VALUES = values();

	private String name;
	public BlockXen block;
	public ItemBlockXen item;
	public Object stencilSprite;

	EnumXenPattern(String n)
	{
		name = n;
	}

	@Override
	public String getName()
	{
		return name;
	}
}