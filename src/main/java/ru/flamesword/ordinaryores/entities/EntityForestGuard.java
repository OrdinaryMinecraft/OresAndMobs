package ru.flamesword.ordinaryores.entities;

import java.util.ArrayList;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
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
import ru.flamesword.ordinaryores.items.ItemRegistry;

public class EntityForestGuard extends EntityMob implements IShearable {
	
	public EntityForestGuard(World par1World) {
		super(par1World);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	@Override
	public void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(12, Integer.valueOf(0));
		this.dataWatcher.addObject(13, Integer.valueOf(0));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(7.5D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4375D);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("shearCount", this.dataWatcher.getWatchableObjectInt(12));
		tag.setInteger("hasBeenAttacked", this.getDataWatcher().getWatchableObjectInt(13));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.getDataWatcher().updateObject(12, Integer.valueOf(tag.getInteger("shearCount")));
		this.dataWatcher.updateObject(13, Integer.valueOf(tag.getInteger("hasBeenAttacked")));
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(this.worldObj.isRaining() || this.isInWater()) {
			this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 500, 4));
		}
		/*if(this.worldObj.isDaytime() && this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ)) {
			this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 500, 4));
		}*/
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		super.attackEntityFrom(source, damage);
		if (this.rand.nextInt(19) == 1) {
			if(!this.worldObj.isRemote) {
				for(int i = 0; i < MathHelper.getRandomIntegerInRange(rand, 3, 5); i++) {
					//System.out.println("SHOULD SPAWN");
					EntitySprout sprout = new EntitySprout(this.worldObj);
					sprout.setPosition(this.posX, this.posY, this.posZ);
					this.worldObj.spawnEntityInWorld(sprout);
				}
			}
		}
		return true;
	}
	
	@Override
	public void dropFewItems(boolean hitRecently, int looting) {
		dropItem(Item.getItemFromBlock(Blocks.log), rand.nextInt(2));
		dropItem(Item.getItemFromBlock(Blocks.leaves), rand.nextInt(2));
		dropItem(Items.golden_apple, rand.nextInt(2));
		dropItem(Items.apple, rand.nextInt(5)+1);
	}
	
	@Override
	public void dropRareDrop(int looting) {
		dropItem(ItemRegistry.rootoflife, 1);
	}
	
	@Override
	public String getHurtSound() {
		return "dig.wood";
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return this.getDataWatcher().getWatchableObjectInt(12) < 4;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList();
		
		ret.add(new ItemStack(Blocks.leaves));
		ret.add(new ItemStack(Blocks.log));
		
		this.getDataWatcher().updateObject(12, Integer.valueOf(this.getDataWatcher().getWatchableObjectInt(12) + 1));
		return ret;
	}
	
	/*
	@Override
	public boolean getCanSpawnHere() {
		if(Resources.entityBlockList.containsKey("ForestGuard") && Resources.entityBlockList.get("ForestGuard") == this.worldObj.provider.dimensionId) {
			return false;
		} else {
			return super.getCanSpawnHere();
		}
	} */
	
	@Override
	public boolean canDespawn() {
		return true;
	}

	public String getBanlistName() {
		return "ForestGuard";
	}
}
