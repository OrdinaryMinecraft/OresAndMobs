package ru.flamesword.ordinaryores.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.items.ItemRegistry;

/*
 * @author VapourDrive
 * https://github.com/VapourDrive/MagTools/tree/master/src/main/java/com/vapourdrive/magtools/anvilhandler
 */

public class AnvilManager
{
    private static final AnvilManager instance = new AnvilManager();
    public List recipes = new ArrayList();

    public AnvilManager()
    {
        this.addItemRecipe(
                Items.bow,
                Item.getItemFromBlock(Blocks.diamond_block),
                1,
                ItemRegistry.reinforcedbow,
                10
        );
        this.addItemRecipe(
                ItemRegistry.reinforcedbow,
                ItemRegistry.iceheart,
                1,
                ItemRegistry.frostbow,
                15
        );
        this.addItemRecipe(
                ItemRegistry.magicoreingot,
                ItemRegistry.dragonblood,
                1,
                ItemRegistry.dragoniteingot,
                6
        );
        this.addItemRecipe(
                ItemRegistry.dragonbonedetail,
                ItemRegistry.dragonbonedetail,
                1,
                ItemRegistry.dragonicbow,
                25
        );
        this.addItemRecipe(
                ItemRegistry.dragonbonedetail,
                ItemRegistry.dragonictoolhead,
                1,
                ItemRegistry.dragonictool,
                25
        );
        this.addItemRecipe(
                ItemRegistry.dragonbonedetail,
                ItemRegistry.dragonicspearhead,
                1,
                ItemRegistry.dragonicspear,
                25
        );
        this.addItemRecipe(
                ItemRegistry.dragonictool,
                ItemRegistry.dragonessense,
                1,
                ItemRegistry.dragonictoolcharged,
                50
        );
        this.addItemRecipe(
                ItemRegistry.dragonicbow,
                ItemRegistry.dragonessense,
            1,
                ItemRegistry.dragonicbowcharged,
            50
        );
        this.addItemRecipe(
                ItemRegistry.dragonicspear,
                ItemRegistry.dragonessense,
                1,
                ItemRegistry.dragonicspearcharged,
                50
        );
    }

    public static AnvilManager getInstance()
    {
        return instance;
    }

    public void addItemRecipe(Item left, Item right, int matCost, Item Tool, int Cost)
    {
        this.addRecipe(new ItemStack(left, 1, 0), new ItemStack(right, 1, 0), matCost, new ItemStack(Tool, 1, 0), Cost);
    }

    public AnvilRecipe addRecipe(ItemStack left, ItemStack right, int matCost, ItemStack Tool, int Cost)
    {
        AnvilRecipe anvilrecipe = new AnvilRecipe(left, right, matCost, Tool, Cost);

        this.recipes.add(anvilrecipe);
        return anvilrecipe;
    }

    public ItemStack getValidRecipe(ItemStack left, ItemStack right)
    {
        List<IAnvilRecipe> recipes = AnvilManager.getInstance().getRecipeList();

        Iterator<IAnvilRecipe> iterator = recipes.iterator();

        while (iterator.hasNext())
        {
            IAnvilRecipe recipe = iterator.next();
            ItemStack Left = recipe.getLeftItem();
            ItemStack Right = recipe.getRightItem();

            if (Left != null && Right != null)
            {
                if (Left.getItem() == left.getItem() && Right.getItem() == right.getItem())
                {
                    if (recipe.getResult() != null)
                    {
                        ItemStack result = recipe.getResult();
                        result.setTagCompound(left.getTagCompound());
                        return result;
                    }
                }
            }
        }

        return null;
    }

    public int getCost(ItemStack left, ItemStack right)
    {
        List<IAnvilRecipe> recipes = AnvilManager.getInstance().getRecipeList();

        Iterator<IAnvilRecipe> iterator = recipes.iterator();

        while (iterator.hasNext())
        {
            IAnvilRecipe recipe = iterator.next();
            ItemStack Left = recipe.getLeftItem();
            ItemStack Right = recipe.getRightItem();

            if (Left != null && Right != null)
            {
                if (Left.getItem() == left.getItem() && Right.getItem() == right.getItem())
                {
                    if (recipe.getResult() != null)
                    {
                        return recipe.getCost();
                    }
                }
            }
        }

        return 1;
    }

    public int getMatCost(ItemStack left, ItemStack right)
    {
        List<IAnvilRecipe> recipes = AnvilManager.getInstance().getRecipeList();

        Iterator<IAnvilRecipe> iterator = recipes.iterator();

        while (iterator.hasNext())
        {
            IAnvilRecipe recipe = iterator.next();
            ItemStack Left = recipe.getLeftItem();
            ItemStack Right = recipe.getRightItem();

            if (Left != null && Right != null)
            {
                if (Left.getItem() == left.getItem() && Right.getItem() == right.getItem())
                {
                    if (recipe.getResult() != null)
                    {
                        return recipe.getMatCost();
                    }
                }
            }
        }

        return 1;
    }

    public List getRecipeList()
    {
        return this.recipes;
    }
}
