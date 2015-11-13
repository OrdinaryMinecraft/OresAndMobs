package ru.flamesword.ordinaryores.items;

import net.minecraft.item.ItemPickaxe;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class ItemMalachitePickaxe extends ItemPickaxe {

	public ItemMalachitePickaxe() {
        super(OrdinaryOresBase.MALACHITETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:MalachitePickaxe");
	}

}
