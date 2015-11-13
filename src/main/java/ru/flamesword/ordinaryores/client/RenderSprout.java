package ru.flamesword.ordinaryores.client;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSprout extends RenderLiving {

	public RenderSprout() {
		super(new ModelSprout(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return new ResourceLocation("ordinaryores", "textures/entity/sprout.png");
	}

}
