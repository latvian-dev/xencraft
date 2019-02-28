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
		public int xen_light_value = 15;
	}

	public static class Colors
	{
		@Config.LangKey("xencraft.color.white")
		public String white = "#FFFFFF";

		@Config.LangKey("xencraft.color.gray")
		public String gray = "#707070";

		@Config.LangKey("xencraft.color.black")
		public String black = "#161616";

		@Config.LangKey("xencraft.color.brown")
		public String brown = "#723D14";

		@Config.LangKey("xencraft.color.red")
		public String red = "#FF0000";

		@Config.LangKey("xencraft.color.orange")
		public String orange = "#FF8C00";

		@Config.LangKey("xencraft.color.yellow")
		public String yellow = "#FFD800";

		@Config.LangKey("xencraft.color.lime")
		public String lime = "#B6FF00";

		@Config.LangKey("xencraft.color.green")
		public String green = "#00FF00";

		@Config.LangKey("xencraft.color.teal")
		public String teal = "#00FFCB";

		@Config.LangKey("xencraft.color.cyan")
		public String cyan = "#00FFFF";

		@Config.LangKey("xencraft.color.sky")
		public String sky = "#21C3FF";

		@Config.LangKey("xencraft.color.blue")
		public String blue = "#0052FF";

		@Config.LangKey("xencraft.color.purple")
		public String purple = "#AA00FF";

		@Config.LangKey("xencraft.color.magenta")
		public String magenta = "#FF00FF";

		@Config.LangKey("xencraft.color.pink")
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