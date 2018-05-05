package ru.flamesword.ordinaryores.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import java.util.Random;

public class WorldUtils {

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static void teleportToDimension(EntityPlayerMP player, int dimensionId) {
        boolean dimensionTeleport = dimensionId != player.getEntityWorld().provider.dimensionId;
        if(dimensionTeleport) {
            player.travelToDimension(dimensionId);
        }
        World world = DimensionManager.getWorld(dimensionId);
        int x = 0;
        int y = 0;
        int z = 0;
        boolean pointFind = false;
        while (!pointFind) {
            x = randInt(-1000, 1000);
            y = randInt(50, 100);
            z = randInt(-1000, 1000);
            if (world.getBlock(x,y,z) == Blocks.air && world.getBlock(x,y+1,z) == Blocks.air) {
                if (world.getBlock(x,y-1,z) != Blocks.air && world.getBlock(x,y-1,z) != Blocks.water && world.getBlock(x,y-1,z) != Blocks.lava) {
                    pointFind = true;
                }
            }
        }
        //player.rotationYaw = getRotationYaw(facing);
        player.setPositionAndUpdate(x + 0.5, y + 0.5, z + 0.5);
    }
}
