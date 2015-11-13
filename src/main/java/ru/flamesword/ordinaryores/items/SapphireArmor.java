package ru.flamesword.ordinaryores.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class SapphireArmor extends ItemArmor {
	
	  private String texturePath = "ordinaryores:textures/models/armor/";
      
      public SapphireArmor(int id, int armorType) {
              super(OrdinaryOresBase.SAPPHIREARM, id, armorType);
              this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
              this.setMaxStackSize(1);
              this.setTextureName();
      }

      public void setTextureName ()
      {
              if(armorType == 0||armorType == 1||armorType == 3){
                      this.texturePath += "Sapphirelayer" + 1 + ".png";
              }
              else {
                      this.texturePath += "Sapphirelayer" + 2 + ".png";
              }
      }
      
      @Override
      public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type){
              return this.texturePath;
      }
}
