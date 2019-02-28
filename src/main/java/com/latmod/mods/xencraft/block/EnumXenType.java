package com.latmod.mods.xencraft.block;

import net.minecraft.util.IStringSerializable;

/**
 * @author LatvianModder
 */
public enum EnumXenType implements IStringSerializable
{
	DARK("dark"),
	LIGHT("light");

	public static final EnumXenType[] VALUES = values();

	private final String name;

	EnumXenType(String n)
	{
		name = n;
	}

	@Override
	public String getName()
	{
		return name;
	}
}