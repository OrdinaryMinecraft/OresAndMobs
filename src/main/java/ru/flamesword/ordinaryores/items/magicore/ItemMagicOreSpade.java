package ru.flamesword.ordinaryores.items.magicore;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemMagicOreSpade extends ItemSpade {

	public ItemMagicOreSpade() {
        super(OrdinaryOresBase.MAGICORETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:MagicOreShovel");
	}

	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
	     if (entity instanceof EntityPlayer)
	     {          
	    	 EntityPlayer player = (EntityPlayer) entity;
	          if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemMagicOreSpade)
	          {
	        	  	player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20));
	          }
	     }
	}
	
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
		list.add(ConfigHelper.digSpeedEffectName + " I " + ConfigHelper.effectIndicator);
	}
}
