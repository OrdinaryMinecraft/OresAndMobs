package ru.flamesword.ordinaryores.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import ru.flamesword.ordinaryores.entities.EntityHerobrine;
import ru.flamesword.ordinaryores.model.WorldPoint;
import ru.flamesword.ordinaryores.util.WorldUtils;

import java.util.Random;

// Herobrine event
// 1/2hour
public class HerobrineEvent implements IPlayerEvent {

    private Random random = new Random();

    private static final long event_herobrine_period = 144000;
    private static long event_herobrine_last_spawn = 0;
    private static String event_herobrine_last_player = "";
    
    @Override
    public Boolean eventCanStart(EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            if (!player.getDisplayName().equals(getEventLastPlayer()) && Math.random() <= 0.02) {
                return true;
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
            x = random.nextInt(32) + (int) player.posX - 16;
            y = random.nextInt(4) + (int) player.posY - 2;
            z = random.nextInt(32) + (int) player.posZ - 16;

            // Distance between 2 points > 8
            if (Math.sqrt(Math.pow((x - player.posX), 2) + Math.pow((z - player.posZ), 2)) > 8) {
                if (player.worldObj.getBlock(x, y, z) == Blocks.air && player.worldObj.getBlock(x, y + 1, z) == Blocks.air) {
                    if (player.worldObj.getBlock(x, y - 1, z).isNormalCube()) {
                        worldPoint = new WorldPoint(player.worldObj, x, y, z);
                        pointFound = true;
                    }
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
            EntityHerobrine herobrine = new EntityHerobrine(worldPoint.world);
            herobrine.setPosition(worldPoint.x, worldPoint.y, worldPoint.z);
            worldPoint.world.spawnEntityInWorld(herobrine);

            if (WorldUtils.entityExist(EntityHerobrine.class, worldPoint.world, worldPoint.x, worldPoint.y, worldPoint.z)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void showEventMessage(WorldPoint worldPoint, EntityPlayer player) {
        if (player != null) {
            player.addChatMessage(new ChatComponentTranslation("event.herobrine.start"));
            System.out.println("------------------------");
            System.out.println("HEROBRINE EVENT. Player: " + player.getDisplayName());
            System.out.println("Player coordinates: " + player.posX + " " + player.posY + " " + player.posZ);
            System.out.println("Player world: " + player.worldObj.provider.getDimensionName() + " "  + player.worldObj.provider.dimensionId);
            System.out.println("Herobrine coordinates: " + worldPoint.x + " " + worldPoint.y + " " + worldPoint.z);
            System.out.println("Dist: " + Math.sqrt(Math.pow((worldPoint.x - player.posX), 2) + Math.pow((worldPoint.z - player.posZ), 2)));
            System.out.println("Time: " + player.worldObj.getWorldTime());
            System.out.println("------------------------");
        }
    }

    @Override
    public Long getEventPeriod() {
        return event_herobrine_period;
    }

    @Override
    public void setEventLastSpawn(Long time) {
        event_herobrine_last_spawn = time;
    }

    @Override
    public Long getEventLastSpawn() {
        return event_herobrine_last_spawn;
    }

    @Override
    public void setEventLastPlayer(String lastPlayerName) {
        event_herobrine_last_player = lastPlayerName;
    }

    @Override
    public String getEventLastPlayer() {
        return event_herobrine_last_player;
    }
}
