package com.latmod.mods.xencraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

/**
 * @author LatvianModder
 */
public enum EnumXenColor implements IStringSerializable
{
	WHITE("white", 0, MapColor.WHITE_STAINED_HARDENED_CLAY, 0xFFFFFF),
	GRAY("gray", 1, MapColor.GRAY, 0x707070),
	BLACK("black", 2, MapColor.BLACK, 0x161616),
	BROWN("brown", 3, MapColor.BROWN, 0x723D14),
	RED("red", 4, MapColor.RED, 0xFF0000),
	ORANGE("orange", 5, MapColor.ADOBE, 0xFF8C00),
	YELLOW("yellow", 6, MapColor.YELLOW, 0xFFD800),
	LIME("lime", 7, MapColor.LIME, 0xB6FF00),
	GREEN("green", 8, MapColor.GREEN, 0x00FF00),
	TEAL("teal", 9, MapColor.DIAMOND, 0x00FFCB),
	CYAN("cyan", 10, MapColor.CYAN, 0x00FFFF),
	SKY("sky", 11, MapColor.LIGHT_BLUE, 0x21C3FF),
	BLUE("blue", 12, MapColor.BLUE, 0x0052FF),
	PURPLE("purple", 13, MapColor.PURPLE, 0xAA00FF),
	MAGENTA("magenta", 14, MapColor.MAGENTA, 0xFF00FF),
	PINK("pink", 15, MapColor.PINK, 0xFF75B5);

	public static final EnumXenColor[] VALUES = values();
	public static final EnumXenColor[] BRIGHT_COLORS = {RED, ORANGE, YELLOW, LIME, GREEN, TEAL, CYAN, SKY, BLUE, PURPLE, MAGENTA, PINK};

	public static EnumXenColor byMeta(int m)
	{
		return m < 0 || m >= VALUES.length ? WHITE : VALUES[m];
	}

	public static EnumXenColor getBrightColor(@Nullable BlockPos pos)
	{
		int i = (pos == null ? 0 : (pos.getX() + pos.getY() + pos.getZ())) % BRIGHT_COLORS.length;
		return i < 0 ? BRIGHT_COLORS[BRIGHT_COLORS.length + i] : BRIGHT_COLORS[i];
	}

	private final String name;
	private final int metadata;
	private final MapColor mapColor;
	private final int color;

	EnumXenColor(String n, int i, MapColor m, int c)
	{
		name = n;
		metadata = i;
		mapColor = m;
		color = 0xFF000000 | c;
	}

	@Override
	public String getName()
	{
		return name;
	}

	public int getMetadata()
	{
		return metadata;
	}

	public MapColor getMapColor()
	{
		return mapColor;
	}

	public int getColor()
	{
		return color;
	}
}