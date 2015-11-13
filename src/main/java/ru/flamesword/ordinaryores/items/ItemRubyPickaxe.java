package ru.flamesword.ordinaryores.items;

import net.minecraft.item.ItemPickaxe;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemRubyPickaxe extends ItemPickaxe {

	public ItemRubyPickaxe() {
        super(OrdinaryOresBase.RUBYTOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:RubyPickaxe");
	}

}
