package ru.flamesword.ordinaryores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.util.WorldUtils;

import java.util.List;

public class ItemWorldStone extends Item {

    public ItemWorldStone() {
        super();
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:WorldStone");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.worldObj.playSoundEffect(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "mob.endermen.portal", 1.0F, 1.0F);
        if (!entityplayer.capabilities.isCreativeMode)
            itemstack.stackSize--;
        if (!world.isRemote) {
            WorldUtils.teleportToDimension((EntityPlayerMP)entityplayer, 0, (int) entityplayer.posX, (int) entityplayer.posZ);
        }
        entityplayer.addStat(AchievementList.theEnd2, 1);
        return itemstack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdvanced) {
        list.add(StatCollector.translateToLocal("item.worldstone.tooltip"));
    }
}

