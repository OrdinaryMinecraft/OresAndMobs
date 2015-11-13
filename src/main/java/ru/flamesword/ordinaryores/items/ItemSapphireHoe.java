package ru.flamesword.ordinaryores.items;

import net.minecraft.item.ItemHoe;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemSapphireHoe extends ItemHoe {

	public ItemSapphireHoe() {
        super(OrdinaryOresBase.SAPPHIRETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:SapphireHoe");
	}
}

