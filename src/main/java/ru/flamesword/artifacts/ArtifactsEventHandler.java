package ru.flamesword.artifacts;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.Random;


public class ArtifactsEventHandler {

    private static Random random = new Random();

    @SubscribeEvent
    public void onMobDeath(LivingDeathEvent event) {
        if ((event.entityLiving instanceof EntityMob || event.entityLiving.getClass().getName().toLowerCase().contains("custom")) && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.source.getEntity();
            EntityLiving killedMob = (EntityLiving) event.entityLiving;

            if (killedMob.getMaxHealth() <= 10 || ArtifactsUtils.entityIsFromSpawner(killedMob)) {
                //System.out.println("Skip entity: " + killedMob.toString());
                return;
            }

            int level = 0;
            if (killedMob.getMaxHealth() <= 20) {
                level = 1;
            } else if (killedMob.getMaxHealth() > 20 && killedMob.getMaxHealth() <= 100) {
                level = 2;
            } else if (killedMob.getMaxHealth() > 100 && killedMob.getMaxHealth() <= 500) {
                level = 3;
            } else if (killedMob.getMaxHealth() > 500) {
                level = 4;
            }

            if (level < 4) {
                if (Math.random() <= 0.05) {
                    level++;
                }
            }

            // Вероятность выпадения зависит от хп моба
            // 300 сделать зависимой от левела
            if (ArtifactsUtils.randomBetween(0, 300) <= level) {
                if (!killedMob.worldObj.isRemote) {
                    // Сила предмета так же зависит от хп моба
                    String mobName = killedMob.getCommandSenderName();
                    if (StringUtils.isNullOrEmpty(mobName)) {
                        mobName = StatCollector.translateToLocal("tooltip.item.unknownmob");
                    }
                    ArtifactsUtils.createRandomArtifact((short) ArtifactsUtils.randomBetween(1, level), mobName, player, (int) killedMob.posX, (int) killedMob.posY, (int) killedMob.posZ);
                    //EntityItem entityAftifact = new EntityItem(killedMob.worldObj, killedMob.posX, killedMob.posY, killedMob.posZ, aftifact);
                    //event.entity.worldObj.spawnEntityInWorld(entityAftifact);
                    player.addChatMessage(new ChatComponentTranslation("event.artifact.drop", mobName));
                }
            }
        }
    }

    @SubscribeEvent
    public void onTooltipItem(ItemTooltipEvent event) {
        if (event.itemStack.getTagCompound() != null && event.itemStack.getTagCompound().getShort("Indicator") > 0) {
            if (event.itemStack.getTagCompound().getShort("Indicator") == 1) {
                event.toolTip.add("");
                event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.uncommonIndicator);
                event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.uncommonIndicatorLore);
            }
            if (event.itemStack.getTagCompound().getShort("Indicator") == 2) {
                event.toolTip.add("");
                event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.rareIndicator);
                event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.rareIndicatorLore);
            }
            if (event.itemStack.getTagCompound().getShort("Indicator") == 3) {
                event.toolTip.add("");
                event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.legendaryIndicator);
                event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.legendaryIndicatorLore);
            }
            if (event.itemStack.getTagCompound().getShort("Indicator") == 4) {
                event.toolTip.add("");
                event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.artifactIndicator);
                event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.artifactIndicatorLore);
            }
        } else {
            if (event.itemStack.getItem() != null) {
                if (ConfigHelper.uncommonItems.contains(String.valueOf(Item.getIdFromItem(event.itemStack.getItem())))) {
                    event.toolTip.add("");
                    event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.uncommonIndicator);
                    event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.uncommonIndicatorLore);
                }
                if (ConfigHelper.rareItems.contains(String.valueOf(Item.getIdFromItem(event.itemStack.getItem())))) {
                    event.toolTip.add("");
                    event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.rareIndicator);
                    event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.rareIndicatorLore);
                }
                if (ConfigHelper.legendaryItems.contains(String.valueOf(Item.getIdFromItem(event.itemStack.getItem())))) {
                    event.toolTip.add("");
                    event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.legendaryIndicator);
                    event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.legendaryIndicatorLore);
                }
                if (ConfigHelper.artifactItems.contains(String.valueOf(Item.getIdFromItem(event.itemStack.getItem())))) {
                    event.toolTip.add("");
                    event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.artifactIndicator);
                    event.toolTip.add(ru.flamesword.ordinaryores.util.ConfigHelper.artifactIndicatorLore);
                }
            }
        }

        if (event.itemStack.getTagCompound() != null) {
            if (event.itemStack.getTagCompound().getString("IndicatorLore") != "") {
                String lore = "";
                if (event.itemStack.getTagCompound().getString("IndicatorLore").equals(ArtifactsUtils.FROM_CHEST)) {
                    lore = "§8" + StatCollector.translateToLocal("tooltip.item.lore.chest");
                } else {
                    lore = "§8" + event.itemStack.getTagCompound().getString("IndicatorLore") + " " + StatCollector.translateToLocal("tooltip.item.lore");
                }

                event.toolTip.add(lore);
            }
        }
    }
}
