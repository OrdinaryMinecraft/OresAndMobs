package ru.flamesword.ordinaryores.blocks;

import ru.flamesword.ordinaryores.OrdinaryOresBase;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockMalachiteStairs extends BlockStairs {

	public BlockMalachiteStairs() {
		super(OrdinaryOresBase.malachiteblock, 1);
		this.setBlockName("malachitestairs");
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setHardness(3F);
		this.setLightOpacity(0);
		this.setResistance(5F);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName("ordinaryores:MalachiteBlock");
	}
}
