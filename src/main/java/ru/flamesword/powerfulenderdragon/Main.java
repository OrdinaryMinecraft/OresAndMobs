package ru.flamesword.powerfulenderdragon;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import ru.flamesword.ordinaryores.OrdinaryOresBase;


@Mod (modid = "powerfulenderdragon", name = "PowerfulEnderdragon", version = "0.3")

public class Main {
	@Mod.Instance("PowerfulEnderdragon")
	public static Main instance;
	@SidedProxy(clientSide = "ru.flamesword.powerfulenderdragon.ClientProxy", serverSide = "ru.flamesword.powerfulenderdragon.CommonProxy")
	public static CommonProxy proxy;

    public static DragonHandler eventHandler = new DragonHandler();

    public static Block dragonchest;


	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		proxy.registerRenderers();

		ConfigHelper.setupConfig(new Configuration(event.getSuggestedConfigurationFile()));

		FMLCommonHandler.instance().bus().register(eventHandler);
		MinecraftForge.EVENT_BUS.register(eventHandler);
		EntityRegistry.registerGlobalEntityID(EntityAncientEnderDragon.class, "ancientenderdragon", EntityRegistry.findGlobalUniqueEntityId());
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 1);

		GameRegistry.registerTileEntity(TileEntityDragonChest.class, "dragonchest");
		dragonchest = new BlockDragonChest(0).setBlockName("dragonchest").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
		GameRegistry.registerBlock(dragonchest, "dragonchest");

	}
	
}
