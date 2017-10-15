package ru.flamesword.ordinaryores.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.util.ResourceLocation;
import ru.flamesword.ordinaryores.entities.EntityIceElemental;
import ru.flamesword.ordinaryores.entities.EntityUndeadSpider;
import ru.flamesword.ordinaryores.entities.EntityUndeadSpiderling;

public class RenderUndeadSpiderling extends RenderLiving {

	public RenderUndeadSpiderling() {
		super(new ModelSpider(), 0.5F);
        this.shadowSize *= 0.5F;
	}

    protected void preRenderCallback(EntityUndeadSpiderling p_77041_1_, float p_77041_2_)
    {
        GL11.glScalef(0.5F, 0.5F, 0.5F);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityUndeadSpiderling)p_77041_1_, p_77041_2_);
    }


	@Override
	public ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("ordinaryores", "textures/entity/undead_spider.png");
	}

}
