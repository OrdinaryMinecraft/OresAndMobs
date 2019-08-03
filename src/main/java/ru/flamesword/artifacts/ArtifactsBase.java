package ru.flamesword.artifacts;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod (modid = "artifacts", name = "Artifacts", version = "0.1")

public class ArtifactsBase {

	public static Item artifactbox;
	public static Item ancientkey;
	public static FMLEventChannel otherChannel;

	@EventHandler
	public void initialize(FMLPreInitializationEvent event)
	{
		ConfigHelper.setupConfig(new Configuration(event.getSuggestedConfigurationFile()));
		ArtifactsEventHandler eventHandler = new ArtifactsEventHandler();
		MinecraftForge.EVENT_BUS.register(eventHandler);


		artifactbox = new ItemArtifactBox().setUnlocalizedName("artifactbox");
		GameRegistry.registerItem(artifactbox, "artifactbox");
		ancientkey = new ItemAncientKey().setUnlocalizedName("ancientkey");
		GameRegistry.registerItem(ancientkey, "ancientkey");

		PacketHandler sk = new PacketHandler();
		otherChannel = NetworkRegistry.INSTANCE.newEventDrivenChannel(PacketChannel.ARTIFACTSOTHER.name());
		otherChannel.register(sk);
	}
	
}
