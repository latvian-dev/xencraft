package com.latmod.mods.xencraft;

import com.latmod.mods.xencraft.item.XenCraftItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
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
			return new ItemStack(XenCraftItems.XEN_GEM);
		}
	};

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		//NetworkRegistry.INSTANCE.registerGuiHandler(this, XenCraftGuiHandler.INSTANCE);
		//XenCraftNet.init();
	}

	@EventHandler
	public void onInit(FMLInitializationEvent event)
	{
		OreDictionary.registerOre("oreXen", XenCraftItems.XEN_ORE);
		OreDictionary.registerOre("blockXen", new ItemStack(XenCraftItems.XEN_GEM_BLOCK, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemXen", XenCraftItems.XEN_GEM);
		OreDictionary.registerOre("ingotXen", XenCraftItems.XEN_INGOT);
	}

	@EventHandler
	public void onPostInit(FMLPostInitializationEvent event)
	{
		FurnaceRecipes.instance().addSmelting(XenCraftItems.XEN_ORE, new ItemStack(XenCraftItems.XEN_GEM, 6), 1F);
		FurnaceRecipes.instance().addSmelting(XenCraftItems.XEN_GEM, new ItemStack(XenCraftItems.XEN_INGOT), 0.1F);
	}
}