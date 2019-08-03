package ru.flamesword.ordinaryores.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.common.BiomeDictionary;
import ru.flamesword.ordinaryores.entities.EntityBear;
import ru.flamesword.ordinaryores.entities.EntityHerobrine;
import ru.flamesword.ordinaryores.model.WorldPoint;
import ru.flamesword.ordinaryores.util.WorldUtils;

import java.util.Random;

// Grizzly event
// 2/hour
public class GrizzlyEvent implements IPlayerEvent {

    private Random random = new Random();

    private static final long event_grizzly_period = 36000;
    private static long event_grizzly_last_spawn = 0;
    private static String event_grizzly_last_player = "";

    @Override
    public Boolean eventCanStart(EntityPlayer player) {
        if (!player.capabilities.isCreativeMode && player.worldObj.provider.isSurfaceWorld() && player.posY > 50) {
            if (!player.getDisplayName().equals(getEventLastPlayer()) && Math.random() <= 0.2) {
                BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(player.worldObj.getBiomeGenForCoords((int)player.posX, (int)player.posZ));
                if (types != null) {
                    for (BiomeDictionary.Type type : types) {
                        if (type == BiomeDictionary.Type.FOREST) {
                            return true;
                        }
                    }
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
            x = random.nextInt(32) + (int) player.posX - 16;
            y = player.worldObj.getTopSolidOrLiquidBlock(x, z) + 1;
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
            EntityBear grizzly = new EntityBear(worldPoint.world);
            grizzly.setPosition(worldPoint.x, worldPoint.y, worldPoint.z);
            worldPoint.world.spawnEntityInWorld(grizzly);

            if (WorldUtils.entityExist(EntityBear.class, worldPoint.world, worldPoint.x, worldPoint.y, worldPoint.z)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void showEventMessage(WorldPoint worldPoint, EntityPlayer player) {
        if (player != null) {
            player.addChatMessage(new ChatComponentTranslation("event.grizzly.start"));
            System.out.println("------------------------");
            System.out.println("GRIZZLY EVENT. Player: " + player.getDisplayName());
            System.out.println("Player coordinates: " + player.posX + " " + player.posY + " " + player.posZ);
            System.out.println("Player world: " + player.worldObj.provider.getDimensionName() + " "  + player.worldObj.provider.dimensionId);
            System.out.println("Grizzly coordinates: " + worldPoint.x + " " + worldPoint.y + " " + worldPoint.z);
            System.out.println("Dist: " + Math.sqrt(Math.pow((worldPoint.x - player.posX), 2) + Math.pow((worldPoint.z - player.posZ), 2)));
            System.out.println("Time: " + player.worldObj.getWorldTime());
            System.out.println("------------------------");
        }
    }

    @Override
    public Long getEventPeriod() {
        return event_grizzly_period;
    }

    @Override
    public void setEventLastSpawn(Long time) {
        event_grizzly_last_spawn = time;
    }

    @Override
    public Long getEventLastSpawn() {
        return event_grizzly_last_spawn;
    }

    @Override
    public void setEventLastPlayer(String lastPlayerName) {
        event_grizzly_last_player = lastPlayerName;
    }

    @Override
    public String getEventLastPlayer() {
        return event_grizzly_last_player;
    }
}
