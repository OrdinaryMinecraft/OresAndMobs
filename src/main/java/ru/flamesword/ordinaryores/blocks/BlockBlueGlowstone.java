package ru.flamesword.ordinaryores.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class BlockBlueGlowstone extends Block {

	public BlockBlueGlowstone() {
		super(Material.glass);
		this.setBlockName("blueglowstone");
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setBlockTextureName("ordinaryores:GlowstoneBlue");
		this.setStepSound(soundTypeGlass);
		this.setHardness(0.5F);
		this.setLightOpacity(50);
		this.setLightLevel(1F);
		this.setResistance(1F);
		this.setHarvestLevel("pickaxe", 0);
	}

}
