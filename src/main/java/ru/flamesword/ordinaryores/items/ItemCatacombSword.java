package ru.flamesword.ordinaryores.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import ru.flamesword.ordinaryores.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

import java.util.List;

public class ItemCatacombSword extends ItemSword {

    public ItemCatacombSword() {
        super(OrdinaryOresBase.ARTIFACTTOOL1);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        setTextureName("ordinaryores:CatacombSword");
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
        list.add(ConfigHelper.artifactIndicator);
        list.add(ConfigHelper.expEffectName);
    }
}
