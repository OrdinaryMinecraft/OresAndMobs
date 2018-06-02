package ru.flamesword.ordinaryores.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import java.util.Objects;
import java.util.Random;

public class WorldUtils {

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static void teleportToDimension(EntityPlayerMP player, int dimensionId) {
        teleportToDimension(player, dimensionId, null, null);
    }

    public static void teleportToDimension(EntityPlayerMP player, int dimensionId, Integer x, Integer z) {

        World world = DimensionManager.getWorld(dimensionId);
        boolean randomCoordinates = false;
        if (Objects.isNull(x) || Objects.isNull(z)) {
            randomCoordinates = true;
        }
        if (randomCoordinates) {
            x = randInt(-300, 300);
            z = randInt(-300, 300);
        }
        int y = 50;
        boolean pointFind = false;
        while (!pointFind) {
            if (world.getBlock(x,y,z) == Blocks.air && world.getBlock(x,y+1,z) == Blocks.air) {
                System.out.println("X:"+x+", Y:"+y+", Z:"+z);
                if (world.getBlock(x,y-1,z) != Blocks.air && world.getBlock(x,y-1,z) != Blocks.water && world.getBlock(x,y-1,z) != Blocks.lava) {
                    pointFind = true;
                }
            }
            y++;
            if (y > 120) {
                y = 50;
                if (randomCoordinates) {
                    x = randInt(-300, 300);
                    z = randInt(-300, 300);
                } else {
                    if (Math.random() >= 0.5) {
                        x++;
                    } else {
                        z++;
                    }
                }
            }
        }
        boolean dimensionTeleport = dimensionId != player.getEntityWorld().provider.dimensionId;
        if(dimensionTeleport) {
            player.travelToDimension(dimensionId);
        }
        player.setPositionAndUpdate(x + 0.5, y + 0.5, z + 0.5);
        //player.rotationYaw = getRotationYaw(facing);
    }
}
