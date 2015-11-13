package ru.flamesword.ordinaryores.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class BlockGreenGlowstone extends Block {

	public BlockGreenGlowstone() {
		super(Material.glass);
		this.setBlockName("greenglowstone");
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setBlockTextureName("ordinaryores:GlowstoneGreen");
		this.setStepSound(soundTypeGlass);
		this.setHardness(0.5F);
		this.setLightOpacity(50);
		this.setLightLevel(1F);
		this.setResistance(1F);
		this.setHarvestLevel("pickaxe", 0);
	}

}