package ru.flamesword.ordinaryores;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.flamesword.ordinaryores.items.ItemRegistry;

public class TabOrdinaryOres extends CreativeTabs {

	public TabOrdinaryOres(String string) {
		super(string);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
	return ItemRegistry.magicoreingot;
	}

}
