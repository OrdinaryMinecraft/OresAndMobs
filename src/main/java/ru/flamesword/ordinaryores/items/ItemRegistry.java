package ru.flamesword.ordinaryores.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.items.dragonic.*;
import ru.flamesword.ordinaryores.items.magicore.*;
import ru.flamesword.ordinaryores.items.malachite.*;
import ru.flamesword.ordinaryores.items.ruby.*;
import ru.flamesword.ordinaryores.items.sapphire.*;
import ru.flamesword.ordinaryores.util.ConfigHelper;


public class ItemRegistry {

    public static Item malachiteitem;
    public static Item rubyitem;
    public static Item sapphireitem;
    public static Item magicorenugget;
    public static Item magicoreingot;
    public static Item magicoreblank;
    public static Item magicorelamella;

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
    public static Item magicorepickaxeaxe;
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
    public static Item magicalpage;
    public static Item unknownbook;
    public static Item unknownbook2;
    public static Item frostarrow;
    public static Item dragonbone;
    public static Item dragonbonedetail;
    public static Item dragonictoolhead;
    public static Item dragonicspearhead;
    public static Item dragonskin;
    public static Item dragonblood;
    public static Item dragonessense;
    public static Item dragoniteingot;
    public static Item repairtool;

    public static Item netherstone;
    public static Item endstone;
    public static Item worldstone;

    public static Item invisibilitycloak;
    public static Item cursesword;
    public static Item coldsword;
    public static Item regenerationchest;
    public static Item icesword;
    public static Item reinforcedbow;
    public static Item frostbow;
    public static Item vampiresword;
    public static Item catacombsword;
    public static Item purplemace;
    public static Item raingodspear;
    public static Item natureboots;

    public static Item dragonicbow;
    public static Item dragonicbowcharged;
    public static Item dragonictool;
    public static Item dragonictoolcharged;
    public static Item dragonicspear;
    public static Item dragonicspearcharged;

    public static Item necromanthelmet;
    public static Item necromantplate;
    public static Item necromantpants;

