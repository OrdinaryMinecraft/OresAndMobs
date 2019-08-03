package ru.flamesword.powerfulenderdragon;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import ru.flamesword.ordinaryores.util.EntityUtils;
import ru.flamesword.ordinaryores.util.WorldUtils;


public class DragonHandler {

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onEntitySpawn(EntityJoinWorldEvent event) {
		if (event.world.isRemote) {
			return;
		}
		if (event.entity instanceof EntityCreature && EntityUtils.entityIsNotNew((EntityCreature) event.entity)) {
			return;
		}

		if (event.entity.getClass().equals(EntityDragon.class)) {
			WorldUtils.unloadEntity(event.entity);
			EntityAncientEnderDragon dragon = new EntityAncientEnderDragon(event.world);
			dragon.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
			event.world.spawnEntityInWorld(dragon);
			//event.setCanceled(true);
		}
	}
}
