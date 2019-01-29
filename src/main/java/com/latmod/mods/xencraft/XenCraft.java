package com.latmod.mods.xencraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
		modid = XenCraft.MOD_ID,
		name = XenCraft.MOD_NAME,
		version = XenCraft.VERSION,
		acceptedMinecraftVersions = "[1.12,)"
)
public class XenCraft
{
	public static final String MOD_ID = "xencraft";
	public static final String MOD_NAME = "XenCraft";
	public static final String VERSION = "0.0.0.xencraft";
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	@Mod.Instance(MOD_ID)
	public static XenCraft INSTANCE;

	@SidedProxy(serverSide = "com.latmod.mods.modularpipes.XenCraftCommon", clientSide = "com.latmod.mods.xencraft.client.XenCraftClient")
	public static XenCraftCommon PROXY;

	public static final CreativeTabs TAB = new CreativeTabs(MOD_ID)
	{
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(Items.DIAMOND);
		}
	};

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		//NetworkRegistry.INSTANCE.registerGuiHandler(this, XenCraftGuiHandler.INSTANCE);
		//XenCraftNet.init();
	}
}