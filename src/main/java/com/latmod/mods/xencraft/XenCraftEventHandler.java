package com.latmod.mods.xencraft;

import com.latmod.mods.xencraft.block.BlockXen;
import com.latmod.mods.xencraft.block.BlockXenOre;
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
		r.register(withName(new BlockXen(), "xen_block"));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> r = event.getRegistry();
		r.register(new ItemBlock(XenCraftBlocks.XEN_ORE).setRegistryName("xen_ore"));
		r.register(new ItemBlockXen(XenCraftBlocks.XEN_BLOCK).setRegistryName("xen_block"));

		r.register(withName(new Item(), "xen_gem"));
		r.register(withName(new Item(), "xen_ingot"));
	}
}