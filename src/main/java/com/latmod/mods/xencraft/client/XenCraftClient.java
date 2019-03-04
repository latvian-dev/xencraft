package com.latmod.mods.xencraft.client;

import com.latmod.mods.xencraft.XenCraftCommon;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author LatvianModder
 */
public class XenCraftClient extends XenCraftCommon
{
	/**
	 * A terrible hack that I will figure out how to handle better
	 */
	@Override
	public int getXenLightValue(IBlockAccess world, BlockRenderLayer layer)
	{
		if (world instanceof World)
		{
			if (!((World) world).isRemote)
			{
				return 0;
			}
		}

		return MinecraftForgeClient.getRenderLayer() == layer ? XenCraftClientConfig.general.xen_light_value : 0;
	}
}