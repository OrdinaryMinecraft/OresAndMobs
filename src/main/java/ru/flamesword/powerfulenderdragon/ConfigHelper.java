package ru.flamesword.powerfulenderdragon;

import net.minecraftforge.common.config.Configuration;

import java.util.Arrays;
import java.util.List;

public class ConfigHelper {

    public ConfigHelper() {

    }

    public static List<String> dragonChestloot;

    public static void setupConfig(Configuration config) {
        try {
            config.load();
            dragonChestloot = Arrays.asList(config.getStringList("Item for Ender Dragon chest", "Lists", new String[]{"384","381","368","264","388","399"}, "Loot for dragon chest."));
        } catch(Exception e) {
            System.out.println("A severe error has occured when attempting to load the config file for this mod!");
        } finally {
            if(config.hasChanged()) {
                config.save();
            }
        }
    }
}
