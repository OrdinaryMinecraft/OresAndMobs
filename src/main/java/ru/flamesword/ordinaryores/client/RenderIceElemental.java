package ru.flamesword.ordinaryores.client;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import ru.flamesword.ordinaryores.entities.EntityIceElemental;

public class RenderIceElemental extends RenderBiped {

	public RenderIceElemental() {
		super(new ModelZombie(), 0.5F, 1.0F);
	}
	
	
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityIceElemental elemental = (EntityIceElemental)entity;
			return new ResourceLocation("ordinaryores", "textures/entity/ice_elemental.png");
	}

}
