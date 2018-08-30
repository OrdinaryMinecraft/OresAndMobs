package ru.flamesword.artifacts;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

public class ArtifactsUtils {

    private static Random random = new Random();

    public static int randomBetween(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static ItemStack getRandomArtifact(short level, String from) {
        ItemStack result = null;
        int number = randomBetween(0, ConfigHelper.itemsForDrop.size() - 1);
        Item item = Item.getItemById(Integer.parseInt(ConfigHelper.itemsForDrop.get(number)));
        result = new ItemStack(item, 1);

        String word1 = "";
        if (result.getDisplayName().substring(result.getDisplayName().length() - 1).equals("а")) {
            // Женский род
            number = randomBetween(0, ConfigHelper.nameParts1f.size()) - 1;
            word1 = ConfigHelper.nameParts1f.get(number);
        } if (result.getDisplayName().substring(result.getDisplayName().length() - 1).equals("и") || result.getDisplayName().substring(result.getDisplayName().length() - 1).equals("ы")) {
            // Множественное число
            number = randomBetween(0, ConfigHelper.nameParts1p.size()) - 1;
            word1 = ConfigHelper.nameParts1p.get(number);
        } else {
            // Мужской род
            number = randomBetween(0, ConfigHelper.nameParts1m.size()) - 1;
            word1 = ConfigHelper.nameParts1m.get(number);
        }

        number = randomBetween(0, ConfigHelper.nameParts2.size() - 1);
        String word2 = ConfigHelper.nameParts2.get(number);

        number = randomBetween(0, ConfigHelper.nameParts3.size() - 1);
        String word3 = ConfigHelper.nameParts3.get(number);

        if (Math.random() <= 0.8 * level) {
            result.setStackDisplayName(word1 + " " + result.getDisplayName().toLowerCase());
        }
        result.setStackDisplayName("§b" + result.getDisplayName());

        if (Math.random() <= 0.2 * level) {
            result.setStackDisplayName(result.getDisplayName() + " " + word2);
        }

        if (Math.random() <= 0.2 * level) {
            result.setStackDisplayName(result.getDisplayName() + " " + word3);
        }

        result = addIndicator(result, level);
        result = addLore(result, from);

        EnchantmentHelper.addRandomEnchantment(random, result, 10 * level);

        System.out.println("CREATED ARTIFACT level:" + level + " item:" + item.getUnlocalizedName() + " name:" + result.getDisplayName());
        return result;
    }

    public static ItemStack addIndicator(ItemStack is, short level) {
        if (is != null) {
            NBTTagCompound tagCompound = is.getTagCompound();
            if (tagCompound == null)
                tagCompound = new NBTTagCompound();
            is.setTagCompound(tagCompound);
            tagCompound.setShort("Indicator", level);
        }
        return is;
    }

    public static ItemStack addLore(ItemStack is, String from) {
        if (is != null) {
            NBTTagCompound tagCompound = is.getTagCompound();
            if (tagCompound == null)
                tagCompound = new NBTTagCompound();
            is.setTagCompound(tagCompound);
            tagCompound.setString("IndicatorLore", from);
        }
        return is;
    }
}
