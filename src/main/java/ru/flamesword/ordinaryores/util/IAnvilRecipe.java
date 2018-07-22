package ru.flamesword.ordinaryores.util;

import net.minecraft.item.ItemStack;

/*
 * @author VapourDrive
 * https://github.com/VapourDrive/MagTools/tree/master/src/main/java/com/vapourdrive/magtools/anvilhandler
 */

public interface IAnvilRecipe
{
    boolean matches(ItemStack handle, ItemStack toolhead);

    ItemStack getLeftItem();

    ItemStack getResult();

    ItemStack getRightItem();

    int getCost();

    int getMatCost();
}
