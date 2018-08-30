package ru.flamesword.artifacts;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod (modid = "artifacts", name = "Artifacts", version = "0.1")

public class ArtifactsBase {

	@EventHandler
	public void initialize(FMLPreInitializationEvent event)
	{
		ConfigHelper.setupConfig(new Configuration(event.getSuggestedConfigurationFile()));
		PlayerInteractHandler eventHandler = new PlayerInteractHandler();
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}
	
}
