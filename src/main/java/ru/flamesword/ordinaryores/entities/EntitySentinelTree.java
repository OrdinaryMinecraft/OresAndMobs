package ru.flamesword.ordinaryores.entities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class EntitySentinelTree extends EntityMob {

    private Double  fixedX = null;
    private Double fixedY = null;
    private Double fixedZ = null;
    private long lastRegenTick = 0;

    public EntitySentinelTree(World p_i1738_1_) {
        super(p_i1738_1_);
        this.experienceValue = 0;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(10);
        this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
        this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage).setBaseValue(0);
        this.setRotation(0,0);
    }

    @Override
    public boolean canDespawn() {
        return true;
    }

    @Override
    public void dropFewItems(boolean hitRecently, int looting) {
        dropItem(Item.getItemFromBlock(Blocks.sapling), 1);
    }

    @Override
    protected Entity findPlayerToAttack()
    {
        return null;
    }

    @Override
    public boolean canAttackClass(Class par1Class)
    {
        return EntityCreeper.class != par1Class && EntityGhast.class != par1Class;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.setRotation(0, 0);

        this.motionX = 0;
        if (this.motionY > 0) {
            this.motionY = 0;
        }
        this.motionZ = 0;

        if (this.fixedX == null || this.fixedY == null || this.fixedZ == null) {
            this.fixedX = this.posX;
            this.fixedY = this.posY;
            this.fixedZ = this.posZ;
        } else {
            this.posX = this.fixedX;
            if (this.posY > this.fixedY) {
                this.posY = this.fixedY;
            }
            this.posZ = this.fixedZ;
        }

        if (this.ticksExisted % 100 == 0) {
            if (lastRegenTick != this.ticksExisted) {
                lastRegenTick = this.ticksExisted;

                int radius = 8;
                //this.worldObj.playAuxSFX(2002, (int)this.posX, (int)this.posY, (int)this.posZ, 20);
                //this.worldObj.playAuxSFX(2002, (int)this.posX, (int)this.posY + 1, (int)this.posZ, 20);
                //this.worldObj.playAuxSFX(2002, (int)this.posX, (int)this.posY + 2, (int)this.posZ, 20);
                this.worldObj.playAuxSFX(2001, (int)this.posX - 1, (int)this.posY, (int)this.posZ - 1, 18);
                this.worldObj.playAuxSFX(2001, (int)this.posX - 1, (int)this.posY + 1, (int)this.posZ - 1, 18);
                this.worldObj.playAuxSFX(2001, (int)this.posX - 1, (int)this.posY + 2, (int)this.posZ - 1, 18);


                List entityList = this.worldObj.getEntitiesWithinAABB(
                        EntityLivingBase.class,
                        AxisAlignedBB.getBoundingBox(
                                this.posX-radius,
                                this.posY-radius,
                                this.posZ-radius,
                                (this.posX + radius),
                                (this.posY + radius),
                                (this.posZ + radius)
                        )
                );
                for (Object obj : entityList) {
                    if (obj instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer) obj;
                        player.worldObj.playAuxSFX(2005, (int)player.posX - 1, (int)(player.posY + 0.3), (int)player.posZ - 1, 0);
                        player.heal(4F);

                    } else if (obj instanceof EntityAnimal) {
                        EntityAnimal animal = (EntityAnimal) obj;
                        animal.worldObj.playAuxSFX(2005, (int)animal.posX - 1, (int)(animal.posY + 0.3), (int)animal.posZ - 1, 0);
                        animal.heal(4F);

                    } else if (obj instanceof EntityMob && !(obj instanceof EntitySentinelTree)) {
                        EntityMob mob = (EntityMob) obj;
                        mob.worldObj.playAuxSFX(2004, (int)mob.posX - 1, (int)(mob.posY + 0.3), (int)mob.posZ - 1, 0);
                        mob.attackEntityFrom(DamageSource.onFire, 4F);
                    }
                }
                int y = (int)this.posY;
                for (int x = (int)this.posX - radius; x < this.posX + radius; x++) {
                    for (int z = (int)this.posZ - radius; z < (int)this.posZ + radius; z++) {

                        Block block = this.worldObj.getBlock(x, y, z);
                        if (block instanceof BlockCrops) {
                            Block soil = this.worldObj.getBlock(x, y - 1, z);
                            if (!soil.isAir(this.worldObj, x, y - 1, z) && soil.canSustainPlant(this.worldObj, x, y - 1, z, ForgeDirection.UP, (IPlantable) block)) {
                                ItemDye.applyBonemeal(new ItemStack(Items.dye, 1, 15), this.worldObj, x, y, z, null);
                                this.worldObj.playAuxSFX(2005, x, y, z, 0);
                            }
                        }
                    }
                }
            }
        }
    }
}
