package ru.flamesword.ordinaryores.items;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemCurseSword extends ItemSword {

	public ItemCurseSword() {
		super(OrdinaryOresBase.RARETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		setTextureName("ordinaryores:CurseSword");
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase attackedEntity, EntityLivingBase attacker) {
		super.hitEntity(stack, attackedEntity, attacker);
		attacker.heal(1.0F);
		return true;
	}

	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
		list.add(ConfigHelper.rareIndicator);
		list.add(ConfigHelper.lifestealEffectName + " I");
	}
}
