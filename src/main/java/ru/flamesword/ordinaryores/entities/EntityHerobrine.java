package ru.flamesword.ordinaryores.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.items.ItemRegistry;

public class EntityHerobrine extends EntityZombie {

	public EntityHerobrine(World par1World) {
		super(par1World);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(350D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0);
		this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
		this.setCustomNameTag("Herobrine");
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataWatcher().addObject(15, Integer.valueOf(0));
	}
	
	@Override
	public boolean isEntityUndead() {
		return false;
	}
	
	@Override
	public String getLivingSound() {
		if (isAngry()) {
            return "mob.endermen.scream";
        }
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
		return null;
	}
	
	@Override
    public boolean isVillager() {
        return false;
    }
	
	@Override
	public boolean isChild() {
        return false;
    }
	
	@Override
	public void onLivingUpdate() {
        super.onLivingUpdate();
		
		if (this.isBurning()) {
			this.extinguish();
		}
		if (this.isAngry()) {
			this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2000, 2));
			this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2000, 2));
		}
		/*if (!this.worldObj.isDaytime() && this.isAngry()) {
			this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2000, 2));
			this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2000, 2));
		}*/
		/*if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && this.isAngry()) {
			this.worldObj.spawnParticle("largesmoke", this.posX, this.posY, this.posZ, 0, 0, 0);
		}*/
	}
	
	public boolean isAngry() {
		return this.getDataWatcher().getWatchableObjectInt(15) == 1;
	}
	
	
	public void setAngry() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.35D);
		this.getDataWatcher().updateObject(15, Integer.valueOf(1));
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		super.attackEntityFrom(source, damage);
		Entity attacker = source.getEntity();
		if(attacker != null && attacker instanceof EntityPlayer) {
            if (!isAngry()) {
                setAngry();
            }
			if(this.rand.nextInt(5) == 1) {
				this.posX = attacker.posX;
				this.posY = attacker.posY;
				this.posZ = attacker.posZ;
				this.setPositionAndUpdate(attacker.posX, attacker.posY, attacker.posZ);
				this.playSound("mob.endermen.portal", 1.0F, 1.0F);
				this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.endermen.portal", 1.0F, 1.0F);
			}
		}
		return true;
	}
	
	/*
	public boolean getCanSpawnHere() {
		if(Resources.entityBlockList.containsKey("IronZombie") && Resources.entityBlockList.get("IronZombie") == this.worldObj.provider.dimensionId) {
			return false;
		} else {
			return super.getCanSpawnHere();
		}
	} */
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("isAngry", this.dataWatcher.getWatchableObjectInt(15));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		if(tag.getInteger("isAngry") == 1) {
			this.getDataWatcher().updateObject(15, Integer.valueOf(1));
		} else {
			this.getDataWatcher().updateObject(15, Integer.valueOf(0));
		}
	}
	
	@Override
	protected Item getDropItem() {
		return null;
	}
	
	@Override
	public void dropFewItems(boolean hitRecently, int looting) {
		dropItem(ItemRegistry.magicoreingot, rand.nextInt(2));
		dropItem(ItemRegistry.unknownbook, rand.nextInt(2));
		dropItem(ItemRegistry.repairtool, rand.nextInt(1));
		dropItem(Items.diamond, rand.nextInt(5));
		dropItem(Items.emerald, rand.nextInt(5));
		dropItem(Items.gold_ingot, rand.nextInt(8));
	}
	
	@Override
	public void dropRareDrop(int looting) {
		dropItem(ItemRegistry.endstone, 1);
	}
	
	@Override
	public boolean canDespawn() {
		return true;
	}

	@Override
    public boolean attackEntityAsMob(Entity entity) {
	    if (isAngry()) {
	        return super.attackEntityAsMob(entity);
        }
        return false;
    }

	public String getBanlistName() {
		return "Herobrine";
	}

}
