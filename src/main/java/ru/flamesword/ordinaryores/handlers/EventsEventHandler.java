package ru.flamesword.ordinaryores.handlers;

import cpw.mods.fml.client.config.ConfigGuiType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import ru.flamesword.ordinaryores.entities.EntityBandit;
import ru.flamesword.ordinaryores.entities.EntityBanditLeader;
import ru.flamesword.ordinaryores.entities.EntityHerobrine;
import ru.flamesword.ordinaryores.entities.EntityRedDragon;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.util.WorldUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventsEventHandler {

    private final Random random = new Random();


    final int [] negativeEffects = {2, 4, 9, 15, 17, 18, 19, 20};

    @SubscribeEvent
    public void periodicalPlayerEvent(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (event.phase != TickEvent.Phase.START || player.worldObj.isRemote) {
            return;
        }

        // Closing portals
        if (player.worldObj.getWorldTime() % 40 == 0) {
            if (player.motionX != 0 || player.motionY != 0 || player.motionZ != 0 ) {
                WorldUtils.ckeckAndRemovePortals(player, null, null);
                WorldUtils.checkAndRemoveFire(player);
            }
        }

        // Bandits event
        // 2/hour
        if (player.worldObj.getWorldTime() % ConfigHelper.event_bandit_period == 0 && player.worldObj.getWorldTime() != ConfigHelper.event_bandit_last_spawn) {
            ConfigHelper.event_bandit_last_spawn = player.worldObj.getWorldTime();
            if (!player.capabilities.isCreativeMode && !player.capabilities.isFlying && player.worldObj.provider.isSurfaceWorld() && player.posY > 50) {
                if (!player.getDisplayName().equals(ConfigHelper.event_bandit_last_player) && Math.random() <= 0.20) {
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
                    if (pointFound) {
                        player.addChatMessage(new ChatComponentTranslation("event.bandit.start"));
                        System.out.println("------------------------");
                        System.out.println("BANDIT EVENT. Player: " + player.getDisplayName());
                        System.out.println("Player coordinates: " + player.posX + " " + player.posY + " " + player.posZ);
                        System.out.println("Player level: " + WorldUtils.getPlayerLevel(player));
                        System.out.println("Player world: " + player.worldObj.provider.getDimensionName() + " "  + player.worldObj.provider.dimensionId);
                        System.out.println("Bandit coordinates: " + x + " " + y + " " + z);
                        System.out.println("Dist: " + Math.sqrt(Math.pow((x - player.posX), 2) + Math.pow((z - player.posZ), 2)));
                        System.out.println("Bandit lightlevel: " + player.worldObj.getLightBrightness(x, y, z));
                        System.out.println("------------------------");
                        int count = 1;
                        if (WorldUtils.getPlayerLevel(player) > 10) {
                            count = WorldUtils.getPlayerLevel(player) / 5;
                        }
                        if (count > 5) {
                            count = 5;
                        }
                        for (int i = 0; i < count; i++) {
                            EntityBandit bandit = new EntityBandit(player.worldObj);
                            bandit.setPosition(x, y, z);
                            bandit.addRandomEquipment();
                            player.worldObj.spawnEntityInWorld(bandit);
                            bandit.setRevengeTarget(player);
                            bandit.onUpdate();
                        }
                        if (WorldUtils.getPlayerLevel(player) > 20) {
                            EntityBanditLeader banditLeader = new EntityBanditLeader(player.worldObj);
                            banditLeader.setPosition(x, y, z);
                            banditLeader.addRandomEquipment();
                            player.worldObj.spawnEntityInWorld(banditLeader);
                            banditLeader.setRevengeTarget(player);
                            banditLeader.onUpdate();
                        }
                    }
                    return;
                }
                if (player.getDisplayName().equals(ConfigHelper.event_bandit_last_player)) {
                    ConfigHelper.event_bandit_last_player = "";
                }
            }
        }

        // Dragon event
        // 1/hour
        if (player.worldObj.getWorldTime() % ConfigHelper.event_dragon_period == 0 && player.worldObj.getWorldTime() != ConfigHelper.event_dragon_last_spawn) {
            ConfigHelper.event_dragon_last_spawn = player.worldObj.getWorldTime();
            if (!player.capabilities.isCreativeMode &&
                    !player.capabilities.isFlying &&
                    player.worldObj.provider.isSurfaceWorld() &&
                    player.worldObj.provider.dimensionId != 3 &&
                    player.posY > 50) {
                if (!player.getDisplayName().equals(ConfigHelper.event_dragon_last_player) && Math.random() <= 0.10) {
                    if (WorldUtils.getPlayerLevel(player) > 20) {
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
                                    pointFound = true;
                                }
                            }
                            tryCount++;
                            if (tryCount > 100) {
                                break;
                            }
                        }
                        if (pointFound) {
                            MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentTranslation("event.dragon.start", player.getDisplayName()));
                            System.out.println("------------------------");
                            System.out.println("DRAGON EVENT. Player: " + player.getDisplayName());
                            System.out.println("Player coordinates: " + player.posX + " " + player.posY + " " + player.posZ);
                            System.out.println("Player level: " + WorldUtils.getPlayerLevel(player));
                            System.out.println("Player world: " + player.worldObj.provider.getDimensionName() + " "  + player.worldObj.provider.dimensionId);
                            System.out.println("Dragon coordinates: " + x + " " + y + " " + z);
                            System.out.println("Dist: " + Math.sqrt(Math.pow((x - player.posX), 2) + Math.pow((z - player.posZ), 2)));
                            System.out.println("Time: " + player.worldObj.getWorldTime());
                            System.out.println("------------------------");
                            EntityRedDragon dragon = new EntityRedDragon(player.worldObj);
                            dragon.setPosition(x, y, z);
                            player.worldObj.spawnEntityInWorld(dragon);
                            dragon.setRevengeTarget(player);
                            dragon.onUpdate();
                        }
                        return;
                    }
                }
                if (player.getDisplayName().equals(ConfigHelper.event_dragon_last_player)) {
                    ConfigHelper.event_dragon_last_player = "";
                }
            }
        }

        // Herobrine event
        // 1/2hour
        if (player.worldObj.getWorldTime() % ConfigHelper.event_herobrine_period == 0 && player.worldObj.getWorldTime() != ConfigHelper.event_herobrine_last_spawn) {
            ConfigHelper.event_herobrine_last_spawn = player.worldObj.getWorldTime();
            if (!player.capabilities.isCreativeMode &&
                    !player.capabilities.isFlying &&
                    player.worldObj.provider.dimensionId != 3) {
                if (!player.getDisplayName().equals(ConfigHelper.event_herobrine_last_player) && Math.random() <= 0.02) {
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
                                    pointFound = true;
                                }
                            }
                        }
                        tryCount++;
                        if (tryCount > 100) {
                            break;
                        }
                    }
                    if (pointFound) {
                        player.addChatMessage(new ChatComponentTranslation("event.herobrine.start"));
                        System.out.println("------------------------");
                        System.out.println("HEROBRINE EVENT. Player: " + player.getDisplayName());
                        System.out.println("Player coordinates: " + player.posX + " " + player.posY + " " + player.posZ);
                        System.out.println("Player world: " + player.worldObj.provider.getDimensionName() + " "  + player.worldObj.provider.dimensionId);
                        System.out.println("Herobrine coordinates: " + x + " " + y + " " + z);
                        System.out.println("Dist: " + Math.sqrt(Math.pow((x - player.posX), 2) + Math.pow((z - player.posZ), 2)));
                        System.out.println("Time: " + player.worldObj.getWorldTime());
                        System.out.println("------------------------");
                        EntityHerobrine herobrine = new EntityHerobrine(player.worldObj);
                        herobrine.setPosition(x, y, z);
                        player.worldObj.spawnEntityInWorld(herobrine);
                    }
                    return;
                }
                if (player.getDisplayName().equals(ConfigHelper.event_herobrine_last_player)) {
                    ConfigHelper.event_herobrine_last_player = "";
                }
            }
        }
    }
}
