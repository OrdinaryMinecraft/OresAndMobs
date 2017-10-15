package ru.flamesword.ordinaryores;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.IWorldGenerator;

public class MagicOreGenerator implements IWorldGenerator {
	@Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
            IChunkProvider chunkProvider)
    {
		/*if (BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16), Type.MOUNTAIN)) {
					switch(world.provider.dimensionId)
					{
					case 0: generateOverworld(world, random, chunkX * 16, chunkZ * 16);
					}
		}*/
		
        if (world.provider.isSurfaceWorld())
        {
        		this.generateSurface(world, random, chunkX << 4, chunkZ << 4);
        }
    }

    /*private void generateOverworld(World world, Random random, int x, int z) 
    {
    	this.addOreSpawn(OrdinaryOresBase.magicoreblock, world, random, x, z, 16, 16, 2 + random.nextInt(2), 5, 1, 256);
    }*/
    
    public void generateSurface(World world, Random par2Random, int chunkX, int chunkZ)
    {
      if ((BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(chunkX + 8, chunkZ + 8), Type.MOUNTAIN)) ||
    		  (world.getBiomeGenForCoords(chunkX + 8, chunkZ + 8).biomeName.equals("Forested Hills"))) //��� ExtrabiomesXL, Forested Hills - ������ ����, ������� �� ������� ��� MOUNTAIN
      {
        int rarity = 7;
        int veinSize = 3;
        int height = 32;
        for (int i = 0; i < rarity; ++i)
        {
     	 int randomPosX = chunkX + par2Random.nextInt(16);
     	 int randomPosY = par2Random.nextInt(height);
     	 int randomPosZ = chunkZ + par2Random.nextInt(16);
     	 (new WorldGenMinable(OrdinaryOresBase.magicoreblock, veinSize)).generate(world, par2Random, randomPosX, randomPosY, randomPosZ);
        }
      }
    }

    /*public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ,
            int maxVeinSize, int chancesToSpawn, int minY, int maxY)
    {
            int maxPossY = minY + (maxY - 1);
            assert maxY > minY : "������������ Y ������ ���� ������ �����������";
            assert maxX > 0 && maxX <= 16 : "addOreSpawn: ������������ X ������ ���� ������ 0 � ������ 16";
            assert minY > 0 : "addOreSpawn: ����������� Y ������ ���� ������ 0";
            assert maxY < 256 && maxY > 0 : "addOreSpawn: ������������ Y ������ ���� ������ 256 � ������ 0";
            assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: ������������ Z ������ ���� ������ 0 � ������ 16";

            int diffBtwnMinMaxY = maxY - minY;
            for (int x = 0; x < chancesToSpawn; x++)
            {
                    int posX = blockXPos + random.nextInt(maxX);
                    int posY = minY + random.nextInt(diffBtwnMinMaxY);
                    int posZ = blockZPos + random.nextInt(maxZ);
                    (new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
            }
    }*/
}
