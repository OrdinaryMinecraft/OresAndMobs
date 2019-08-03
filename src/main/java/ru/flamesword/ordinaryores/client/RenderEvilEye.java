package ru.flamesword.ordinaryores.client;

import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.flamesword.ordinaryores.entities.EntityEvilEye;

public class RenderEvilEye extends RenderLiving {

    public RenderEvilEye() {
        super(new ModelGhast(), 0.5F);
        this.shadowSize *= 0.5F;
    }

    protected void preRenderCallback(EntityEvilEye p_77041_1_, float p_77041_2_)
    {
        GL11.glScalef(0.5F, 0.5F, 0.5F);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityEvilEye)p_77041_1_, p_77041_2_);
    }


    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return new ResourceLocation("ordinaryores", "textures/entity/evil_eye.png");
    }
}
