package ru.flamesword.ordinaryores.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class BlockMalachitePillar extends BlockRotatedPillar {
	
	public IIcon[] icons = new IIcon[6];

	public BlockMalachitePillar() {
		super(Material.rock);
		this.setBlockName("malachiteblock");
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setHardness(3F);
		this.setResistance(5F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName("ordinaryores:MalachitePillar");
	}
	
	/*@Override
	public void registerBlockIcons(IIconRegister reg) {
	    for (int i = 0; i < 6; i ++) {
	        if (i < 2) this.icons[i] = reg.registerIcon(this.textureName + "Top");
	        else this.icons[i] = reg.registerIcon(this.textureName + "Side");
	    }
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
	    return this.icons[side];
	}*/
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.icons[0] = reg.registerIcon(this.textureName + "Top");
	    this.icons[1] = reg.registerIcon(this.textureName + "Side");
	}
	
	@Override
	protected IIcon getTopIcon(int p_150161_1_) {
		return this.icons[0];
	}
	
	@Override
	protected IIcon getSideIcon(int p_150163_1_) {
		return this.icons[1];
	}
}
