package ru.flamesword.ordinaryores.items.ruby;

import net.minecraft.item.ItemHoe;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemRubyHoe extends ItemHoe {

	public ItemRubyHoe() {
        super(OrdinaryOresBase.RUBYTOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:RubyHoe");
	}
}

