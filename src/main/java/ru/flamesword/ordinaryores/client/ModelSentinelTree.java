package ru.flamesword.ordinaryores.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSentinelTree extends ModelBase {
    //fields
    ModelRenderer stvol1;
    ModelRenderer stvol2;
    ModelRenderer stvol3;
    ModelRenderer stvol4;
    ModelRenderer stvol5;
    ModelRenderer stvol6;
    ModelRenderer stvol7;
    ModelRenderer listva1;
    ModelRenderer listva3;
    ModelRenderer listva4;
    ModelRenderer listva5;
    ModelRenderer listva6;
    ModelRenderer Shape1;

    public ModelSentinelTree()
    {
        textureWidth = 64;
        textureHeight = 32;

        stvol1 = new ModelRenderer(this, 56, 0);
        stvol1.addBox(0F, 0F, 0F, 2, 24, 2);
        stvol1.setRotationPoint(0F, 0F, 0F);
        stvol1.setTextureSize(64, 32);
        stvol1.mirror = true;
        setRotation(stvol1, 0F, 0F, 0F);
        stvol2 = new ModelRenderer(this, 39, 0);
        stvol2.addBox(-2F, 0F, 0F, 2, 9, 2);
        stvol2.setRotationPoint(0F, 12F, 0F);
        stvol2.setTextureSize(64, 32);
        stvol2.mirror = true;
        setRotation(stvol2, 0F, 0F, 2.490967F);
        stvol3 = new ModelRenderer(this, 40, 0);
        stvol3.addBox(0F, 0F, 0F, 2, 6, 2);
        stvol3.setRotationPoint(-5.5F, -1F, 0F);
        stvol3.setTextureSize(64, 32);
        stvol3.mirror = true;
        setRotation(stvol3, 0F, 0F, 0F);

        stvol4 = new ModelRenderer(this, 37, 0);
        stvol4.addBox(0F, -6F, 0F, 2, 7, 2);
        stvol4.setRotationPoint(0F, -1F, 0.2F);
        stvol4.setTextureSize(64, 32);
        stvol4.mirror = true;
        setRotation(stvol4, -0.2094395F, 0F, 0F);
        stvol4.mirror = false;
        stvol5 = new ModelRenderer(this, 39, 0);
        stvol5.addBox(0F, -9.7F, 0F, 2, 10, 2);
        stvol5.setRotationPoint(0F, 10F, 0F);
        stvol5.setTextureSize(64, 32);
        stvol5.mirror = true;
        setRotation(stvol5, 0.4363323F, 0F, 0.418879F);
        stvol6 = new ModelRenderer(this, 33, 0);
        stvol6.addBox(0F, -10F, 0F, 2, 10, 2);
        stvol6.setRotationPoint(0F, 14F, 0F);
        stvol6.setTextureSize(64, 32);
        stvol6.mirror = true;
        setRotation(stvol6, -0.7853982F, 0F, 0F);
        stvol7 = new ModelRenderer(this, 57, 0);
        stvol7.addBox(0F, -7F, -1F, 1, 7, 1);
        stvol7.setRotationPoint(0F, 16F, 1F);
        stvol7.setTextureSize(64, 32);
        stvol7.mirror = true;
        setRotation(stvol7, 0.6632251F, 0F, -0.2617994F);
        listva1 = new ModelRenderer(this, 0, 0);
        listva1.addBox(-2F, -4F, -0.5F, 7, 7, 7);
        listva1.setRotationPoint(1F, -8F, 0F);
        listva1.setTextureSize(64, 32);
        listva1.mirror = true;
        setRotation(listva1, 0.6890436F, -1.028092F, 0.5934119F);
        listva3 = new ModelRenderer(this, 0, 0);
        listva3.addBox(-3F, -4F, -1F, 6, 6, 6);
        listva3.setRotationPoint(-4F, 0F, 0F);
        listva3.setTextureSize(64, 32);
        listva3.mirror = true;
        setRotation(listva3, -0.4089647F, -0.5205006F, -0.1661677F);
        listva4 = new ModelRenderer(this, 0, 0);
        listva4.addBox(-1F, -2F, 2.5F, 5, 6, 5);
        listva4.setRotationPoint(3F, 1F, -7F);
        listva4.setTextureSize(64, 32);
        listva4.mirror = true;
        setRotation(listva4, 0F, 0.2602503F, 0.4089647F);
        listva5 = new ModelRenderer(this, 0, -1);
        listva5.addBox(0F, 0F, 0F, 6, 7, 6);
        listva5.setRotationPoint(-1F, 2F, 6.5F);
        listva5.setTextureSize(64, 32);
        listva5.mirror = true;
        setRotation(listva5, -0.4089647F, -0.1115358F, 0.1312612F);
        listva6 = new ModelRenderer(this, 1, 4);
        listva6.addBox(-3F, -3F, -1F, 3, 3, 3);
        listva6.setRotationPoint(0F, 12F, -3F);
        listva6.setTextureSize(64, 32);
        listva6.mirror = true;
        setRotation(listva6, 0.3717861F, -0.4089647F, 0F);
        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-3F, -5F, -4F, 7, 7, 7);
        Shape1.setRotationPoint(1F, -8F, 3F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0.5948578F, 0.8551081F, -0.7063936F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        stvol1.render(f5);
        stvol2.render(f5);
        stvol3.render(f5);
        stvol4.render(f5);
        stvol5.render(f5);
        stvol6.render(f5);
        stvol7.render(f5);
        listva1.render(f5);
        listva3.render(f5);
        listva4.render(f5);
        listva5.render(f5);
        listva6.render(f5);
        Shape1.render(f5);
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