package ru.flamesword.ordinaryores.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import ru.flamesword.ordinaryores.entities.EntityHerobrine;

public class RenderHerobrine extends RenderBiped {

	public RenderHerobrine() {
		super(new ModelBiped(), 0.5F, 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityHerobrine herobrine = (EntityHerobrine)entity;
		if(herobrine.isAngry()) {
			return new ResourceLocation("ordinaryores", "textures/entity/herobrine_angry.png");
		} else {
			return new ResourceLocation("ordinaryores", "textures/entity/herobrine.png");
		}
	}

}
