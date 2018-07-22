package ru.flamesword.ordinaryores.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.items.ItemRegistry;

public class PlayerEventHandler {

    @SubscribeEvent
    public void onPickupXP(PlayerPickupXpEvent event) {

        EntityPlayer player = event.entityPlayer;

        if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() != null && player.inventory.getCurrentItem().getItem() == ItemRegistry.catacombsword) {
            player.addExperience(event.orb.xpValue);
        }
    }
}
