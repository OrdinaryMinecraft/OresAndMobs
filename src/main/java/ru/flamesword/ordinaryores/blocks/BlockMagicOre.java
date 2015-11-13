package ru.flamesword.ordinaryores.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class BlockMagicOre extends Block {

	public BlockMagicOre() {
		super(Material.rock);
		this.setBlockName("magicore");
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setHardness(3F);
		this.setResistance(5F);
		this.setLightLevel(0.5F);
		//this.setLightOpacity(100);
		this.setHarvestLevel("pickaxe", 2);
		this.setBlockTextureName("ordinaryores:MagicOreBlock");
	}
}
