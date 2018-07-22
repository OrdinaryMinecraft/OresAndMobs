package ru.flamesword.ordinaryores.client;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class RenderRedDragon extends RenderDragon {

    @Override
    protected void renderEquippedItems(EntityDragon p_77029_1_, float p_77029_2_)
    {
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return new ResourceLocation("ordinaryores", "textures/entity/red_dragon.png");
    }

}