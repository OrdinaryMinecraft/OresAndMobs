package ru.flamesword.ordinaryores.items.ruby;

import net.minecraft.item.ItemPickaxe;
import net.minecraft.potion.PotionEffect;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemRubyPickaxe extends ItemPickaxe {

	public ItemRubyPickaxe() {
        super(OrdinaryOresBase.RUBYTOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:RubyPickaxe");
	}

}
