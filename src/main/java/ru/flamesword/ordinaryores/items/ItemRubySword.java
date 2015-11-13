package ru.flamesword.ordinaryores.items;

import net.minecraft.item.ItemSword;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemRubySword extends ItemSword {

	public ItemRubySword() {
        super(OrdinaryOresBase.RUBYTOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:RubySword");
	}

}
