package ru.flamesword.ordinaryores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

import java.util.List;

public class ItemFrostArrow extends Item {

    public ItemFrostArrow() {
        super();
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        setTextureName("ordinaryores:FrostArrow");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdvanced) {
        list.add(StatCollector.translateToLocal("item.frostarrow.tooltip"));
    }
}
