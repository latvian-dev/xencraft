package com.latmod.mods.xencraft;

import com.latmod.mods.xencraft.client.XenCraftClientConfig;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = XenCraft.MOD_ID)
@Config(modid = XenCraft.MOD_ID, category = "")
public class XenCraftConfig
{
	public static final General general = new General();

	public static class General
	{
		@Config.RangeInt(min = 0, max = 100)
		public int ores_per_chunk = 3;

		@Config.RangeInt(min = 1, max = 64)
		public int ores_size = 8;

		public int ores_min_y = 0;
		public int ores_max_y = 36;
	}

	public static void sync()
	{
		ConfigManager.sync(XenCraft.MOD_ID, Config.Type.INSTANCE);
		XenCraftClientConfig.sync();
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equals(XenCraft.MOD_ID))
		{
			sync();
		}
	}
}