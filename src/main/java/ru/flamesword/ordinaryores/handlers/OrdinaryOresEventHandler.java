package ru.flamesword.ordinaryores.handlers;

import java.util.Random;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import ru.flamesword.ordinaryores.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.OrdinaryOresUtil;
import ru.flamesword.ordinaryores.entities.EntityEnderSkeleton;
import ru.flamesword.ordinaryores.entities.EntityLivingBlock;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;


public class OrdinaryOresEventHandler {

	private final Random random = new Random();
	private static final UUID speedID = UUID.fromString("9a34886b-d488-40a0-ac07-ad9da3fbeee3");
	private static double natureBootsSpeedBonus = 0.5;
	private static float natureBootsJumpBonus = 1.3F;
	
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
	public void onLivingHurt(LivingHurtEvent event)
	{
		if (event.entityLiving.getClass() == EntityEnderSkeleton.class) {
			if (this.random.nextInt(1)+1 == 1) {
				EntityEnderSkeleton entity = (EntityEnderSkeleton) event.entityLiving;
				if (entity.teleportRandomly())
				event.setCanceled(true);
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
			boolean naturebootsbonus = false;
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
				else if (boots.getItem() == OrdinaryOresBase.natureboots)
				{
					naturebootsbonus = true;
					World world = player.worldObj;
					if (player.ticksExisted % 5 == 0 && Math.random() <= 0.25) {
						int x = (int) Math.floor(player.posX);
						int y = (int) (player.posY - player.getYOffset());
						int z = (int) Math.floor(player.posZ);
						Block block = world.getBlock(x, y, z);
						Integer blockid = Block.getIdFromBlock(block);
						if (block instanceof IPlantable && !ConfigHelper.cropsBlacklist.contains(blockid.toString())) {
							Block soil = world.getBlock(x, y - 1, z);
							if (!soil.isAir(world, x, y - 1, z) && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (IPlantable) block)) {
								ItemDye.applyBonemeal(new ItemStack(Items.dye, 1, 15), world, x, y, z, player);
								world.playAuxSFX(2005, x, y, z, 0);
							}
						}
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

			// Applying bonuses
			IAttributeInstance atinst = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
			AttributeModifier natureBootsModifier;
			natureBootsModifier = new AttributeModifier(speedID, "NatureBootsBonus", natureBootsSpeedBonus, 2);
			if (naturebootsbonus) {
				if (atinst.getModifier(speedID) == null) {
					atinst.applyModifier(natureBootsModifier);
				}
			}
			else {
				if (atinst.getModifier(speedID) != null) {
					atinst.removeModifier(natureBootsModifier);
				}
			}
		}
	}


	@SubscribeEvent
	public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entityLiving;

			boolean naturebootsbonus = false;
			ItemStack boots = player.getCurrentArmor(0);
			if (boots != null) {
				if (boots.getItem() == OrdinaryOresBase.natureboots) {
					naturebootsbonus = true;
				}
			}
			if (naturebootsbonus) {
				player.motionY = player.motionY * natureBootsJumpBonus;
			}
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

			// Inferno and Necromant armor
			{
				int fireDuration = 0;
				int witherDuration = 0;

				if (helmet != null) {
					if(helmet.getItem()  == OrdinaryOresBase.infernohelmet) {
						fireDuration++;
					}
					if(helmet.getItem()  == OrdinaryOresBase.necromanthelmet) {
						witherDuration++;
					}
				}
				if (plate != null) {
					if(plate.getItem() == OrdinaryOresBase.infernoplate) {
						fireDuration++;
					}
					if(plate.getItem() == OrdinaryOresBase.necromantplate) {
						witherDuration++;
					}
				}
				if (pants != null) {
					if(pants.getItem() == OrdinaryOresBase.infernopants) {
						fireDuration++;
					}
					if(pants.getItem() == OrdinaryOresBase.necromantpants) {
						witherDuration++;
					}
				}

				if (boots != null) {
					if(boots.getItem() == OrdinaryOresBase.infernoboots) {
						fireDuration++;
					}
				}

				if (event.source.getEntity() instanceof EntityMob) {
					EntityMob mob = (EntityMob)event.source.getEntity();
					if (fireDuration > 0) {
						mob.setFire(fireDuration);
					}
					if (witherDuration > 0) {
						mob.addPotionEffect(new PotionEffect(Potion.wither.id, witherDuration * 40, 0));
					}
				}
				if (event.source.getEntity() instanceof EntityPlayer) {
					EntityPlayer attacking_player = (EntityPlayer)event.source.getEntity();
					if (fireDuration > 0) {
						attacking_player.setFire(fireDuration);
					}
					if (witherDuration > 0) {
						attacking_player.addPotionEffect(new PotionEffect(Potion.wither.id, witherDuration * 40, 0));
					}
				}
			}

			// Necromant armor
			if (helmet != null && plate != null && pants != null) {
				if (helmet.getItem() == OrdinaryOresBase.necromanthelmet && plate.getItem() == OrdinaryOresBase.necromantplate && pants.getItem() == OrdinaryOresBase.necromantpants) {
					if (Math.random() <= 0.1) {
						System.out.println("test");
						player.getEntityWorld().playAuxSFX(2003, (int) player.posX-1, (int) player.posY+1, (int) player.posZ, 15);
						player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 1.0F, 1.5F + random.nextFloat() * 0.2F);
						event.setCanceled(true);
					}
				}
			}
		}
	}
}
