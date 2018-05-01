package ru.flamesword.ordinaryores.items;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import ru.flamesword.ordinaryores.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemColdSword extends ItemSword {

	public ItemColdSword() {
		super(OrdinaryOresBase.RARETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		setTextureName("ordinaryores:ColdSword");
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase attackedEntity, EntityLivingBase attacker) {
		super.hitEntity(stack, attackedEntity, attacker);
		attackedEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 0));
		return true;
	}

	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
		list.add(ConfigHelper.rareIndicator);
		list.add(ConfigHelper.freezeEffectName + " I");
	}
}
