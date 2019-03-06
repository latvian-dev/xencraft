package com.latmod.mods.xencraft.gui;

import com.latmod.mods.xencraft.block.EnumXenColor;
import com.latmod.mods.xencraft.block.EnumXenPattern;
import com.latmod.mods.xencraft.block.TileXenTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

/**
 * @author LatvianModder
 */
public class ContainerXenTable extends Container
{
	public final TileXenTable table;

	public ContainerXenTable(TileXenTable t, EntityPlayer p)
	{
		table = t;
		addSlotToContainer(new SlotItemHandler(table, 0, 93, 54));
		addSlotToContainer(new SlotItemHandler(table, 1, 139, 54));

		for (int k = 0; k < 3; ++k)
		{
			for (int i1 = 0; i1 < 9; ++i1)
			{
				addSlotToContainer(new Slot(p.inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l)
		{
			addSlotToContainer(new Slot(p.inventory, l, 8 + l * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index)
	{
		ItemStack prevStack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack stack = slot.getStack();
			prevStack = stack.copy();

			if (index <= 1)
			{
				if (!mergeItemStack(stack, 2, inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!mergeItemStack(stack, 0, 1, false))
			{
				return ItemStack.EMPTY;
			}

			if (stack.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return prevStack;
	}

	@Override
	public boolean enchantItem(EntityPlayer player, int id)
	{
		if (id >= 0 && id < 16)
		{
			table.color = EnumXenColor.byMeta(id);
			table.markDirty();
			return true;
		}
		else if (id == 16)
		{
			table.pattern = EnumXenPattern.VALUES[(table.pattern.ordinal() + 1) % EnumXenPattern.VALUES.length];
			table.markDirty();
			return true;
		}

		return false;
	}
}