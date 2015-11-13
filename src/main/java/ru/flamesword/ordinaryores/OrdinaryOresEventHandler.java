package ru.flamesword.ordinaryores;

import java.util.Random;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import ru.flamesword.ordinaryores.entities.EntityLivingBlock;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;


public class OrdinaryOresEventHandler {
	
	public static OrdinaryOresEventHandler	INSTANCE	= new OrdinaryOresEventHandler();
	private final Random random = new Random();
	int k = 0;
	
	@SubscribeEvent
	public void onBlockBreak(BreakEvent event) {
		if(!event.world.isRemote && random.nextInt(199) == 1) {
			if(event.block == Blocks.dirt) {
				EntityLivingBlock livingBlock = new EntityLivingBlock(event.world);

				livingBlock.setBlockType(0);
				livingBlock.setPosition(event.x+0.5, event.y, event.z+0.5);
				event.world.spawnEntityInWorld(livingBlock);
			}
			if(event.block == Blocks.stone) {
                EntityLivingBlock livingBlockStone = new EntityLivingBlock(event.world);
				
				livingBlockStone.setBlockType(1);
				livingBlockStone.setPosition(event.x+0.5, event.y, event.z+0.5);
				event.world.spawnEntityInWorld(livingBlockStone);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		if (event.phase != Phase.START || player.worldObj.isRemote)
		{
			return;
		}
		
		if (!player.capabilities.isCreativeMode)
		{
			player.capabilities.disableDamage = false;
		}
		
		ItemStack helmet = player.getCurrentArmor(3);
		ItemStack plate = player.getCurrentArmor(2);
		ItemStack pants = player.getCurrentArmor(1);
		ItemStack boots = player.getCurrentArmor(0);
		boolean flag1 = player.ticksExisted % 10 != 0;
		boolean flag2 = false;
		
		if (player.ticksExisted % 2 != 0)
		{
			int px = (int) player.posX;
			int py = (int) player.posY;
			int pz = (int) player.posZ;
			
			if (boots != null)
				if (boots.getItem() == OrdinaryOresBase.infernoboots)
				{
					OrdinaryOresUtil.burn(player.worldObj, px, py - 1, pz);
					
					if (flag1 && player.isInWater())
					{
						boots.damageItem(1, player);
						flag2 = true;
					}
				}
			if (pants != null)
				if (pants.getItem() == OrdinaryOresBase.infernopants)
				{
					if (flag1 && player.isInWater())
					{
						pants.damageItem(1, player);
						flag2 = true;
					}
				}
			if (plate != null)
				if (plate.getItem() == OrdinaryOresBase.infernoplate)
				{
					if (flag1 && player.isInWater())
					{
						plate.damageItem(1, player);
						flag2 = true;
					}
				}
			if (helmet != null)
				if (helmet.getItem() == OrdinaryOresBase.infernohelmet)
				{
					if (flag1 && player.isInWater())
					{
						helmet.damageItem(1, player);
						flag2 = true;
					}
				}
			if (flag2) player.worldObj.playAuxSFX(1004, px, py + 1, pz, 0);
			flag2 = false;
		}
	}
	
	@SubscribeEvent
	public void attackEntityFrom(LivingAttackEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			ItemStack helmet = player.getCurrentArmor(3);
			ItemStack plate = player.getCurrentArmor(2);
			ItemStack pants = player.getCurrentArmor(1);
			ItemStack boots = player.getCurrentArmor(0);
		
			if (helmet != null)
				if(helmet.getItem()  == OrdinaryOresBase.infernohelmet) {
					k = k + 1;
				}
			if (plate != null)
				if(plate.getItem() == OrdinaryOresBase.infernoplate) {
					k = k + 2;
				}
			if (pants != null)
				if(pants.getItem() == OrdinaryOresBase.infernopants) {
					k = k + 2;
				}
			if (boots != null)
				if(boots.getItem() == OrdinaryOresBase.infernoboots) {
					k = k + 1;
				}
			if (event.source.getEntity() instanceof EntityMob) {
				EntityMob mob = (EntityMob)event.source.getEntity();
				mob.setFire(k);
				player.worldObj.spawnParticle("lava", player.posX, player.posY+0.7, player.posZ, 0, 0, 0);
			}
			if (event.source.getEntity() instanceof EntityPlayer) {
				EntityPlayer attacking_player = (EntityPlayer)event.source.getEntity();
				attacking_player.setFire(k);
				player.worldObj.spawnParticle("lava", player.posX, player.posY+0.7, player.posZ, 0, 0, 0);
			}
			k = 0;
		}
	}
}
