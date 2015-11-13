package ru.flamesword.ordinaryores.items;

import net.minecraft.item.ItemSpade;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemRubySpade extends ItemSpade{

	public ItemRubySpade() {
        super(OrdinaryOresBase.RUBYTOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:RubySpade");
	}

}
