package ru.flamesword.powerfulenderdragon;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class RenderItemDragonChest extends TileEntityRendererChestHelper {

    @Override
    public void renderChest(Block block, int i, float f)
    {
        if (block == Main.dragonchest)
        {
            TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityDragonChest(), 0.0D, 0.0D, 0.0D, 0.0F);
        }
        else
        {
            super.renderChest(block, i, f);
        }
    }
}