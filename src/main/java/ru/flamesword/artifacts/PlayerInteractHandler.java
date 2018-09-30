package ru.flamesword.artifacts;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.Random;


public class PlayerInteractHandler {

    private static Random random = new Random();

    @SubscribeEvent
    public void onAnvilRepair(LivingDeathEvent event) {
        if (event.entityLiving instanceof EntityMob && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.source.getEntity();
            EntityMob killedMob = (EntityMob) event.entityLiving;
            int level = 0;
            if (killedMob.getMaxHealth() < 20) {
                level = 1;
            }
            if (killedMob.getMaxHealth() >= 20 && killedMob.getMaxHealth() < 50) {
                level = 2;
            }
            if (killedMob.getMaxHealth() >= 50 && killedMob.getMaxHealth() < 100) {
                level = 3;
            }
            if (killedMob.getMaxHealth() >= 100) {
                level = 4;
            }
            // Вероятность выпадения зависит от хп моба
            if (Math.random() <= 0.01 * level) {
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
