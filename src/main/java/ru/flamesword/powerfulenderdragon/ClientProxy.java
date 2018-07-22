package ru.flamesword.powerfulenderdragon;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        System.out.println("Registering entity renderers...");
        DragonChestRenderer renderDragonChest = new DragonChestRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDragonChest.class, renderDragonChest);
        TileEntityRendererChestHelper.instance = new RenderItemDragonChest();
        System.out.println("Successfully registered all entity renderers!");
    }

}
