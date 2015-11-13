package ru.flamesword.ordinaryores.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemRootOfLife extends ItemFood {
	
	public ItemRootOfLife() {
		super(10, true);
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setTextureName("ordinaryores:RootOfLife");
	}
	
	@Override
	public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player) {
		super.onEaten(itemstack, world, player);
		player.addPotionEffect(new PotionEffect(21, 6000, 4));
		return itemstack;
	}
}
