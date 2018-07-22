package ru.flamesword.ordinaryores.items.dragonic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.util.ConfigHelper;

import java.util.List;

public class ItemDragonicToolCharged extends ItemDragonicTool {

    public String[] levelArray = {" I ", " II ", " III ", " IV ", " V "};

    public ItemDragonicToolCharged() {
        super();
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:DragonicToolCharged");
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            int level = getBonusLevel(itemstack);
            if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemDragonicToolCharged)
            {
                player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20, level));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
        int level = getBonusLevel(stack);
        list.add(ConfigHelper.artifactIndicator);
        list.add(StatCollector.translateToLocal("tooltip.item.dragonic.charged"));
        list.add(ConfigHelper.digSpeedEffectName + levelArray[level] + ConfigHelper.effectIndicator);
        list.add(StatCollector.translateToLocal("item.dragonictool.tooltip.charged"));
        list.add(StatCollector.translateToLocal("item.dragonictool.tooltip"));
    }

    public static int getBonusLevel(ItemStack itemstack) {
        int blocksDigged = getBlocksDigged(itemstack);
        int level = blocksDigged / 10000;
        if (level > 4) {
            level = 4;
        }
        return level;
    }
}
