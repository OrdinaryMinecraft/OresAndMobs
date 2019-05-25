package ru.flamesword.ordinaryores.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.items.ItemRegistry;

public class EntityZigomoreSkeleton extends EntitySkeleton {

    public EntityZigomoreSkeleton(World par1World) {
        super(par1World);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20D);
        this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.followRange).setBaseValue(20.5D);
    }

    @Override
    public String getLivingSound() {
        return null;
    }

    @Override
    public String getHurtSound() {
        return "mob.skeleton.hurt";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.skeleton.step", 0.15F, 1.0F);
    }

    @Override
    public String getDeathSound() {
        return "mob.skeleton.death";
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            if (Math.random() <= 0.15) {
                this.worldObj.spawnParticle("fireworksSpark", this.posX + rand.nextFloat() * (0.8) - 0.4, this.posY + rand.nextFloat() * (2) + 0.5, this.posZ + rand.nextFloat() * (0.8) - 0.4, 0, 0, 0);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        super.attackEntityFrom(source, damage);
        return true;
    }

    @Override
    protected Item getDropItem() {
        return null;
    }

    @Override
    public void dropFewItems(boolean hitRecently, int looting) {
        dropItem(Items.bone, rand.nextInt(2));
        dropItem(Items.arrow, rand.nextInt(2));
        dropItem(ItemRegistry.frostarrow, rand.nextInt(1));
    }

    @Override
    public void dropRareDrop(int looting) {
        dropItem(Items.bow, 1);
    }

    @Override
    public boolean canDespawn() {
        return true;
    }


    public String getBanlistName() {
        return "ZigomoreSkeleton";
    }

}