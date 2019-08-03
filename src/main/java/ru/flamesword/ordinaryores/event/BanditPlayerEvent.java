package ru.flamesword.ordinaryores.event;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import ru.flamesword.artifacts.ArtifactsUtils;
import ru.flamesword.ordinaryores.entities.EntityBandit;
import ru.flamesword.ordinaryores.entities.EntityBanditLeader;
import ru.flamesword.ordinaryores.model.WorldPoint;
import ru.flamesword.ordinaryores.util.WorldUtils;

import java.util.Random;

// Bandits event
// 2/hour
public class BanditPlayerEvent implements IPlayerEvent {

    private Random random = new Random();

    private static final long event_bandit_period = 36000;
    private static long event_bandit_last_spawn = 0;
    private static String event_bandit_last_player = "";

    @Override
    public Boolean eventCanStart(EntityPlayer player) {
        if (!player.capabilities.isCreativeMode && player.worldObj.provider.isSurfaceWorld() && player.posY > 50) {
            if (!player.getDisplayName().equals(getEventLastPlayer()) && Math.random() <= 0.20) {
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

            // Distance between 2 points > 12
            if (Math.sqrt(Math.pow((x - player.posX), 2) + Math.pow((z - player.posZ), 2)) > 12) {
                if (player.worldObj.getBlock(x, y, z) == Blocks.air && player.worldObj.getBlock(x, y + 1, z) == Blocks.air) {
                    Material material = player.worldObj.getBlock(x, y - 1, z).getMaterial();
                    if (material != Material.carpet && material != Material.wood && material != Material.cloth) {
                        if (player.worldObj.getBlock(x, y - 1, z).isNormalCube() && WorldUtils.blockIsInShadow(player.worldObj, x, y, z)) {
                            worldPoint = new WorldPoint(player.worldObj, x, y, z);
                            pointFound = true;
                        }
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
            int count = 1;

            int playerLevel;
            if (player != null) {
                playerLevel = WorldUtils.getPlayerLevel(player);
            } else {
                playerLevel = ArtifactsUtils.randomBetween(1, 30);
            }

            if (playerLevel > 10) {
                count = playerLevel / 5;
            }
            if (count > 5) {
                count = 5;
            }

            for (int i = 0; i < count; i++) {
                EntityBandit bandit = new EntityBandit(worldPoint.world);
                bandit.setPosition(worldPoint.x, worldPoint.y, worldPoint.z);
                bandit.addRandomEquipment();
                worldPoint.world.spawnEntityInWorld(bandit);
                if (player != null) {
                    bandit.setRevengeTarget(player);
                }
                bandit.onUpdate();
            }
            if (playerLevel > 20) {
                EntityBanditLeader banditLeader = new EntityBanditLeader(worldPoint.world);
                banditLeader.setPosition(worldPoint.x, worldPoint.y, worldPoint.z);
                banditLeader.addRandomEquipment();
                worldPoint.world.spawnEntityInWorld(banditLeader);
                if (player != null) {
                    banditLeader.setRevengeTarget(player);
                }
                banditLeader.onUpdate();
            }

            if (WorldUtils.entityExist(EntityBandit.class, worldPoint.world, worldPoint.x, worldPoint.y, worldPoint.z)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void showEventMessage(WorldPoint worldPoint, EntityPlayer player) {
        if (player != null) {
            player.addChatMessage(new ChatComponentTranslation("event.bandit.start"));
            System.out.println("------------------------");
            System.out.println("BANDIT EVENT. Player: " + player.getDisplayName());
            System.out.println("Player coordinates: " + player.posX + " " + player.posY + " " + player.posZ);
            System.out.println("Player level: " + WorldUtils.getPlayerLevel(player));
            System.out.println("Player world: " + player.worldObj.provider.getDimensionName() + " "  + player.worldObj.provider.dimensionId);
            System.out.println("Bandit coordinates: " + worldPoint.x + " " + worldPoint.y + " " + worldPoint.z);
            System.out.println("Dist: " + Math.sqrt(Math.pow((worldPoint.x - player.posX), 2) + Math.pow((worldPoint.z - player.posZ), 2)));
            System.out.println("Bandit lightlevel: " + player.worldObj.getLightBrightness(worldPoint.x, worldPoint.y, worldPoint.z));
            System.out.println("------------------------");
        }
    }

    @Override
    public Long getEventPeriod() {
        return event_bandit_period;
    }

    @Override
    public void setEventLastSpawn(Long time) {
        event_bandit_last_spawn = time;
    }

    @Override
    public Long getEventLastSpawn() {
        return event_bandit_last_spawn;
    }

    @Override
    public void setEventLastPlayer(String lastPlayerName) {
        event_bandit_last_player = lastPlayerName;
    }

    @Override
    public String getEventLastPlayer() {
        return event_bandit_last_player;
    }
}
