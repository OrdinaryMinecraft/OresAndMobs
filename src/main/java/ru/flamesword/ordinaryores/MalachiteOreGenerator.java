package ru.flamesword.ordinaryores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class MalachiteOreGenerator implements IWorldGenerator {
	@Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
            IChunkProvider chunkProvider)
    {
            if (world.provider.isSurfaceWorld()) {
                    generateOverworld(world, random, chunkX * 16, chunkZ * 16);
            }
    }

    /*private void generateEnd(World world, Random random, int x, int z) 
    {
    int Xcoord = x + random.nextInt(16);
    int Ycoord = 10 + random.nextInt(128);
    int Zcoord = z + random.nextInt(16);
    
    (new WorldGenMinable(OrdinaryOresBase.malachiteoreblock, 1, 15, Blocks.end_stone)).generate(world, random, Xcoord, Ycoord, Zcoord);
    }*/

    private void generateOverworld(World world, Random random, int x, int z) 
    {
            this.addOreSpawn(OrdinaryOresBase.malachiteoreblock, world, random, x, z, 16, 16, 5 + random.nextInt(6), 25, 32, 255);
    }

    /*private void generateNether(World world, Random random, int x, int z)
    {
    int Xcoord = x + random.nextInt(16);

    int Ycoord = 10 + random.nextInt(128);

    int Zcoord = z + random.nextInt(16);

    (new WorldGenMinable(OrdinaryOresBase.malachiteoreblock, 1, 15, Blocks.netherrack)).generate(world, random, Xcoord, Ycoord, Zcoord);
    }*/
    
/**
 * ��������� ��������� ���� � Minecraft. ������ �������������� ���� ������� ��� ����������� ������������ ���.

 * @param block ����, ������� ������ ������������

 * @param world ��� (�� ���������), � ������� ���� ���� ������ ��������������

 * @param random ��������� ����� ��� ��������� ��������� ��������� �����

 * @param blockXPos ����� ��� ����, ����� ���� ������ ����� �� ����������� X ��� ������ ��������� (���������� ��������� ����)

 * @param blockZPos ����� ��� ����, ����� ���� ������ ����� �� ����������� Z ��� ������ ��������� (���������� ��������� ����)

 * @param maxX �����, ������� �������� ������������ X ���������� ��� ��������� ���� �� ��� X �� ����

 * @param maxZ �����, ������� �������� ������������ Z ���������� ��� ��������� ���� �� ��� Z �� ����

 * @param maxVeinSize ������������ ����� ������ ���� � ����� ����

 * @param chancesToSpawn ���� ��������� ������ �� ����

 * @param minY ����������� ���������� Y �� ������� ���� ����� ���������������

 * @param maxY ������������ ���������� Y �� ������� ���� ����� ���������������
 */
    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ,
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
    }
}
