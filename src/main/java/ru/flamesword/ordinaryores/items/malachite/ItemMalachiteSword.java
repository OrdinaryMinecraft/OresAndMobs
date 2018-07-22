package ru.flamesword.ordinaryores.items.malachite;

import net.minecraft.item.ItemSword;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemMalachiteSword extends ItemSword {

	public ItemMalachiteSword() {
        super(OrdinaryOresBase.MALACHITETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:MalachiteSword");
	}

}
