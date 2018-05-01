package ru.flamesword.ordinaryores.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class PlayerEventHandler {

    @SubscribeEvent
    public void onPickupXP(PlayerPickupXpEvent event) {

        EntityPlayer player = event.entityPlayer;

        if (player.inventory.getCurrentItem().getItem() == OrdinaryOresBase.catacombsword) {
            System.out.println(event.orb.xpValue);
            player.addExperience(event.orb.xpValue);
        }
    }
}
