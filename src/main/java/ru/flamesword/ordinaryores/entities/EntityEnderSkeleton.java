package ru.flamesword.ordinaryores.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityEnderSkeleton extends EntitySkeleton {

	public EntityEnderSkeleton(World par1World) {
		super(par1World);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30D);
		this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.followRange).setBaseValue(20.5D);
	}
	
	@Override
	public String getLivingSound() {
		return null;
	}
	
	@Override
	public String getHurtSound() {
		return "game.neutral.hurt";
	}
	
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.sheep.step", 0.15F, 1.0F);
    }
	
	@Override
	public String getDeathSound() {
		return "game.neutral.hurt";
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		if (this.isBurning()) {
			this.extinguish();
		}
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			this.worldObj.spawnParticle("portal", this.posX+rand.nextFloat()*(0.8)-0.4, this.posY+rand.nextFloat()*(2)-0.5, this.posZ+rand.nextFloat()*(0.8)-0.4, 0, 0, 0);
		}
	}

    public boolean teleportRandomly() {
		for (int i = 0; i < 10; i++) {
	        double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D;
	        double d1 = this.posY + (double)(this.rand.nextInt(8) - 4);
	        double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D;
	        if (this.teleportTo(d0, d1, d2)) return true;
		}
		return false;
    }

    private boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_) {
        EnderTeleportEvent event = new EnderTeleportEvent(this, p_70825_1_, p_70825_3_, p_70825_5_, 0);

        double d3 = this.posX;
        double d4 = this.posY;
        double d5 = this.posZ;
        this.posX = event.targetX;
        this.posY = event.targetY;
        this.posZ = event.targetZ;
        boolean flag = false;
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, j, k))
        {
            boolean flag1 = false;

            while (!flag1 && j > 0)
            {
                Block block = this.worldObj.getBlock(i, j - 1, k);

                if (block.getMaterial().blocksMovement())
                {
                    flag1 = true;
                }
                else
                {
                    --this.posY;
                    --j;
                }
            }

            if (flag1)
            {
                this.setPosition(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
                {
                    flag = true;
                }
            }
        }

        if (!flag)
        {
            this.setPosition(d3, d4, d5);
            return false;
        }
        else
        {
            short short1 = 128;

            for (int l = 0; l < short1; ++l)
            {
                double d6 = (double)l / ((double)short1 - 1.0D);
                float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
                double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
            }

            this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.playSound("mob.endermen.portal", 1.0F, 1.0F);
            return true;
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
		dropItem(Items.bone, rand.nextInt(3));
		dropItem(Items.arrow, rand.nextInt(3));
	}
	
	@Override
	public void dropRareDrop(int looting) {
		dropItem(Items.ender_pearl, 1);
	}
	
	@Override
	public boolean canDespawn() {
		return true;
	}
	

	public String getBanlistName() {
		return "EnderSkeleton";
	}

}
