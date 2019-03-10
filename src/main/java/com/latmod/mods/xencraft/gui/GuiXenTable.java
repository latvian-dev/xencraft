package com.latmod.mods.xencraft.gui;

import com.latmod.mods.xencraft.XenCraft;
import com.latmod.mods.xencraft.block.EnumXenColor;
import com.latmod.mods.xencraft.client.XenCraftClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * @author LatvianModder
 */
public class GuiXenTable extends GuiContainer
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(XenCraft.MOD_ID, "textures/gui/table.png");

	private class ButtonColor extends GuiButton
	{
		private final EnumXenColor color;

		public ButtonColor(int buttonId, int x, int y, EnumXenColor c)
		{
			super(buttonId, x, y, 16, 16, "");
			color = c;
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
		{
			if (visible)
			{
				mc.getTextureManager().bindTexture(TEXTURE);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

				hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;

				if (container.table.color == color)
				{
					drawTexturedModalRect(x - 2, y - 2, 177, 0, 20, 20);
				}
				else if (hovered)
				{
					drawTexturedModalRect(x - 1, y - 1, 178, 22, 18, 18);
				}

				drawRect(x, y, x + 16, y + 16, XenCraftClientConfig.colors.getColor(color));
				mouseDragged(mc, mouseX, mouseY);
			}
		}
	}

	private class ButtonPattern extends GuiButton
	{
		public ButtonPattern(int buttonId, int x, int y, String buttonText)
		{
			super(buttonId, x, y, 34, 34, buttonText);
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
		{
			if (visible)
			{
				mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
				hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;

				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder buffer = tessellator.getBuffer();
				buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

				TextureAtlasSprite back = mc.getTextureMapBlocks().getAtlasSprite("xencraft:blocks/xenstone");
				buffer.pos(x + 1, y + height - 1, zLevel).tex(back.getMinU(), back.getMaxV()).color(255, 255, 255, 255).endVertex();
				buffer.pos(x + width - 1, y + height - 1, zLevel).tex(back.getMaxU(), back.getMaxV()).color(255, 255, 255, 255).endVertex();
				buffer.pos(x + width - 1, y + 1, zLevel).tex(back.getMaxU(), back.getMinV()).color(255, 255, 255, 255).endVertex();
				buffer.pos(x + 1, y + 1, zLevel).tex(back.getMinU(), back.getMinV()).color(255, 255, 255, 255).endVertex();

				int col = XenCraftClientConfig.colors.getColor(container.table.color);
				int r = (col >> 16) & 0xFF;
				int g = (col >> 8) & 0xFF;
				int b = col & 0xFF;

				TextureAtlasSprite front = (TextureAtlasSprite) container.table.pattern.stencilSprite;
				buffer.pos(x + 1, y + height - 1, zLevel).tex(front.getMinU(), front.getMaxV()).color(r, g, b, 255).endVertex();
				buffer.pos(x + width - 1, y + height - 1, zLevel).tex(front.getMaxU(), front.getMaxV()).color(r, g, b, 255).endVertex();
				buffer.pos(x + width - 1, y + 1, zLevel).tex(front.getMaxU(), front.getMinV()).color(r, g, b, 255).endVertex();
				buffer.pos(x + 1, y + 1, zLevel).tex(front.getMinU(), front.getMinV()).color(r, g, b, 255).endVertex();

				tessellator.draw();

				mouseDragged(mc, mouseX, mouseY);
			}
		}
	}

	public final ContainerXenTable container;

	public GuiXenTable(ContainerXenTable c)
	{
		super(c);
		ySize = 178;
		container = (ContainerXenTable) inventorySlots;
	}

	@Override
	public void initGui()
	{
		super.initGui();

		for (int i = 0; i < 16; i++)
		{
			int x = guiLeft + 8 + (i % 4) * 19;
			int y = guiTop + 6 + (i / 4) * 19;
			buttonList.add(new ButtonColor(i, x, y, EnumXenColor.byMeta(i)));
		}

		buttonList.add(new ButtonPattern(16, guiLeft + 135, guiTop + 5, ""));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (container.enchantItem(mc.player, button.id))
		{
			mc.playerController.sendEnchantPacket(container.windowId, button.id);
		}
	}
}