package com.latmod.mods.xencraft.client;

import com.latmod.mods.xencraft.XenCraftCommon;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author LatvianModder
 */
public class XenCraftClient extends XenCraftCommon
{
	@Override
	public int getSolidXenLightValue()
	{
		return MinecraftForgeClient.getRenderLayer() == BlockRenderLayer.SOLID ? 15 : 0;
	}

	@Override
	public int getCutoutXenLightValue()
	{
		return MinecraftForgeClient.getRenderLayer() == BlockRenderLayer.CUTOUT ? 15 : 0;
	}
}