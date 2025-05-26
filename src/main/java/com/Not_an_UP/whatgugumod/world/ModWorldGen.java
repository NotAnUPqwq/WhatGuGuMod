package com.Not_an_UP.whatgugumod.world;

import java.util.Random;

import com.Not_an_UP.whatgugumod.entity.EntityGuGu;
import com.Not_an_UP.whatgugumod.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ModWorldGen implements IWorldGenerator{
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == 0) {
			generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
	}
	
	private void generateSurface(World world, Random random, int chunkX, int chunkZ, int tried) {
        if (tried < 6) { // 平均16个区块生成一个
            int x = chunkX*16 + random.nextInt(16);
            int z = chunkZ*16 + random.nextInt(16);
            int y = world.getHeight(x, z); // 获取地表高度
            BlockPos getPos = new BlockPos(x,y,z);
            Block getGroundBlock = world.getBlockState(getPos.down()).getBlock();
            
            if (!(getGroundBlock instanceof BlockLiquid |
            	(tried<4 & getGroundBlock instanceof BlockLeaves)|
            	getGroundBlock instanceof BlockCrops |
            	getGroundBlock instanceof BlockFlower)) {
            	// 咕咕宝箱，启动！
                int getNum = random.nextInt(100);
                if (getNum < 5) {
                	world.setBlockState(getPos, ModBlocks.GUGU_BIGGEST_CHEST.getDefaultState());
                }else if (getNum < 20){
                	world.setBlockState(getPos, ModBlocks.GUGU_BIGGER_CHEST.getDefaultState());
                }else {
                	world.setBlockState(getPos, ModBlocks.GUGU_CHEST.getDefaultState());
                }
            }else {
            	this.generateSurface(world, random, chunkX, chunkZ, tried+1);
            	return;
            }
        }
        
        if (random.nextFloat() < 0.15) { // 15%的几率生成
        	int num = random.nextInt(2) + 2;
        	for (int i = 0; i < num; i++) {
        		generateGuGu(world, random, chunkX, chunkZ);
        	}
        }
    }
	
	private void generateGuGu(World world, Random random, int chunkX, int chunkZ) {
		int x = chunkX*16 + random.nextInt(16);
        int z = chunkZ*16 + random.nextInt(16);
        int y = world.getHeight(x, z);
        EntityGuGu entity = new EntityGuGu(world);
        entity.setPosition(x + random.nextInt(16), y, z + random.nextInt(16));
        world.spawnEntity(entity);
	}
	
	private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (random.nextInt(64) == 0) {
			generateSurface(world, random, chunkX, chunkZ, 0);
		}
		
		generateOre(ModBlocks.GUGU_PRIMOGEMS_ORE.getDefaultState(), world, random, chunkX*16, chunkZ*16, 5, 40, 3, 10);
		generateOre(ModBlocks.GUGU_ORE.getDefaultState(), world, random, chunkX*16, chunkZ*16, 5, 160, random.nextInt(5)+4, 15);
		generateOre(ModBlocks.COAL_GUGU_ORE.getDefaultState(), world, random, chunkX*16, chunkZ*16, 5, 160, random.nextInt(5)+4, 20);
		generateOre(ModBlocks.RAW_TRIM_ORE.getDefaultState(), world, random, chunkX*16, chunkZ*16, 5, 160, random.nextInt(5)+4, 25);
	}
	
	private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
		int deltaY = maxY - minY;
		
		for (int i = 0; i < chances; i++) {
			BlockPos pos = new BlockPos(x+random.nextInt(16),minY+random.nextInt(deltaY),z+random.nextInt(16));
			
			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}

}
