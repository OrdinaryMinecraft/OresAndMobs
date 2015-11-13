package ru.flamesword.ordinaryores.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class BlockMalachiteOre extends Block {

	public BlockMalachiteOre() {
		super(Material.rock);
		this.setBlockName("malachiteore");
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setHardness(3F);
		this.setResistance(5F);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName("ordinaryores:MalachiteOre");
	}
	
	@Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return OrdinaryOresBase.malachiteitem;
    }
	
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 4;
    }
}
