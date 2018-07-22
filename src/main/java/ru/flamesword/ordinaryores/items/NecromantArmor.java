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

public class NecromantArmor extends ItemArmor {

    private String texturePath = "ordinaryores:textures/models/armor/";
    Random rand = new Random();
    boolean flag;
    int k = 0;

    public NecromantArmor(int id, int armorType) {
        super(OrdinaryOresBase.ARTIFACTARM, id, armorType);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setMaxStackSize(1);
        this.setTextureName();
    }

    public void setTextureName ()
    {
        if(armorType == 0||armorType == 1||armorType == 3){
            this.texturePath += "Necromantlayer" + 1 + ".png";
        }
        else {
            this.texturePath += "Necromantlayer" + 2 + ".png";
        }
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type){
        return this.texturePath;
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
        /*
        if (player.motionX > 0 || player.motionY > 0 || player.motionZ > 0 ) {
            player.worldObj.spawnParticle(
                    "portal",
                    player.posX+rand.nextFloat()*(1.4)-0.7,
                    player.posY+rand.nextFloat()-1.5,
                    player.posZ+rand.nextFloat()*(1.4)-0.7,
                    0,
                    0,
                    0
            );
        }
        */
    }

    public void addInformation(ItemStack armor, EntityPlayer player, List list, boolean show) {
        list.add(ConfigHelper.artifactIndicator);
        list.add(ConfigHelper.effectNecromant1);
        list.add(ConfigHelper.effectNecromant2);
        list.add(ConfigHelper.effectNecromant3);
    }


}
