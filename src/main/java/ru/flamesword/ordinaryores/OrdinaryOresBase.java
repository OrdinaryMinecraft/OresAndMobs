package ru.flamesword.ordinaryores;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
import ru.flamesword.ordinaryores.blocks.BlockBlueGlowstone;
import ru.flamesword.ordinaryores.blocks.BlockBlueTorch;
import ru.flamesword.ordinaryores.blocks.BlockGreenGlowstone;
import ru.flamesword.ordinaryores.blocks.BlockGreenTorch;
import ru.flamesword.ordinaryores.blocks.BlockMagicOre;
import ru.flamesword.ordinaryores.blocks.BlockMalachiteBlock;
import ru.flamesword.ordinaryores.blocks.BlockMalachiteOre;
import ru.flamesword.ordinaryores.blocks.BlockMalachitePillar;
import ru.flamesword.ordinaryores.blocks.BlockMalachiteStairs;
import ru.flamesword.ordinaryores.blocks.BlockMalachiteTiles;
import ru.flamesword.ordinaryores.blocks.BlockRedGlowstone;
import ru.flamesword.ordinaryores.blocks.BlockRedTorch;
import ru.flamesword.ordinaryores.blocks.BlockRubyBlock;
import ru.flamesword.ordinaryores.blocks.BlockRubyOre;
import ru.flamesword.ordinaryores.blocks.BlockSapphireBlock;
import ru.flamesword.ordinaryores.blocks.BlockSapphireOre;
import ru.flamesword.ordinaryores.entities.EntityEnderCreeper;
import ru.flamesword.ordinaryores.entities.EntityEnderSkeleton;
import ru.flamesword.ordinaryores.entities.EntityForestGuard;
import ru.flamesword.ordinaryores.entities.EntityGhost;
import ru.flamesword.ordinaryores.entities.EntityGhoul;
import ru.flamesword.ordinaryores.entities.EntityHerobrine;
import ru.flamesword.ordinaryores.entities.EntityIceElemental;
import ru.flamesword.ordinaryores.entities.EntityInfernoGolem;
import ru.flamesword.ordinaryores.entities.EntityLivingBlock;
import ru.flamesword.ordinaryores.entities.EntitySprout;
import ru.flamesword.ordinaryores.entities.EntityStealthCreeper;
import ru.flamesword.ordinaryores.entities.EntitySuperSlime;
import ru.flamesword.ordinaryores.entities.EntityUndeadSpider;
import ru.flamesword.ordinaryores.entities.EntityUndeadSpidy;
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

@Mod (modid = "ordinaryores", name = "Ordinary Ores", version = "1.4.5")

public class OrdinaryOresBase {
	
	@Instance("OrdinaryOres")
	public static OrdinaryOresBase instance;
	@SidedProxy(clientSide = "ru.flamesword.ordinaryores.client.ClientProxy", serverSide = "ru.flamesword.ordinaryores.CommonProxy")
	public static CommonProxy proxy;
	public static OrdinaryOresEventHandler eventHandler = new OrdinaryOresEventHandler();
	
	public static Block malachiteoreblock;
	public static Block rubyoreblock;
	public static Block sapphireoreblock;
	public static Block magicoreblock;
	
	public static Block malachiteblock;
	public static Block malachitetiles;
	public static Block malachitepillar;
	public static Block malachitestairs;
	public static Block rubyblock;
	public static Block sapphireblock;
	
	public static Item malachiteitem;
	public static Item rubyitem;
	public static Item sapphireitem;
	public static Item magicorenugget;
	public static Item magicoreingot;
	public static Item magicoreblank;
	public static Item magicorelamella;
	
	public static Block greentorchblock;
	public static Block redtorchblock;
	public static Block bluetorchblock;
	public static Block greenglowstoneblock;
	public static Block redglowstoneblock;
	public static Block blueglowstoneblock;
	
	public static Item malachitepickaxe;
	public static Item malachitehoe;
	public static Item malachiteaxe;
	public static Item malachitespade;
	public static Item malachitesword;
	public static Item malachitehelmet;
	public static Item malachiteplate;
	public static Item malachitepants;
	public static Item malachiteboots;
	
	public static Item rubypickaxe;
	public static Item rubyhoe;
	public static Item rubyaxe;
	public static Item rubyspade;
	public static Item rubysword;
	public static Item rubyhelmet;
	public static Item rubyplate;
	public static Item rubypants;
	public static Item rubyboots;
	
