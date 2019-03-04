package com.latmod.mods.xencraft.client;

import com.latmod.mods.xencraft.XenCraft;
import com.latmod.mods.xencraft.block.EnumXenColor;
import net.minecraftforge.common.config.Config;

/**
 * @author LatvianModder
 */
@Config(modid = XenCraft.MOD_ID, category = "", name = "../local/client/" + XenCraft.MOD_ID)
@Config.LangKey(XenCraft.MOD_ID + "_client")
public class XenCraftClientConfig
{
	public static final General general = new General();

	@Config.Comment("#RRGGBB hex codes for block colors.")
	public static final Colors colors = new Colors();

	public static class General
	{
		@Config.RangeInt(min = 0, max = 15)
		@Config.Comment("How bright xen blocks are.")
		public int xen_light_value = 13;
	}

	public static class Colors
	{
		public String white = "#FFFFFF";
		public String gray = "#707070";
		public String black = "#161616";
		public String brown = "#723D14";
		public String red = "#FF0000";
		public String orange = "#FF8C00";
		public String yellow = "#FFD800";
		public String lime = "#B6FF00";
		public String green = "#00FF00";
		public String teal = "#00FFCB";
		public String cyan = "#00FFFF";
		public String sky = "#21C3FF";
		public String blue = "#0052FF";
		public String purple = "#AA00FF";
		public String magenta = "#FF00FF";
		public String pink = "#FF75B5";

		private int[] colors = new int[EnumXenColor.VALUES.length];

		public int getColor(EnumXenColor color)
		{
			return colors[color.ordinal()];
		}
	}

	public static void sync()
	{
		colors.colors[EnumXenColor.WHITE.ordinal()] = parseColor(colors.white);
		colors.colors[EnumXenColor.GRAY.ordinal()] = parseColor(colors.gray);
		colors.colors[EnumXenColor.BLACK.ordinal()] = parseColor(colors.black);
		colors.colors[EnumXenColor.BROWN.ordinal()] = parseColor(colors.brown);
		colors.colors[EnumXenColor.RED.ordinal()] = parseColor(colors.red);
		colors.colors[EnumXenColor.ORANGE.ordinal()] = parseColor(colors.orange);
		colors.colors[EnumXenColor.YELLOW.ordinal()] = parseColor(colors.yellow);
		colors.colors[EnumXenColor.LIME.ordinal()] = parseColor(colors.lime);
		colors.colors[EnumXenColor.GREEN.ordinal()] = parseColor(colors.green);
		colors.colors[EnumXenColor.TEAL.ordinal()] = parseColor(colors.teal);
		colors.colors[EnumXenColor.CYAN.ordinal()] = parseColor(colors.cyan);
		colors.colors[EnumXenColor.SKY.ordinal()] = parseColor(colors.sky);
		colors.colors[EnumXenColor.BLUE.ordinal()] = parseColor(colors.blue);
		colors.colors[EnumXenColor.PURPLE.ordinal()] = parseColor(colors.purple);
		colors.colors[EnumXenColor.MAGENTA.ordinal()] = parseColor(colors.magenta);
		colors.colors[EnumXenColor.PINK.ordinal()] = parseColor(colors.pink);
	}

	private static int parseColor(String c)
	{
		try
		{
			return 0xFF000000 | Integer.decode(c);
		}
		catch (Exception ex)
		{
			return 0xFF000000;
		}
	}
}