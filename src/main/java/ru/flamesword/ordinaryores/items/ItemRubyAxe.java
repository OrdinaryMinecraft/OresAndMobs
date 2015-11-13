package ru.flamesword.ordinaryores.items;

import net.minecraft.item.ItemAxe;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemRubyAxe extends ItemAxe {

	public ItemRubyAxe() {
        super(OrdinaryOresBase.RUBYTOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:RubyAxe");
	}

}
