package ru.flamesword.ordinaryores.event;

import net.minecraft.entity.player.EntityPlayer;
import ru.flamesword.ordinaryores.model.WorldPoint;

public interface IPlayerEvent {

    Boolean eventCanStart(EntityPlayer player);
    WorldPoint findEventPoint(EntityPlayer player);
    Boolean startEvent(WorldPoint worldPoint, EntityPlayer player);
    void showEventMessage(WorldPoint worldPoint, EntityPlayer player);

    Long getEventPeriod();
    void setEventLastSpawn(Long time);
    Long getEventLastSpawn();
    void setEventLastPlayer(String lastPlayerName);
    String getEventLastPlayer();
}
