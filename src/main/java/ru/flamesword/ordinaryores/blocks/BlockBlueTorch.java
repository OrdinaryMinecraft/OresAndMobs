package ru.flamesword.ordinaryores.blocks;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBlueTorch extends BlockTorch {

	
	public BlockBlueTorch()  {
		this.setBlockName("bluetorch");
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setBlockTextureName("ordinaryores:TorchBlue");
		this.setLightLevel(0.95F);
		this.setLightOpacity(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {
        int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
        double d0 = (double)((float)p_149734_2_ + 0.5F);
        double d1 = (double)((float)p_149734_3_ + 0.7F);
        double d2 = (double)((float)p_149734_4_ + 0.5F);
        double d3 = 0.2199999988079071D;
        double d4 = 0.27000001072883606D;

        if (l == 1)
        {
            p_149734_1_.spawnParticle("smoke", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 - d4 + 0.05, d1 + d3, d2 + 0.05, 0.0D, 0.03D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 - d4, d1 + d3, d2, 0.0D, 0.02D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 - d4 + 0.1, d1 + d3, d2 + 0.1, 0.0D, 0.01D, 0.0D);
        }
        else if (l == 2)
        {
            p_149734_1_.spawnParticle("smoke", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 + d4 + 0.05, d1 + d3, d2 + 0.05, 0.0D, 0.03D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 + d4, d1 + d3, d2, 0.0D, 0.02D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 + d4 + 0.1, d1 + d3, d2 + 0.1, 0.0D, 0.01D, 0.0D);
        }
        else if (l == 3)
        {
            p_149734_1_.spawnParticle("smoke", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 + 0.05, d1 + d3, d2 - d4 + 0.05, 0.0D, 0.03D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0, d1 + d3, d2 - d4, 0.0D, 0.02D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 + 0.1, d1 + d3, d2 - d4 + 0.1, 0.0D, 0.01D, 0.0D);
        }
        else if (l == 4)
        {
            p_149734_1_.spawnParticle("smoke", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 + 0.05, d1 + d3, d2 + d4 + 0.05, 0.0D, 0.03D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0, d1 + d3, d2 + d4, 0.0D, 0.02D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 + 0.1, d1 + d3, d2 + d4 + 0.1, 0.0D, 0.01D, 0.0D);
        }
        else
        {
            p_149734_1_.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 + 0.05, d1, d2 + 0.05, 0.0D, 0.03D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0, d1, d2, 0.0D, 0.02D, 0.0D);
            p_149734_1_.spawnParticle("wake", d0 + 0.1, d1, d2 + 0.1, 0.0D, 0.01D, 0.0D);
        }
    }
}
