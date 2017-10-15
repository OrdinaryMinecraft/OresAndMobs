package ru.flamesword.ordinaryores.entities;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class EntityUndeadSpider extends EntityMob {
	
	public EntityUndeadSpider(World par1World) {
		super(par1World);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(7.5D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4375D);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		super.attackEntityFrom(source, damage);
		if (this.rand.nextInt(3) == 1) {
			if(!this.worldObj.isRemote) {
				for(int i = 0; i < MathHelper.getRandomIntegerInRange(rand, 3, 5); i++) {
					EntityUndeadSpiderling spiderling = new EntityUndeadSpiderling(this.worldObj);
					spiderling.setPosition(this.posX, this.posY, this.posZ);
					this.worldObj.spawnEntityInWorld(spiderling);
				}
			}
		}
		return true;
	}
	
	@Override
	public void dropFewItems(boolean hitRecently, int looting) {
		dropItem(Item.getItemFromBlock(Blocks.web), rand.nextInt(2));
		dropItem(Items.string, rand.nextInt(9));
	}
	
	@Override
	public void dropRareDrop(int looting) {
		dropItem(OrdinaryOresBase.spidergland, 1);
	}

    protected String getLivingSound()
    {
        return "mob.spider.say";
    }

    protected String getHurtSound()
    {
        return "mob.spider.say";
    }

    protected String getDeathSound()
    {
        return "mob.spider.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.spider.step", 0.15F, 1.0F);
    }
	
	@Override
	public boolean canDespawn() {
		return true;
	}

	public String getBanlistName() {
		return "UndeadSpider";
	}
}
