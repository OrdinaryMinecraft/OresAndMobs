package ru.flamesword.ordinaryores.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import ru.flamesword.ordinaryores.items.ItemRegistry;

public class EntityEnderCreeper extends EntityCreeper {

	public EntityEnderCreeper(World par1World) {
		super(par1World);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.40D);
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			this.worldObj.spawnParticle("portal", this.posX+rand.nextFloat()*(0.8)-0.4, this.posY+rand.nextFloat()*(2)-0.5, this.posZ+rand.nextFloat()*(0.8)-0.4, 0, 0, 0);
		}
		if ((this.getAttackTarget() != null) && (this.rand.nextInt(50) == 1)) {
			this.addPotionEffect(new PotionEffect(Potion.invisibility.id, 20));
		}
	}
	
	@Override
	public Item getDropItem() {
		return ItemRegistry.enderdust;
	}
	
	/*
	@Override
	public boolean getCanSpawnHere() {
		if(Resources.entityBlockList.containsKey("EnderCreeper")) {
			if(Resources.entityBlockList.get("EnderCreeper") == this.worldObj.provider.dimensionId) {
				return false;
			}
		}
		return super.getCanSpawnHere();
	} */
	
	@Override
	public boolean canDespawn() {
		return true;
	}

	public String getBanlistName() {
		return "EnderCreeper";
	}

}
