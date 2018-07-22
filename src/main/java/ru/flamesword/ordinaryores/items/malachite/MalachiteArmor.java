package ru.flamesword.ordinaryores.items.malachite;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class MalachiteArmor extends ItemArmor {
	
	  private String texturePath = "ordinaryores:textures/models/armor/";
      
      public MalachiteArmor(int id, int armorType) {
              super(OrdinaryOresBase.MALACHITEARM, id, armorType);
              this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
              this.setMaxStackSize(1);
              this.setTextureName();
      }

      public void setTextureName ()
      {
              if(armorType == 0||armorType == 1||armorType == 3){
                      this.texturePath += "Malachitelayer" + 1 + ".png";
              }
              else {
                      this.texturePath += "Malachitelayer" + 2 + ".png";
              }
      }
      
      @Override
      public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type){
              return this.texturePath;
      }
}
