package ru.flamesword.ordinaryores.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSprout extends ModelBase {
    //fields
    ModelRenderer listva1;
    ModelRenderer body1;
    ModelRenderer body2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer kysok1;
    ModelRenderer kysok2;
    ModelRenderer kysok3;
    ModelRenderer kysok4;
    ModelRenderer listva2;
    ModelRenderer listva3;
    ModelRenderer listva4;
    ModelRenderer listva5;
    ModelRenderer Shape1;

    public ModelSprout()
    {
        textureWidth = 64;
        textureHeight = 32;

        listva1 = new ModelRenderer(this, 32, 0);
        listva1.addBox(-2F, -4F, -2F, 8, 8, 8);
        listva1.setRotationPoint(-2F, 5F, -1F);
        listva1.setTextureSize(64, 32);
        listva1.mirror = true;
        setRotation(listva1, -1.692763F, -0.2985649F, -1.041001F);
        body1 = new ModelRenderer(this, 2, 2);
        body1.addBox(-4F, 0F, -3F, 8, 12, 6);
        body1.setRotationPoint(0F, 6F, 0F);
        body1.setTextureSize(64, 32);
        body1.mirror = true;
        setRotation(body1, 0F, 0F, 0F);
        body2 = new ModelRenderer(this, 1, 0);
        body2.addBox(-3F, 0F, -4F, 6, 11, 8);
        body2.setRotationPoint(0F, 7F, 0F);
        body2.setTextureSize(64, 32);
        body2.mirror = true;
        setRotation(body2, 0F, 0F, 0F);
        leg3 = new ModelRenderer(this, 0, 16);
        leg3.addBox(-2F, 0F, -2F, 4, 6, 4);
        leg3.setRotationPoint(-4F, 18F, -4F);
        leg3.setTextureSize(64, 32);
        leg3.mirror = true;
        setRotation(leg3, 0F, 0F, 0F);
        leg4 = new ModelRenderer(this, 0, 16);
        leg4.addBox(-2F, 0F, -2F, 4, 6, 4);
        leg4.setRotationPoint(4F, 18F, -4F);
        leg4.setTextureSize(64, 32);
        leg4.mirror = true;
        setRotation(leg4, 0F, 0F, 0F);
        leg1 = new ModelRenderer(this, 0, 16);
        leg1.addBox(-2F, 0F, -2F, 4, 6, 4);
        leg1.setRotationPoint(-4F, 18F, 4F);
        leg1.setTextureSize(64, 32);
        leg1.mirror = true;
        setRotation(leg1, 0F, 0F, 0F);
        leg2 = new ModelRenderer(this, 0, 16);
        leg2.addBox(0F, 0F, -2F, 4, 6, 4);
        leg2.setRotationPoint(2F, 18F, 4F);
        leg2.setTextureSize(64, 32);
        leg2.mirror = true;
        setRotation(leg2, 0F, 0F, 0F);
        kysok1 = new ModelRenderer(this, 0, 0);
        kysok1.addBox(0F, 0F, 0F, 2, 2, 2);
        kysok1.setRotationPoint(-5F, 16F, 3F);
        kysok1.setTextureSize(64, 32);
        kysok1.mirror = true;
        setRotation(kysok1, 0F, 0F, 0F);
        kysok2 = new ModelRenderer(this, 0, 0);
        kysok2.addBox(0F, -1F, 0F, 2, 2, 2);
        kysok2.setRotationPoint(3F, 17F, 3F);
        kysok2.setTextureSize(64, 32);
        kysok2.mirror = true;
        setRotation(kysok2, 0F, 0F, 0F);
        kysok3 = new ModelRenderer(this, 0, 0);
        kysok3.addBox(0F, 0F, 0F, 2, 2, 2);
        kysok3.setRotationPoint(3F, 16F, -5F);
        kysok3.setTextureSize(64, 32);
        kysok3.mirror = true;
        setRotation(kysok3, 0F, 0F, 0F);
        kysok4 = new ModelRenderer(this, 0, 0);
        kysok4.addBox(0F, 0F, 0F, 2, 2, 2);
        kysok4.setRotationPoint(-5F, 16F, -5F);
        kysok4.setTextureSize(64, 32);
        kysok4.mirror = true;
        setRotation(kysok4, 0F, 0F, 0F);
        listva2 = new ModelRenderer(this, 32, 0);
        listva2.addBox(0F, 2F, 3F, 6, 6, 6);
        listva2.setRotationPoint(0F, 0F, 0F);
        listva2.setTextureSize(64, 32);
        listva2.mirror = true;
        setRotation(listva2, -0.4089647F, 0.2230717F, 0.1684398F);
        listva3 = new ModelRenderer(this, 32, 0);
        listva3.addBox(0F, 0F, 0F, 7, 6, 6);
        listva3.setRotationPoint(0F, 2F, -1F);
        listva3.setTextureSize(64, 32);
        listva3.mirror = true;
        setRotation(listva3, 0F, -2.156359F, 0.4461433F);
        listva4 = new ModelRenderer(this, 32, 0);
        listva4.addBox(0F, 0F, -2F, 5, 5, 5);
        listva4.setRotationPoint(-5F, 3F, 2F);
        listva4.setTextureSize(64, 32);
        listva4.mirror = true;
        setRotation(listva4, 0F, -0.0743572F, 0.6320364F);
        listva5 = new ModelRenderer(this, 32, 0);
        listva5.addBox(-2F, -1F, -3F, 4, 3, 4);
        listva5.setRotationPoint(1F, 3F, -5F);
        listva5.setTextureSize(64, 32);
        listva5.mirror = true;
        setRotation(listva5, -1.33843F, -1.152537F, 2.007645F);
        Shape1 = new ModelRenderer(this, 32, 0);
        Shape1.addBox(0F, 0F, 0F, 3, 3, 3);
        Shape1.setRotationPoint(4F, 6F, -2F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, -0.2974289F, -0.3717861F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        listva1.render(f5);
        body1.render(f5);
        body2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        kysok1.render(f5);
        kysok2.render(f5);
        kysok3.render(f5);
        kysok4.render(f5);
        listva2.render(f5);
        listva3.render(f5);
        listva4.render(f5);
        listva5.render(f5);
        Shape1.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float time, float moveSpeed, float par3, float yaw, float pitch, float par6, Entity entity) {
        super.setRotationAngles(time, moveSpeed, par3, yaw, pitch, par6, entity);
        this.leg1.rotateAngleX = MathHelper.cos(time * 0.6662F) * 1.4F * moveSpeed;
        this.leg2.rotateAngleX = MathHelper.cos(time * 0.6662F + (float)Math.PI) * 1.4F * moveSpeed;
        this.leg3.rotateAngleX = MathHelper.cos(time * 0.6662F + (float)Math.PI) * 1.4F * moveSpeed;
        this.leg4.rotateAngleX = MathHelper.cos(time * 0.6662F) * 1.4F * moveSpeed;
    }
}