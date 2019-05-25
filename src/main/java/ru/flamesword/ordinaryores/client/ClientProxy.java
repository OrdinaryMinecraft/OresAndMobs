package ru.flamesword.ordinaryores.client;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraftforge.client.MinecraftForgeClient;
import ru.flamesword.ordinaryores.CommonProxy;
import ru.flamesword.ordinaryores.entities.*;
import cpw.mods.fml.client.registry.RenderingRegistry;
import ru.flamesword.ordinaryores.items.ItemRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		System.out.println("Registering entity renderers...");
		// Почему не рендерится?!
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostArrow.class, new RenderFrostArrow());

		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrine.class, new RenderHerobrine());
		RenderingRegistry.registerEntityRenderingHandler(EntitySuperSlime.class, new RenderSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(EntityForestGuard.class, new RenderForestGuard());
		RenderingRegistry.registerEntityRenderingHandler(EntityLivingBlock.class, new RenderLivingBlock());
		RenderingRegistry.registerEntityRenderingHandler(EntityInfernoGolem.class, new RenderInfernoGolem());
		RenderingRegistry.registerEntityRenderingHandler(EntitySprout.class, new RenderSprout());
		RenderingRegistry.registerEntityRenderingHandler(EntityIceElemental.class, new RenderIceElemental());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderCreeper.class, new RenderEnderCreeper());
		RenderingRegistry.registerEntityRenderingHandler(EntityUndeadSpider.class, new RenderUndeadSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityUndeadSpidy.class, new RenderUndeadSpiderling());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderSkeleton.class, new RenderEnderSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityGhoul.class, new RenderGhoul());
		RenderingRegistry.registerEntityRenderingHandler(EntityGhost.class, new RenderGhost());
		RenderingRegistry.registerEntityRenderingHandler(EntityBandit.class, new RenderBandit());
		RenderingRegistry.registerEntityRenderingHandler(EntityBanditLeader.class, new RenderBanditLeader());
		RenderingRegistry.registerEntityRenderingHandler(EntityRedDragon.class, new RenderRedDragon());
		RenderingRegistry.registerEntityRenderingHandler(EntityZigomoreSkeleton.class, new RenderZigomoreSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntitySentinelTree.class, new RenderSentinelTree());
		RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, new RenderBear());
		MinecraftForgeClient.registerItemRenderer(ItemRegistry.raingodspear, new ItemRendererSpear());
		MinecraftForgeClient.registerItemRenderer(ItemRegistry.dragonicbow, new ItemRendererBow());
		MinecraftForgeClient.registerItemRenderer(ItemRegistry.dragonicbowcharged, new ItemRendererBow());
		MinecraftForgeClient.registerItemRenderer(ItemRegistry.frostbow, new ItemRendererBow());
		MinecraftForgeClient.registerItemRenderer(ItemRegistry.reinforcedbow, new ItemRendererBow());
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.dragonicspear, new ItemRendererSpear());
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.dragonicspearcharged, new ItemRendererSpear());
		System.out.println("Successfully registered all entity renderers!");
	}

	/*@Override
	public int registerDarkArmorRenderPrefix() {
		return RenderingRegistry.addNewArmourRendererPrefix("magic_ore");
	}*/
	
	/*@Override
	public int registerInfernoArmorRenderPrefix() {
		return RenderingRegistry.addNewArmourRendererPrefix("inferno");
	}*/
	
}
