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
	WHITE("white", MapColor.WHITE_STAINED_HARDENED_CLAY),
	GRAY("gray", MapColor.GRAY),
	BLACK("black", MapColor.BLACK),
	BROWN("brown", MapColor.BROWN),
	ORANGE("orange", MapColor.ADOBE),
	YELLOW("yellow", MapColor.YELLOW),
	LIME("lime", MapColor.LIME),
	GREEN("green", MapColor.GREEN),
	TEAL("teal", MapColor.DIAMOND),
	CYAN("cyan", MapColor.CYAN),
	SKY("sky", MapColor.LIGHT_BLUE),
	BLUE("blue", MapColor.BLUE),
	PURPLE("purple", MapColor.PURPLE),
	MAGENTA("magenta", MapColor.MAGENTA),
	PINK("pink", MapColor.PINK),
	RED("red", MapColor.RED);

	public static final EnumXenColor[] VALUES = values();
	public static final EnumXenColor[] BRIGHT_COLORS = {ORANGE, YELLOW, LIME, GREEN, TEAL, CYAN, SKY, BLUE, PURPLE, MAGENTA, PINK, RED};

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
	private final MapColor mapColor;
	private final String translationKey;

	EnumXenColor(String n, MapColor m)
	{
		name = n;
		mapColor = m;
		translationKey = "xencraft.colors." + name;
	}

	@Override
	public String getName()
	{
		return name;
	}

	public int getMetadata()
	{
		return ordinal();
	}

	public MapColor getMapColor()
	{
		return mapColor;
	}

	public String getTranslationKey()
	{
		return translationKey;
	}
}