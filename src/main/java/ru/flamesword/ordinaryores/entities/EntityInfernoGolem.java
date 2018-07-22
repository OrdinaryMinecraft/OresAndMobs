package ru.flamesword.ordinaryores.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import ru.flamesword.ordinaryores.items.ItemRegistry;

public class EntityInfernoGolem extends EntityIronGolem {

	public EntityInfernoGolem(World p_i1694_1_) {
		super(p_i1694_1_);
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.isImmuneToFire = true;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80D);
	}
	
	@Override
	public Item getDropItem() {
		return null;
	}
	
	//player.isImmuneToFire(true);
	
	@Override
	public void dropFewItems(boolean hitRecently, int looting) {
		dropItem(ItemRegistry.infernoingot, rand.nextInt(2)+1);
	}
	
	@Override
    public boolean isBurning() {
        return false;
    }

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		super.attackEntityFrom(source, damage);
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			this.worldObj.spawnParticle("lava", this.posX, this.posY+1, this.posZ, 0, 0.05, 0);
		}
		return true;
	}
	
	@Override
	protected void attackEntity(Entity entity, float par2) {
		super.attackEntity(entity, par2);
		if(this.rand.nextInt(19) == 1) {
			entity.setFire(8);
		}
	}
	
	@Override
	public boolean canDespawn() {
		return true;
	}

	public String getBanlistName() {
		return "Inferno Golem";
	}

}
