package com.finalkg.nethermine.common.world.gen;

import java.util.Random;

import com.finalkg.nethermine.NetherMineCore;
import com.finalkg.nethermine.common.block.BlockNetherOre;
import com.finalkg.nethermine.common.lib.EnumNetherOre;
import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorNetherOre implements IWorldGenerator {
    private final Predicate<IBlockState> predicate = new NetherrackPredicate(); // 1.12.2

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == -1) {
			generateNether(world, random, chunkX, chunkZ, true);
		}
	}

	private void generateNether(World world, Random random, int chunkX, int chunkZ, boolean newGen){
		if (NetherMineCore.options.enableWorldGen) {
			for(EnumNetherOre o : EnumNetherOre.values()) {
				if(o.getForced() || (o.isRegisteredSmelting() || o.isRegisteredMacerator() || NetherMineCore.options.forceAllOreSpawn &&!o.getDisabled())) {
					if (newGen) {
						for (int i = NetherMineCore.options.getGroupsPerChunk(o); i --> 0; ){
							int x = chunkX * 16 + random.nextInt(16);
							int y = o.getMinY() + random.nextInt(o.getMaxY() - o.getMinY());
							int z = chunkZ * 16 + random.nextInt(16);
							//new WorldGenNetherOre(NetherMineCore.blockNetherOres[o.getBlockIndex()], o.getMetadata(), o.getBlocksPerGroup()).generate(world, random, new BlockPos(x, y, z));
							BlockNetherOre block = (BlockNetherOre) NetherMineCore.blockNetherOres[o.getBlockIndex()];
							WorldGenMinable gen = new WorldGenMinable(block.getStateFromMeta(o.getMetadata()), NetherMineCore.options.getBlocksPerGroup(o), predicate);
							gen.generate(world, random, new BlockPos(x, y, z));
						}
					}
				}
			}
		}
	}
    static class NetherrackPredicate implements Predicate<IBlockState>
    {
        private NetherrackPredicate()
        {
        }

        public boolean apply(IBlockState state)
        {
            if (state != null && state.getBlock() == Blocks.NETHERRACK)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
