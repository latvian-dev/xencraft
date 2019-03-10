package com.latmod.mods.xencraft.item;

import com.latmod.mods.xencraft.XenCraftGuiHandler;
import com.latmod.mods.xencraft.block.TileXenTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * @author LatvianModder
 */
public class ItemXenTablet extends Item
{
	public static class ItemXenTabletData extends TileXenTable
	{
		public final ItemStack stack;

		public ItemXenTabletData(ItemStack is)
		{
			stack = is;

			if (stack.hasTagCompound() && stack.getTagCompound().hasKey("tablet"))
			{
				readData(stack.getTagCompound().getCompoundTag("tablet"));
			}
		}

		@Override
		public void markDirty()
		{
			NBTTagCompound nbt = new NBTTagCompound();
			writeData(nbt);
			stack.setTagInfo("tablet", nbt);
		}
	}

	public ItemXenTablet()
	{
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if (!world.isRemote)
		{
			XenCraftGuiHandler.openTablet(player, hand);
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
}