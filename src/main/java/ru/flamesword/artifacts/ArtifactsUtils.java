package ru.flamesword.artifacts;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArtifactsUtils {

    private static Random random = new Random();

    public static final String FROM_CHEST = "chest";

    public static int randomBetween(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    // Я не нашел способа перевести название предмета на нужный язык на сервере. Really?
    // Здесь выполняется отправка айди предмета в клиент для перевода на нужный язык
    // Инкостыляция 80 лвла
    public static void createRandomArtifact(short level, String from, EntityPlayer player, int x, int y, int z) {
        Integer itemId = null;
        System.out.println(level);
        for (int i = 0; i < 50; i++) {
            Integer number = randomBetween(0, ConfigHelper.itemsForDrop.size() - 1);
            itemId = Integer.parseInt(ConfigHelper.itemsForDrop.get(number));
            Item item = Item.getItemById(itemId);
            // Проверки. Стараюсь сгенерировать предмет качества по уровню
            if (item instanceof ItemTool) {
                System.out.println("Try " + i + " durability:" + ((ItemTool) item).func_150913_i().getMaxUses());
                if (level > 2 && item.getMaxDamage() > 800) {
                    break;
                }
                if (level <= 2 && item.getMaxDamage() <= 800) {
                    break;
                }
            } else if (item instanceof ItemSword) {
                System.out.println("Try " + i + " damage:" + ((ItemSword) item).func_150931_i());
                if (level > 2 && item.getMaxDamage() > 800) {
                    break;
                }
                if (level <= 2 && item.getMaxDamage() <= 800) {
                    break;
                }
            } else if (item instanceof ItemArmor) {
                System.out.println("Try " + i + " arm dur:" + ((ItemArmor) item).getArmorMaterial().getDurability(1));
                if (level > 2 && item.getMaxDamage() > 300) {
                    break;
                }
                if (level <= 2 && item.getMaxDamage() <= 300) {
                    break;
                }
            } else {
                break;
            }
        }
        FMLProxyPacket packet = PacketHandler.getOtherPacket(Side.CLIENT, itemId, level, from, x, y, z, "");
        ArtifactsBase.otherChannel.sendTo(packet, (EntityPlayerMP) player);
    }


    public static ItemStack getArtifact(short level, String name, int itemId, String from, String playername) {
        ItemStack result = null;
        Item item = Item.getItemById(itemId);
        result = new ItemStack(item, 1);
        String word1 = "";
        int number = 0;
        if (getLastSybmol(name).equals("а") || getLastSybmol(name).equals("я")) {
            // Женский род
            number = randomBetween(0, ConfigHelper.nameParts1f.size() - 1);
            word1 = ConfigHelper.nameParts1f.get(number);
        } else if (getLastSybmol(name).equals("и") || getLastSybmol(name).equals("ы")) {
            // Множественное число
            number = randomBetween(0, ConfigHelper.nameParts1p.size() - 1);
            word1 = ConfigHelper.nameParts1p.get(number);
        } else {
            // Мужской род
            number = randomBetween(0, ConfigHelper.nameParts1m.size() - 1);
            word1 = ConfigHelper.nameParts1m.get(number);
        }

        number = randomBetween(0, ConfigHelper.nameParts2.size() - 1);
        String word2 = ConfigHelper.nameParts2.get(number);

        number = randomBetween(0, ConfigHelper.nameParts3.size() - 1);
        String word3 = ConfigHelper.nameParts3.get(number);

        result.setStackDisplayName(name);
        boolean wordAdded = false;
        if (Math.random() <= 0.8 * level) {
            result.setStackDisplayName(word1 + " " + result.getDisplayName().toLowerCase());
            wordAdded = true;
        }
        result.setStackDisplayName("§b" + result.getDisplayName());

        if (Math.random() <= 0.2 * level || !wordAdded) {
            result.setStackDisplayName(result.getDisplayName() + " " + word2);
        }

        if (Math.random() <= 0.2 * level) {
            result.setStackDisplayName(result.getDisplayName() + " " + word3);
        }

        result = addIndicator(result, level);
        result = addLore(result, from);

        // Add enchantments
        for (int i = 0; i < 10; i++) {
            if (!result.isItemEnchanted()) {
                EnchantmentHelper.addRandomEnchantment(random, result, 10 * level);
                if (level > 2 && result.getEnchantmentTagList().tagCount() < 2) {
                    System.out.println("CREATED ARTIFACT WARN: not enough enchants! Adding more...");
                    EnchantmentHelper.addRandomEnchantment(random, result, 10 * level);
                }
            }
        }

        // Clear duplicating enchantments
        if (result.isItemEnchanted()) {
            try {
                List<Short> enchantmentIds = new ArrayList<Short>();
                for (int j = 0; j < result.getEnchantmentTagList().tagCount(); j++) {
                    short enchantmentId = result.getEnchantmentTagList().getCompoundTagAt(j).getShort("id");
                    if (enchantmentIds.contains(enchantmentId)) {
                        result.getEnchantmentTagList().removeTag(j);
                        j--;
                    }
                    enchantmentIds.add(enchantmentId);
                }
            } catch (Exception e) {
                System.out.println("CREATED ARTIFACT ERROR: error clearing duplicating enchants!");
            }
        }

        result.setItemDamage(randomBetween(0, (int) (result.getMaxDamage() * (0.4 - 0.1 * level) * 2)));

        System.out.println("CREATED ARTIFACT level:" + level + " item:" + item.getUnlocalizedName() + " from:" + from + " name:" + result.getDisplayName() + " player:" + playername);
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

    public static String getLastSybmol(String string) {
        return string.substring(string.length() - 1);
    }
}
