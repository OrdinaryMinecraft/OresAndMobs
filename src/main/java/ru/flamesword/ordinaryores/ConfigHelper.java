package ru.flamesword.ordinaryores;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public class ConfigHelper {

	public ConfigHelper() {
		
	}
	
	public static int herobrineSpawnRate;
	public static int forestGuardSpawnRate;
	public static int iceElementalSpawnRate;
	public static int superSlimeSpawnRate;
	public static int stealthCreeperSpawnRate;
	public static int infernoGolemSpawnRate;
	public static int enderCreeperSpawnRate;
	public static int undeadSpiderSpawnRate;
	public static int enderSkeletonSpawnRate;
	public static int ghoulSpawnRate;
	public static int ghostSpawnRate;
	//public static boolean addMobsToDungeons;
	public static boolean addLootToDungeons;
	public static boolean addMalachiteArmor;

	public static String effectIndicator = StatCollector.translateToLocal("tooltip.item.effect");
	public static String artifactIndicator = StatCollector.translateToLocal("tooltip.item.artifact");
	public static String rareIndicator = StatCollector.translateToLocal("tooltip.item.rare");
	public static String moveSpeedEffectName = StatCollector.translateToLocal("tooltip.item.effect.movespeed");
	public static String resistanceEffectName = StatCollector.translateToLocal("tooltip.item.effect.resistance");
	public static String jumpEffectName = StatCollector.translateToLocal("tooltip.item.effect.jump");
	public static String nightVisionEffectName = StatCollector.translateToLocal("tooltip.item.effect.nightvision");
	public static String digSpeedEffectName = StatCollector.translateToLocal("tooltip.item.effect.haste");
	public static String damageBoostEffectName = StatCollector.translateToLocal("tooltip.item.effect.damage");
	public static String invisibilityEffectName = StatCollector.translateToLocal("tooltip.item.effect.invisibility");
	public static String regenerationEffectName = StatCollector.translateToLocal("tooltip.item.effect.regeneration");
	public static String lifestealEffectName = StatCollector.translateToLocal("tooltip.item.effect.lifesteal");
	public static String freezeEffectName = StatCollector.translateToLocal("tooltip.item.effect.freeze");
	public static String blindnessEffectName = StatCollector.translateToLocal("tooltip.item.effect.blindness");
	public static Object effectInferno1 = StatCollector.translateToLocal("tooltip.item.effect.inferno1");
	public static Object effectInferno2 = StatCollector.translateToLocal("tooltip.item.effect.inferno2");
	public static Object effectInferno3 = StatCollector.translateToLocal("tooltip.item.effect.inferno3");
	public static String expEffectName = StatCollector.translateToLocal("tooltip.item.effect.exp");
	public static String thunderEffectName = StatCollector.translateToLocal("tooltip.item.effect.thunder");
	public static Object effectNecromant1 = StatCollector.translateToLocal("tooltip.item.effect.necromant1");
	public static Object effectNecromant2 = StatCollector.translateToLocal("tooltip.item.effect.necromant2");
	public static Object effectNecromant3 = StatCollector.translateToLocal("tooltip.item.effect.necromant3");
	
	
	
	public static void setupConfig(Configuration config) {
		try {
			config.load();
			herobrineSpawnRate = config.get("Spawning", "herobrineSpawnRate", 3).getInt(3);
			forestGuardSpawnRate = config.get("Spawning", "forestGuardSpawnRate", 10).getInt(10);
			iceElementalSpawnRate = config.get("Spawning", "iceElementalSpawnRate", 10).getInt(10);
			superSlimeSpawnRate = config.get("Spawning", "superSlimeSpawnRate", 3).getInt(3);
			infernoGolemSpawnRate = config.get("Spawning", "infernoGolemSpawnRate", 10).getInt(10);
			enderCreeperSpawnRate = config.get("Spawning", "enderCreeperSpawnRate", 10).getInt(10);
			undeadSpiderSpawnRate = config.get("Spawning", "undeadSpiderSpawnRate", 5).getInt(5);
			enderSkeletonSpawnRate = config.get("Spawning", "enderSkeletonSpawnRate", 5).getInt(5);
			ghoulSpawnRate = config.get("Spawning", "ghoulSpawnRate", 5).getInt(5);
			ghostSpawnRate = config.get("Spawning", "ghostSpawnRate", 5).getInt(5);
			//addMobsToDungeons = config.get("General", "addMobsToDungeons", true).getBoolean(true);
			addLootToDungeons = config.get("General", "addLootToDungeons", true).getBoolean(true);
			addMalachiteArmor = config.get("General", "addMalachiteArmor", false).getBoolean(false);
			
		} catch(Exception e) {
			System.out.println("A severe error has occured when attempting to load the config file for this mod!");
		} finally {
			if(config.hasChanged()) {
				config.save();
			}
		}
	}

}
