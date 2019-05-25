package ru.flamesword.ordinaryores.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.items.ItemRegistry;

public class EntityFrostArrow extends EntityArrow {

    public EntityFrostArrow(World world) {
        super(world);
    }

    public EntityFrostArrow(World world, EntityPlayer player, float f) {
        super(world, player, f);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer p_70100_1_)
    {
        if (!this.worldObj.isRemote && isOnGround() && this.arrowShake <= 0)
        {
            boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && p_70100_1_.capabilities.isCreativeMode;

            if (this.canBePickedUp == 1 && !p_70100_1_.inventory.addItemStackToInventory(new ItemStack(ItemRegistry.frostarrow, 1)))
            {
                flag = false;
            }

            if (flag)
            {
                this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                p_70100_1_.onItemPickup(this, 1);
                this.setDead();
            }
        }
    }

    private boolean isOnGround() {
        if (this.ticksExisted > 20) {
            if (Math.abs(this.motionX) <= 0.8 && Math.abs(this.motionY) <= 0.8 && Math.abs(this.motionZ) <= 0.8) {
                return true;
            }
        }
        return false;
    }

}
