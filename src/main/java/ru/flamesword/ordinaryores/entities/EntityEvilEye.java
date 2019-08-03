package ru.flamesword.ordinaryores.entities;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityEvilEye extends EntityGhast {

    private Entity targetedEntity;
    private int targetCoodown = 0;

    public EntityEvilEye(World p_i1735_1_) {
        super(p_i1735_1_);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20D);
        this.setSize(1.0F, 1.0F);
        this.isImmuneToFire = false;
        this.experienceValue = 2;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
    }

    @Override
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.isEntityInvulnerable()) {
            return false;
        } else {
            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        //this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);
    }

    private void setRandomCourse() {
        this.waypointX = this.posX + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        this.waypointY = this.posY + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        this.waypointZ = this.posZ + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
    }

    @Override
    protected void updateEntityActionState() {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
            this.setDead();
        }

        this.despawnEntity();
        this.prevAttackCounter = this.attackCounter;
        double d0 = this.waypointX - this.posX;
        double d1 = this.waypointY - this.posY;
        double d2 = this.waypointZ - this.posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D) {
            setRandomCourse();
        }

        //---

        if (this.targetedEntity != null && this.targetedEntity.isDead) {
            this.targetedEntity = null;
        }

        if (this.targetedEntity == null && this.targetCoodown == 0) {
            this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);
        }
        if (targetCoodown > 0) {
            targetCoodown--;
        }

        double d4 = 64.0D;

        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < d4 * d4) {
            double d5 = this.targetedEntity.posX - this.posX;
            double d6 = this.targetedEntity.boundingBox.minY + (double) (this.targetedEntity.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
            double d7 = this.targetedEntity.posZ - this.posZ;
            this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(d5, d7)) * 180.0F / (float) Math.PI;

            if (this.canEntityBeSeen(this.targetedEntity)) {
                this.waypointX = this.targetedEntity.posX;
                this.waypointY = this.targetedEntity.posY + 1;
                this.waypointZ = this.targetedEntity.posZ;
                // speed up?
            } else {
                setRandomCourse();
            }
        } else {
            this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;
        }

        //---

        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            d3 = (double) MathHelper.sqrt_double(d3);

            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3)) {
                this.motionX += d0 / d3 * 0.1D;
                this.motionY += d1 / d3 * 0.1D;
                this.motionZ += d2 / d3 * 0.1D;
            } else {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }
    }

    private boolean isCourseTraversable(double p_70790_1_, double p_70790_3_, double p_70790_5_, double p_70790_7_) {
        double d4 = (this.waypointX - this.posX) / p_70790_7_;
        double d5 = (this.waypointY - this.posY) / p_70790_7_;
        double d6 = (this.waypointZ - this.posZ) / p_70790_7_;
        AxisAlignedBB axisalignedbb = this.boundingBox.copy();

        for (int i = 1; (double) i < p_70790_7_; ++i) {
            axisalignedbb.offset(d4, d5, d6);

            if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected void collideWithEntity(Entity entity) {
        super.collideWithEntity(entity);
        if (entity instanceof EntityPlayer) {
            EntityPlayer target = (EntityPlayer) entity;
            this.attackEntityAsMob(target);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity p_70652_1_) {
        float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (p_70652_1_ instanceof EntityLivingBase) {
            f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) p_70652_1_);
            i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) p_70652_1_);
        }

        boolean flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag) {
            if (i > 0) {
                p_70652_1_.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0) {
                p_70652_1_.setFire(j * 4);
            }

            if (p_70652_1_ instanceof EntityLivingBase) {
                EnchantmentHelper.func_151384_a((EntityLivingBase) p_70652_1_, this);
            }

            EnchantmentHelper.func_151385_b(this, p_70652_1_);
        }

        if (this.targetedEntity != null) {
            if (p_70652_1_.getEntityId() == this.targetedEntity.getEntityId()) {
                this.targetedEntity = null;
                this.targetCoodown = 20;
                setRandomCourse();
            }
        }

        return flag;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 8;
    }

    @Override
    protected float getSoundVolume()
    {
        return 1.0F;
    }
}
