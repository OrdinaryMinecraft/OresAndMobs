package ru.flamesword.ordinaryores.client;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.flamesword.ordinaryores.entities.EntitySentinelTree;

public class RenderSentinelTree extends RenderLiving {

    public RenderSentinelTree() {
        super(new ModelSentinelTree(), 1.3F);;
        this.shadowSize *= 0.5F;
    }

    protected void preRenderCallback(EntitySentinelTree p_77041_1_, float p_77041_2_)
    {
        GL11.glScalef(1.3F, 1.3F, 1.3F);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntitySentinelTree)p_77041_1_, p_77041_2_);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return new ResourceLocation("ordinaryores", "textures/entity/tree.png");
    }

}
