package com.latmod.mods.xencraft.client;

import com.latmod.mods.xencraft.XenCraft;
import com.latmod.mods.xencraft.block.BlockXen;
import com.latmod.mods.xencraft.block.EnumXenColor;
import com.latmod.mods.xencraft.block.XenCraftBlocks;
import com.latmod.mods.xencraft.item.XenCraftItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = XenCraft.MOD_ID, value = Side.CLIENT)
public class XenCraftClientEventHandler
{
	private static void addModel(Item item, String variant)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), variant));
	}

	private static void addXenModel(Block block, Item item)
	{
		ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockXen.COLOR).build());

		for (EnumXenColor color : EnumXenColor.VALUES)
		{
			ModelLoader.setCustomModelResourceLocation(item, color.getMetadata(), new ModelResourceLocation(item.getRegistryName(), "normal"));
		}
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		addModel(XenCraftItems.XEN_ORE, "normal");
		addXenModel(XenCraftBlocks.XEN_GEM_BLOCK, XenCraftItems.XEN_GEM_BLOCK);
		addModel(XenCraftItems.DARK_XENSTONE, "normal");
		addXenModel(XenCraftBlocks.DARK_XEN_BLOCK, XenCraftItems.DARK_XEN_BLOCK);
		addXenModel(XenCraftBlocks.DARK_XEN_BRICKS, XenCraftItems.DARK_XEN_BRICKS);
		addXenModel(XenCraftBlocks.DARK_XEN_SMALL_BRICKS, XenCraftItems.DARK_XEN_SMALL_BRICKS);
		addXenModel(XenCraftBlocks.DARK_XEN_PLATE, XenCraftItems.DARK_XEN_PLATE);
		addXenModel(XenCraftBlocks.DARK_XEN_TILES, XenCraftItems.DARK_XEN_TILES);
		addXenModel(XenCraftBlocks.DARK_XEN_SMALL_TILES, XenCraftItems.DARK_XEN_SMALL_TILES);
		addModel(XenCraftItems.LIGHT_XENSTONE, "normal");
		addXenModel(XenCraftBlocks.LIGHT_XEN_BLOCK, XenCraftItems.LIGHT_XEN_BLOCK);
		addXenModel(XenCraftBlocks.LIGHT_XEN_BRICKS, XenCraftItems.LIGHT_XEN_BRICKS);
		addXenModel(XenCraftBlocks.LIGHT_XEN_SMALL_BRICKS, XenCraftItems.LIGHT_XEN_SMALL_BRICKS);
		addXenModel(XenCraftBlocks.LIGHT_XEN_PLATE, XenCraftItems.LIGHT_XEN_PLATE);
		addXenModel(XenCraftBlocks.LIGHT_XEN_TILES, XenCraftItems.LIGHT_XEN_TILES);
		addXenModel(XenCraftBlocks.LIGHT_XEN_SMALL_TILES, XenCraftItems.LIGHT_XEN_SMALL_TILES);

		addModel(XenCraftItems.XEN_GEM, "inventory");
		addModel(XenCraftItems.XEN_INGOT, "inventory");
	}

	@SubscribeEvent
	public static void registerBlockColors(ColorHandlerEvent.Block event)
	{
		event.getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> tintIndex == 0 ? EnumXenColor.getBrightColor(pos).getColor() : 0xFFFFFFFF,
				XenCraftBlocks.XEN_ORE);

		event.getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> tintIndex == 0 ? state.getValue(BlockXen.COLOR).getColor() : 0xFFFFFFFF,
				XenCraftBlocks.XEN_GEM_BLOCK,
				XenCraftBlocks.DARK_XEN_BLOCK,
				XenCraftBlocks.DARK_XEN_BRICKS,
				XenCraftBlocks.DARK_XEN_SMALL_BRICKS,
				XenCraftBlocks.DARK_XEN_PLATE,
				XenCraftBlocks.DARK_XEN_TILES,
				XenCraftBlocks.DARK_XEN_SMALL_TILES,
				XenCraftBlocks.LIGHT_XEN_BLOCK,
				XenCraftBlocks.LIGHT_XEN_BRICKS,
				XenCraftBlocks.LIGHT_XEN_SMALL_BRICKS,
				XenCraftBlocks.LIGHT_XEN_PLATE,
				XenCraftBlocks.LIGHT_XEN_TILES,
				XenCraftBlocks.LIGHT_XEN_SMALL_TILES);
	}

	@SubscribeEvent
	public static void registerItemColors(ColorHandlerEvent.Item event)
	{
		event.getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? EnumXenColor.byMeta(stack.getMetadata()).getColor() : 0xFFFFFFFF,
				XenCraftItems.XEN_GEM_BLOCK,
				XenCraftItems.DARK_XEN_BLOCK,
				XenCraftItems.DARK_XEN_BRICKS,
				XenCraftItems.DARK_XEN_SMALL_BRICKS,
				XenCraftItems.DARK_XEN_PLATE,
				XenCraftItems.DARK_XEN_TILES,
				XenCraftItems.DARK_XEN_SMALL_TILES,
				XenCraftItems.LIGHT_XEN_BLOCK,
				XenCraftItems.LIGHT_XEN_BRICKS,
				XenCraftItems.LIGHT_XEN_SMALL_BRICKS,
				XenCraftItems.LIGHT_XEN_PLATE,
				XenCraftItems.LIGHT_XEN_TILES,
				XenCraftItems.LIGHT_XEN_SMALL_TILES);
	}
}