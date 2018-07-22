package ru.flamesword.ordinaryores.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import ru.flamesword.ordinaryores.entities.EntityIceElemental;

public class RenderBanditLeader extends RenderBiped {

    public RenderBanditLeader() {
        super(new ModelBiped(), 0.5F, 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return new ResourceLocation("ordinaryores", "textures/entity/bandit2.png");
    }

}
