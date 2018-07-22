package ru.flamesword.ordinaryores.items.sapphire;

import net.minecraft.item.ItemPickaxe;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemSapphirePickaxe extends ItemPickaxe {

	public ItemSapphirePickaxe() {
        super(OrdinaryOresBase.SAPPHIRETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:SapphirePickaxe");
	}

}
