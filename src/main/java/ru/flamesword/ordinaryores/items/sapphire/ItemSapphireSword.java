package ru.flamesword.ordinaryores.items.sapphire;

import net.minecraft.item.ItemSword;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemSapphireSword extends ItemSword {

	public ItemSapphireSword() {
        super(OrdinaryOresBase.SAPPHIRETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:SapphireSword");
	}

}
