package ru.flamesword.ordinaryores.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMinionSprout  extends EntityMob {

    public EntityMinionSprout(World p_i1738_1_) {
        super(p_i1738_1_);
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityMob.class, 1.0D, false));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(10);
        this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
        this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage).setBaseValue(1);
    }

    @Override
    public boolean canDespawn() {
        return true;
    }

    @Override
    public void dropFewItems(boolean hitRecently, int looting) {
        dropItem(Item.getItemFromBlock(Blocks.sapling), 1);
    }
}
