package ru.flamesword.ordinaryores;

import java.awt.Color;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import ru.flamesword.ordinaryores.blocks.BlockRegistry;
import ru.flamesword.ordinaryores.entities.*;
import ru.flamesword.ordinaryores.handlers.EventsEventHandler;
import ru.flamesword.ordinaryores.handlers.OrdinaryOresEventHandler;
import ru.flamesword.ordinaryores.handlers.PlayerEventHandler;
import ru.flamesword.ordinaryores.items.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import ru.flamesword.ordinaryores.util.ConfigHelper;
import ru.flamesword.ordinaryores.util.RecipeResistry;

import static ru.flamesword.ordinaryores.items.ItemRegistry.*;

@Mod (modid = "ordinaryores", name = "Ordinary Ores", version = "1.5.1")

public class OrdinaryOresBase {

	public static final String ID = "ordinaryores";
	@Instance("OrdinaryOres")
	public static OrdinaryOresBase instance;
	@SidedProxy(modId=ID, clientSide = "ru.flamesword.ordinaryores.client.ClientProxy", serverSide = "ru.flamesword.ordinaryores.CommonProxy")
	public static CommonProxy proxy;
	public static OrdinaryOresEventHandler eventHandler = new OrdinaryOresEventHandler();
	
	public static MalachiteOreGenerator malachiteoregenerator = new MalachiteOreGenerator();
	public static RubyOreGenerator rubyoregenerator = new RubyOreGenerator();
	public static SapphireOreGenerator sapphireoregenerator = new SapphireOreGenerator();
	public static MagicOreGenerator magicoregenerator = new MagicOreGenerator();
	
	public static CreativeTabs tabOrdinaryOres = new TabOrdinaryOres("ordinaryores");
	
	public static ToolMaterial MALACHITETOOL = EnumHelper.addToolMaterial("MALACHITE", 1, 128, 4.0F, 1.0F, 20);
	public static ArmorMaterial MALACHITEARM = EnumHelper.addArmorMaterial("MALACHITE", 10, new int[] {1, 3, 3, 1}, 20);

	public static ToolMaterial RUBYTOOL = EnumHelper.addToolMaterial("RUBY", 2, 512, 5.0F, 2.0F, 27);
	public static ArmorMaterial RUBYARM = EnumHelper.addArmorMaterial("RUBY", 20, new int[] {2, 6, 6, 2}, 30);

	public static ToolMaterial SAPPHIRETOOL = EnumHelper.addToolMaterial("SAPPHIRE", 3, 1024, 7.0F, 3.0F, 20);
	public static ArmorMaterial SAPPHIREARM = EnumHelper.addArmorMaterial("SAPPHIRE", 30, new int[] {3, 7, 5, 3}, 25);

	public static ToolMaterial MAGICORETOOL = EnumHelper.addToolMaterial("MAGICORE", 3, 1561, 8.0F, 3.0F, 30);
	public static ArmorMaterial MAGICOREARM = EnumHelper.addArmorMaterial("MAGICORE", 44, new int[] {3, 8, 6, 3}, 30);

	public static ArmorMaterial INFERNOARM = EnumHelper.addArmorMaterial("INFERNO", 44, new int[] {3, 8, 6, 3}, 10);

	public static ToolMaterial RARETOOL = EnumHelper.addToolMaterial("RARE", 4, 1561, 10.0F, 4.0F, 30);
	public static ArmorMaterial RAREARM = EnumHelper.addArmorMaterial("RARE", 59, new int[] {3, 8, 6, 3}, 30);

	public static ToolMaterial ARTIFACTTOOL = EnumHelper.addToolMaterial("ARTIFACT", 5, 2096, 10.0F, 4.5F, 30);
	public static ArmorMaterial ARTIFACTARM = EnumHelper.addArmorMaterial("ARTIFACT", 64, new int[] {3, 8, 6, 3}, 30);
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(eventHandler);
		MinecraftForge.EVENT_BUS.register(eventHandler);
		//MinecraftForge.EVENT_BUS.register(new OrdinaryOresEventHandler());
		ConfigHelper.setupConfig(new Configuration(event.getSuggestedConfigurationFile()));

		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		FMLCommonHandler.instance().bus().register(new EventsEventHandler());

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
		EntityRegistry.registerGlobalEntityID(EntityMinionSprout.class, "MinionSprout", EntityRegistry.findGlobalUniqueEntityId());

		EntityRegistry.addSpawn(EntityHerobrine.class, ConfigHelper.herobrineSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(Type.PLAINS));
		EntityRegistry.addSpawn(EntityUndeadSpider.class, ConfigHelper.undeadSpiderSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(Type.DEAD));
		EntityRegistry.addSpawn(EntityEnderSkeleton.class, ConfigHelper.enderSkeletonSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(Type.END));
		EntityRegistry.addSpawn(EntityGhoul.class, ConfigHelper.ghoulSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(Type.PLAINS));
		EntityRegistry.addSpawn(EntityGhost.class, ConfigHelper.ghostSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(Type.DEAD));
		EntityRegistry.addSpawn(EntitySuperSlime.class, ConfigHelper.superSlimeSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(Type.SWAMP));
		EntityRegistry.addSpawn(EntityForestGuard.class, ConfigHelper.forestGuardSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(Type.FOREST));
		EntityRegistry.addSpawn(EntityInfernoGolem.class, ConfigHelper.infernoGolemSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(Type.NETHER));
		EntityRegistry.addSpawn(EntityIceElemental.class, ConfigHelper.iceElementalSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(Type.SNOWY));
		EntityRegistry.addSpawn(EntityEnderCreeper.class, ConfigHelper.enderCreeperSpawnRate, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(Type.END));

		BlockRegistry.registerBlocks();
		ItemRegistry.registerItems();
		RecipeResistry.registerRecipes();
		proxy.registerRenderers();

		if(ConfigHelper.addLootToDungeons) {
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(magicorenugget, 1), 1, 5, 10));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(rubyitem, 1), 1, 4, 10));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(sapphireitem, 1), 1, 3, 10));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(malachiteitem, 1), 1, 6, 10));
		}

        GameRegistry.registerWorldGenerator(malachiteoregenerator, 0);
        GameRegistry.registerWorldGenerator(rubyoregenerator, 0);
        GameRegistry.registerWorldGenerator(sapphireoregenerator, 0);
        GameRegistry.registerWorldGenerator(magicoregenerator, 0);

		OreDictionary.registerOre("ingotMagicOre", ItemRegistry.magicoreingot);
		OreDictionary.registerOre("ingotInferno", ItemRegistry.infernoingot);
		OreDictionary.registerOre("malachite", ItemRegistry.malachiteitem);
		OreDictionary.registerOre("ruby", ItemRegistry.rubyitem);
		OreDictionary.registerOre("sapphire", ItemRegistry.sapphireitem);
	}
}
