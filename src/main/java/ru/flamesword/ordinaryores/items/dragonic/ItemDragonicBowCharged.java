package ru.flamesword.ordinaryores.items.dragonic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.util.ConfigHelper;

import java.util.List;

public class ItemDragonicBowCharged extends ItemDragonicBow {


    public ItemDragonicBowCharged() {
        super();
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:DragonicBowCharged");
        this.setCharged(true);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
        list.add(ConfigHelper.artifactIndicator);
        list.add(StatCollector.translateToLocal("tooltip.item.dragonic.charged"));
        list.add(StatCollector.translateToLocal("tooltip.item.bonus.firearrows"));
        list.add(StatCollector.translateToLocal("tooltip.item.bonus.damage") + " " + super.getBonusValue(stack) + "%");
        list.add(StatCollector.translateToLocal("item.dragonicbow.tooltip.charged"));
        list.add(StatCollector.translateToLocal("item.dragonicbow.tooltip1"));
        list.add(StatCollector.translateToLocal("item.dragonicbow.tooltip2"));
    }
}
