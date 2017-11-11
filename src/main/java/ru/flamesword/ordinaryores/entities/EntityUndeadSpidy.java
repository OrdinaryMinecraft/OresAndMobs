package ru.flamesword.ordinaryores.entities;

import net.minecraft.block.Block;
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
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

public class EntityUndeadSpidy extends EntityMob {

	public EntityUndeadSpidy(World p_i1738_1_) {
		super(p_i1738_1_);
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
		this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(4);
		this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed).setBaseValue(0.43D);
		this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage).setBaseValue(3);
	}
	
	@Override
	public boolean canDespawn() {
		return true;
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
	public void dropFewItems(boolean hitRecently, int looting) {
	}	
	
	@Override
	public void dropRareDrop(int looting) {
		dropItem(Items.string, 1);
	}
}
