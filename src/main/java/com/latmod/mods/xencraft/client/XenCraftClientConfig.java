package com.latmod.mods.xencraft.client;

import com.latmod.mods.xencraft.XenCraft;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = XenCraft.MOD_ID, value = Side.CLIENT)
@Config(modid = XenCraft.MOD_ID + "_client", category = "", name = "../local/client/" + XenCraft.MOD_ID)
public class XenCraftClientConfig
{
	@Config.LangKey("stat.generalButton")
	public static final General general = new General();

	public static class General
	{
		@Config.RangeInt(min = 0, max = 15)
		public int xen_light_value = 15;
	}

	public static void sync()
	{
		ConfigManager.sync(XenCraft.MOD_ID + "_client", Config.Type.INSTANCE);
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equals(XenCraft.MOD_ID + "_client"))
		{
			sync();
		}
	}
}