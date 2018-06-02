package ru.flamesword.ordinaryores.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.util.IIcon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;



@SideOnly(Side.CLIENT)
public class ItemRendererSpear implements IItemRenderer {

    Minecraft mc = Minecraft.getMinecraft();
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch(type){
            case ENTITY:
                return true;
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
                return false;
            default: return false;
        }

    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        switch(type){
            case ENTITY:
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
                return true;

            default: return false;
            //return false;
        }
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch(type){
            case ENTITY: {
                GL11.glPushMatrix();
                TextureManager textureManager = this.mc.getTextureManager();
                textureManager.bindTexture(textureManager.getResourceLocation(item.getItemSpriteNumber()));
                IIcon icon = item.getItem().getIcon(item, 0);
                Tessellator tessellator = Tessellator.instance;
                GL11.glTranslatef(-0.5F, 0F, -0.5F);
                ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED:
            {
                GL11.glPushMatrix();
                TextureManager textureManager = this.mc.getTextureManager();
                textureManager.bindTexture(textureManager.getResourceLocation(item.getItemSpriteNumber()));
                IIcon icon = item.getItem().getIcon(item, 0);
                Tessellator tessellator = Tessellator.instance;
                GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f); //X
                GL11.glRotatef(-45.0f, 0.0f, 1.0f, 0.0f); //Y
                GL11.glRotatef(20.0f, 0.0f, 0.0f, 1.0f); //Z
                GL11.glTranslatef(-1.0f, -1.0f, 0.0f);
                GL11.glScalef(4.0f, 4.0f, 4.0f);
                ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED_FIRST_PERSON:
            {
                GL11.glPushMatrix();
                TextureManager textureManager = this.mc.getTextureManager();
                textureManager.bindTexture(textureManager.getResourceLocation(item.getItemSpriteNumber()));
                IIcon icon = item.getItem().getIcon(item, 0);
                Tessellator tessellator = Tessellator.instance;
                //GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f); //X
                //GL11.glRotatef(220.0f, 0.0f, 1.0f, 0.0f); //Y
                //GL11.glRotatef(-10.0f, 0.0f, 0.0f, 1.0f); //Z
                //GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                GL11.glScalef(1.0f, 1.0f, 1.0f);
                ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
                GL11.glPopMatrix();
                break;
            }
            default:
                break;

        }

    }

}