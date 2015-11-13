package ru.flamesword.ordinaryores.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSprout extends ModelBase {
    ModelRenderer trunk;
    ModelRenderer arm1;
    ModelRenderer arm2;
    ModelRenderer steam;
    ModelRenderer leaf1;
    ModelRenderer leaf2;
  
  public ModelSprout() {
      textureWidth = 64;
      textureHeight = 32;
    
      trunk = new ModelRenderer(this, 0, 16);
      trunk.addBox(0F, 0F, 0F, 4, 13, 4);
      trunk.setRotationPoint(-2F, 10F, -2F);
      trunk.setTextureSize(64, 32);
      trunk.mirror = true;
      setRotation(trunk, 0F, 0F, 0F);
      arm1 = new ModelRenderer(this, 0, 16);
      arm1.addBox(0F, 0F, 0F, 2, 2, 4);
      arm1.setRotationPoint(2F, 14F, -4F);
      arm1.setTextureSize(64, 32);
      arm1.mirror = true;
      setRotation(arm1, 0F, 0F, 0F);
      arm2 = new ModelRenderer(this, 0, 16);
      arm2.addBox(0F, 0F, 0F, 2, 2, 4);
      arm2.setRotationPoint(-4F, 14F, -4F);
      arm2.setTextureSize(64, 32);
      arm2.mirror = true;
      setRotation(arm2, 0F, 0F, 0F);
      steam = new ModelRenderer(this, 32, 16);
      steam.addBox(0F, 0F, 0F, 1, 4, 1);
      steam.setRotationPoint(-0.5F, 6F, -0.5F);
      steam.setTextureSize(64, 32);
      steam.mirror = true;
      setRotation(steam, 0F, 0F, 0F);
      leaf1 = new ModelRenderer(this, 32, 0);
      leaf1.addBox(0F, 0F, 0F, 5, 1, 4);
      leaf1.setRotationPoint(-5.2F, 6F, -2F);
      leaf1.setTextureSize(64, 32);
      leaf1.mirror = true;
      setRotation(leaf1, 0F, 0F, 0F);
      leaf2 = new ModelRenderer(this, 32, 0);
      leaf2.addBox(0F, 0F, 0F, 5, 1, 4);
      leaf2.setRotationPoint(0F, 6F, -2F);
      leaf2.setTextureSize(64, 32);
      leaf2.mirror = true;
      setRotation(leaf2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    trunk.render(f5);
    arm1.render(f5);
    arm2.render(f5);
    steam.render(f5);
    leaf1.render(f5);
    leaf2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
