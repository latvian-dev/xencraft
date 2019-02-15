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
	public int getXenLightValue(BlockRenderLayer layer)
	{
		return MinecraftForgeClient.getRenderLayer() == layer ? 15 : 0;
	}
}