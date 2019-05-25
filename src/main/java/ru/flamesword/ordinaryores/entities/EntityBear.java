package ru.flamesword.ordinaryores.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBear extends EntityMob {

    public EntityBear(World par1World) {
        super(par1World);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.35D);
        this.setSize(1.5F, 1.5F);
        this.setCanPickUpLoot(false);
    }

    @Override
    public boolean getCanSpawnHere() {
        int x = MathHelper.floor_double(this.posX);
        int y = MathHelper.floor_double(this.boundingBox.minY);
        int z = MathHelper.floor_double(this.posZ);
        Block b = this.worldObj.getBlock(x, y, z);

        return super.getCanSpawnHere();
    }

    @Override
    protected Entity findPlayerToAttack() {
        double d0 = 16.0D;
        return this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
    }

    @Override
    protected void attackEntity(Entity p_70785_1_, float p_70785_2_) {
        if (this.rand.nextInt(100) == 0) {
            this.entityToAttack = null;
        } else {
            if (p_70785_2_ > 2.0F && p_70785_2_ < 6.0F && this.rand.nextInt(10) == 0) {
                if (this.onGround) {
                    double d0 = p_70785_1_.posX - this.posX;
                    double d1 = p_70785_1_.posZ - this.posZ;
                    float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                    this.motionX = d0 / (double) f2 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                    this.motionZ = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                    this.motionY = 0.4000000059604645D;
                }
            } else {
                super.attackEntity(p_70785_1_, p_70785_2_);
            }
        }
    }

    @Override
    protected String getLivingSound() {
        if (Math.random() > 0.5) {
            return  "mob.zombiepig.zpigangry";
        } else {
            return "mob.wolf.growl";
        }
    }

    @Override
    protected String getHurtSound() {
        return "game.neutral.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "game.neutral.die";
    }

    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.playSound("mob.pig.step", 0.15F, 1.0F);
    }

    @Override
    protected Item getDropItem() {
        return null;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        if (Math.random() <= 0.25) {
            this.entityDropItem(new ItemStack(Items.leather, 1, rand.nextInt(3)), 0);
        }

        if (Math.random() <= 0.25) {
            this.dropItem(Items.bone, rand.nextInt(2));
        }
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    public String getBanlistName() {
        return "Bear";
    }
}
