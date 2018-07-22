package ru.flamesword.ordinaryores.items.magicore;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemMagicOreSword extends ItemSword {

	public ItemMagicOreSword() {
        super(OrdinaryOresBase.MAGICORETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:MagicOreSword");
	}
	
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
	     if (entity instanceof EntityPlayer)
	     {          
	    	 EntityPlayer player = (EntityPlayer) entity;
	          if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemMagicOreSword)
	          {
	        	  	player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20));
	          }
	     }
	}
	
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
		list.add(ConfigHelper.damageBoostEffectName + " I " + ConfigHelper.effectIndicator);
	}
}
