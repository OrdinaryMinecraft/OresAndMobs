package ru.flamesword.ordinaryores.handlers;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import ru.flamesword.ordinaryores.entities.*;
import ru.flamesword.ordinaryores.items.ItemRegistry;
import ru.flamesword.ordinaryores.items.dragonic.ItemDragonicBow;
import ru.flamesword.ordinaryores.items.dragonic.ItemDragonicBowCharged;
import ru.flamesword.ordinaryores.util.EntityUtils;
import ru.flamesword.ordinaryores.util.WorldUtils;

import java.util.Random;

public class EntitySpawnHandler {

    private final Random random = new Random();


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onEntityFirstSpawn(EntityJoinWorldEvent event) {
        if (event.world.isRemote) {
            return;
        }
        if (event.entity instanceof EntityPlayer) {
            return;
        }
        if (event.entity instanceof EntityCreature) {
            EntityCreature creature = (EntityCreature) event.entity;
            if (EntityUtils.entityHasNoSpawnTime(creature)) {
                EntityUtils.writeEntitySpawnTime(creature);
            } else {
                return;
            }

            if (event.entity instanceof EntityBear || event.entity instanceof EntityForestGuard) {
                if (event.entity.posY < 50) {
                    int x = (int) event.entity.posX;
                    int z = (int) event.entity.posZ;
                    int newY = event.world.getTopSolidOrLiquidBlock(x, z) + 1;
                    ((EntityMob) event.entity).setPositionAndUpdate(x, newY, z);
                }
            }
        }

    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onMobSpawn(EntityJoinWorldEvent event) {
        if (event.world.isRemote) {
            return;
        }
        if (event.entity instanceof EntityCreature && EntityUtils.entityIsNotNew((EntityCreature) event.entity)) {
            return;
        }

        boolean isColdBiome = false;
        for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SNOWY)) {
            if (biome == event.world.getBiomeGenForCoords((int) event.entity.posX, (int) event.entity.posZ)) {
                isColdBiome = true;
                break;
            }
        }
        if (event.entity.getClass().equals(EntityZombie.class)) {
            if (isColdBiome) {
                WorldUtils.unloadEntity(event.entity);
                EntityIceElemental iceElemental = new EntityIceElemental(event.world);
                iceElemental.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
                event.world.spawnEntityInWorld(iceElemental);
                //event.setCanceled(true);
            } else {
                if (Math.random() <= 0.1) {
                    double random = Math.random();
                    if (random < 0.5) {
                        WorldUtils.unloadEntity(event.entity);
                        EntityGhoul ghoul = new EntityGhoul(event.world);
                        ghoul.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
                        event.world.spawnEntityInWorld(ghoul);
                    } else {
                        WorldUtils.unloadEntity(event.entity);
                        EntityGhost ghost = new EntityGhost(event.world);
                        ghost.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
                        event.world.spawnEntityInWorld(ghost);
                    }
                    //event.setCanceled(true);
                }
            }
        } else if (event.entity.getClass().equals(EntitySkeleton.class)) {
            if (isColdBiome) {
                if (Math.random() <= 0.8) {
                    WorldUtils.unloadEntity(event.entity);
                    EntityZigomoreSkeleton zigomore = new EntityZigomoreSkeleton(event.world);
                    zigomore.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
                    zigomore.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
                    event.world.spawnEntityInWorld(zigomore);
                    //event.setCanceled(true);
                }
            } else {
                if (Math.random() <= 0.05) {
                    WorldUtils.unloadEntity(event.entity);
                    EntityEnderSkeleton skeleton = new EntityEnderSkeleton(event.world);
                    skeleton.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
                    skeleton.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
                    event.world.spawnEntityInWorld(skeleton);
                    //event.setCanceled(true);
                }
            }
        } else if (event.entity.getClass().equals(EntityCreeper.class)) {
            if (Math.random() <= 0.05) {
                WorldUtils.unloadEntity(event.entity);
                EntityEnderCreeper creeper = new EntityEnderCreeper(event.world);
                creeper.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
                event.world.spawnEntityInWorld(creeper);
                //event.setCanceled(true);
            }
        } else if (event.entity instanceof EntityArrow) {
            EntityArrow arrow = (EntityArrow) event.entity;
            if (arrow.shootingEntity instanceof EntityPlayer) {
                EntityPlayer archer = (EntityPlayer) arrow.shootingEntity;
                if (event.entity.getClass().getName().equals(EntityArrow.class.getName()) || event.entity.getClass().getName().equals("imc.entities.EntityIMCArrow")) {
                    if (archer.getHeldItem() != null) {

                        boolean isFrostBow = false;
                        if (archer.getHeldItem().getItem() instanceof ItemDragonicBow) {
                            boolean charged = false;
                            if (archer.getHeldItem().getItem() instanceof ItemDragonicBowCharged) {
                                charged = true;
                            }
                            double damageBonus = 0.1;
                            if (charged) {
                                damageBonus = 0.1 + ((double) ((ItemDragonicBowCharged)archer.getHeldItem().getItem()).getBonusValue(archer.getHeldItem()) * 0.01);
                                arrow.setFire(10);
                            }
                            arrow.setDamage(arrow.getDamage() * ((double) 1 + damageBonus));
                            // + 50% range
                            arrow.motionX *= 1.0F + 50 / 100F;
                            arrow.motionY *= 1.0F + 50 / 100F;
                            arrow.motionZ *= 1.0F + 50 / 100F;

                        } else if (archer.getHeldItem().getItem() == ItemRegistry.frostbow) {
                            // + 5% damage
                            isFrostBow = true;
                            arrow.setDamage(arrow.getDamage() * 1.05);
                        }

                        int countFrostArrows = 0;
                        for (ItemStack s : archer.inventory.mainInventory)
                        {
                            if (s != null && s.getItem() == ItemRegistry.frostarrow) {
                                countFrostArrows = countFrostArrows + s.stackSize;
                            }
                        }

                        if (countFrostArrows > 0 || isFrostBow) {
                            EntityFrostArrow frostArrow = new EntityFrostArrow(arrow.worldObj, archer, 0);
                            frostArrow.setDamage(arrow.getDamage());
                            frostArrow.posX = arrow.posX + 0.1 + random.nextFloat() * (-0.1 - 0.1);
                            frostArrow.posY = arrow.posY + 0.1 + random.nextFloat() * (-0.1 - 0.1);
                            frostArrow.posZ = arrow.posZ + 0.1 + random.nextFloat() * (-0.1 - 0.1);
                            frostArrow.rotationPitch = arrow.rotationPitch;
                            frostArrow.rotationYaw = arrow.rotationYaw;
                            frostArrow.motionX = arrow.motionX + 0.1 + random.nextFloat() * (-0.1 - 0.1);
                            frostArrow.motionY = arrow.motionY + 0.1 + random.nextFloat() * (-0.1 - 0.1);
                            frostArrow.motionZ = arrow.motionZ + 0.1 + random.nextFloat() * (-0.1 - 0.1);
                            frostArrow.setIsCritical(arrow.getIsCritical());

                            if (!archer.capabilities.isCreativeMode && !isFrostBow) {
                                archer.inventory.consumeInventoryItem(ItemRegistry.frostarrow);
                                archer.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 1));
                            }

                            if (arrow.isEntityAlive()) {
                                WorldUtils.unloadEntity(arrow);
                            }

                            arrow.worldObj.spawnEntityInWorld(frostArrow);
                        }
                    }
                }
            }
        }
    }
}
