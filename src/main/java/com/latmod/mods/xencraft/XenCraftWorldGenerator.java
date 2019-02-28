package com.latmod.mods.xencraft;

import com.latmod.mods.xencraft.block.XenCraftBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * @author LatvianModder
 */
public class XenCraftWorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		int y0 = XenCraftConfig.general.ores_min_y;
		int y1 = XenCraftConfig.general.ores_max_y;

		for (int i = 0; i < XenCraftConfig.general.ores_per_chunk; i++)
		{
			BlockPos pos = new BlockPos((chunkX << 4) + random.nextInt(16), y0 + random.nextInt(y1 - y0 + 1), (chunkZ << 4) + random.nextInt(16));
			WorldGenMinable generator = new WorldGenMinable(XenCraftBlocks.XEN_ORE.getDefaultState(), XenCraftConfig.general.ores_size);
			generator.generate(world, random, pos);
		}
	}
}