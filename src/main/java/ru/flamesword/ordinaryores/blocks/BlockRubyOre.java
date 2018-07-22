package ru.flamesword.ordinaryores.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.items.ItemRegistry;

public class BlockRubyOre extends Block {

	public BlockRubyOre() {
		super(Material.rock);
		this.setBlockName("rubyore");
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setHardness(3F);
		this.setResistance(5F);
		this.setHarvestLevel("pickaxe", 1);
		this.setBlockTextureName("ordinaryores:RubyOre");
	}
	
	@Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return ItemRegistry.rubyitem;
    }
}
