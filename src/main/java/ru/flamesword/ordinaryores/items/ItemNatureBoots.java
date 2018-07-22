package ru.flamesword.ordinaryores.items;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemNatureBoots extends ItemArmor {

    private String texturePath = "ordinaryores:textures/models/armor/";
    Random rand = new Random();

    public ItemNatureBoots(int id, int armorType) {
        super(OrdinaryOresBase.ARTIFACTARM, id, armorType);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setMaxStackSize(1);
        this.setTextureName();
    }

    public int armorType() {
        return 3;
    }

    public void setTextureName ()
    {
        if(armorType == 0||armorType == 1||armorType == 3){
            this.texturePath += "Invulnerabilitychestlayer" + 1 + ".png";
        }
        else {
            this.texturePath += "Invulnerabilitychestlayer" + 2 + ".png";
        }
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type){
        return this.texturePath;
    }

    public void addInformation(ItemStack armor, EntityPlayer player, List list, boolean show) {
        if(armor.getItem() == ItemRegistry.natureboots) {
            list.add(ConfigHelper.artifactIndicator);
            list.add(ConfigHelper.jumpEffectName + " II");
            list.add(ConfigHelper.moveSpeedEffectName + " II");
            list.add(ConfigHelper.growthEffectName);
        }
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
        if(armor.getItem() == ItemRegistry.natureboots) {
            if (player.motionX > 0 || player.motionY > 0 || player.motionZ > 0) {
                if (Math.random() <= 0.5) {
                    player.worldObj.spawnParticle("happyVillager", player.posX+rand.nextFloat()*(0.8)-0.4, player.posY+rand.nextFloat()*(1)-1.5, player.posZ+rand.nextFloat()*(0.8)-0.4, 0, 0, 0);
                    //player.getEntityWorld().playAuxSFX(2005, x, y-1, z, 0);
                }
            }
            //player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20, 2));
            //player.addPotionEffect(new PotionEffect(Potion.jump.id, 20, 2));
        }
    }
}
