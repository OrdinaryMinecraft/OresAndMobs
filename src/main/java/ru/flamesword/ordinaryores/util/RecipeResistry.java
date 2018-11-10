package ru.flamesword.ordinaryores.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static ru.flamesword.ordinaryores.items.ItemRegistry.*;
import static ru.flamesword.ordinaryores.blocks.BlockRegistry.*;

public class RecipeResistry {

    public static void registerRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(malachiteitem, 9), new Object[] {malachiteblock});
        GameRegistry.addRecipe(new ItemStack(malachiteblock, 1),
                new Object[]{ "AAA", "AAA", "AAA",
                        ('A'), malachiteitem});
        GameRegistry.addRecipe(new ItemStack(malachitepickaxe, 1),
                new Object[]{ "AAA", " B ", " B ",
                        ('A'), malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(malachitehoe, 1),
                new Object[]{ "AA ", " B ", " B ",
                        ('A'), malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(malachitehoe, 1),
                new Object[]{ " AA", " B ", " B ",
                        ('A'), malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(malachiteaxe, 1),
                new Object[]{ "AA ", "AB ", " B ",
                        ('A'), malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(malachiteaxe, 1),
                new Object[]{ " AA", " BA", " B ",
                        ('A'), malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(malachitespade, 1),
                new Object[]{ " A ", " B ", " B ",
                        ('A'), malachiteitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(malachitesword, 1),
                new Object[]{ " A ", " A ", " B ",
                        ('A'), malachiteitem, ('B'), Items.stick});

        if(ConfigHelper.addMalachiteArmor) {
            GameRegistry.addRecipe(new ItemStack(malachiteplate, 1),
                    new Object[]{ "A A", "AAA", "AAA",
                            ('A'), malachiteitem});
            GameRegistry.addRecipe(new ItemStack(malachitepants, 1),
                    new Object[]{ "AAA", "A A", "A A",
                            ('A'), malachiteitem});
            GameRegistry.addRecipe(new ItemStack(malachitehelmet, 1),
                    new Object[]{ "AAA", "A A",
                            ('A'), malachiteitem});
            GameRegistry.addRecipe(new ItemStack(malachiteboots, 1),
                    new Object[]{ "A A", "A A",
                            ('A'), malachiteitem});
        }

        GameRegistry.addShapelessRecipe(new ItemStack(rubyitem, 9), new Object[] {rubyblock});
        GameRegistry.addRecipe(new ItemStack(rubyblock, 1),
                new Object[]{ "AAA", "AAA", "AAA",
                        ('A'), rubyitem});
        GameRegistry.addRecipe(new ItemStack(rubypickaxe, 1),
                new Object[]{ "AAA", " B ", " B ",
                        ('A'), rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(rubyhoe, 1),
                new Object[]{ "AA ", " B ", " B ",
                        ('A'), rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(rubyhoe, 1),
                new Object[]{ " AA", " B ", " B ",
                        ('A'), rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(rubyaxe, 1),
                new Object[]{ "AA ", "AB ", " B ",
                        ('A'), rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(rubyaxe, 1),
                new Object[]{ " AA", " BA", " B ",
                        ('A'), rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(rubyspade, 1),
                new Object[]{ " A ", " B ", " B ",
                        ('A'), rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(rubysword, 1),
                new Object[]{ " A ", " A ", " B ",
                        ('A'), rubyitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(rubyplate, 1),
                new Object[]{ "A A", "AAA", "AAA",
                        ('A'), rubyitem});
        GameRegistry.addRecipe(new ItemStack(rubypants, 1),
                new Object[]{ "AAA", "A A", "A A",
                        ('A'), rubyitem});
        GameRegistry.addRecipe(new ItemStack(rubyhelmet, 1),
                new Object[]{ "AAA", "A A",
                        ('A'), rubyitem});
        GameRegistry.addRecipe(new ItemStack(rubyboots, 1),
                new Object[]{ "A A", "A A",
                        ('A'), rubyitem});

        GameRegistry.addShapelessRecipe(new ItemStack(sapphireitem, 9), new Object[] {sapphireblock});
        GameRegistry.addRecipe(new ItemStack(sapphireblock, 1),
                new Object[]{ "AAA", "AAA", "AAA",
                        ('A'), sapphireitem});
        GameRegistry.addRecipe(new ItemStack(sapphirepickaxe, 1),
                new Object[]{ "AAA", " B ", " B ",
                        ('A'), sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(sapphirehoe, 1),
                new Object[]{ "AA ", " B ", " B ",
                        ('A'), sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(sapphirehoe, 1),
                new Object[]{ " AA", " B ", " B ",
                        ('A'), sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(sapphireaxe, 1),
                new Object[]{ "AA ", "AB ", " B ",
                        ('A'), sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(sapphireaxe, 1),
                new Object[]{ " AA", " BA", " B ",
                        ('A'), sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(sapphirespade, 1),
                new Object[]{ " A ", " B ", " B ",
                        ('A'), sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(sapphiresword, 1),
                new Object[]{ " A ", " A ", " B ",
                        ('A'), sapphireitem, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(sapphireplate, 1),
                new Object[]{ "A A", "AAA", "AAA",
                        ('A'), sapphireitem});
        GameRegistry.addRecipe(new ItemStack(sapphirepants, 1),
                new Object[]{ "AAA", "A A", "A A",
                        ('A'), sapphireitem});
        GameRegistry.addRecipe(new ItemStack(sapphirehelmet, 1),
                new Object[]{ "AAA", "A A",
                        ('A'), sapphireitem});
        GameRegistry.addRecipe(new ItemStack(sapphireboots, 1),
                new Object[]{ "A A", "A A",
                        ('A'), sapphireitem});

        GameRegistry.addShapelessRecipe(new ItemStack(magicorenugget, 9), new Object[] {magicoreingot});
        GameRegistry.addSmelting(magicoreblock, new ItemStack(magicoreingot, 1), 20.0F);
        GameRegistry.addRecipe(new ItemStack(magicoreingot, 1),
                new Object[]{ "AAA", "AAA", "AAA",
                        ('A'), magicorenugget});
        GameRegistry.addRecipe(new ItemStack(magicoreblank, 1),
                new Object[]{ "  A", " A ", "A  ",
                        ('A'), magicoreingot});
        GameRegistry.addRecipe(new ItemStack(magicorelamella, 1),
                new Object[]{ "AAA",
                        ('A'), magicoreingot});
        GameRegistry.addRecipe(new ItemStack(magicoreblank, 1),
                new Object[]{ "A  ", " A ", "  A",
                        ('A'), magicoreingot});
        GameRegistry.addRecipe(new ItemStack(magicorepickaxe, 1),
                new Object[]{ "AAA", " B ", " B ",
                        ('A'), magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(magicorehoe, 1),
                new Object[]{ "AA ", " B ", " B ",
                        ('A'), magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(magicorehoe, 1),
                new Object[]{ " AA", " B ", " B ",
                        ('A'), magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(magicoreaxe, 1),
                new Object[]{ "AA ", "AB ", " B ",
                        ('A'), magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(magicoreaxe, 1),
                new Object[]{ " AA", " BA", " B ",
                        ('A'), magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(magicorespade, 1),
                new Object[]{ " A ", " B ", " B ",
                        ('A'), magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(magicoresword, 1),
                new Object[]{ " A ", " A ", " B ",
                        ('A'), magicoreingot, ('B'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(magicoreplate, 1),
                new Object[]{ "A A", "AAA", "AAA",
                        ('A'), magicoreingot});
        GameRegistry.addRecipe(new ItemStack(magicorepants, 1),
                new Object[]{ "AAA", "A A", "A A",
                        ('A'), magicoreingot});
        GameRegistry.addRecipe(new ItemStack(magicorehelmet, 1),
                new Object[]{ "AAA", "A A",
                        ('A'), magicoreingot});
        GameRegistry.addRecipe(new ItemStack(magicoreboots, 1),
                new Object[]{ "A A", "A A",
                        ('A'), magicoreingot});
        GameRegistry.addShapelessRecipe(new ItemStack(magicorepickaxeaxe, 1), new Object[] {magicorepickaxe, magicoreaxe});

        GameRegistry.addRecipe(new ItemStack(infernoplate, 1),
                new Object[]{ "A A", "AAA", "AAA",
                        ('A'), infernoingot});
        GameRegistry.addRecipe(new ItemStack(infernopants, 1),
                new Object[]{ "AAA", "A A", "A A",
                        ('A'), infernoingot});
        GameRegistry.addRecipe(new ItemStack(infernohelmet, 1),
                new Object[]{ "AAA", "A A",
                        ('A'), infernoingot});
        GameRegistry.addRecipe(new ItemStack(infernoboots, 1),
                new Object[]{ "A A", "A A",
                        ('A'), infernoingot});

        GameRegistry.addShapelessRecipe(new ItemStack(greentorchblock, 1), new Object[] {Blocks.torch, malachiteitem});
        GameRegistry.addShapelessRecipe(new ItemStack(redtorchblock, 1), new Object[] {Blocks.torch, rubyitem});
        GameRegistry.addShapelessRecipe(new ItemStack(bluetorchblock, 1), new Object[] {Blocks.torch, sapphireitem});
        GameRegistry.addShapelessRecipe(new ItemStack(greenglowstoneblock, 1), new Object[] {Blocks.glowstone, malachiteitem});
        GameRegistry.addShapelessRecipe(new ItemStack(redglowstoneblock, 1), new Object[] {Blocks.glowstone, rubyitem});
        GameRegistry.addShapelessRecipe(new ItemStack(blueglowstoneblock, 1), new Object[] {Blocks.glowstone, sapphireitem});

        GameRegistry.addRecipe(new ItemStack(endercloth, 1),
                new Object[]{ "AAA", "BBB",
                        ('A'), enderdust, ('B'), Items.leather});
        GameRegistry.addRecipe(new ItemStack(invisibilitycloak, 1),
                new Object[]{ "ABA", "BBB", "BBB",
                        ('A'), magicoreingot, ('B'), endercloth});

        GameRegistry.addRecipe(new ItemStack(cursesword, 1),
                new Object[]{ " A ", " B ", "CDC",
                        ('A'), Items.nether_star, ('B'), magicoreblank, ('C'), vampiretooth, ('D'), Item.getItemFromBlock(rubyblock)});

        GameRegistry.addRecipe(new ItemStack(coldsword, 1),
                new Object[]{ " A ", " B ", "CDC",
                        ('A'), iceheart, ('B'), magicoreblank, ('C'), Item.getItemFromBlock(Blocks.packed_ice), ('D'), Item.getItemFromBlock(sapphireblock)});

        GameRegistry.addRecipe(new ItemStack(regenerationchest, 1),
                new Object[]{ "C C", "ABA", "CAC",
                        ('A'), Item.getItemFromBlock(malachiteblock), ('B'), rootoflife, ('C'), magicorelamella});

        GameRegistry.addRecipe(new ItemStack(unknownbook, 1),
                new Object[]{ "AAA", "AAA", "AAA",
                        ('A'), magicalpage});

        boolean craftCatacombSword = false;
        if (craftCatacombSword) {
            GameRegistry.addShapelessRecipe(new ItemStack(catacombsword, 1), new Object[] {catacombswordpart1, catacombswordpart2});
        }

        GameRegistry.addRecipe(new ItemStack(frostarrow, 8),
                new Object[]{ "AAA", "ABA", "AAA",
                        ('A'), Items.arrow, ('B'), iceheart});

        GameRegistry.addRecipe(new ItemStack(dragonbonedetail, 1),
                new Object[]{ " AB", " BA", "B  ",
                        ('A'), dragonskin, ('B'), dragonbone});

        GameRegistry.addRecipe(new ItemStack(dragonictoolhead, 1),
                new Object[]{ "AAA", " AA",
                        ('A'), dragoniteingot});

        GameRegistry.addRecipe(new ItemStack(dragonicspearhead, 1),
                new Object[]{ "   A", "AA ", "AA ",
                        ('A'), dragoniteingot});
    }
}
