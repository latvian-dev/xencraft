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
	WHITE("white", 0, MapColor.WHITE_STAINED_HARDENED_CLAY),
	GRAY("gray", 1, MapColor.GRAY),
	BLACK("black", 2, MapColor.BLACK),
	BROWN("brown", 3, MapColor.BROWN),
	RED("red", 4, MapColor.RED),
	ORANGE("orange", 5, MapColor.ADOBE),
	YELLOW("yellow", 6, MapColor.YELLOW),
	LIME("lime", 7, MapColor.LIME),
	GREEN("green", 8, MapColor.GREEN),
	TEAL("teal", 9, MapColor.DIAMOND),
	CYAN("cyan", 10, MapColor.CYAN),
	SKY("sky", 11, MapColor.LIGHT_BLUE),
	BLUE("blue", 12, MapColor.BLUE),
	PURPLE("purple", 13, MapColor.PURPLE),
	MAGENTA("magenta", 14, MapColor.MAGENTA),
	PINK("pink", 15, MapColor.PINK);

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

	EnumXenColor(String n, int i, MapColor m)
	{
		name = n;
		metadata = i;
		mapColor = m;
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
}