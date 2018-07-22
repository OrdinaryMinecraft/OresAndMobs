package ru.flamesword.ordinaryores.items.malachite;

import net.minecraft.item.ItemSpade;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemMalachiteSpade extends ItemSpade{

	public ItemMalachiteSpade() {
        super(OrdinaryOresBase.MALACHITETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:MalachiteSpade");
	}

}
