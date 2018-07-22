package ru.flamesword.ordinaryores.items.magicore;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.items.ItemRegistry;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class MagicOreArmor extends ItemArmor {
	
	  private String texturePath = "ordinaryores:textures/models/armor/";
      
      public MagicOreArmor(int id, int armorType) {
              super(OrdinaryOresBase.MAGICOREARM, id, armorType);
              this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
              this.setMaxStackSize(1);
              this.setTextureName();
      }

      public void setTextureName ()
      {
              if(armorType == 0||armorType == 1||armorType == 3){
                      this.texturePath += "Magicorelayer" + 1 + ".png";
              }
              else {
                      this.texturePath += "Magicorelayer" + 2 + ".png";
              }
      }
      
      @Override
      public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type){
              return this.texturePath;
      }
      
  	public void addInformation(ItemStack armor, EntityPlayer player, List list, boolean show) {
  			if(armor.getItem() == ItemRegistry.magicorehelmet) {
				list.add(ConfigHelper.nightVisionEffectName + " I " + ConfigHelper.effectIndicator);
  			}
  			if(armor.getItem() == ItemRegistry.magicoreplate) {
				list.add(ConfigHelper.resistanceEffectName + " I " + ConfigHelper.effectIndicator);
  			}
  			if(armor.getItem() == ItemRegistry.magicorepants) {
				list.add(ConfigHelper.moveSpeedEffectName + " I " + ConfigHelper.effectIndicator);
  			}
  			if(armor.getItem() == ItemRegistry.magicoreboots) {
				list.add(ConfigHelper.jumpEffectName + " I " + ConfigHelper.effectIndicator);
  			}
	}
  	

  	  public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
  			  if(armor.getItem() == ItemRegistry.magicorehelmet) {
  				  	player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 20));
  			  }
  			  if(armor.getItem() == ItemRegistry.magicoreplate) {
  				  	player.addPotionEffect(new PotionEffect(Potion.resistance.id, 20));
  			  }
  			  if(armor.getItem() == ItemRegistry.magicorepants) {
  				  	player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20));
  			  }
  			  if(armor.getItem() == ItemRegistry.magicoreboots) {
  				  	player.addPotionEffect(new PotionEffect(Potion.jump.id, 20));
  			  }
  	  }
}
