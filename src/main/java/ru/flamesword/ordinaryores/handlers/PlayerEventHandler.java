package ru.flamesword.ordinaryores.handlers;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import ru.flamesword.ordinaryores.entities.EntitySentinelTree;
import ru.flamesword.ordinaryores.items.ItemAnimalCrate;
import ru.flamesword.ordinaryores.items.ItemHoeOfNature;
import ru.flamesword.ordinaryores.items.ItemRegistry;
import ru.flamesword.ordinaryores.util.WorldUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class PlayerEventHandler {

    @SubscribeEvent
    public void onPickupXP(PlayerPickupXpEvent event) {

        EntityPlayer player = event.entityPlayer;

        if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() != null && player.inventory.getCurrentItem().getItem() == ItemRegistry.catacombsword) {
            player.addExperience(event.orb.xpValue);
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onInteract(PlayerInteractEvent event) {
        if (event.world.isRemote) {
            return;
        }

        ItemStack item = event.entityPlayer.getHeldItem();
        if (Objects.nonNull(item) && Objects.nonNull(item.getItem())) {

            World world = event.world;
            int x = event.x;
            int y = event.y;
            int z = event.z;
            EntityPlayer player = event.entityPlayer;
            int damage = item.getItemDamage();

            if (item.getItem() instanceof ItemHoeOfNature) {
                if (item.getMaxDamage() - damage > 10) {
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
                                        item.setItemDamage(damage + 10);
                                    }
                                }
                            }
                        }
                    }

                    if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
                        int playerLevel = event.entityPlayer.experienceLevel;

                        if (!player.capabilities.isCreativeMode) {
                            if (playerLevel < 10) {
                                player.addChatMessage(new ChatComponentTranslation("message.tree.notenoughlevel"));
                                return;
                            }

                            event.entityPlayer.addExperienceLevel(-10);

                            if (item.isItemStackDamageable()) {
                                item.setItemDamage(damage + 10);
                            }
                        }

                        EntitySentinelTree minion = new EntitySentinelTree(world);
                        minion.setPosition(x + 0.5, y + 1, z + 0.5);
                        world.spawnEntityInWorld(minion);

                        world.playAuxSFX(2005, x, y + 1, z, 0);
                        world.playAuxSFX(2005, x, y + 2, z, 0);
                        world.playAuxSFX(2005, x, y + 3, z, 0);
                    }
                }
            } else if (item.getItem() instanceof ItemAnimalCrate) {
                ItemAnimalCrate itemAnimalCrate = (ItemAnimalCrate) item.getItem();
                if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                    if (itemAnimalCrate.crateHasAnimal(item)) {
                        // TODO: check for space
                        try {
                            Class animalClass = Class.forName(itemAnimalCrate.getAnimalClass(item));
                            EntityAnimal animal = (EntityAnimal) animalClass.getDeclaredConstructor(World.class).newInstance(world);
                            animal.readEntityFromNBT((NBTTagCompound) JsonToNBT.func_150315_a(itemAnimalCrate.getAnimalNBT(item).replace("\"\"", "")));
                            animal.setPosition(x, y + 1, z);
                            //animal.setWorld(world);
                            world.spawnEntityInWorld(animal);
                            itemAnimalCrate.setCrateEmpty(item);
                        } catch (ClassNotFoundException e) {
                            System.out.println("Can't find class: " + itemAnimalCrate.getAnimalClass(item));
                        } catch (NBTException e) {
                            System.out.println("Can't load entity from NBT: " + e.getMessage());
                        } catch (IllegalAccessException e) {
                            System.out.println("Error on load entity from NBT (IllegalAccessException): " + e.getMessage());
                        } catch (InstantiationException e) {
                            System.out.println("Error on load entity from NBT (InstantiationException): " + e.getMessage());
                        } catch (NoSuchMethodException e) {
                            System.out.println("Can't find default constructor (with World parameter): " + itemAnimalCrate.getAnimalClass(item));
                        } catch (InvocationTargetException e) {
                            System.out.println("Error on load entity from NBT (InvocationTargetException): " + e.getMessage());
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onEntityInteract(EntityInteractEvent event) {
        if (event.entityPlayer.worldObj.isRemote) {
            return;
        }

        ItemStack item = event.entityPlayer.getHeldItem();
        if (Objects.nonNull(item) && Objects.nonNull(item.getItem())) {

            Entity entity = event.target;

            if (item.getItem() instanceof ItemAnimalCrate) {
                ItemAnimalCrate itemAnimalCrate = (ItemAnimalCrate) item.getItem();
                if (!itemAnimalCrate.crateHasAnimal(item)) {
                    if (entity instanceof EntityAnimal) {
                        EntityAnimal animal = (EntityAnimal) entity;
                        itemAnimalCrate.putAnimalInCrate(animal, item);
                        WorldUtils.unloadEntity(animal);
                    }
                }
            }
        }
    }
}
