package ru.flamesword.ordinaryores.entities;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class EntityGhost extends EntityMob {
	
	private boolean field_146076_bu = false;
	private final EntityAIBreakDoor field_146075_bs = new EntityAIBreakDoor(this);

	public EntityGhost(World par1World) {
		super(par1World);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.setSize(0.6F, 1.8F);
        this.experienceValue = 10;
		//this.isImmuneToFire = true;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.35D);
	}
	
	//c �����
    public int getTotalArmorValue()
    {
        int i = super.getTotalArmorValue() + 2;

        if (i > 20)
        {
            i = 20;
        }

        return i;
    }
    
    protected boolean isAIEnabled()
    {
        return true;
    }
    
    protected int getExperiencePoints(EntityPlayer p_70693_1_)
    {
        if (this.isChild())
        {
            this.experienceValue = (int)((float)this.experienceValue * 7.5F);
        }

        return super.getExperiencePoints(p_70693_1_);
    }
    
    protected void addRandomArmor()
    {
        super.addRandomArmor();

        if (this.rand.nextFloat() < (this.worldObj.difficultySetting == EnumDifficulty.HARD ? 0.05F : 0.01F))
        {
            int i = this.rand.nextInt(3);

            if (i == 0)
            {
                this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
            }
            else
            {
                this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_shovel));
            }
        }
    }
    
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setBoolean("CanBreakDoors", this.func_146072_bX());
    }
    
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        this.func_146070_a(p_70037_1_.getBoolean("CanBreakDoors"));
    }
    
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        Object p_110161_1_1 = super.onSpawnWithEgg(p_110161_1_);
        float f = this.worldObj.func_147462_b(this.posX, this.posY, this.posZ);
        this.setCanPickUpLoot(false);

        this.func_146070_a(this.rand.nextFloat() < f * 0.1F);
        this.addRandomArmor();
        this.enchantEquipment();

        if (this.getEquipmentInSlot(4) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }

        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
        double d0 = this.rand.nextDouble() * 1.5D * (double)this.worldObj.func_147462_b(this.posX, this.posY, this.posZ);

        return (IEntityLivingData)p_110161_1_1;
    }
    
    public boolean func_146072_bX()
    {
        return this.field_146076_bu;
    }

    public void func_146070_a(boolean p_146070_1_)
    {
        if (this.field_146076_bu != p_146070_1_)
        {
            this.field_146076_bu = p_146070_1_;

            if (p_146070_1_)
            {
                this.tasks.addTask(1, this.field_146075_bs);
            }
            else
            {
                this.tasks.removeTask(this.field_146075_bs);
            }
        }
    }
    
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			this.worldObj.spawnParticle("largesmoke", this.posX, this.posY+0.7, this.posZ, 0, 0, 0);
		}
	}
	
	@Override
	public boolean isEntityUndead() {
		return true;
	}
	
	@Override
	public boolean isChild() {
        return false;
    }
	
	public static Item getArmorItemForSlot(int i, int j) {
		return null;
	}
	
	@Override
	public ItemStack getEquipmentInSlot(int i) {
		return null;
	}
	
	@Override
	public ItemStack getHeldItem() {
		return new ItemStack (Items.iron_sword, 1);
	}
	
	@Override
	public String getLivingSound() {
		return "mob.blaze.breathe";
	}
	
	@Override
	public String getHurtSound() {
		return "game.neutral.hurt";
	}
	
	@Override
	public String getDeathSound() {
		return "mob.blaze.death";
	}
	
	@Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		super.attackEntityFrom(source, damage);
		return true;
	}
	
	@Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }
	
	@Override
	 public boolean attackEntityAsMob(Entity entity) {
        boolean flag = super.attackEntityAsMob(entity);

        if (flag)
        {
            int i = this.worldObj.difficultySetting.getDifficultyId();

            if (this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < (float)i * 0.3F)
            {
            	entity.setFire(2 * i);
            }
        }
        
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 0));
		}
		if (entity instanceof EntityMob) {
			EntityMob mob = (EntityMob)entity;
			mob.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 0));
		}
		return flag;
	}
	
	/*@Override
	protected void collideWithEntity(Entity entity) {
		super.collideWithEntity(entity);
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 2));
	}*/
	
	@Override
	protected Item getDropItem() {
		return null;
	}
	
	@Override
	public void dropFewItems(boolean hitRecently, int looting) {
		dropItem(Items.gunpowder, rand.nextInt(2)+1);
		dropItem(Items.gold_nugget, rand.nextInt(2));
	}
	
	@Override
	public void dropRareDrop(int looting) {
		dropItem(Items.gold_ingot, 1);
	}
	
	@Override
	public boolean canDespawn() {
		return true;
	}

	public String getBanlistName() {
		return "Ghost";
	}

}
