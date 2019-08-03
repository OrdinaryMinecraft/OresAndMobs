package ru.flamesword.ordinaryores.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import ru.flamesword.ordinaryores.entities.EntityRedDragon;
import ru.flamesword.ordinaryores.model.WorldPoint;
import ru.flamesword.ordinaryores.util.WorldUtils;

import java.util.Random;

// Dragon event
// 1/hour
public class DragonPlayerEvent implements IPlayerEvent {

    private Random random = new Random();

    private static final long event_dragon_period = 72000;
    private static long event_dragon_last_spawn = 0;
    private static String event_dragon_last_player = "";

    @Override
    public Boolean eventCanStart(EntityPlayer player) {
        if (!player.capabilities.isCreativeMode && player.worldObj.provider.isSurfaceWorld() && player.worldObj.provider.dimensionId != 3 && player.posY > 50) {
            if (!player.getDisplayName().equals(getEventLastPlayer()) && Math.random() <= 0.05) {
                if (WorldUtils.getPlayerLevel(player) > 20) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public WorldPoint findEventPoint(EntityPlayer player) {
        WorldPoint worldPoint = null;

        boolean pointFound = false;
        int x = 0;
        int y = 0;
        int z = 0;
        int tryCount = 0;
        while (!pointFound) {
            x = random.nextInt(64) + (int) player.posX - 32;
            y = random.nextInt(64) + (int) player.posY + 16;
            z = random.nextInt(64) + (int) player.posZ - 32;

            // Distance between 2 points > 16
            if (Math.sqrt(Math.pow((x - player.posX), 2) + Math.pow((z - player.posZ), 2)) > 16) {
                if (player.worldObj.getBlock(x, y, z) == Blocks.air && player.worldObj.getBlock(x, y + 1, z) == Blocks.air) {
                    worldPoint = new WorldPoint(player.worldObj, x, y, z);
                    pointFound = true;
                }
            }
            tryCount++;
            if (tryCount > 100) {
                break;
            }
        }

        return worldPoint;
    }

    @Override
    public Boolean startEvent(WorldPoint worldPoint, EntityPlayer player) {
        if (worldPoint != null) {
            EntityRedDragon dragon = new EntityRedDragon(worldPoint.world);
            dragon.setPosition(worldPoint.x, worldPoint.y, worldPoint.z);
            worldPoint.world.spawnEntityInWorld(dragon);
            if (player != null) {
                dragon.setRevengeTarget(player);
            }
            dragon.onUpdate();

            if (WorldUtils.entityExist(EntityRedDragon.class, worldPoint.world, worldPoint.x, worldPoint.y, worldPoint.z)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void showEventMessage(WorldPoint worldPoint, EntityPlayer player) {
        if (player != null) {
            MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentTranslation("event.dragon.start", player.getDisplayName()));
            System.out.println("------------------------");
            System.out.println("DRAGON EVENT. Player: " + player.getDisplayName());
            System.out.println("Player coordinates: " + player.posX + " " + player.posY + " " + player.posZ);
            System.out.println("Player level: " + WorldUtils.getPlayerLevel(player));
            System.out.println("Player world: " + player.worldObj.provider.getDimensionName() + " "  + player.worldObj.provider.dimensionId);
            System.out.println("Dragon coordinates: " + worldPoint.x + " " + worldPoint.y + " " + worldPoint.z);
            System.out.println("Dist: " + Math.sqrt(Math.pow((worldPoint.x - player.posX), 2) + Math.pow((worldPoint.z - player.posZ), 2)));
            System.out.println("Time: " + player.worldObj.getWorldTime());
            System.out.println("------------------------");
        }
    }

    @Override
    public Long getEventPeriod() {
        return event_dragon_period;
    }

    @Override
    public void setEventLastSpawn(Long time) {
        event_dragon_last_spawn = time;
    }

    @Override
    public Long getEventLastSpawn() {
        return event_dragon_last_spawn;
    }

    @Override
    public void setEventLastPlayer(String lastPlayerName) {
        event_dragon_last_player = lastPlayerName;
    }

    @Override
    public String getEventLastPlayer() {
        return event_dragon_last_player;
    }
}
