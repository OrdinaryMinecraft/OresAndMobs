package ru.flamesword.ordinaryores.handlers;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.entities.EntityMinionSprout;
import ru.flamesword.ordinaryores.entities.EntitySprout;
import ru.flamesword.ordinaryores.items.ItemHoeOfNature;
import ru.flamesword.ordinaryores.items.ItemRegistry;

import java.util.Objects;

public class PlayerEventHandler {

    @SubscribeEvent
    public void onPickupXP(PlayerPickupXpEvent event) {

        EntityPlayer player = event.entityPlayer;

        if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() != null && player.inventory.getCurrentItem().getItem() == ItemRegistry.catacombsword) {
            player.addExperience(event.orb.xpValue);
        }
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        ItemStack item = event.entityPlayer.getHeldItem();
        if (Objects.nonNull(item) && Objects.nonNull(item.getItem())) {
            if (item.getItem() instanceof ItemHoeOfNature) {

                World world = event.world;
                int x = event.x;
                int y = event.y;
                int z = event.z;
                EntityPlayer player = event.entityPlayer;

                int damage = item.getItemDamage();

                if (item.getMaxDamage() - damage > 1) {
                    if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                        Block block = event.world.getBlock(event.x, event.y, event.z);
                        if (Objects.nonNull(block)) {
                            if (block instanceof IPlantable) {
                                Block soil = world.getBlock(x, y - 1, z);
                                if (!soil.isAir(world, x, y - 1, z) && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (IPlantable) block)) {
                                    ItemDye.applyBonemeal(new ItemStack(Items.dye, 1, 15), world, x, y, z, player);
                                    world.playAuxSFX(2005, x, y, z, 0);
                                }

                                //item.damageItem(1, event.entityPlayer);
                                if (!player.capabilities.isCreativeMode) {
                                    if (item.isItemStackDamageable()) {
                                        item.setItemDamage(damage + 1);
                                    }
                                }
                            }
                        }
                    }

                    if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
                        EntityMinionSprout minion = new EntityMinionSprout(world);
                        minion.setPosition(x, y, z);
                        world.spawnEntityInWorld(minion);

                        //item.damageItem(1, event.entityPlayer);
                        if (!player.capabilities.isCreativeMode) {
                            if (item.isItemStackDamageable()) {
                                item.setItemDamage(damage + 1);
                            }
                        }
                    }

                }
            }
        }
    }
}
