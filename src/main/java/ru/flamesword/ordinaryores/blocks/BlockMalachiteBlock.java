package ru.flamesword.ordinaryores.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class BlockMalachiteBlock extends Block {

	public BlockMalachiteBlock() {
		super(Material.rock);
		this.setBlockName("malachiteblock");
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setHardness(3F);
		this.setResistance(5F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName("ordinaryores:MalachiteBlock");
	}
}
