package com.latmod.mods.xencraft.integration.jei;

import com.latmod.mods.xencraft.block.EnumXenColor;
import com.latmod.mods.xencraft.block.EnumXenPattern;
import com.latmod.mods.xencraft.client.XenCraftClientConfig;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author LatvianModder
 */
public class XenWrapper implements IRecipeWrapper
{
	public final EnumXenPattern pattern;
	public final EnumXenColor color;
	public final List<List<ItemStack>> input;
	public final ItemStack output;

	public XenWrapper(List<List<ItemStack>> in, EnumXenPattern p, EnumXenColor c)
	{
		pattern = p;
		color = c;

		input = in;
		output = new ItemStack(p.item, 1, c.getMetadata());
	}

	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInputLists(VanillaTypes.ITEM, input);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}

	@Override
	public void drawInfo(Minecraft mc, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
	{
		int x = 10;
		int y = 10;
		int w = 16;
		int h = 16;
		int z = 0;

		mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

		TextureAtlasSprite back = mc.getTextureMapBlocks().getAtlasSprite("xencraft:blocks/xenstone");
		buffer.pos(x, y + h, z).tex(back.getMinU(), back.getMaxV()).color(255, 255, 255, 255).endVertex();
		buffer.pos(x + w, y + h, z).tex(back.getMaxU(), back.getMaxV()).color(255, 255, 255, 255).endVertex();
		buffer.pos(x + w, y, z).tex(back.getMaxU(), back.getMinV()).color(255, 255, 255, 255).endVertex();
		buffer.pos(x, y, z).tex(back.getMinU(), back.getMinV()).color(255, 255, 255, 255).endVertex();

		int col = XenCraftClientConfig.colors.getColor(color);
		int r = (col >> 16) & 0xFF;
		int g = (col >> 8) & 0xFF;
		int b = col & 0xFF;

		TextureAtlasSprite front = (TextureAtlasSprite) pattern.stencilSprite;
		buffer.pos(x, y + h, z).tex(front.getMinU(), front.getMaxV()).color(r, g, b, 255).endVertex();
		buffer.pos(x + w, y + h, z).tex(front.getMaxU(), front.getMaxV()).color(r, g, b, 255).endVertex();
		buffer.pos(x + w, y, z).tex(front.getMaxU(), front.getMinV()).color(r, g, b, 255).endVertex();
		buffer.pos(x, y, z).tex(front.getMinU(), front.getMinV()).color(r, g, b, 255).endVertex();

		tessellator.draw();
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY)
	{
		if (mouseX >= 9 && mouseY >= 9 && mouseX < 27 && mouseY < 27)
		{
			List<String> list = new ArrayList<>();
			list.add(I18n.format(pattern.item.getTranslationKey() + ".name"));
			list.add(TextFormatting.GRAY + I18n.format(color.getTranslationKey()));
			return list;
		}

		return Collections.emptyList();
	}
}