    public static void registerItems() {

        raingodspear = new ItemRainGodSpear().setUnlocalizedName("raingodspear");
        GameRegistry.registerItem(raingodspear, "raingodspear");

        dragonicbow = new ItemDragonicBow().setUnlocalizedName("dragonicbow");
        GameRegistry.registerItem(dragonicbow, "dragonicbow");

        dragonicbowcharged = new ItemDragonicBowCharged().setUnlocalizedName("dragonicbowcharged");
        GameRegistry.registerItem(dragonicbowcharged, "dragonicbowcharged");

        dragonictool = new ItemDragonicTool().setUnlocalizedName("dragonictool");
        GameRegistry.registerItem(dragonictool, "dragonictool");

        dragonictoolcharged = new ItemDragonicToolCharged().setUnlocalizedName("dragonictoolcharged");
        GameRegistry.registerItem(dragonictoolcharged, "dragonictoolcharged");

        dragonicspear = new ItemDragonicSpear().setUnlocalizedName("dragonicspear");
        GameRegistry.registerItem(dragonicspear, "dragonicspear");

        dragonicspearcharged = new ItemDragonicSpearCharged().setUnlocalizedName("dragonicspearcharged");
        GameRegistry.registerItem(dragonicspearcharged, "dragonicspearcharged");

        malachiteitem = new Item().setUnlocalizedName("malachite").setTextureName("ordinaryores:Malachite").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(malachiteitem, "malachite");
        rubyitem = new Item().setUnlocalizedName("ruby").setTextureName("ordinaryores:Ruby").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(rubyitem, "ruby");
        sapphireitem = new Item().setUnlocalizedName("sapphire").setTextureName("ordinaryores:Sapphire").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(sapphireitem, "sapphire");
        magicorenugget = new Item().setUnlocalizedName("magicorenugget").setTextureName("ordinaryores:MagicOreNugget").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(magicorenugget, "magicorenugget");
        magicoreingot = new Item().setUnlocalizedName("magicoreingot").setTextureName("ordinaryores:MagicOreIngot").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(magicoreingot, "magicoreingot");
        magicoreblank = new Item().setUnlocalizedName("magicoreblank").setTextureName("ordinaryores:MagicOreBlank").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(magicoreblank, "magicoreblank");
        magicorelamella = new Item().setUnlocalizedName("magicorelamella").setTextureName("ordinaryores:MagicOreLamella").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
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
        magicorepickaxeaxe = new ItemMagicOrePickaxeAxe().setUnlocalizedName("magicorepickaxeaxe");
        GameRegistry.registerItem(magicorepickaxeaxe, "magicorepickaxeaxe");

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
        infernoingot = new Item().setUnlocalizedName("infernoingot").setTextureName("ordinaryores:InfernoIngot").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(infernoingot, "infernoingot");
        iceheart = new Item().setUnlocalizedName("iceheart").setTextureName("ordinaryores:IceHeart").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(iceheart, "iceheart");
        enderdust = new ItemEnderDust().setUnlocalizedName("enderdust");
        GameRegistry.registerItem(enderdust, "enderdust");
        endercloth= new Item().setUnlocalizedName("endercloth").setTextureName("ordinaryores:EnderCloth").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(endercloth, "endercloth");
        spidergland = new Item().setUnlocalizedName("spidergland").setTextureName("ordinaryores:SpiderGland").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(spidergland, "spidergland");
        vampiretooth = new Item().setUnlocalizedName("vampiretooth").setTextureName("ordinaryores:VampireTooth").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(vampiretooth, "vampiretooth");
        catacombswordpart1 = new Item().setUnlocalizedName("catacombswordpart1").setTextureName("ordinaryores:CatacombSword1").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres).setMaxStackSize(1);
        GameRegistry.registerItem(catacombswordpart1, "catacombswordpart1");
        catacombswordpart2 = new Item().setUnlocalizedName("catacombswordpart2").setTextureName("ordinaryores:CatacombSword2").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres).setMaxStackSize(1);
        GameRegistry.registerItem(catacombswordpart2, "catacombswordpart2");
        magicalpage = new Item().setUnlocalizedName("magicalpage").setTextureName("ordinaryores:MagicalPage").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(magicalpage, "magicalpage");
        unknownbook = new ItemUnknownBook().setUnlocalizedName("unknownbook");
        GameRegistry.registerItem(unknownbook, "unknownbook");
        unknownbook2 = new ItemUnknownBook2().setUnlocalizedName("unknownbook2");
        GameRegistry.registerItem(unknownbook2, "unknownbook2");
        dragonbone = new Item().setUnlocalizedName("dragonbone").setTextureName("ordinaryores:DragonBone").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(dragonbone, "dragonbone");
        dragonbonedetail = new Item().setUnlocalizedName("dragonbonedetail").setTextureName("ordinaryores:DragonBoneDetail").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres).setMaxStackSize(1);
        GameRegistry.registerItem(dragonbonedetail, "dragonbonedetail");
        dragonictoolhead = new Item().setUnlocalizedName("dragonictoolhead").setTextureName("ordinaryores:DragonicToolHead").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres).setMaxStackSize(1);
        GameRegistry.registerItem(dragonictoolhead, "dragonictoolhead");
        dragonicspearhead = new Item().setUnlocalizedName("dragonicspearhead").setTextureName("ordinaryores:DragonicSpearHead").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres).setMaxStackSize(1);
        GameRegistry.registerItem(dragonicspearhead, "dragonicspearhead");
        dragonskin = new Item().setUnlocalizedName("dragonskin").setTextureName("ordinaryores:DragonSkin").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(dragonskin, "dragonskin");
        dragonblood = new Item().setUnlocalizedName("dragonblood").setTextureName("ordinaryores:DragonBlood").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres).setMaxStackSize(1);
        GameRegistry.registerItem(dragonblood, "dragonblood");
        dragonessense = new Item().setUnlocalizedName("dragonessense").setTextureName("ordinaryores:DragonEssense").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(dragonessense, "dragonessense");
        dragoniteingot = new Item().setUnlocalizedName("dragoniteingot").setTextureName("ordinaryores:DragoniteIngot").setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        GameRegistry.registerItem(dragoniteingot, "dragoniteingot");
        repairtool = new ItemRepairTool().setUnlocalizedName("repairtool");
        GameRegistry.registerItem(repairtool, "repairtool");
        frostarrow = new ItemFrostArrow().setUnlocalizedName("frostarrow");
        GameRegistry.registerItem(frostarrow, "frostarrow");

        netherstone = new ItemNetherStone().setUnlocalizedName("netherstone");
        GameRegistry.registerItem(netherstone, "netherstone");
        endstone = new ItemEndStone().setUnlocalizedName("endstone");
        GameRegistry.registerItem(endstone, "endstone");
        worldstone = new ItemWorldStone().setUnlocalizedName("worldstone");
        GameRegistry.registerItem(worldstone, "worldstone");

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
        reinforcedbow = new ItemReinforcedBow().setUnlocalizedName("reinforcedbow");
        GameRegistry.registerItem(reinforcedbow, "reinforcedbow");
        frostbow = new ItemFrostBow().setUnlocalizedName("frostbow");
        GameRegistry.registerItem(frostbow, "frostbow");
        vampiresword = new ItemVampireSword().setUnlocalizedName("vampiresword");
        GameRegistry.registerItem(vampiresword, "vampiresword");
        catacombsword = new ItemCatacombSword().setUnlocalizedName("catacombsword");
        GameRegistry.registerItem(catacombsword, "catacombsword");
        natureboots = new ItemNatureBoots(0, 3).setUnlocalizedName("natureboots").setTextureName("ordinaryores:NatureBoots");
        GameRegistry.registerItem(natureboots, "natureboots");

        necromanthelmet = new NecromantArmor(0, 0).setUnlocalizedName("necromanthelmet").setTextureName("ordinaryores:NecromantHelmet");
        necromantplate = new NecromantArmor(0, 1).setUnlocalizedName("necromantplate").setTextureName("ordinaryores:NecromantChestplate");
        necromantpants = new NecromantArmor(0, 2).setUnlocalizedName("necromantpants").setTextureName("ordinaryores:NecromantLeggings");
        GameRegistry.registerItem(necromanthelmet, "necromanthelmet");
        GameRegistry.registerItem(necromantplate, "necromantplate");
        GameRegistry.registerItem(necromantpants, "necromantpants");
    }
}
