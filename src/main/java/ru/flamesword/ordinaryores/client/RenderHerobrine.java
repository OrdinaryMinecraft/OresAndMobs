package ru.flamesword.ordinaryores.client;

import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import ru.flamesword.ordinaryores.entities.EntityHerobrine;

public class RenderHerobrine extends RenderZombie {

	public RenderHerobrine() {
		
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityZombie entity) {
		EntityHerobrine herobrine = (EntityHerobrine)entity;
		if(herobrine.isAngry()) {
			return new ResourceLocation("ordinaryores", "textures/entity/herobrine_angry.png");
		} else {
			return new ResourceLocation("ordinaryores", "textures/entity/herobrine.png");
		}
	}

}
