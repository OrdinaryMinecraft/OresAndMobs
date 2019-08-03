package ru.flamesword.ordinaryores.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import ru.flamesword.ordinaryores.entities.EntityHerobrine;
import ru.flamesword.ordinaryores.entities.EntityRedDragon;
import ru.flamesword.ordinaryores.event.*;
import ru.flamesword.ordinaryores.model.WorldPoint;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.util.WorldUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ServiceLoader;

public class EventsEventHandler {

    private boolean initialized = false;
    private boolean locked = false;

    private List<IPlayerEvent> playerEvents = new ArrayList<IPlayerEvent>();

    //final int [] negativeEffects = {2, 4, 9, 15, 17, 18, 19, 20};

    private void initialize() {
        playerEvents.add(new BanditPlayerEvent());
        playerEvents.add(new DragonPlayerEvent());
        playerEvents.add(new HerobrineEvent());
        playerEvents.add(new GrizzlyEvent());
    }

    @SubscribeEvent
    public void periodicalPlayerEvent(TickEvent.PlayerTickEvent event) {
        if (!initialized) {
            initialize();
            initialized = true;
        }

        EntityPlayer player = event.player;
        if (event.phase != TickEvent.Phase.START || player.worldObj.isRemote) {
            return;
        }

        if (!locked) {
            locked = true;

            try {
                // Closing portals
                if (player.worldObj.getWorldTime() % 40 == 0) {
                    if (player.motionX != 0 || player.motionY != 0 || player.motionZ != 0 ) {
                        WorldUtils.ckeckAndRemovePortals(player, null, null);
                        WorldUtils.checkAndRemoveFire(player);
                    }
                }

                for (IPlayerEvent playerEvent : playerEvents) {
                    if (player.worldObj.getWorldTime() % playerEvent.getEventPeriod() == 0 && player.worldObj.getWorldTime() != playerEvent.getEventLastSpawn()) {
                        if (playerEvent.eventCanStart(player)) {
                            WorldPoint worldPoint = playerEvent.findEventPoint(player);
                            if (playerEvent.startEvent(worldPoint, player)) {
                                playerEvent.setEventLastSpawn(player.worldObj.getWorldTime());
                                playerEvent.setEventLastPlayer(player.getDisplayName());
                                playerEvent.showEventMessage(worldPoint, player);
                                break;
                            } else {
                                System.out.println("------------------------");
                                System.out.println("EVENT NOT STARTED. Player: " + player.getDisplayName());
                                System.out.println("Player coordinates: " + player.posX + " " + player.posY + " " + player.posZ);
                                System.out.println("Player world: " + player.worldObj.provider.getDimensionName() + " "  + player.worldObj.provider.dimensionId);
                                System.out.println("------------------------");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error in playerEventThread: " + e.getMessage());
                e.printStackTrace();
            }

            locked = false;
        }
    }
}
