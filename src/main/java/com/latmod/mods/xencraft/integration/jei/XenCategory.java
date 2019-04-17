package com.latmod.mods.xencraft.integration.jei;

import com.latmod.mods.xencraft.XenCraft;
import com.latmod.mods.xencraft.item.XenCraftItems;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author LatvianModder
 */
public class XenCategory implements IRecipeCategory<XenWrapper>
{
	public static final String UID = "xencraft.blocks";
	public static final ResourceLocation TEXTURE = new ResourceLocation(XenCraft.MOD_ID + ":textures/gui/jei.png");

	private final IDrawable background;
	private final IDrawable icon;

	public XenCategory(IGuiHelper guiHelper)
	{
		background = guiHelper.createDrawable(TEXTURE, 0, 0, 106, 36);
		icon = guiHelper.createDrawableIngredient(new ItemStack(XenCraftItems.TABLE));
	}

	@Override
	public String getUid()
	{
		return UID;
	}

	@Override
	public String getTitle()
	{
		return I18n.format("tile.xencraft.xen_block.name");
	}

	@Override
	public String getModName()
	{
		return XenCraft.MOD_NAME;
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}

	@Override
	public IDrawable getIcon()
	{
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayout layout, XenWrapper entry, IIngredients ingredients)
	{
		IGuiItemStackGroup stacks = layout.getItemStacks();
		stacks.init(0, true, 27, 9);
		stacks.init(1, false, 78, 9);
		stacks.set(ingredients);
	}
}