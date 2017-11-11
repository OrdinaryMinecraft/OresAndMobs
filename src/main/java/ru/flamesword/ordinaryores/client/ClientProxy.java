package ru.flamesword.ordinaryores.client;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.RenderSlime;
import ru.flamesword.ordinaryores.CommonProxy;
import ru.flamesword.ordinaryores.entities.EntityEnderCreeper;
import ru.flamesword.ordinaryores.entities.EntityEnderSkeleton;
import ru.flamesword.ordinaryores.entities.EntityForestGuard;
import ru.flamesword.ordinaryores.entities.EntityGhost;
import ru.flamesword.ordinaryores.entities.EntityGhoul;
import ru.flamesword.ordinaryores.entities.EntityHerobrine;
import ru.flamesword.ordinaryores.entities.EntityIceElemental;
import ru.flamesword.ordinaryores.entities.EntityInfernoGolem;
import ru.flamesword.ordinaryores.entities.EntityLivingBlock;
import ru.flamesword.ordinaryores.entities.EntitySprout;
import ru.flamesword.ordinaryores.entities.EntitySuperSlime;
import ru.flamesword.ordinaryores.entities.EntityUndeadSpider;
import ru.flamesword.ordinaryores.entities.EntityUndeadSpidy;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		System.out.println("Registering entity renderers...");
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
		System.out.println("Successfully registered all entity renderers!");
	}
	
	/*@Override
	public int registerDarkArmorRenderPrefix() {
		return RenderingRegistry.addNewArmourRendererPrefix("dark_iron");
	}*/
	
	/*@Override
	public int registerInfernoArmorRenderPrefix() {
		return RenderingRegistry.addNewArmourRendererPrefix("inferno");
	}*/
	
}
