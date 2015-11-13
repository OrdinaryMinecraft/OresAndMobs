package ru.flamesword.ordinaryores;

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
	//public static boolean addMobsToDungeons;
	public static boolean addLootToDungeons;
	public static boolean addMalachiteArmor;
	
	public static String effectIndicator;
	public static String artifactIndicator;
	public static String moveSpeedEffectName;
	public static String resistanceEffectName;
	public static String jumpEffectName;
	public static String nightVisionEffectName;
	public static String digSpeedEffectName;
	public static String damageBoostEffectName;
	public static String invisibilityEffectName;
	public static String regenerationEffectName;
	public static String lifestealEffectName;
	public static String freezeEffectName;
	public static Object effectInferno1;
	public static Object effectInferno2;
	public static Object effectInferno3;
	
	
	
	public static void setupConfig(Configuration config) {
		try {
			config.load();
			herobrineSpawnRate = config.get("Spawning", "herobrineSpawnRate", 3).getInt(3);
			forestGuardSpawnRate = config.get("Spawning", "forestGuardSpawnRate", 10).getInt(10);
			iceElementalSpawnRate = config.get("Spawning", "iceElementalSpawnRate", 10).getInt(10);
			superSlimeSpawnRate = config.get("Spawning", "superSlimeSpawnRate", 3).getInt(3);
			stealthCreeperSpawnRate = config.get("Spawning", "stealthCreeperSpawnRate", 5).getInt(5);
			infernoGolemSpawnRate = config.get("Spawning", "infernoGolemSpawnRate", 10).getInt(10);
			enderCreeperSpawnRate = config.get("Spawning", "enderCreeperSpawnRate", 10).getInt(10);
			//addMobsToDungeons = config.get("General", "addMobsToDungeons", true).getBoolean(true);
			addLootToDungeons = config.get("General", "addLootToDungeons", true).getBoolean(true);
			addMalachiteArmor = config.get("General", "addMalachiteArmor", false).getBoolean(false);
			
			effectIndicator = config.get("Effects", "effectIndicator", "(effect)").getString();
			artifactIndicator = config.get("Effects", "artifactIndicator", "\u00a76Artifact").getString();
			moveSpeedEffectName = config.get("Effects", "moveSpeedEffectName", "Move Speed").getString();
			resistanceEffectName = config.get("Effects", "resistanceEffectName", "Resistance").getString();
			jumpEffectName = config.get("Effects", "jumpEffectName", "Jump").getString();
			nightVisionEffectName = config.get("Effects", "nightVisionEffectName", "Night Vision").getString();
			digSpeedEffectName = config.get("Effects", "digSpeedEffectName", "Haste").getString();
			damageBoostEffectName = config.get("Effects", "damageBoostEffectName", "Damage Boost").getString();
			invisibilityEffectName = config.get("Effects", "invisibilityEffectName", "Invisibility").getString();
			regenerationEffectName = config.get("Effects", "regenerationEffectName", "Regeneration").getString();
			lifestealEffectName = config.get("Effects", "lifestealEffectName", "Lifesteal").getString();
			freezeEffectName = config.get("Effects", "freezeEffectName", "Freeze").getString();
			effectInferno1 = config.get("Effects", "effectInferno1", "\u00a78The full Inferno set gives fire and lava invulnerability").getString();
			effectInferno2 = config.get("Effects", "effectInferno2", "Bonus: Fire extinguishing on you immediately").getString();
			effectInferno3 = config.get("Effects", "effectInferno3", "Bonus: Burns enemies attacking you").getString();
			
		} catch(Exception e) {
			System.out.println("A severe error has occured when attempting to load the config file for this mod!");
		} finally {
			if(config.hasChanged()) {
				config.save();
			}
		}
	}

}
