package ru.flamesword.ordinaryores.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import ru.flamesword.ordinaryores.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemRainGodSpear extends ItemSword {

    public ItemRainGodSpear() {
        super(OrdinaryOresBase.ARTIFACTTOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        setTextureName("ordinaryores:RainGodSpear");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase attackedEntity, EntityLivingBase attacker) {
        super.hitEntity(stack, attackedEntity, attacker);
        if (Math.random() <= 0.1) {
            attackedEntity.worldObj.getWorldInfo().setRaining(!attackedEntity.worldObj.getWorldInfo().isRaining());
            //attackedEntity.worldObj.addWeatherEffect(new EntityLightningBolt(attackedEntity.worldObj, attackedEntity.posX, attackedEntity.posY, attackedEntity.posZ));
        }
        return true;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
        list.add(ConfigHelper.artifactIndicator);
        list.add(ConfigHelper.thunderEffectName);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister, ItemStack item) {
        itemIcon = iconRegister.registerIcon("ordinaryores:RainGodSpear");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconIndex(ItemStack item) {
        return this.getIcon(item,0);
    }
}
