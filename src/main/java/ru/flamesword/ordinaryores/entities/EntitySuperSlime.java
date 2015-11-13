package ru.flamesword.ordinaryores.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySuperSlime extends EntitySlime {

	public EntitySuperSlime(World par1World) {
		super(par1World);
		this.setSlimeSize(MathHelper.getRandomIntegerInRange(rand, 10, 14));
	}
	
	@Override
	public void setDead() {
		this.isDead = true;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(125D);
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if(this.rand.nextInt(10) == 1) {
			player.knockBack(player, 10.5F, 10.5F, 10.5F);
		}
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		int minionSpawnCounter = 0;

		if(this.rand.nextInt(24) == 1) {
			minionSpawnCounter++;
			if(minionSpawnCounter >= 10 && this.getAttackTarget() != null && !this.worldObj.isRemote) {
				EntitySlime slime = new EntitySlime(this.worldObj);
				slime.copyLocationAndAnglesFrom(this);
				this.worldObj.spawnEntityInWorld(slime);
				minionSpawnCounter = 0;
			}
		}
	}
	/*
	
	@Override
	public boolean getCanSpawnHere() {
		super.getCanSpawnHere();
		if(Resources.entityBlockList.containsKey("SuperSlime")) {
			return Resources.entityBlockList.get("SuperSlime") == this.worldObj.provider.dimensionId;
		} else {
			return true;
		}
	} */
	
	/*@Override
	public Item getDropItem() {
		return Item.getItemFromBlock(MBBlocks.blockSlime);
	}
	
	@Override
	public void dropFewItems(boolean hitRecently, int looting) {
		if(hitRecently) {
			this.dropItem(Item.getItemFromBlock(MBBlocks.blockSlime), rand.nextInt(3));
			this.dropItem(Items.slime_ball, rand.nextInt(9));
		}
	}*/
	
	@Override
	public boolean canDespawn() {
		return true;
	}

	public String getBanlistName() {
		return "SuperSlime";
	}

}
