package ru.flamesword.ordinaryores.client;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBear extends ModelQuadruped {
    //fields
    ModelRenderer tail;
    ModelRenderer horn1;
    ModelRenderer horn2;
    ModelRenderer udders;
    ModelRenderer nose;
    ModelRenderer mouth;

    public ModelBear()
    {
        super(12, 0.0F);
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -4F, -6F, 8, 8, 6);
        head.setRotationPoint(0F, 12F, -8F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);

        body = new ModelRenderer(this, 20, 10);
        body.addBox(-6F, -10F, -7F, 12, 12, 10);
        body.setRotationPoint(0F, 11F, 2F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 1.570796F, 0F, 0F);

        tail = new ModelRenderer(this, 27, 17);
        tail.addBox(-1.5F, -2F, -1F, 3, 3, 3);
        tail.setRotationPoint(0F, 17F, 10F);
        tail.setTextureSize(64, 32);
        tail.mirror = true;
        setRotation(tail, 1.308997F, 0F, 0F);

        udders = new ModelRenderer(this, 20, 10);
        udders.addBox(-5.5F, -2F, -4.4F, 11, 9, 10);
        udders.setRotationPoint(0F, 14F, 3F);
        udders.setTextureSize(64, 32);
        udders.mirror = true;
        setRotation(udders, 1.308997F, 0F, 0F);

        leg1 = new ModelRenderer(this, 0, 21);
        leg1.addBox(-2.5F, 6F, -1F, 4, 6, 4);
        leg1.setRotationPoint(-3F, 12F, 6F);
        leg1.setTextureSize(64, 32);
        leg1.mirror = true;
        setRotation(leg1, 0F, 0F, 0F);
        leg2 = new ModelRenderer(this, 0, 21);
        leg2.addBox(-1.5F, 6F, -1F, 4, 6, 4);
        leg2.setRotationPoint(3F, 12F, 6F);
        leg2.setTextureSize(64, 32);
        leg2.mirror = true;
        setRotation(leg2, 0F, 0F, 0F);
        leg3 = new ModelRenderer(this, 0, 21);
        leg3.addBox(-3F, 6F, -2F, 4, 7, 4);
        leg3.setRotationPoint(-3F, 11F, -5F);
        leg3.setTextureSize(64, 32);
        leg3.mirror = true;
        setRotation(leg3, 0F, 0F, 0F);
        leg4 = new ModelRenderer(this, 0, 21);
        leg4.addBox(-1F, 6F, -2F, 4, 7, 4);
        leg4.setRotationPoint(3F, 11F, -5F);
        leg4.setTextureSize(64, 32);
        leg4.mirror = true;
        setRotation(leg4, 0F, 0F, 0F);

        horn1 = new ModelRenderer(this, 29, 0);
        head.addChild(horn1);
        horn1.addBox(-2F, -17F, 3F, 1, 3, 2);
        horn1.setRotationPoint(0F, 11F, -7F);
        horn1.setTextureSize(64, 32);
        horn1.mirror = true;
        setRotation(horn1, 0F, 0F, -0.1570796F);
        horn2 = new ModelRenderer(this, 29, 0);
        head.addChild(horn2);
        horn2.addBox(1F, -17F, 3F, 1, 3, 2);
        horn2.setRotationPoint(0F, 11F, -7F);
        horn2.setTextureSize(64, 32);
        horn2.mirror = true;
        setRotation(horn2, 0F, 0F, 0.1570796F);
        nose = new ModelRenderer(this, 48, 0);
        head.addChild(nose);
        nose.addBox(-2F, -14F, 3F, 4, 3, 4);
        nose.setRotationPoint(0F, 13F, -13F);
        nose.setTextureSize(64, 32);
        nose.mirror = true;
        setRotation(nose, 0F, 0F, 0F);
        mouth = new ModelRenderer(this, 36, 0);
        head.addChild(mouth);
        mouth.addBox(0F, -11F, 8F, 2, 2, 4);
        mouth.setRotationPoint(-1F, 15F, -13F);
        mouth.setTextureSize(64, 32);
        mouth.mirror = true;
        setRotation(mouth, 0.3665191F, 0F, 0F);

        //-4F, -4F, -6F

        //body.addChild(udders);
        //body.addChild(tail);

    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        tail.render(f5);
        //horn1.render(f5);
        //horn2.render(f5);
        udders.render(f5);
        //nose.render(f5);
        //mouth.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}