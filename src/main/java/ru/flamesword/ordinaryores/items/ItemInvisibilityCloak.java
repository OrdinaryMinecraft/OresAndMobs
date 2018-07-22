package ru.flamesword.ordinaryores.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemInvisibilityCloak extends ItemArmor {
	
	  private String texturePath = "ordinaryores:textures/models/armor/";
      
      public ItemInvisibilityCloak(int id, int armorType) {
              super(ArmorMaterial.GOLD, id, armorType);
              this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
              this.setMaxStackSize(1);
              this.setTextureName();
      }
      
      public int armorType() {
    	  return 2;
      }

      public void setTextureName ()
      {
              if(armorType == 0||armorType == 1||armorType == 3){
                      this.texturePath += "Invisibilitylayer" + 1 + ".png";
              }
              else {
                      this.texturePath += "Invisibilitylayer" + 2 + ".png";
              }
      }
      
      @Override
      public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type){
              return this.texturePath;
      }
      
    	public void addInformation(ItemStack armor, EntityPlayer player, List list, boolean show) {
  			if(armor.getItem() == ItemRegistry.invisibilitycloak) {
				list.add(ConfigHelper.invisibilityEffectName + " I " + ConfigHelper.effectIndicator);
  			}
	}
  	

  	  public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
  			  if(armor.getItem() == ItemRegistry.invisibilitycloak) {
  				  	player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 20));
  			  }
  	  }
}