	public static Item sapphirepickaxe;
	public static Item sapphirehoe;
	public static Item sapphireaxe;
	public static Item sapphirespade;
	public static Item sapphiresword;
	public static Item sapphirehelmet;
	public static Item sapphireplate;
	public static Item sapphirepants;
	public static Item sapphireboots;
	
	public static Item magicorepickaxe;
	public static Item magicorehoe;
	public static Item magicoreaxe;
	public static Item magicorespade;
	public static Item magicoresword;
	public static Item magicorehelmet;
	public static Item magicoreplate;
	public static Item magicorepants;
	public static Item magicoreboots;
	
	public static Item infernohelmet;
	public static Item infernoplate;
	public static Item infernopants;
	public static Item infernoboots;
	
	public static Item rootoflife;
	public static Item infernoingot;
	public static Item iceheart;
	public static Item enderdust;
	public static Item endercloth;
	public static Item spidergland;
	public static Item vampiretooth;
	public static Item catacombswordpart1;
	public static Item catacombswordpart2;

	public static Item netherstone;
	public static Item endstone;
	
	public static Item invisibilitycloak;
	public static Item cursesword;
	public static Item coldsword;
	public static Item regenerationchest;
	public static Item icesword;
	public static Item vampiresword;
	public static Item catacombsword;
	public static Item purplemace;
	public static Item raingodspear;
	
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
	public static ToolMaterial RARETOOL = EnumHelper.addToolMaterial("RARE", 3, 1561, 10.0F, 4.0F, 30);
	public static ToolMaterial ARTIFACTTOOL = EnumHelper.addToolMaterial("ARTIFACT", 3, 2096, 10.0F, 4.5F, 30);
	public static ArmorMaterial RAREARM = EnumHelper.addArmorMaterial("RARE", 59, new int[] {3, 8, 6, 3}, 30);
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
        //������ �����
		FMLCommonHandler.instance().bus().register(eventHandler);
		MinecraftForge.EVENT_BUS.register(eventHandler);
		//MinecraftForge.EVENT_BUS.register(new OrdinaryOresEventHandler());
		ConfigHelper.setupConfig(new Configuration(event.getSuggestedConfigurationFile()));

		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());

		raingodspear = new ItemRainGodSpear().setUnlocalizedName("raingodspear");
		GameRegistry.registerItem(raingodspear, "raingodspear");

		//�������� 
		proxy.registerRenderers();
		
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
		
		//�����
		malachiteoreblock = new BlockMalachiteOre();
		GameRegistry.registerBlock(malachiteoreblock, "malachiteore");
		rubyoreblock = new BlockRubyOre();
		GameRegistry.registerBlock(rubyoreblock, "rubyore");
		sapphireoreblock = new BlockSapphireOre();
		GameRegistry.registerBlock(sapphireoreblock, "sapphireore");
		magicoreblock = new BlockMagicOre();
		GameRegistry.registerBlock(magicoreblock, "magicore");
		
		malachiteblock = new BlockMalachiteBlock();
		GameRegistry.registerBlock(malachiteblock, "malachiteblock");
		malachitetiles = new BlockMalachiteTiles();
		GameRegistry.registerBlock(malachitetiles, "malachitetiles");
		malachitepillar = new BlockMalachitePillar();
		GameRegistry.registerBlock(malachitepillar, "malachitepillar");
		malachitestairs = new BlockMalachiteStairs();
		GameRegistry.registerBlock(malachitestairs, "malachitestairs");
		rubyblock = new BlockRubyBlock();
		GameRegistry.registerBlock(rubyblock, "rubyblock");
		sapphireblock = new BlockSapphireBlock();
		GameRegistry.registerBlock(sapphireblock, "sapphireblock");
		
		greenglowstoneblock = new BlockGreenGlowstone();
		GameRegistry.registerBlock(greenglowstoneblock, "greenglowstone");
		redglowstoneblock = new BlockRedGlowstone();
		GameRegistry.registerBlock(redglowstoneblock, "redglowstone");
		blueglowstoneblock = new BlockBlueGlowstone();
		GameRegistry.registerBlock(blueglowstoneblock, "blueglowstone");
		greentorchblock = new BlockGreenTorch();
		GameRegistry.registerBlock(greentorchblock, "greentorch");
		redtorchblock = new BlockRedTorch();
		GameRegistry.registerBlock(redtorchblock, "redtorch");
		bluetorchblock = new BlockBlueTorch();
		GameRegistry.registerBlock(bluetorchblock, "bluetorch");
		
		//��������
		malachiteitem = new ItemMalachite().setUnlocalizedName("malachite");
		GameRegistry.registerItem(malachiteitem, "malachite");
		rubyitem = new ItemRuby().setUnlocalizedName("ruby");
		GameRegistry.registerItem(rubyitem, "ruby");
		sapphireitem = new ItemSapphire().setUnlocalizedName("sapphire");
		GameRegistry.registerItem(sapphireitem, "sapphire");
		magicorenugget = new ItemMagicOreNugget().setUnlocalizedName("magicorenugget");
		GameRegistry.registerItem(magicorenugget, "magicorenugget");
		magicoreingot = new ItemMagicOreIngot().setUnlocalizedName("magicoreingot");
		GameRegistry.registerItem(magicoreingot, "magicoreingot");
		magicoreblank = new ItemMagicOreBlank().setUnlocalizedName("magicoreblank");
		GameRegistry.registerItem(magicoreblank, "magicoreblank");
		magicorelamella = new ItemMagicOreLamella().setUnlocalizedName("magicorelamella");
		GameRegistry.registerItem(magicorelamella, "magicorelamella");
		
		malachitepickaxe = new ItemMalachitePickaxe().setUnlocalizedName("malachitepickaxe");
		GameRegistry.registerItem(malachitepickaxe, "malachitepickaxe");
		malachitehoe = new ItemMalachiteHoe().setUnlocalizedName("malachitehoe");
		GameRegistry.registerItem(malachitehoe, "malachitehoe");
		malachiteaxe = new ItemMalachiteAxe().setUnlocalizedName("malachiteaxe");
		GameRegistry.registerItem(malachiteaxe, "malachiteaxe");
		malachitespade = new ItemMalachiteSpade().setUnlocalizedName("malachitespade");
		GameRegistry.registerItem(malachitespade, "malachitespade");
		malachitesword = new ItemMalachiteSword().setUnlocalizedName("malachitesword");
		GameRegistry.registerItem(malachitesword, "malachitesword");
		
		if(ConfigHelper.addMalachiteArmor) {
		malachitehelmet = new MalachiteArmor(0, 0).setUnlocalizedName("malachitehelmet").setTextureName("ordinaryores:MalachiteHelmet");
		malachiteplate = new MalachiteArmor(0, 1).setUnlocalizedName("malachiteplate").setTextureName("ordinaryores:MalachiteChestplate");
		malachitepants = new MalachiteArmor(0, 2).setUnlocalizedName("malachitepants").setTextureName("ordinaryores:MalachiteLeggings");
		malachiteboots = new MalachiteArmor(0, 3).setUnlocalizedName("malachiteboots").setTextureName("ordinaryores:MalachiteBoots");
		GameRegistry.registerItem(malachitehelmet, "malachitehelmet");
		GameRegistry.registerItem(malachiteplate, "malachiteplate");
		GameRegistry.registerItem(malachitepants, "malachitepants");
		GameRegistry.registerItem(malachiteboots, "malachiteboots");
		}
		
		rubypickaxe = new ItemRubyPickaxe().setUnlocalizedName("rubypickaxe");
		GameRegistry.registerItem(rubypickaxe, "rubypickaxe");
		rubyhoe = new ItemRubyHoe().setUnlocalizedName("rubyhoe");
		GameRegistry.registerItem(rubyhoe, "rubyhoe");
		rubyaxe = new ItemRubyAxe().setUnlocalizedName("rubyaxe");
		GameRegistry.registerItem(rubyaxe, "rubyaxe");
		rubyspade = new ItemRubySpade().setUnlocalizedName("rubyspade");
		GameRegistry.registerItem(rubyspade, "rubyspade");
		rubysword = new ItemRubySword().setUnlocalizedName("rubysword");
		GameRegistry.registerItem(rubysword, "rubysword");
		
		rubyhelmet = new RubyArmor(0, 0).setUnlocalizedName("rubyhelmet").setTextureName("ordinaryores:RubyHelmet");
		rubyplate = new RubyArmor(0, 1).setUnlocalizedName("rubyplate").setTextureName("ordinaryores:RubyChestplate");
		rubypants = new RubyArmor(0, 2).setUnlocalizedName("rubypants").setTextureName("ordinaryores:RubyLeggings");
		rubyboots = new RubyArmor(0, 3).setUnlocalizedName("rubyboots").setTextureName("ordinaryores:RubyBoots");
		GameRegistry.registerItem(rubyhelmet, "rubyhelmet");
		GameRegistry.registerItem(rubyplate, "rubyplate");
		GameRegistry.registerItem(rubypants, "rubypants");
		GameRegistry.registerItem(rubyboots, "rubyboots");
	
		sapphirepickaxe = new ItemSapphirePickaxe().setUnlocalizedName("sapphirepickaxe");
		GameRegistry.registerItem(sapphirepickaxe, "sapphirepickaxe");
		sapphirehoe = new ItemSapphireHoe().setUnlocalizedName("sapphirehoe");
		GameRegistry.registerItem(sapphirehoe, "sapphirehoe");
		sapphireaxe = new ItemSapphireAxe().setUnlocalizedName("sapphireaxe");
		GameRegistry.registerItem(sapphireaxe, "sapphireaxe");
		sapphirespade = new ItemSapphireSpade().setUnlocalizedName("sapphirespade");
		GameRegistry.registerItem(sapphirespade, "sapphirespade");
		sapphiresword = new ItemSapphireSword().setUnlocalizedName("sapphiresword");
		GameRegistry.registerItem(sapphiresword, "sapphiresword");
		
		sapphirehelmet = new SapphireArmor(0, 0).setUnlocalizedName("sapphirehelmet").setTextureName("ordinaryores:SapphireHelmet");
		sapphireplate = new SapphireArmor(0, 1).setUnlocalizedName("sapphireplate").setTextureName("ordinaryores:SapphireChestplate");
		sapphirepants = new SapphireArmor(0, 2).setUnlocalizedName("sapphirepants").setTextureName("ordinaryores:SapphireLeggings");
		sapphireboots = new SapphireArmor(0, 3).setUnlocalizedName("sapphireboots").setTextureName("ordinaryores:SapphireBoots");
		GameRegistry.registerItem(sapphirehelmet, "sapphirehelmet");
		GameRegistry.registerItem(sapphireplate, "sapphireplate");
		GameRegistry.registerItem(sapphirepants, "sapphirepants");
		GameRegistry.registerItem(sapphireboots, "sapphireboots");
		
		magicorepickaxe = new ItemMagicOrePickaxe().setUnlocalizedName("magicorepickaxe");
		GameRegistry.registerItem(magicorepickaxe, "magicorepickaxe");
		magicorehoe = new ItemMagicOreHoe().setUnlocalizedName("magicorehoe");
		GameRegistry.registerItem(magicorehoe, "magicorehoe");
		magicoreaxe = new ItemMagicOreAxe().setUnlocalizedName("magicoreaxe");
		GameRegistry.registerItem(magicoreaxe, "magicoreaxe");
		magicorespade = new ItemMagicOreSpade().setUnlocalizedName("magicorespade");
		GameRegistry.registerItem(magicorespade, "magicorespade");
		magicoresword = new ItemMagicOreSword().setUnlocalizedName("magicoresword");
		GameRegistry.registerItem(magicoresword, "magicoresword");
		
		magicorehelmet = new MagicOreArmor(0, 0).setUnlocalizedName("magicorehelmet").setTextureName("ordinaryores:MagicOreHelmet");
		magicoreplate = new MagicOreArmor(0, 1).setUnlocalizedName("magicoreplate").setTextureName("ordinaryores:MagicOrePlate");
		magicorepants = new MagicOreArmor(0, 2).setUnlocalizedName("magicorepants").setTextureName("ordinaryores:MagicOreLeggings");
		magicoreboots = new MagicOreArmor(0, 3).setUnlocalizedName("magicoreboots").setTextureName("ordinaryores:MagicOreBoots");
		GameRegistry.registerItem(magicorehelmet, "magicorehelmet");
		GameRegistry.registerItem(magicoreplate, "magicoreplate");
		GameRegistry.registerItem(magicorepants, "magicorepants");
		GameRegistry.registerItem(magicoreboots, "magicoreboots");
		
		infernohelmet = new InfernoArmor(0, 0).setUnlocalizedName("infernohelmet").setTextureName("ordinaryores:InfernoHelmet");
		infernoplate = new InfernoArmor(0, 1).setUnlocalizedName("infernoplate").setTextureName("ordinaryores:InfernoPlate");
		infernopants = new InfernoArmor(0, 2).setUnlocalizedName("infernopants").setTextureName("ordinaryores:InfernoLeggings");
		infernoboots = new InfernoArmor(0, 3).setUnlocalizedName("infernoboots").setTextureName("ordinaryores:InfernoBoots");
		GameRegistry.registerItem(infernohelmet, "infernohelmet");
		GameRegistry.registerItem(infernoplate, "infernoplate");
		GameRegistry.registerItem(infernopants, "infernopants");
		GameRegistry.registerItem(infernoboots, "infernoboots");
		
		rootoflife = new ItemRootOfLife().setUnlocalizedName("rootoflife");
		GameRegistry.registerItem(rootoflife, "rootoflife");
		infernoingot = new ItemInfernoIngot().setUnlocalizedName("infernoingot");
		GameRegistry.registerItem(infernoingot, "infernoingot");
		iceheart = new ItemIceHeart().setUnlocalizedName("iceheart");
		GameRegistry.registerItem(iceheart, "iceheart");
		enderdust = new ItemEnderDust().setUnlocalizedName("enderdust");
		GameRegistry.registerItem(enderdust, "enderdust");
		endercloth= new ItemEnderCloth().setUnlocalizedName("endercloth");
		GameRegistry.registerItem(endercloth, "endercloth");
		spidergland = new ItemSpiderGland().setUnlocalizedName("spidergland");
		GameRegistry.registerItem(spidergland, "spidergland");
		vampiretooth = new ItemVampireTooth().setUnlocalizedName("vampiretooth");
		GameRegistry.registerItem(vampiretooth, "vampiretooth");
		catacombswordpart1 = new ItemCatacombSwordPart1().setUnlocalizedName("catacombswordpart1");
		GameRegistry.registerItem(catacombswordpart1, "catacombswordpart1");
		catacombswordpart2 = new ItemCatacombSwordPart2().setUnlocalizedName("catacombswordpart2");
		GameRegistry.registerItem(catacombswordpart2, "catacombswordpart2");

		netherstone = new ItemNetherStone().setUnlocalizedName("netherstone");
		GameRegistry.registerItem(netherstone, "netherstone");
		endstone = new ItemEndStone().setUnlocalizedName("endstone");
		GameRegistry.registerItem(endstone, "endstone");
		
		invisibilitycloak = new ItemInvisibilityCloak(0, 1).setUnlocalizedName("invisibilitycloak").setTextureName("ordinaryores:Cloak");
		GameRegistry.registerItem(invisibilitycloak, "invisibilitycloak");
		cursesword = new ItemCurseSword().setUnlocalizedName("cursesword");
		GameRegistry.registerItem(cursesword, "cursesword");
		coldsword = new ItemColdSword().setUnlocalizedName("coldsword");
		GameRegistry.registerItem(coldsword, "coldsword");
		regenerationchest = new ItemChestplateOfRegeneration(0, 1).setUnlocalizedName("regenerationchest").setTextureName("ordinaryores:ChestplateOfInvulnerability");
		GameRegistry.registerItem(regenerationchest, "regenerationchest");
		purplemace = new ItemPurpleMace().setUnlocalizedName("purplemace");
		GameRegistry.registerItem(purplemace, "purplemace");

		icesword = new ItemIceSword().setUnlocalizedName("icesword");
		GameRegistry.registerItem(icesword, "icesword");
		vampiresword = new ItemVampireSword().setUnlocalizedName("vampiresword");
		GameRegistry.registerItem(vampiresword, "vampiresword");
		catacombsword = new ItemCatacombSword().setUnlocalizedName("catacombsword");
		GameRegistry.registerItem(catacombsword, "catacombsword");
		
		//��������� � ��������
		if(ConfigHelper.addLootToDungeons) {
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(OrdinaryOresBase.magicorenugget, 1), 1, 5, 10));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(OrdinaryOresBase.rubyitem, 1), 1, 4, 10));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(OrdinaryOresBase.sapphireitem, 1), 1, 3, 10));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(OrdinaryOresBase.malachiteitem, 1), 1, 6, 10));
		}
		
		//�������� ������� ����� � ������
		/*if(ConfigHelper.addMobsToDungeons) {
			DungeonHooks.addDungeonMob("Forest Guard", 50);
		}*/
		
		//�������
		GameRegistry.addShapelessRecipe(new ItemStack(OrdinaryOresBase.malachiteitem, 9), new Object[] {OrdinaryOresBase.malachiteblock});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachiteblock, 1), 
                new Object[]{ "AAA", "AAA", "AAA",
                ('A'), OrdinaryOresBase.malachiteitem});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachitetiles, 4), 
                new Object[]{ "AA", "AA",
                ('A'), Item.getItemFromBlock(OrdinaryOresBase.malachiteblock)});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachitepillar, 2), 
                new Object[]{ "A", "A",
                ('A'), Item.getItemFromBlock(OrdinaryOresBase.malachiteblock)});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachitestairs, 4), 
                new Object[]{ "A  ", "AA ", "AAA",
                ('A'), Item.getItemFromBlock(OrdinaryOresBase.malachiteblock)});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachitepickaxe, 1), 
                new Object[]{ "AAA", " B ", " B ",
        	('A'), OrdinaryOresBase.malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachitehoe, 1), 
                new Object[]{ "AA ", " B ", " B ",
        	('A'), OrdinaryOresBase.malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachitehoe, 1), 
                new Object[]{ " AA", " B ", " B ",
        	('A'), OrdinaryOresBase.malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachiteaxe, 1), 
                new Object[]{ "AA ", "AB ", " B ",
        	('A'), OrdinaryOresBase.malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachiteaxe, 1), 
        		new Object[]{ " AA", " BA", " B ",
        	('A'), OrdinaryOresBase.malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachitespade, 1), 
                new Object[]{ " A ", " B ", " B ",
        	('A'), OrdinaryOresBase.malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachitesword, 1), 
                new Object[]{ " A ", " A ", " B ",
        	('A'), OrdinaryOresBase.malachiteitem, ('B'), Items.stick});
        
        if(ConfigHelper.addMalachiteArmor) {
			GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachiteplate, 1), 
	                new Object[]{ "A A", "AAA", "AAA",
	                ('A'), OrdinaryOresBase.malachiteitem});
			GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachitepants, 1), 
	                new Object[]{ "AAA", "A A", "A A",
	                ('A'), OrdinaryOresBase.malachiteitem});
			GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachitehelmet, 1), 
	                new Object[]{ "AAA", "A A",
	                ('A'), OrdinaryOresBase.malachiteitem});
			GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.malachiteboots, 1), 
	                new Object[]{ "A A", "A A",
	                ('A'), OrdinaryOresBase.malachiteitem});
        }
		
		GameRegistry.addShapelessRecipe(new ItemStack(OrdinaryOresBase.rubyitem, 9), new Object[] {OrdinaryOresBase.rubyblock});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubyblock, 1), 
                new Object[]{ "AAA", "AAA", "AAA",
                ('A'), OrdinaryOresBase.rubyitem});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubypickaxe, 1), 
                new Object[]{ "AAA", " B ", " B ",
        	('A'), OrdinaryOresBase.rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubyhoe, 1), 
                new Object[]{ "AA ", " B ", " B ",
        	('A'), OrdinaryOresBase.rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubyhoe, 1), 
                new Object[]{ " AA", " B ", " B ",
        	('A'), OrdinaryOresBase.rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubyaxe, 1), 
                new Object[]{ "AA ", "AB ", " B ",
        	('A'), OrdinaryOresBase.rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubyaxe, 1), 
        		new Object[]{ " AA", " BA", " B ",
        	('A'), OrdinaryOresBase.rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubyspade, 1), 
                new Object[]{ " A ", " B ", " B ",
        	('A'), OrdinaryOresBase.rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubysword, 1), 
                new Object[]{ " A ", " A ", " B ",
        	('A'), OrdinaryOresBase.rubyitem, ('B'), Items.stick});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubyplate, 1), 
                new Object[]{ "A A", "AAA", "AAA",
                ('A'), OrdinaryOresBase.rubyitem});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubypants, 1), 
                new Object[]{ "AAA", "A A", "A A",
                ('A'), OrdinaryOresBase.rubyitem});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubyhelmet, 1), 
                new Object[]{ "AAA", "A A",
                ('A'), OrdinaryOresBase.rubyitem});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.rubyboots, 1), 
                new Object[]{ "A A", "A A",
                ('A'), OrdinaryOresBase.rubyitem});
		
		GameRegistry.addShapelessRecipe(new ItemStack(OrdinaryOresBase.sapphireitem, 9), new Object[] {OrdinaryOresBase.sapphireblock});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphireblock, 1), 
                new Object[]{ "AAA", "AAA", "AAA",
                ('A'), OrdinaryOresBase.sapphireitem});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphirepickaxe, 1), 
                new Object[]{ "AAA", " B ", " B ",
        	('A'), OrdinaryOresBase.sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphirehoe, 1), 
                new Object[]{ "AA ", " B ", " B ",
        	('A'), OrdinaryOresBase.sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphirehoe, 1), 
                new Object[]{ " AA", " B ", " B ",
        	('A'), OrdinaryOresBase.sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphireaxe, 1), 
                new Object[]{ "AA ", "AB ", " B ",
        	('A'), OrdinaryOresBase.sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphireaxe, 1), 
        		new Object[]{ " AA", " BA", " B ",
        	('A'), OrdinaryOresBase.sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphirespade, 1), 
                new Object[]{ " A ", " B ", " B ",
        	('A'), OrdinaryOresBase.sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphiresword, 1), 
                new Object[]{ " A ", " A ", " B ",
        	('A'), OrdinaryOresBase.sapphireitem, ('B'), Items.stick});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphireplate, 1), 
                new Object[]{ "A A", "AAA", "AAA",
                ('A'), OrdinaryOresBase.sapphireitem});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphirepants, 1), 
                new Object[]{ "AAA", "A A", "A A",
                ('A'), OrdinaryOresBase.sapphireitem});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphirehelmet, 1), 
                new Object[]{ "AAA", "A A",
                ('A'), OrdinaryOresBase.sapphireitem});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.sapphireboots, 1), 
                new Object[]{ "A A", "A A",
                ('A'), OrdinaryOresBase.sapphireitem});
		
		GameRegistry.addShapelessRecipe(new ItemStack(OrdinaryOresBase.magicorenugget, 9), new Object[] {OrdinaryOresBase.magicoreingot});
		GameRegistry.addSmelting(OrdinaryOresBase.magicoreblock, new ItemStack(OrdinaryOresBase.magicoreingot, 1), 20.0F);
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicoreingot, 1), 
                new Object[]{ "AAA", "AAA", "AAA",
                ('A'), OrdinaryOresBase.magicorenugget});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicoreblank, 1), 
                new Object[]{ "  A", " A ", "A  ",
                ('A'), OrdinaryOresBase.magicoreingot});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicorelamella, 1), 
                new Object[]{ "AAA",
                ('A'), OrdinaryOresBase.magicoreingot});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicoreblank, 1), 
                new Object[]{ "A  ", " A ", "  A",
                ('A'), OrdinaryOresBase.magicoreingot});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicorepickaxe, 1), 
                new Object[]{ "AAA", " B ", " B ",
        	('A'), OrdinaryOresBase.magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicorehoe, 1), 
                new Object[]{ "AA ", " B ", " B ",
        	('A'), OrdinaryOresBase.magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicorehoe, 1), 
                new Object[]{ " AA", " B ", " B ",
        	('A'), OrdinaryOresBase.magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicoreaxe, 1), 
                new Object[]{ "AA ", "AB ", " B ",
        	('A'), OrdinaryOresBase.magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicoreaxe, 1), 
        		new Object[]{ " AA", " BA", " B ",
        	('A'), OrdinaryOresBase.magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicorespade, 1), 
                new Object[]{ " A ", " B ", " B ",
        	('A'), OrdinaryOresBase.magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicoresword, 1), 
                new Object[]{ " A ", " A ", " B ",
        	('A'), OrdinaryOresBase.magicoreingot, ('B'), Items.stick});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicoreplate, 1), 
                new Object[]{ "A A", "AAA", "AAA",
                ('A'), OrdinaryOresBase.magicoreingot});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicorepants, 1), 
                new Object[]{ "AAA", "A A", "A A",
                ('A'), OrdinaryOresBase.magicoreingot});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicorehelmet, 1), 
                new Object[]{ "AAA", "A A",
                ('A'), OrdinaryOresBase.magicoreingot});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.magicoreboots, 1), 
                new Object[]{ "A A", "A A",
                ('A'), OrdinaryOresBase.magicoreingot});
		
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.infernoplate, 1), 
                new Object[]{ "A A", "AAA", "AAA",
                ('A'), OrdinaryOresBase.infernoingot});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.infernopants, 1), 
                new Object[]{ "AAA", "A A", "A A",
                ('A'), OrdinaryOresBase.infernoingot});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.infernohelmet, 1), 
                new Object[]{ "AAA", "A A",
                ('A'), OrdinaryOresBase.infernoingot});
		GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.infernoboots, 1), 
                new Object[]{ "A A", "A A",
                ('A'), OrdinaryOresBase.infernoingot});
		
		GameRegistry.addShapelessRecipe(new ItemStack(OrdinaryOresBase.greentorchblock, 1), new Object[] {Blocks.torch, OrdinaryOresBase.malachiteitem});
		GameRegistry.addShapelessRecipe(new ItemStack(OrdinaryOresBase.redtorchblock, 1), new Object[] {Blocks.torch, OrdinaryOresBase.rubyitem});
		GameRegistry.addShapelessRecipe(new ItemStack(OrdinaryOresBase.bluetorchblock, 1), new Object[] {Blocks.torch, OrdinaryOresBase.sapphireitem});
		GameRegistry.addShapelessRecipe(new ItemStack(OrdinaryOresBase.greenglowstoneblock, 1), new Object[] {Blocks.glowstone, OrdinaryOresBase.malachiteitem});
		GameRegistry.addShapelessRecipe(new ItemStack(OrdinaryOresBase.redglowstoneblock, 1), new Object[] {Blocks.glowstone, OrdinaryOresBase.rubyitem});
		GameRegistry.addShapelessRecipe(new ItemStack(OrdinaryOresBase.blueglowstoneblock, 1), new Object[] {Blocks.glowstone, OrdinaryOresBase.sapphireitem});
		
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.endercloth, 1), 
                new Object[]{ "AAA", "BBB",
        	('A'), OrdinaryOresBase.enderdust, ('B'), Items.leather});
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.invisibilitycloak, 1), 
                new Object[]{ "ABA", "BBB", "BBB",
        	('A'), OrdinaryOresBase.magicoreingot, ('B'), OrdinaryOresBase.endercloth});
        
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.cursesword, 1),
                new Object[]{ " A ", " B ", "CDC",
        	('A'), Items.nether_star, ('B'), OrdinaryOresBase.magicoreblank, ('C'), OrdinaryOresBase.vampiretooth, ('D'), Item.getItemFromBlock(OrdinaryOresBase.rubyblock)});
        
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.coldsword, 1),
                new Object[]{ " A ", " B ", "CDC",
        	('A'), OrdinaryOresBase.iceheart, ('B'), OrdinaryOresBase.magicoreblank, ('C'), Item.getItemFromBlock(Blocks.packed_ice), ('D'), Item.getItemFromBlock(OrdinaryOresBase.sapphireblock)});
        
        GameRegistry.addRecipe(new ItemStack(OrdinaryOresBase.regenerationchest, 1),
                new Object[]{ "C C", "ABA", "CAC",
        	('A'), Item.getItemFromBlock(OrdinaryOresBase.malachiteblock), ('B'), OrdinaryOresBase.rootoflife, ('C'), OrdinaryOresBase.magicorelamella});

        boolean craftCatacombSword = false;
        if (craftCatacombSword) {
			GameRegistry.addShapelessRecipe(new ItemStack(OrdinaryOresBase.catacombsword, 1), new Object[] {OrdinaryOresBase.catacombswordpart1, OrdinaryOresBase.catacombswordpart2});
		}

		//���������
        GameRegistry.registerWorldGenerator(malachiteoregenerator, 0);
        GameRegistry.registerWorldGenerator(rubyoregenerator, 0);
        GameRegistry.registerWorldGenerator(sapphireoregenerator, 0);
        GameRegistry.registerWorldGenerator(magicoregenerator, 0);
        
        //����
		OreDictionary.registerOre("ingotMagicOre", OrdinaryOresBase.magicoreingot);
		//OreDictionary.registerOre("ingotInferno", MBItems.ingotInferno);
	}
}
