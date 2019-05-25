package ru.flamesword.ordinaryores.handlers;

import java.util.*;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import ru.flamesword.ordinaryores.items.dragonic.ItemDragonicBow;
import ru.flamesword.ordinaryores.items.ItemRegistry;
import ru.flamesword.ordinaryores.util.AnvilManager;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.OrdinaryOresUtil;
import ru.flamesword.ordinaryores.entities.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import ru.flamesword.ordinaryores.util.WorldUtils;


public class OrdinaryOresEventHandler {

	private final Random random = new Random();
	private static final UUID speedID = UUID.fromString("9a34886b-d488-40a0-ac07-ad9da3fbeee3");
	private static double natureBootsSpeedBonus = 0.3;
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
	public void onLivingHurt(LivingHurtEvent event) {
		if (event.entityLiving.getClass() == EntityEnderSkeleton.class) {
			if (this.random.nextInt(1)+1 == 1) {
				EntityEnderSkeleton entity = (EntityEnderSkeleton) event.entityLiving;
				if (entity.teleportRandomly()) {
					event.entityLiving.hurtTime = event.entityLiving.maxHurtTime = 0;
					event.entityLiving.hurtResistantTime = 0;
					event.entityLiving.motionX = 0;
					event.entityLiving.motionY = 0;
					event.entityLiving.motionZ = 0;
					event.entityLiving.setAbsorptionAmount(event.ammount);
				}
			}
		}
		if (event.source.isProjectile()) {
			if (event.source.getSourceOfDamage() instanceof EntityFrostArrow) {
				EntityPlayer player = null;
				if (event.entityLiving instanceof EntityPlayer) {
					player = (EntityPlayer) event.entity;
				}
				if (Objects.isNull(player) || (Objects.nonNull(player) && !player.capabilities.isCreativeMode)) {
					event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 0));
				}
			}
			if (event.source.getEntity() instanceof EntityZigomoreSkeleton) {
				EntityPlayer player = null;
				if (event.entityLiving instanceof EntityPlayer) {
					player = (EntityPlayer) event.entity;
				}
				if (Objects.isNull(player) || (Objects.nonNull(player) && !player.capabilities.isCreativeMode)) {
					event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 1));
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (event.phase != Phase.START || player.worldObj.isRemote) {
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
				if (boots.getItem() == ItemRegistry.infernoboots)
				{
					OrdinaryOresUtil.burn(player.worldObj, px, py - 1, pz);

					if (flag1 && player.isInWater())
					{
						boots.damageItem(1, player);
						flag2 = true;
					}
				}
				else if (boots.getItem() == ItemRegistry.natureboots)
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
				if (pants.getItem() == ItemRegistry.infernopants)
				{
					if (flag1 && player.isInWater())
					{
						pants.damageItem(1, player);
						flag2 = true;
					}
				}
			if (plate != null)
				if (plate.getItem() == ItemRegistry.infernoplate)
				{
					if (flag1 && player.isInWater())
					{
						plate.damageItem(1, player);
						flag2 = true;
					}
				}
			if (helmet != null)
				if (helmet.getItem() == ItemRegistry.infernohelmet)
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
	public void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		WorldUtils.ckeckAndRemovePortals(event.player, event.fromDim, event.toDim);
	}

	@SubscribeEvent
	public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entityLiving;

			boolean naturebootsbonus = false;
			ItemStack boots = player.getCurrentArmor(0);
			if (boots != null) {
				if (boots.getItem() == ItemRegistry.natureboots) {
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
					if(helmet.getItem()  == ItemRegistry.infernohelmet) {
						fireDuration++;
					}
					if(helmet.getItem()  == ItemRegistry.necromanthelmet) {
						witherDuration++;
					}
				}
				if (plate != null) {
					if(plate.getItem() == ItemRegistry.infernoplate) {
						fireDuration++;
					}
					if(plate.getItem() == ItemRegistry.necromantplate) {
						witherDuration++;
					}
				}
				if (pants != null) {
					if(pants.getItem() == ItemRegistry.infernopants) {
						fireDuration++;
					}
					if(pants.getItem() == ItemRegistry.necromantpants) {
						witherDuration++;
					}
				}

				if (boots != null) {
					if(boots.getItem() == ItemRegistry.infernoboots) {
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
				if (helmet.getItem() == ItemRegistry.necromanthelmet && plate.getItem() == ItemRegistry.necromantplate && pants.getItem() == ItemRegistry.necromantpants) {
					if (Math.random() <= 0.1) {
						player.getEntityWorld().playAuxSFX(2003, (int) player.posX-1, (int) player.posY+1, (int) player.posZ, 15);
						player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 1.0F, 1.5F + random.nextFloat() * 0.2F);
						player.setAbsorptionAmount(event.ammount);
					}
				}
			}
		}
	}

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void anvilEvent(AnvilUpdateEvent event) {
        ItemStack leftInput = event.left;
        ItemStack rightInput = event.right;
        int xpCost;
        int matCost;

        if (leftInput == null || rightInput == null)
        {
            return;
        }

        ItemStack Output = AnvilManager.getInstance().getValidRecipe(leftInput, rightInput);

        if (Output != null)
        {
            xpCost = AnvilManager.getInstance().getCost(leftInput, rightInput);
            matCost = AnvilManager.getInstance().getMatCost(leftInput, rightInput);

            if (xpCost <= 0)
            {
                xpCost = 1;
            }

            event.materialCost = matCost;
            event.cost = xpCost;
            event.output = Output.copy();
        }
    }

    @SubscribeEvent
    public void onTooltipItem(ItemTooltipEvent event) {
        if (event.itemStack.getTagCompound() != null) {
            if (event.itemStack.getTagCompound().getInteger("Counter_BlocksDigged") != 0) {
                String lore = StatCollector.translateToLocal("tooltip.item.counter.blocksdigged") + " " + event.itemStack.getTagCompound().getInteger("Counter_BlocksDigged");
                event.toolTip.add(lore);
            }
            if (event.itemStack.getTagCompound().getInteger("Counter_EnemiesKilled") != 0) {
                String lore = StatCollector.translateToLocal("tooltip.item.counter.enemieskilled") + " " + event.itemStack.getTagCompound().getInteger("Counter_EnemiesKilled");
                event.toolTip.add(lore);
            }
        }
    }

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
		Entity killer = event.source.getEntity();
		if (Objects.nonNull(killer) && killer instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) killer;
			if (Objects.nonNull(player.getHeldItem())) {
				if (player.getHeldItem().getItem() == ItemRegistry.dragonicbow || player.getHeldItem().getItem() == ItemRegistry.dragonicbowcharged) {
					ItemStack dragonicbow = player.getHeldItem();
					int enemiesKilled = ItemDragonicBow.getEnemiesKilled(dragonicbow);
					enemiesKilled++;
					NBTTagCompound tagCompound = dragonicbow.getTagCompound();
					tagCompound.setInteger("Counter_EnemiesKilled", enemiesKilled);
					dragonicbow.setTagCompound(tagCompound);
				} else if (player.getHeldItem().getItem() == ItemRegistry.dragonicspear || player.getHeldItem().getItem() == ItemRegistry.dragonicspearcharged) {
					ItemStack dragonicspear = player.getHeldItem();
					int enemiesKilled = ItemDragonicBow.getEnemiesKilled(dragonicspear);
					enemiesKilled++;
					NBTTagCompound tagCompound = dragonicspear.getTagCompound();
					tagCompound.setInteger("Counter_EnemiesKilled", enemiesKilled);
					dragonicspear.setTagCompound(tagCompound);
				}
			}
		}
    }
}
