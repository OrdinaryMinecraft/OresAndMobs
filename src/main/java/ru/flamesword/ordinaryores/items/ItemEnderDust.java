package ru.flamesword.ordinaryores.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemEnderDust extends Item {
	
	public ItemEnderDust() {
		
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setTextureName("ordinaryores:EnderDust");
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 100));
		stack.stackSize--;
		return stack;
	}

}