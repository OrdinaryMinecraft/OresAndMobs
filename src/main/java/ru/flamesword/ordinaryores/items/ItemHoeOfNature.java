package ru.flamesword.ordinaryores.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.util.ConfigHelper;

import java.util.List;

public class ItemHoeOfNature extends ItemHoe {


    public ItemHoeOfNature() {
        super(OrdinaryOresBase.ARTIFACTTOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:HoeOfNature");
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemHoeOfNature)
            {
                player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20, 1));
            }
        }
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
        list.add(ConfigHelper.digSpeedEffectName + " II " + ConfigHelper.effectIndicator);
    }
}
