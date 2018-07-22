package ru.flamesword.ordinaryores.client;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import ru.flamesword.ordinaryores.entities.EntityFrostArrow;

public class RenderFrostArrow extends RenderArrow {

    @Override
    protected ResourceLocation getEntityTexture(EntityArrow entityArrow)
    {
        return new ResourceLocation("ordinaryores", "textures/entity/frost_arrow.png");
    }
}
