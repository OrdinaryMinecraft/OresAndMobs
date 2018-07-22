package ru.flamesword.powerfulenderdragon;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import java.util.List;

public class Utils {

    public static void spawnDragonIfIsNotExist() {
        World world = DimensionManager.getWorld(1);
        if (!world.isRemote) {
            //List<Entity> entityList = world.getLoadedEntityList();
            int radius = 200;
            List entityList = world.getEntitiesWithinAABB(
                    EntityAncientEnderDragon.class,
                    AxisAlignedBB.getBoundingBox(
                            0-radius,
                            0-radius,
                            0-radius,
                            (0 + radius),
                            (0 + radius),
                            (0 + radius)
                    )
            );

            if (!entityList.isEmpty()) {
                return;
            }

            EntityAncientEnderDragon dragon = new EntityAncientEnderDragon(world);
            dragon.setPosition(10, 100, 10);
            world.spawnEntityInWorld(dragon);
        }

    }

    public static void spawnCrystals() {
        World world = DimensionManager.getWorld(1);
        int radius = 3;
        for (int x = -200; x < 200; x++) {
            for (int z = -200; z < 200; z++) {
                Block block = world.getTopBlock(x, z);
                if (block == Blocks.bedrock) {
                    int y = world.getTopSolidOrLiquidBlock(x, z);
                    List e = world.getEntitiesWithinAABB(
                            EntityEnderCrystal.class,
                            AxisAlignedBB.getBoundingBox(
                                    x-radius,
                                    y-radius,
                                    z-radius,
                                    (x + radius),
                                    (y + radius),
                                    (z + radius)
                            )
                    );
                    if (e.isEmpty()) {
                        world.setBlockToAir(x, y, z);
                        EntityEnderCrystal entityEnderCrystal = new EntityEnderCrystal(world);
                        entityEnderCrystal.setPosition(x, y, z);
                        world.spawnEntityInWorld(entityEnderCrystal);
                    }
                }
            }
        }
    }
}
