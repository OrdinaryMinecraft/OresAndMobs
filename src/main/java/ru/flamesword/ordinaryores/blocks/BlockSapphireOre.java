package ru.flamesword.ordinaryores.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class BlockSapphireOre extends Block {

	public BlockSapphireOre() {
		super(Material.rock);
		this.setBlockName("sapphireore");
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setHardness(3F);
		this.setResistance(5F);
		this.setHarvestLevel("pickaxe", 2);
		this.setBlockTextureName("ordinaryores:SapphireOre");
	}
	
	@Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return OrdinaryOresBase.sapphireitem;
    }
}
