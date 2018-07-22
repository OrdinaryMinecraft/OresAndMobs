package ru.flamesword.ordinaryores.items.sapphire;

import net.minecraft.item.ItemSpade;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemSapphireSpade extends ItemSpade{

	public ItemSapphireSpade() {
        super(OrdinaryOresBase.SAPPHIRETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:SapphireSpade");
	}

}
