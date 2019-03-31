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

public class ItemChestplateOfRegeneration extends ItemArmor {
	
	  private String texturePath = "ordinaryores:textures/models/armor/";
      
      public ItemChestplateOfRegeneration(int id, int armorType) {
              super(OrdinaryOresBase.RAREARM, id, armorType);
              this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
              this.setMaxStackSize(1);
              this.setTextureName();
      }

      public int armorType() {
    	  return 1;
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
  			if(armor.getItem() == ItemRegistry.regenerationchest) {
  				list.add(ConfigHelper.rareIndicator);
				list.add(ConfigHelper.regenerationEffectName + " I " + ConfigHelper.effectIndicator);
  			}
	}
  	

  	  public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
  			  if(armor.getItem() == ItemRegistry.regenerationchest) {
  				  	player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 80));
  			  }
  	  }
}
