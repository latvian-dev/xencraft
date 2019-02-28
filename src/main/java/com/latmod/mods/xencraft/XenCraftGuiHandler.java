package com.latmod.mods.xencraft;

import com.latmod.mods.xencraft.block.TileXenTable;
import com.latmod.mods.xencraft.gui.ContainerXenTable;
import com.latmod.mods.xencraft.gui.GuiXenTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * @author LatvianModder
 */
public class XenCraftGuiHandler implements IGuiHandler
{
	public static void openTable(EntityPlayer player, BlockPos pos)
	{
		player.openGui(XenCraft.INSTANCE, 0, player.world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Nullable
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		if (id == 0)
		{
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

			if (tileEntity instanceof TileXenTable)
			{
				return new ContainerXenTable((TileXenTable) tileEntity, player);
			}
		}

		return null;
	}

	@Nullable
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		return getClientGuiElement0(id, player, world, x, y, z);
	}

	@Nullable
	private Object getClientGuiElement0(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		if (id == 0)
		{
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

			if (tileEntity instanceof TileXenTable)
			{
				return new GuiXenTable(new ContainerXenTable((TileXenTable) tileEntity, player));
			}
		}

		return null;
	}
}