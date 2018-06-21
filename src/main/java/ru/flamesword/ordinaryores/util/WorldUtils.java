package ru.flamesword.ordinaryores.util;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

import java.util.Iterator;
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
            MinecraftServer minecraftserver = MinecraftServer.getServer();
            WorldServer worldserver = minecraftserver.worldServerForDimension(world.provider.dimensionId);
            transferPlayerToDimension(player, dimensionId, new MyTeleporter(worldserver), minecraftserver);
            //player.travelToDimension(dimensionId);
        }
        player.setPositionAndUpdate(x + 0.5, y + 0.5, z + 0.5);
        //player.rotationYaw = getRotationYaw(facing);
    }

    public static void ckeckAndRemovePortals(EntityPlayer player) {
        int radius = 16;
        boolean flagBreak = false;
        for (int x = (int) player.posX - radius; x < (int) player.posX + radius; x++) {
            for (int y = (int) player.posY - radius; y < (int) player.posY + radius; y++) {
                for (int z = (int) player.posZ - radius; z < (int) player.posZ + radius; z++) {
                    if (player.worldObj.getBlock(x, y, z) == Blocks.portal) {
                        removePortal(player.worldObj, x, y, z);
                        player.worldObj.playSoundEffect(x, y, z, "random.explode", 0.7F, 0.7F);
                        player.inventory.addItemStackToInventory(new ItemStack(OrdinaryOresBase.netherstone, 1));
                        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentTranslation("message.portal.found", player.getDisplayName()));
                        flagBreak = true;
                    }
                    if (flagBreak) {
                        break;
                    }
                }
                if (flagBreak) {
                    break;
                }
            }
            if (flagBreak) {
                break;
            }
        }
    }

    public static void removePortal(World world, int portal_x, int portal_y, int portal_z) {
        int radius = 10;
        for (int x = portal_x - radius; x < portal_x + radius; x++) {
            for (int y = portal_y - radius; y < portal_y + radius; y++) {
                for (int z = portal_z - radius; z < portal_z + radius; z++) {
                    if (world.getBlock(x, y, z) == Blocks.portal || world.getBlock(x, y, z) == Blocks.obsidian) {
                        world.setBlockToAir(x, y, z);
                    }
                }
            }
        }
    }

    public static void transferPlayerToDimension(EntityPlayerMP p_72356_1_, int p_72356_2_, Teleporter teleporter, MinecraftServer mcServer)
    {
        int j = p_72356_1_.dimension;
        WorldServer worldserver = mcServer.worldServerForDimension(p_72356_1_.dimension);
        p_72356_1_.dimension = p_72356_2_;
        WorldServer worldserver1 = mcServer.worldServerForDimension(p_72356_1_.dimension);
        p_72356_1_.playerNetServerHandler.sendPacket(new S07PacketRespawn(p_72356_1_.dimension, worldserver1.difficultySetting, worldserver1.getWorldInfo().getTerrainType(), p_72356_1_.theItemInWorldManager.getGameType())); // Forge: Use new dimensions information
        worldserver.removePlayerEntityDangerously(p_72356_1_);
        p_72356_1_.isDead = false;
        transferEntityToWorld(p_72356_1_, j, worldserver, worldserver1, teleporter);
        mcServer.getConfigurationManager().func_72375_a(p_72356_1_, worldserver);
        p_72356_1_.playerNetServerHandler.setPlayerLocation(p_72356_1_.posX, p_72356_1_.posY, p_72356_1_.posZ, p_72356_1_.rotationYaw, p_72356_1_.rotationPitch);
        p_72356_1_.theItemInWorldManager.setWorld(worldserver1);
        mcServer.getConfigurationManager().updateTimeAndWeatherForPlayer(p_72356_1_, worldserver1);
        mcServer.getConfigurationManager().syncPlayerInventory(p_72356_1_);
        Iterator iterator = p_72356_1_.getActivePotionEffects().iterator();

        while (iterator.hasNext())
        {
            PotionEffect potioneffect = (PotionEffect)iterator.next();
            p_72356_1_.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(p_72356_1_.getEntityId(), potioneffect));
        }
        FMLCommonHandler.instance().firePlayerChangedDimensionEvent(p_72356_1_, j, p_72356_2_);
    }


    public static void transferEntityToWorld(Entity p_82448_1_, int p_82448_2_, WorldServer p_82448_3_, WorldServer p_82448_4_, Teleporter teleporter)
    {
        WorldProvider pOld = p_82448_3_.provider;
        WorldProvider pNew = p_82448_4_.provider;
        double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
        double d0 = p_82448_1_.posX * moveFactor;
        double d1 = p_82448_1_.posZ * moveFactor;
        double d3 = p_82448_1_.posX;
        double d4 = p_82448_1_.posY;
        double d5 = p_82448_1_.posZ;
        float f = p_82448_1_.rotationYaw;
        p_82448_3_.theProfiler.startSection("moving");

        if (p_82448_1_.dimension == 1)
        {
            ChunkCoordinates chunkcoordinates;

            if (p_82448_2_ == 1)
            {
                chunkcoordinates = p_82448_4_.getSpawnPoint();
            }
            else
            {
                chunkcoordinates = p_82448_4_.getEntrancePortalLocation();
            }

            d0 = (double)chunkcoordinates.posX;
            d1 = (double)chunkcoordinates.posZ;

            if (p_82448_1_.isEntityAlive())
            {
                p_82448_3_.updateEntityWithOptionalForce(p_82448_1_, false);
            }
        }

        p_82448_3_.theProfiler.endSection();

        if (p_82448_2_ != 1)
        {
            p_82448_3_.theProfiler.startSection("placing");
            d0 = (double)MathHelper.clamp_int((int)d0, -29999872, 29999872);
            d1 = (double)MathHelper.clamp_int((int)d1, -29999872, 29999872);

            if (p_82448_1_.isEntityAlive())
            {
                teleporter.placeInPortal(p_82448_1_, d3, d4, d5, f);
                p_82448_4_.spawnEntityInWorld(p_82448_1_);
                p_82448_4_.updateEntityWithOptionalForce(p_82448_1_, false);
            }

            p_82448_3_.theProfiler.endSection();
        }

        p_82448_1_.setWorld(p_82448_4_);
    }
}
