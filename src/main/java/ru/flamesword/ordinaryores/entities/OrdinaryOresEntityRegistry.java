package ru.flamesword.ordinaryores.entities;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.BiomeDictionary;
import ru.flamesword.ordinaryores.util.ConfigHelper;

import java.awt.*;

public class OrdinaryOresEntityRegistry {

    public static void registerEntities() {
        EntityRegistry.registerGlobalEntityID(EntityHerobrine.class, "Herobrine", EntityRegistry.findGlobalUniqueEntityId(), 0x191970, 0x00FFFF);
        EntityRegistry.registerGlobalEntityID(EntitySuperSlime.class, "Super Slime", EntityRegistry.findGlobalUniqueEntityId(), Color.GREEN.getRGB(), 0x006400);
        EntityRegistry.registerGlobalEntityID(EntityForestGuard.class, "Forest Guard", EntityRegistry.findGlobalUniqueEntityId(), 0x8B4513, Color.GREEN.getRGB());
        EntityRegistry.registerGlobalEntityID(EntityStealthCreeper.class, "Stealth Creeper", EntityRegistry.findGlobalUniqueEntityId(), Color.GREEN.getRGB(), Color.BLACK.getRGB());
        EntityRegistry.registerGlobalEntityID(EntityLivingBlock.class, "Living Block", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID(EntityInfernoGolem.class, "Inferno Golem", EntityRegistry.findGlobalUniqueEntityId(), Color.ORANGE.getRGB(), Color.RED.getRGB());
        EntityRegistry.registerGlobalEntityID(EntitySprout.class, "Sprout", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID(EntityIceElemental.class, "Ice Elemental", EntityRegistry.findGlobalUniqueEntityId(), Color.CYAN.getRGB(), Color.BLUE.getRGB());
        EntityRegistry.registerGlobalEntityID(EntityEnderCreeper.class, "Ender Creeper", EntityRegistry.findGlobalUniqueEntityId(), Color.BLACK.getRGB(), Color.MAGENTA.getRGB());
        EntityRegistry.registerGlobalEntityID(EntityUndeadSpider.class, "Undead Spider", EntityRegistry.findGlobalUniqueEntityId(), Color.BLACK.getRGB(), Color.GRAY.getRGB());
        EntityRegistry.registerGlobalEntityID(EntityUndeadSpidy.class, "Undead Spiderling", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID(EntityEnderSkeleton.class, "Ender Skeleton", EntityRegistry.findGlobalUniqueEntityId(), Color.WHITE.getRGB(), Color.MAGENTA.getRGB());
        EntityRegistry.registerGlobalEntityID(EntityGhoul.class, "Ghoul", EntityRegistry.findGlobalUniqueEntityId(), Color.GREEN.getRGB(), Color.GRAY.getRGB());
        EntityRegistry.registerGlobalEntityID(EntityGhost.class, "Ghost", EntityRegistry.findGlobalUniqueEntityId(), Color.GRAY.getRGB(), Color.LIGHT_GRAY.getRGB());
        EntityRegistry.registerGlobalEntityID(EntityBandit.class, "Bandit", EntityRegistry.findGlobalUniqueEntityId(), Color.black.getRGB(), Color.GRAY.getRGB());
        EntityRegistry.registerGlobalEntityID(EntityBanditLeader.class, "Bandit Leader", EntityRegistry.findGlobalUniqueEntityId(), Color.black.getRGB(), Color.GRAY.getRGB());
        EntityRegistry.registerGlobalEntityID(EntityRedDragon.class, "Red Dragon", EntityRegistry.findGlobalUniqueEntityId(), Color.red.getRGB(), Color.red.getRGB());
        EntityRegistry.registerGlobalEntityID(EntityFrostArrow.class, "FrostArrow", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID(EntityZigomoreSkeleton.class, "Zigomore Skeleton", EntityRegistry.findGlobalUniqueEntityId(), Color.WHITE.getRGB(), Color.CYAN.getRGB());
        EntityRegistry.registerGlobalEntityID(EntitySentinelTree.class, "Sentinel Tree", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID(EntityBear.class, "Bear", EntityRegistry.findGlobalUniqueEntityId(), Color.decode("#563a34").getRGB(), Color.decode("#3d2421").getRGB());
        EntityRegistry.registerGlobalEntityID(EntityEvilEye.class, "Evil Eye", EntityRegistry.findGlobalUniqueEntityId(), Color.WHITE.getRGB(), Color.RED.getRGB());

        //EntityRegistry.registerModEntity(EntityBear.class, "Bear", 8, OrdinaryOresBase.instance, 160, 5, true);

        //EntityRegistry.addSpawn(EntityUndeadSpider.class, ConfigHelper.undeadSpiderSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.DEAD));
        EntityRegistry.addSpawn(EntityEnderSkeleton.class, ConfigHelper.enderSkeletonSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.END));
        //EntityRegistry.addSpawn(EntityGhoul.class, ConfigHelper.ghoulSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS));
        //EntityRegistry.addSpawn(EntityGhost.class, ConfigHelper.ghostSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.DEAD));
        EntityRegistry.addSpawn(EntitySuperSlime.class, ConfigHelper.superSlimeSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SWAMP));
        EntityRegistry.addSpawn(EntityForestGuard.class, ConfigHelper.forestGuardSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
        EntityRegistry.addSpawn(EntityInfernoGolem.class, ConfigHelper.infernoGolemSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.NETHER));
        EntityRegistry.addSpawn(EntityIceElemental.class, ConfigHelper.iceElementalSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SNOWY));
        EntityRegistry.addSpawn(EntityEnderCreeper.class, ConfigHelper.enderCreeperSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.END));
        EntityRegistry.addSpawn(EntityBear.class, ConfigHelper.bearSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.CONIFEROUS));
    }
}
