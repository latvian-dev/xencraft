package com.latmod.mods.xencraft.gui;

import com.latmod.mods.xencraft.XenCraft;
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
		public ButtonColor(int buttonId, int x, int y, String buttonText)
		{
			super(buttonId, x, y, 16, 16, buttonText);
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
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);

		for (int i = 0; i < 16; i++)
		{
			int x = 13 + (i % 8) * 19;
			int y = 13 + (i % 8) * 19;

		}

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
	}
}