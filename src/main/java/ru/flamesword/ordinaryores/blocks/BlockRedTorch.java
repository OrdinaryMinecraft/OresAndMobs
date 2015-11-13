package ru.flamesword.ordinaryores.blocks;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRedTorch extends BlockTorch {

	
	public BlockRedTorch()  {
		this.setBlockName("redtorch");
		this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		this.setBlockTextureName("ordinaryores:TorchRed");
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
            p_149734_1_.spawnParticle("reddust", d0 - d4, d1 + d3 - 0.1, d2, 0.0D, 0.2D, 0.0D);
            p_149734_1_.spawnParticle("reddust", d0 - d4, d1 + d3, d2, 0.0D, 0.3D, 0.0D);
        }
        else if (l == 2)
        {
            p_149734_1_.spawnParticle("smoke", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("reddust", d0 + d4, d1 + d3 - 0.1, d2, 0.0D, 0.2D, 0.0D);
            p_149734_1_.spawnParticle("reddust", d0 + d4, d1 + d3, d2, 0.0D, 0.3D, 0.0D);
        }
        else if (l == 3)
        {
            p_149734_1_.spawnParticle("smoke", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("reddust", d0, d1 + d3 - 0.1, d2 - d4, 0.0D, 0.2D, 0.0D);
            p_149734_1_.spawnParticle("reddust", d0, d1 + d3, d2 - d4, 0.0D, 0.3D, 0.0D);
        }
        else if (l == 4)
        {
            p_149734_1_.spawnParticle("smoke", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("reddust", d0, d1 + d3 - 0.1, d2 + d4, 0.0D, 0.2D, 0.0D);
            p_149734_1_.spawnParticle("reddust", d0, d1 + d3, d2 + d4, 0.0D, 0.3D, 0.0D);
        }
        else
        {
            p_149734_1_.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("reddust", d0, d1 - 0.1, d2, 0.0D, 0.2D, 0.0D);
            p_149734_1_.spawnParticle("reddust", d0, d1, d2, 0.0D, 0.3D, 0.0D);
        }
    }
}
