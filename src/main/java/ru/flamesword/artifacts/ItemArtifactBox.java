package ru.flamesword.artifacts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

import java.util.List;
import java.util.Random;

public class ItemArtifactBox extends Item {

    private static Random random = new Random();

    public ItemArtifactBox() {
        super();
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("artifacts:ArtifactBox");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.worldObj.playSoundEffect(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "random.pop", 1.0F, 1.0F);
        if (entityplayer.capabilities.isCreativeMode || entityplayer.inventory.hasItem(ArtifactsBase.ancientkey)) {
            if (!entityplayer.capabilities.isCreativeMode) {
                itemstack.stackSize--;
                entityplayer.inventory.consumeInventoryItem(ArtifactsBase.ancientkey);
            }
            if (!world.isRemote) {
                ItemStack artifact = ArtifactsUtils.getRandomArtifact((short) ArtifactsUtils.randomBetween(1, 4), ArtifactsUtils.FROM_CHEST);
                EntityItem entityArtifact = new EntityItem(entityplayer.worldObj, entityplayer.posX, entityplayer.posY, entityplayer.posZ, artifact);
                entityplayer.worldObj.spawnEntityInWorld(entityArtifact);
            }
        } else {
            entityplayer.addChatMessage(new ChatComponentTranslation("message.artifact.nokey"));
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
        list.add(StatCollector.translateToLocal("item.artifactbox.tooltip"));
    }
}

