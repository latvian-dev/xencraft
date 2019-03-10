package com.latmod.mods.xencraft.block;

import com.latmod.mods.xencraft.item.ItemBlockXen;
import com.latmod.mods.xencraft.item.ItemBlockXenstone;
import com.latmod.mods.xencraft.item.XenCraftItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * @author LatvianModder
 */
public class TileXenTable extends TileEntity implements IItemHandlerModifiable
{
	public ItemStack[] items = new ItemStack[2];
	public EnumXenPattern pattern = EnumXenPattern.BLOCK;
	public EnumXenColor color = EnumXenColor.WHITE;

	public TileXenTable()
	{
		Arrays.fill(items, ItemStack.EMPTY);
	}

	public void writeData(NBTTagCompound nbt)
	{
		if (!items[0].isEmpty())
		{
			nbt.setTag("input", items[0].serializeNBT());
		}

		if (!items[1].isEmpty())
		{
			nbt.setTag("output", items[1].serializeNBT());
		}

		nbt.setByte("pattern", (byte) pattern.ordinal());
		nbt.setByte("color", (byte) color.ordinal());
	}

	public void readData(NBTTagCompound nbt)
	{
		items[0] = new ItemStack(nbt.getCompoundTag("input"));

		if (items[0].isEmpty())
		{
			items[0] = ItemStack.EMPTY;
		}

		items[1] = new ItemStack(nbt.getCompoundTag("output"));

		if (items[1].isEmpty())
		{
			items[1] = ItemStack.EMPTY;
		}

		pattern = EnumXenPattern.VALUES[nbt.getByte("pattern")];
		color = EnumXenColor.VALUES[nbt.getByte("color")];
	}

	@Override
	public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, @Nullable net.minecraft.util.EnumFacing facing)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	@Nullable
	public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable net.minecraft.util.EnumFacing facing)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) this : super.getCapability(capability, facing);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		writeData(nbt);
		return super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		readData(nbt);
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag)
	{
		readFromNBT(tag);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		writeData(nbt);
		return new SPacketUpdateTileEntity(pos, 0, nbt);
	}

	@Override
	public void onDataPacket(net.minecraft.network.NetworkManager net, SPacketUpdateTileEntity packet)
	{
		readData(packet.getNbtCompound());
	}

	@Override
	public int getSlots()
	{
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return items[slot];
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
	{
		if (stack.isEmpty())
		{
			return ItemStack.EMPTY;
		}

		ItemStack existing = items[slot];

		int limit = stack.getMaxStackSize();

		if (!existing.isEmpty())
		{
			if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
			{
				return stack;
			}

			limit -= existing.getCount();
		}

		if (limit <= 0)
		{
			return stack;
		}

		boolean reachedLimit = stack.getCount() > limit;

		if (!simulate)
		{
			if (existing.isEmpty())
			{
				items[slot] = reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack;
			}
			else
			{
				existing.grow(reachedLimit ? limit : stack.getCount());
			}

			checkRecipe();
		}

		return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate)
	{
		if (amount == 0)
		{
			return ItemStack.EMPTY;
		}

		ItemStack existing = items[slot];

		if (existing.isEmpty())
		{
			return ItemStack.EMPTY;
		}

		int toExtract = Math.min(amount, existing.getMaxStackSize());

		if (existing.getCount() <= toExtract)
		{
			if (!simulate)
			{
				items[slot] = ItemStack.EMPTY;
				checkRecipe();
			}
			return existing;
		}
		else
		{
			if (!simulate)
			{
				items[slot] = ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract);
				checkRecipe();
			}

			return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
		}
	}

	@Override
	public int getSlotLimit(int slot)
	{
		return 64;
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack)
	{
		if (items[slot] != stack)
		{
			items[slot] = stack;
			checkRecipe();
		}
	}

	@Override
	public boolean isItemValid(int slot, ItemStack stack)
	{
		if (slot == 0)
		{
			Item item = stack.getItem();
			return item == XenCraftItems.XEN_GEM_BLOCK || item instanceof ItemBlockXenstone || item instanceof ItemBlockXen;
		}

		return false;
	}

	public void checkRecipe()
	{
		if (!items[1].isEmpty())
		{
			return;
		}

		Item item = items[0].getItem();

		if (item == XenCraftItems.XEN_GEM_BLOCK)
		{
			items[1] = new ItemStack(XenCraftItems.XEN_GEM_BLOCK, items[0].getCount(), color.getMetadata());
			items[0] = ItemStack.EMPTY;
		}
		else if (item instanceof ItemBlockXenstone || item instanceof ItemBlockXen)
		{
			items[1] = new ItemStack(pattern.item, items[0].getCount(), color.getMetadata());
			items[0] = ItemStack.EMPTY;
		}

		markDirty();
	}
}