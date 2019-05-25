package ru.flamesword.ordinaryores.client;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.flamesword.ordinaryores.entities.EntityBear;

public class RenderBear extends RenderLiving {

    public RenderBear() {
        super(new ModelBear(), 1.5F);;
        this.shadowSize *= 1.0F;
    }

    protected void preRenderCallback(EntityBear p_77041_1_, float p_77041_2_)
    {
        GL11.glScalef(1.5F, 1.5F, 1.5F);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityBear)p_77041_1_, p_77041_2_);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return new ResourceLocation("ordinaryores", "textures/entity/bear.png");
    }

}
