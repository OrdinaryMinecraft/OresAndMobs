package ru.flamesword.ordinaryores.client;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import ru.flamesword.ordinaryores.entities.EntityLivingBlock;

public class RenderLivingBlock extends RenderLiving {
	
	public RenderLivingBlock() {
		super(new ModelLivingBlock(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityLivingBlock block = (EntityLivingBlock)entity;
		
		if(block.getBlockType() == 0) {
			return new ResourceLocation("ordinaryores", "textures/entity/living_block.png");
		} else {
			return new ResourceLocation("ordinaryores", "textures/entity/living_block_stone.png");
		}
	}

}
