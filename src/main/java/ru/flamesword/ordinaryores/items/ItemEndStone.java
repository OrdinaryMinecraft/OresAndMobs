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
import ru.flamesword.powerfulenderdragon.Utils;

import java.util.List;

public class ItemEndStone extends Item {

    public ItemEndStone() {
        super();
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:EndStone");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.worldObj.playSoundEffect(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "mob.endermen.portal", 1.0F, 1.0F);
        if (!entityplayer.capabilities.isCreativeMode)
            itemstack.stackSize--;
        if (!world.isRemote) {
            Utils.spawnCrystals();
            Utils.spawnDragonIfIsNotExist();
            WorldUtils.teleportToDimension((EntityPlayerMP)entityplayer, 1);
        }
        entityplayer.addStat(AchievementList.theEnd, 1);
        return itemstack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdvanced) {
        list.add(StatCollector.translateToLocal("item.endstone.tooltip"));
        list.add(StatCollector.translateToLocal("item.endstone.tooltip2"));
    }
}

