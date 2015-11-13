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
            switch(world.provider.dimensionId)
            {
                    //case -1: generateNether(world, random, chunkX * 16, chunkZ * 16);
                    case 0: generateOverworld(world, random, chunkX * 16, chunkZ * 16);
                    //case 1: generateEnd(world, random, chunkX * 16, chunkZ * 16);
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
 * ƒобавл€ет генерацию руды в Minecraft. ѕросто воспользуйтесь этим методом дл€ регистрации генерируемых руд.

 * @param block Ѕлок, который хотите генерировать

 * @param world ћир (не измерение), в котором этот блок должен генерироватьс€

 * @param random —лучайное число дл€ получени€ координат генерации блока

 * @param blockXPos „исло дл€ того, чтобы было пустое место по координатам X дл€ метода генерации (использует кварцева€ руда)

 * @param blockZPos „исло дл€ того, чтобы было пустое место по координатам Z дл€ метода генерации (использует кварцева€ руда)

 * @param maxX „исло, которое настроит максимальную X координату дл€ генерации руды на оси X на чанк

 * @param maxZ „исло, которое настроит максимальную Z координату дл€ генерации руды на оси Z на чанк

 * @param maxVeinSize ћаксимальное число блоков руды в одной жиле

 * @param chancesToSpawn Ўанс генерации блоков на чанк

 * @param minY ћинимальна€ координата Y на которой руда может сгенерироватьс€

 * @param maxY ћаксимальна€ координата Y на которой руда может сгенерироватьс€
 */
    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ,
            int maxVeinSize, int chancesToSpawn, int minY, int maxY)
    {
            int maxPossY = minY + (maxY - 1);
            assert maxY > minY : "ћаксимальна€ Y должна быть больше минимальной";
            assert maxX > 0 && maxX <= 16 : "addOreSpawn: ћаксимальна€ X должна быть больше 0 и меньше 16";
            assert minY > 0 : "addOreSpawn: ћинимальна€ Y должна быть больше 0";
            assert maxY < 256 && maxY > 0 : "addOreSpawn: ћаксимальна€ Y должна быть меньше 256 и больше 0";
            assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: ћаксимальна€ Z должна быть больше 0 и меньше 16";

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
