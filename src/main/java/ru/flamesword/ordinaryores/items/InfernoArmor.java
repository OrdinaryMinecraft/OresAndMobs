package ru.flamesword.ordinaryores.items;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class InfernoArmor extends ItemArmor {
	
	  private String texturePath = "ordinaryores:textures/models/armor/";
	  Random rand = new Random();
	  boolean flag;
	  int k = 0;
      
      public InfernoArmor(int id, int armorType) {
              super(OrdinaryOresBase.INFERNOARM, id, armorType);
              this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
              this.setMaxStackSize(1);
              this.setTextureName();
      }

      public void setTextureName ()
      {
              if(armorType == 0||armorType == 1||armorType == 3){
                      this.texturePath += "Infernolayer" + 1 + ".png";
              }
              else {
                      this.texturePath += "Infernolayer" + 2 + ".png";
              }
      }
      
      @Override
      public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type){
              return this.texturePath;
      }
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		player.extinguish();
		ItemStack helmet = player.getCurrentArmor(3);
		ItemStack plate = player.getCurrentArmor(2);
		ItemStack pants = player.getCurrentArmor(1);
		ItemStack boots = player.getCurrentArmor(0);
		if (helmet != null && plate != null && pants != null && boots != null)
		if (helmet.getItem() == ItemRegistry.infernohelmet && plate.getItem() == ItemRegistry.infernoplate && pants.getItem() == ItemRegistry.infernopants && boots.getItem() == ItemRegistry.infernoboots) {
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 10, 20));
		}
		
		//player.worldObj.spawnParticle("flame", player.posX+rand.nextFloat()*(0.8)-0.4, player.posY+rand.nextFloat()*(2)-0.5, player.posZ+rand.nextFloat()*(0.8)-0.4, 0, 0, 0);
		//if ((player.moveForward != 0) && (world.getBlock(x, y, z) == Blocks.air)) world.setBlock(x, y, z, Blocks.fire);
        /*if (!player.worldObj.isRemote) {
			if(armor.getItem() == OrdinaryOresBase.infernoboots) {
				if ((player.moveForward != 0) || (player.moveStrafing != 0)) {
					int x = (int) Math.floor(player.posX);
					int y = (int) (player.posY - player.getYOffset());
					int z = (int) Math.floor(player.posZ);
					flag = false;
					if (player.worldObj.getBlock(x, y-1, z) == Blocks.grass) { player.worldObj.setBlock(x, y-1, z, Blocks.dirt); flag = true; player.worldObj.markBlockForUpdate(x, y-1, z); }
					if (player.worldObj.getBlock(x, y-1, z) == Blocks.mycelium) { player.worldObj.setBlock(x, y-1, z, Blocks.dirt);  flag = true; player.worldObj.markBlockForUpdate(x, y-1, z); }
					if (player.worldObj.getBlock(x, y-1, z) == Blocks.dirt) { player.worldObj.setBlock(x, y-1, z, Blocks.dirt);  flag = true; player.worldObj.markBlockForUpdate(x, y-1, z); }
					if (player.worldObj.getBlock(x, y-1, z) == Blocks.ice) { player.worldObj.setBlock(x, y-1, z, Blocks.water);  flag = true; player.worldObj.markBlockForUpdate(x, y-1, z); }
					if (player.worldObj.getBlock(x, y-1, z) == Blocks.packed_ice) { player.worldObj.setBlock(x, y-1, z, Blocks.water);  flag = true; player.worldObj.markBlockForUpdate(x, y-1, z); }
					if (player.worldObj.getBlock(x, y-1, z) == Blocks.snow) { player.worldObj.setBlock(x, y-1, z, Blocks.water);  flag = true; player.worldObj.markBlockForUpdate(x, y, z); }
					if (player.worldObj.getBlock(x, y, z) == Blocks.snow_layer) { player.worldObj.setBlock(x, y, z, Blocks.air);  flag = true; player.worldObj.markBlockForUpdate(x, y, z); }
					
					if (flag) for (int i = 0; i < 2; i++) player.worldObj.spawnParticle("flame", x+rand.nextFloat()*(1), y+0.2, z+rand.nextFloat()*(1), 0, 0, 0);
				}
			}
		}*/
	}
	
  	public void addInformation(ItemStack armor, EntityPlayer player, List list, boolean show) {
		list.add(ConfigHelper.rareIndicator);
		list.add(ConfigHelper.effectInferno1);
		list.add(ConfigHelper.effectInferno2);
		list.add(ConfigHelper.effectInferno3);
  	}


}
