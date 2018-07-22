package ru.flamesword.ordinaryores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

import java.util.List;

public class ItemRepairTool extends Item {

    public ItemRepairTool() {
        super();
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:RepairTool");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.worldObj.playSoundEffect(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "random.anvil_use", 1.0F, 1.0F);
        if (!entityplayer.capabilities.isCreativeMode)
            itemstack.stackSize--;
        if (!world.isRemote) {
            for (ItemStack is : entityplayer.inventory.armorInventory) {
                if (is != null && is.isItemDamaged()) {
                    is.setItemDamage(0);
                }
            }
        }
        return itemstack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdvanced) {
        list.add(StatCollector.translateToLocal("item.repairtool.tooltip"));
    }
}
