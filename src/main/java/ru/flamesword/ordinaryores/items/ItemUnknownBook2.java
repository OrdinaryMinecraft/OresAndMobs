package ru.flamesword.ordinaryores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

import java.util.List;
import java.util.Random;

public class ItemUnknownBook2 extends Item {

    private static Random random = new Random();

    public ItemUnknownBook2() {
        super();
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:UnknownBook2");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.worldObj.playSoundEffect(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "random.pop", 1.0F, 1.0F);
        if (!entityplayer.capabilities.isCreativeMode)
            itemstack.stackSize--;
        if (!world.isRemote) {
            ItemStack book = new ItemStack(Items.book, 1);
            EnchantmentHelper.addRandomEnchantment(random, book, 12);
            EntityItem entityBook = new EntityItem(entityplayer.worldObj, entityplayer.posX, entityplayer.posY, entityplayer.posZ, book);
            entityplayer.worldObj.spawnEntityInWorld(entityBook);
        }
        return itemstack;
    }

    @Override
    public boolean hasEffect(ItemStack itemstack) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdvanced) {
        list.add(StatCollector.translateToLocal("item.unknownbook.tooltip"));
    }
}