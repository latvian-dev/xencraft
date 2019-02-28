package com.latmod.mods.xencraft;

import com.latmod.mods.xencraft.block.BlockXen;
import com.latmod.mods.xencraft.block.BlockXenBase;
import com.latmod.mods.xencraft.block.BlockXenLeaves;
import com.latmod.mods.xencraft.block.BlockXenLog;
import com.latmod.mods.xencraft.block.BlockXenOre;
import com.latmod.mods.xencraft.block.BlockXenSapling;
import com.latmod.mods.xencraft.block.BlockXenTable;
import com.latmod.mods.xencraft.block.BlockXenstone;
import com.latmod.mods.xencraft.block.EnumXenPattern;
import com.latmod.mods.xencraft.block.EnumXenType;
import com.latmod.mods.xencraft.block.TileXenTable;
import com.latmod.mods.xencraft.block.XenCraftBlocks;
import com.latmod.mods.xencraft.item.ItemBlockXen;
import com.latmod.mods.xencraft.item.ItemBlockXenBase;
import com.latmod.mods.xencraft.item.ItemXenTablet;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
		r.register(withName(new BlockXenTable(), "table"));
		r.register(withName(new BlockXenBase(), "xen_gem_block"));
		r.register(withName(new BlockXenstone(true), "dark_xenstone"));
		r.register(withName(new BlockXenstone(false), "light_xenstone"));

		for (EnumXenType type : EnumXenType.VALUES)
		{
			for (EnumXenPattern pattern : EnumXenPattern.VALUES)
			{
				BlockXen blockXen = new BlockXen(type, pattern);
				pattern.blocks[type.ordinal()] = blockXen;
				r.register(withName(blockXen, type.getName() + "_xen_" + pattern.getName()));
			}
		}

		GameRegistry.registerTileEntity(TileXenTable.class, new ResourceLocation(XenCraft.MOD_ID, "table"));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> r = event.getRegistry();
		r.register(withName(new Item(), "xen_gem"));
		r.register(withName(new Item(), "xen_ingot"));
		r.register(withName(new ItemXenTablet(), "tablet"));

		r.register(new ItemBlock(XenCraftBlocks.XEN_ORE).setRegistryName("xen_ore"));
		r.register(new ItemBlock(XenCraftBlocks.XEN_SAPLING).setRegistryName("xen_sapling"));
		r.register(new ItemBlock(XenCraftBlocks.XEN_LOG).setRegistryName("xen_log"));
		r.register(new ItemBlock(XenCraftBlocks.XEN_LEAVES).setRegistryName("xen_leaves"));
		r.register(new ItemBlock(XenCraftBlocks.TABLE).setRegistryName("table"));
		r.register(new ItemBlockXenBase(XenCraftBlocks.XEN_GEM_BLOCK).setRegistryName("xen_gem_block"));
		r.register(new ItemBlock(XenCraftBlocks.DARK_XENSTONE).setRegistryName("dark_xenstone"));
		r.register(new ItemBlock(XenCraftBlocks.LIGHT_XENSTONE).setRegistryName("light_xenstone"));

		for (EnumXenType type : EnumXenType.VALUES)
		{
			for (EnumXenPattern pattern : EnumXenPattern.VALUES)
			{
				ItemBlockXen itemBlockXen = new ItemBlockXen(type, pattern);
				itemBlockXen.setRegistryName(itemBlockXen.getBlock().getRegistryName());
				pattern.items[type.ordinal()] = itemBlockXen;
				r.register(itemBlockXen);
			}
		}
	}
}