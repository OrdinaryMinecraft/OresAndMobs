package ru.flamesword.ordinaryores.items;

import net.minecraft.item.ItemAxe;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemSapphireAxe extends ItemAxe {

	public ItemSapphireAxe() {
        super(OrdinaryOresBase.SAPPHIRETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:SapphireAxe");
	}

}
