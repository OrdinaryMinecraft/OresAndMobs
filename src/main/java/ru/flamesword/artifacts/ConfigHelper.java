package ru.flamesword.artifacts;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import java.util.Arrays;
import java.util.List;

public class ConfigHelper {

    public ConfigHelper() {

    }

    public static List<String> itemsForDrop;
    public static List<String> uncommonItems;
    public static List<String> rareItems;
    public static List<String> legendaryItems;
    public static List<String> artifactItems;
    public static List<String> nameParts1m;
    public static List<String> nameParts1f;
    public static List<String> nameParts1p;
    public static List<String> nameParts2;
    public static List<String> nameParts3;

    public static void setupConfig(Configuration config) {
        try {
            config.load();
            itemsForDrop = Arrays.asList(config.getStringList("Items for artifacts", "Lists", new String[]{"267","279","257","346","261","313","307"}, "Loot that can be artifacts dropped from mobs."));

            uncommonItems = Arrays.asList(config.getStringList("Uncommon items", "Lists", new String[]{}, "Items indicated as uncommon."));
            rareItems = Arrays.asList(config.getStringList("Rare items", "Lists", new String[]{}, "Items indicated as rare."));
            legendaryItems = Arrays.asList(config.getStringList("Legendary items", "Lists", new String[]{}, "Items indicated as legendary."));
            artifactItems = Arrays.asList(config.getStringList("Artifact items", "Lists", new String[]{}, "Items indicated as artifacts."));

            nameParts1m = Arrays.asList(config.getStringList("Words for item names (mus)", "Lists", new String[]{
                    "Strange","Old","Strong","Ancient","Lost","Simple"
            }, "Words that could be used for artifacts names."));
            nameParts1f = Arrays.asList(config.getStringList("Words for item names (fem)", "Lists", new String[]{
                    "Strange","Old","Strong","Ancient","Lost","Simple"
            }, "Words that could be used for artifacts names."));
            nameParts1p = Arrays.asList(config.getStringList("Words for item names (pl)", "Lists", new String[]{
                    "Strange","Old","Strong","Ancient","Lost","Simple"
            }, "Words that could be used for artifacts names."));
            nameParts2 = Arrays.asList(config.getStringList("Words for item names (pt.2)", "Lists", new String[]{
                    "of"
            }, "Words that could be used for artifacts names."));
            nameParts3 = Arrays.asList(config.getStringList("Words for item names (pt.3)", "Lists", new String[]{
                    "king","mamonth","tiger","paladin","giant","knight"
            }, "Words that could be used for artifacts names."));
        } catch(Exception e) {
            System.out.println("A severe error has occured when attempting to load the config file for this mod!");
        } finally {
            if(config.hasChanged()) {
                config.save();
            }
        }
    }
}
