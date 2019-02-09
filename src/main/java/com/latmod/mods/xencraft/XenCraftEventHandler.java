package com.latmod.mods.xencraft;

import com.latmod.mods.xencraft.block.BlockXen;
import com.latmod.mods.xencraft.block.BlockXenLeaves;
import com.latmod.mods.xencraft.block.BlockXenLog;
import com.latmod.mods.xencraft.block.BlockXenOre;
import com.latmod.mods.xencraft.block.BlockXenPlate;
import com.latmod.mods.xencraft.block.BlockXenSapling;
import com.latmod.mods.xencraft.block.BlockXenstone;
import com.latmod.mods.xencraft.block.XenCraftBlocks;
import com.latmod.mods.xencraft.item.ItemBlockXen;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = XenCraft.MOD_ID)
public class XenCraftEventHandler
{
	private static Block withName(Block block, String name)
	{
		block.setCreativeTab(XenCraft.TAB);
		block.setRegistryName(name);
		block.setTranslationKey(XenCraft.MOD_ID + "." + name);
		return block;
	}

	private static Item withName(Item item, String name)
	{
		item.setCreativeTab(XenCraft.TAB);
		item.setRegistryName(name);
		item.setTranslationKey(XenCraft.MOD_ID + "." + name);
		return item;
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> r = event.getRegistry();
		r.register(withName(new BlockXenOre(), "xen_ore"));
		r.register(withName(new BlockXenSapling(), "xen_sapling"));
		r.register(withName(new BlockXenLog(), "xen_log"));
		r.register(withName(new BlockXenLeaves(), "xen_leaves"));
		r.register(withName(new BlockXen(), "xen_gem_block"));
		r.register(withName(new BlockXenstone(true), "dark_xenstone"));
		r.register(withName(new BlockXen(), "dark_xen_block"));
		r.register(withName(new BlockXen(), "dark_xen_bricks"));
		r.register(withName(new BlockXen(), "dark_xen_small_bricks"));
		r.register(withName(new BlockXenPlate(), "dark_xen_plate"));
		r.register(withName(new BlockXen(), "dark_xen_tiles"));
		r.register(withName(new BlockXen(), "dark_xen_small_tiles"));
		r.register(withName(new BlockXenstone(false), "light_xenstone"));
		r.register(withName(new BlockXen(), "light_xen_block"));
		r.register(withName(new BlockXen(), "light_xen_bricks"));
		r.register(withName(new BlockXen(), "light_xen_small_bricks"));
		r.register(withName(new BlockXenPlate(), "light_xen_plate"));
		r.register(withName(new BlockXen(), "light_xen_tiles"));
		r.register(withName(new BlockXen(), "light_xen_small_tiles"));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> r = event.getRegistry();
		r.register(withName(new Item(), "xen_gem"));
		r.register(withName(new Item(), "xen_ingot"));

		r.register(new ItemBlock(XenCraftBlocks.XEN_ORE).setRegistryName("xen_ore"));
		r.register(new ItemBlock(XenCraftBlocks.XEN_SAPLING).setRegistryName("xen_sapling"));
		r.register(new ItemBlock(XenCraftBlocks.XEN_LOG).setRegistryName("xen_log"));
		r.register(new ItemBlock(XenCraftBlocks.XEN_LEAVES).setRegistryName("xen_leaves"));
		r.register(new ItemBlockXen(XenCraftBlocks.XEN_GEM_BLOCK).setRegistryName("xen_gem_block"));
		r.register(new ItemBlockXen(XenCraftBlocks.DARK_XENSTONE).setRegistryName("dark_xenstone"));
		r.register(new ItemBlockXen(XenCraftBlocks.DARK_XEN_BLOCK).setRegistryName("dark_xen_block"));
		r.register(new ItemBlockXen(XenCraftBlocks.DARK_XEN_BRICKS).setRegistryName("dark_xen_bricks"));
		r.register(new ItemBlockXen(XenCraftBlocks.DARK_XEN_SMALL_BRICKS).setRegistryName("dark_xen_small_bricks"));
		r.register(new ItemBlockXen(XenCraftBlocks.DARK_XEN_PLATE).setRegistryName("dark_xen_plate"));
		r.register(new ItemBlockXen(XenCraftBlocks.DARK_XEN_TILES).setRegistryName("dark_xen_tiles"));
		r.register(new ItemBlockXen(XenCraftBlocks.DARK_XEN_SMALL_TILES).setRegistryName("dark_xen_small_tiles"));
		r.register(new ItemBlockXen(XenCraftBlocks.LIGHT_XENSTONE).setRegistryName("light_xenstone"));
		r.register(new ItemBlockXen(XenCraftBlocks.LIGHT_XEN_BLOCK).setRegistryName("light_xen_block"));
		r.register(new ItemBlockXen(XenCraftBlocks.LIGHT_XEN_BRICKS).setRegistryName("light_xen_bricks"));
		r.register(new ItemBlockXen(XenCraftBlocks.LIGHT_XEN_SMALL_BRICKS).setRegistryName("light_xen_small_bricks"));
		r.register(new ItemBlockXen(XenCraftBlocks.LIGHT_XEN_PLATE).setRegistryName("light_xen_plate"));
		r.register(new ItemBlockXen(XenCraftBlocks.LIGHT_XEN_TILES).setRegistryName("light_xen_tiles"));
		r.register(new ItemBlockXen(XenCraftBlocks.LIGHT_XEN_SMALL_TILES).setRegistryName("light_xen_small_tiles"));
	}
}