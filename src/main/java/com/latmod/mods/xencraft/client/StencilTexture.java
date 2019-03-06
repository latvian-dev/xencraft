package com.latmod.mods.xencraft.client;

import com.latmod.mods.xencraft.XenCraft;
import com.latmod.mods.xencraft.block.EnumXenPattern;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * @author LatvianModder
 */
public class StencilTexture extends TextureAtlasSprite
{
	private final EnumXenPattern pattern;
	private final boolean invert;
	private final int mipmapLevels;

	public StencilTexture(EnumXenPattern p, boolean i, int m)
	{
		super(XenCraft.MOD_ID + ":generated/" + p);
		pattern = p;
		invert = i;
		mipmapLevels = m;
	}

	@Override
	public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location)
	{
		return true;
	}

	@Override
	public boolean load(IResourceManager manager, ResourceLocation location, Function<ResourceLocation, TextureAtlasSprite> textureGetter)
	{
		try
		{
			framesTextureData.clear();
			IResource resource = manager.getResource(new ResourceLocation("textures/blocks/slime.png"));
			InputStream inputStream = resource.getInputStream();
			BufferedImage bufferedImage = TextureUtil.readBufferedImage(inputStream);
			BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
			BufferedImage image = op.filter(bufferedImage, null);
			int[][] aint = new int[mipmapLevels][];
			aint[0] = new int[image.getWidth() * image.getHeight()];
			image.getRGB(0, 0, image.getWidth(), image.getHeight(), aint[0], 0, image.getWidth());
			height = image.getHeight();
			width = image.getWidth();
			framesTextureData.add(aint);
			return false;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return super.load(manager, location, textureGetter);
	}

	@Override
	public java.util.Collection<ResourceLocation> getDependencies()
	{
		return com.google.common.collect.ImmutableList.of(new ResourceLocation("blocks/slime"));
	}
}