package com.latmod.mods.xencraft.client;

import com.latmod.mods.xencraft.XenCraft;
import com.latmod.mods.xencraft.block.BlockXen;
import com.latmod.mods.xencraft.block.EnumXenColor;
import com.latmod.mods.xencraft.block.XenCraftBlocks;
import com.latmod.mods.xencraft.item.XenCraftItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
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
		addXenModel(XenCraftBlocks.XEN_BLOCK, XenCraftItems.XEN_BLOCK);

		addModel(XenCraftItems.XEN_GEM, "normal");
		addModel(XenCraftItems.XEN_INGOT, "normal");
	}

	public static void registerColors()
	{
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> tintIndex == 0 ? EnumXenColor.getBrightColor(pos).getColor() : 0xFFFFFFFF,
				XenCraftBlocks.XEN_ORE);

		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) -> tintIndex == 0 ? state.getValue(BlockXen.COLOR).getColor() : 0xFFFFFFFF,
				XenCraftBlocks.XEN_BLOCK);

		Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? EnumXenColor.byMeta(stack.getMetadata()).getColor() : 0xFFFFFFFF,
				XenCraftItems.XEN_BLOCK);
	}
}