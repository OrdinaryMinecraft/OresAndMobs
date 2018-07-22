package ru.flamesword.ordinaryores.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlockRegistry {

    public static Block greentorchblock;
    public static Block redtorchblock;
    public static Block bluetorchblock;
    public static Block greenglowstoneblock;
    public static Block redglowstoneblock;
    public static Block blueglowstoneblock;

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

    public static void registerBlocks() {
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
    }
}
