package ru.flamesword.ordinaryores.items.dragonic;

import java.util.List;
import java.util.Objects;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemDragonicSpear extends ItemSword {

    public ItemDragonicSpear() {
        super(OrdinaryOresBase.ARTIFACTTOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        setTextureName("ordinaryores:DragonicSpear");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase attackedEntity, EntityLivingBase attacker) {
        super.hitEntity(stack, attackedEntity, attacker);
        if (Math.random() <= 0.1) {
            //attackedEntity.worldObj.getWorldInfo().setRaining(!attackedEntity.worldObj.getWorldInfo().isRaining());
            attackedEntity.worldObj.addWeatherEffect(new EntityLightningBolt(attackedEntity.worldObj, attackedEntity.posX, attackedEntity.posY, attackedEntity.posZ));
        }
        return true;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
        list.add(ConfigHelper.artifactIndicator);
        list.add(StatCollector.translateToLocal("item.dragonicspear.tooltip"));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister, ItemStack item) {
        itemIcon = iconRegister.registerIcon("ordinaryores:DragonicSpear");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconIndex(ItemStack item) {
        return this.getIcon(item,0);
    }    public static int getEnemiesKilled(ItemStack item) {
        NBTTagCompound tagCompound = item.getTagCompound();
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            item.setTagCompound(tagCompound);
        }
        int enemiesKilled;
        if (Objects.nonNull(tagCompound.getInteger("Counter_EnemiesKilled"))) {
            enemiesKilled = tagCompound.getInteger("Counter_EnemiesKilled");
        } else {
            enemiesKilled = 0;
        }
        return enemiesKilled;
    }
}
