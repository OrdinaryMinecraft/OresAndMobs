package ru.flamesword.ordinaryores.client;

import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEnderCreeper extends RenderCreeper {

	@Override
	public ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("ordinaryores", "textures/entity/ender_creeper.png");
	}
	
}
