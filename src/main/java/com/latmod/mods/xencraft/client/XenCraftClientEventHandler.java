package com.latmod.mods.xencraft.client;

import com.latmod.mods.xencraft.XenCraft;
import com.latmod.mods.xencraft.block.BlockXenBase;
import com.latmod.mods.xencraft.block.BlockXenSapling;
import com.latmod.mods.xencraft.block.EnumXenColor;
import com.latmod.mods.xencraft.block.EnumXenPattern;
import com.latmod.mods.xencraft.block.EnumXenType;
import com.latmod.mods.xencraft.block.XenCraftBlocks;
import com.latmod.mods.xencraft.item.XenCraftItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
		ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockXenBase.COLOR).build());

		for (EnumXenColor color : EnumXenColor.VALUES)
		{
			ModelLoader.setCustomModelResourceLocation(item, color.getMetadata(), new ModelResourceLocation(item.getRegistryName(), "normal"));
		}
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		addModel(XenCraftItems.XEN_ORE, "normal");
		addModel(XenCraftItems.XEN_SAPLING, "normal");
		ModelLoader.setCustomStateMapper(XenCraftBlocks.XEN_SAPLING, new StateMap.Builder().ignore(BlockXenSapling.STAGE).build());
		addModel(XenCraftItems.XEN_LOG, "normal");
		addModel(XenCraftItems.XEN_LEAVES, "normal");
		ModelLoader.setCustomStateMapper(XenCraftBlocks.XEN_LEAVES, new StateMap.Builder().ignore(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE).build());
		addModel(XenCraftItems.TABLE, "normal");
		addXenModel(XenCraftBlocks.XEN_GEM_BLOCK, XenCraftItems.XEN_GEM_BLOCK);
		addModel(XenCraftItems.DARK_XENSTONE, "normal");
		addModel(XenCraftItems.LIGHT_XENSTONE, "normal");

		for (EnumXenType type : EnumXenType.VALUES)
		{
			for (EnumXenPattern pattern : EnumXenPattern.VALUES)
			{
				addXenModel(pattern.blocks[type.ordinal()], pattern.items[type.ordinal()]);
			}
		}

		addModel(XenCraftItems.XEN_GEM, "inventory");
		addModel(XenCraftItems.XEN_INGOT, "inventory");
		addModel(XenCraftItems.TABLET, "inventory");
	}

	private static int col(EnumXenColor col)
	{
		return XenCraftClientConfig.colors.getColor(col);
	}

	@SubscribeEvent
	public static void registerBlockColors(ColorHandlerEvent.Block event)
	{
		event.getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> tintIndex == 0 ? col(EnumXenColor.getBrightColor(pos)) : 0xFFFFFFFF,
				XenCraftBlocks.XEN_ORE,
				XenCraftBlocks.XEN_SAPLING,
				XenCraftBlocks.XEN_LOG);

		List<Block> blocks = new ArrayList<>();
		blocks.add(XenCraftBlocks.XEN_GEM_BLOCK);

		for (EnumXenType type : EnumXenType.VALUES)
		{
			for (EnumXenPattern pattern : EnumXenPattern.VALUES)
			{
				blocks.add(pattern.blocks[type.ordinal()]);
			}
		}

		event.getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> tintIndex == 0 ? col(state.getValue(BlockXenBase.COLOR)) : 0xFFFFFFFF, blocks.toArray(new Block[0]));

		event.getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> {
			if (tintIndex == 0)
			{
				return world != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic();
			}
			else if (tintIndex == 1)
			{
				return col(EnumXenColor.getBrightColor(pos));
			}
			return 0xFFFFFFFF;
		}, XenCraftBlocks.XEN_LEAVES);
	}

	@SubscribeEvent
	public static void registerItemColors(ColorHandlerEvent.Item event)
	{
		event.getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? Color.HSBtoRGB((System.currentTimeMillis() % 10000L) / 10000F, 0.7F, 0.9F) : 0xFFFFFFFF,
				XenCraftItems.XEN_ORE,
				XenCraftItems.XEN_SAPLING,
				XenCraftItems.XEN_LOG);

		List<Item> items = new ArrayList<>();
		items.add(XenCraftItems.XEN_GEM_BLOCK);

		for (EnumXenType type : EnumXenType.VALUES)
		{
			for (EnumXenPattern pattern : EnumXenPattern.VALUES)
			{
				items.add(pattern.items[type.ordinal()]);
			}
		}

		event.getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? col(EnumXenColor.byMeta(stack.getMetadata())) : 0xFFFFFFFF, items.toArray(new Item[0]));

		event.getItemColors().registerItemColorHandler((stack, tintIndex) -> {
			if (tintIndex == 0)
			{
				return ColorizerFoliage.getFoliageColorBasic();
			}
			else if (tintIndex == 1)
			{
				return Color.HSBtoRGB((System.currentTimeMillis() % 10000L) / 10000F, 0.7F, 0.9F);
			}
			return 0xFFFFFFFF;
		}, XenCraftItems.XEN_LEAVES);
	}
}