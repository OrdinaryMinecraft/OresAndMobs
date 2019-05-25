package ru.flamesword.ordinaryores;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import ru.flamesword.ordinaryores.blocks.BlockRegistry;
import ru.flamesword.ordinaryores.entities.*;
import ru.flamesword.ordinaryores.handlers.*;
import ru.flamesword.ordinaryores.items.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
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
		MinecraftForge.EVENT_BUS.register(new EntitySpawnHandler());
		FMLCommonHandler.instance().bus().register(new EventsEventHandler());
		FMLCommonHandler.instance().bus().register(new WorldEventHandler());

		OrdinaryOresEntityRegistry.registerEntities();
		BlockRegistry.registerBlocks();
		ItemRegistry.registerItems();
		RecipeResistry.registerRecipes();
		proxy.registerRenderers();

		if (ConfigHelper.addLootToDungeons) {
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
