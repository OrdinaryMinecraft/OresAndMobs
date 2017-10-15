package ru.flamesword.ordinaryores.client;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import ru.flamesword.ordinaryores.entities.EntityIceElemental;
import ru.flamesword.ordinaryores.entities.EntityUndeadSpider;

public class RenderUndeadSpider extends RenderLiving {

	public RenderUndeadSpider() {
		super(new ModelSpider(), 1.0F);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("ordinaryores", "textures/entity/undead_spider.png");
	}

}
