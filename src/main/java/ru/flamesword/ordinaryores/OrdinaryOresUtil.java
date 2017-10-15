package ru.flamesword.ordinaryores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class OrdinaryOresUtil {
	
	static Random rand = new Random();
	
	public static boolean burn(World world, int x, int y, int z)
	{
		return burn(world, x, y, z, false);
	}
	
	public static boolean burn(World world, int x, int y, int z, boolean flag)
	{
		Block block = world.getBlock(x, y, z);
		Block block1 = world.getBlock(x, y+1, z);
		
		/*if (block.getFlammability(world, x, y, z, ForgeDirection.NORTH) > 1)
		{
			world.setBlock(x, y, z, Blocks.fire);
			flag = true;
		}
		if (block == Blocks.grass || block == Blocks.mycelium || block == Blocks.farmland)
		{
			world.setBlock(x, y, z, Blocks.dirt);
			flag = true;
		}
		else if (block == Blocks.ice || block == Blocks.packed_ice || block == Blocks.snow)
		{
			world.setBlock(x, y, z, Blocks.water);
			flag = true;
		}
		else if (block == Blocks.dirt)
		{
			if (world.getBlockMetadata(x, y, z) != 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, 0, 3);
				flag = true;
			}
		}*/
		if (block1 == Blocks.snow_layer)
		{
			world.setBlockToAir(x, y+1, z);
			flag = true;
		}
		
		if (flag)
		{
			world.playAuxSFX(2004, x, y, z, 0);
			world.playAuxSFX(2004, x, y, z, 0);
			world.playAuxSFX(1004, x, y, z, 0);
			//for (int i = 0; i < 2; i++) world.spawnParticle("flame", x+rand.nextFloat()*(1), y+0.2, z+rand.nextFloat()*(1), 0, 0, 0);
		}
		
		return flag;
	}

}
