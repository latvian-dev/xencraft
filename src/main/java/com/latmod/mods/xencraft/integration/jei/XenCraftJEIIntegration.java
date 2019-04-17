package com.latmod.mods.xencraft.integration.jei;

import com.latmod.mods.xencraft.block.EnumXenColor;
import com.latmod.mods.xencraft.block.EnumXenPattern;
import com.latmod.mods.xencraft.item.XenCraftItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author LatvianModder
 */
@JEIPlugin
public class XenCraftJEIIntegration implements IModPlugin
{
	@Override
	public void register(IModRegistry r)
	{
		r.handleRecipes(XenWrapper.class, recipe -> recipe, XenCategory.UID);
		r.addRecipeCatalyst(new ItemStack(XenCraftItems.TABLE), XenCategory.UID);
		r.addRecipeCatalyst(new ItemStack(XenCraftItems.TABLET), XenCategory.UID);

		Collection<XenWrapper> recipes = new ArrayList<>();
		List<ItemStack> anyBlock = new ArrayList<>();
		anyBlock.add(new ItemStack(XenCraftItems.XENSTONE));

		for (EnumXenPattern pattern : EnumXenPattern.VALUES)
		{
			for (EnumXenColor color : EnumXenColor.VALUES)
			{
				anyBlock.add(new ItemStack(pattern.item, 1, color.getMetadata()));
			}
		}

		List<List<ItemStack>> input = Collections.singletonList(anyBlock);

		for (EnumXenPattern pattern : EnumXenPattern.VALUES)
		{
			for (EnumXenColor color : EnumXenColor.VALUES)
			{
				recipes.add(new XenWrapper(input, pattern, color));
			}
		}

		r.addRecipes(recipes, XenCategory.UID);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration r)
	{
		r.addRecipeCategories(new XenCategory(r.getJeiHelpers().getGuiHelper()));
	}
}