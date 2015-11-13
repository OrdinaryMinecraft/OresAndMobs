package ru.flamesword.ordinaryores.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityLivingBlock extends EntityMob {
	
	public EntityLivingBlock(World par1World) {
		super(par1World);
		this.tasks.addTask(0, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.height = 1;
		this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(3);
		this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage).setBaseValue(1);
	}
	
	@Override
	public void entityInit() {
		super.entityInit();
		this.getDataWatcher().addObject(12, Integer.valueOf(0));
	}
	
	public void setBlockType(int type) {
		this.getDataWatcher().updateObject(12, Integer.valueOf(type));
	}
	
	public int getBlockType() {
		return this.getDataWatcher().getWatchableObjectInt(12);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("Type", this.getDataWatcher().getWatchableObjectInt(12));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.getDataWatcher().updateObject(12, Integer.valueOf(tag.getInteger("Type")));
	}
	
	@Override
	public void dropFewItems(boolean hitRecently, int looting) {
		if(getBlockType() == 0) {
			dropItem(Item.getItemFromBlock(Blocks.dirt), 1);
		}
		if(getBlockType() == 1) {
			dropItem( Item.getItemFromBlock(Blocks.stone), 1);
		} else {
			dropItem(Item.getItemFromBlock(Blocks.dirt), 1);
		}
	}
	
	@Override
	public boolean canDespawn() {
		return true;
	}

	public String getBanlistName() {
		return "LivingBlock";
	}

}
