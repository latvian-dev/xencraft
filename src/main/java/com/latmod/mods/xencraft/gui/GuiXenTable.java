package com.latmod.mods.xencraft.gui;

import com.latmod.mods.xencraft.XenCraft;
import com.latmod.mods.xencraft.block.EnumXenColor;
import com.latmod.mods.xencraft.client.XenCraftClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

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
			if (this.visible)
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
			super(buttonId, x, y, 16, 16, buttonText);
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
			int x = guiLeft + 13 + (i % 8) * 19;
			int y = guiTop + 8 + (i / 8) * 19;
			buttonList.add(new ButtonColor(i, x, y, EnumXenColor.byMeta(i)));
		}
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