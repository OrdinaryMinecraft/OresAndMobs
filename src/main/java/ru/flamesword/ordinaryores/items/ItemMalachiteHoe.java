package ru.flamesword.ordinaryores.items;

import net.minecraft.item.ItemHoe;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemMalachiteHoe extends ItemHoe {

	public ItemMalachiteHoe() {
        super(OrdinaryOresBase.MALACHITETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:MalachiteHoe");
	}
}

