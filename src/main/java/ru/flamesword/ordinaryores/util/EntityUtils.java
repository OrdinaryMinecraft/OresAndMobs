package ru.flamesword.ordinaryores.util;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Objects;

public class EntityUtils {

    public static void writeEntitySpawnTime(Entity entity) {
        NBTTagCompound data = new NBTTagCompound();
        entity.writeToNBT(data);

        NBTTagCompound forgeData = new NBTTagCompound();
        forgeData.setLong("spawnTime", entity.worldObj.getTotalWorldTime());
        data.setTag("ForgeData", forgeData);

        entity.readFromNBT(data);
    }

    public static boolean entityIsNotNew(Entity entity) {
        NBTTagCompound data = new NBTTagCompound();
        entity.writeToNBT(data);

        NBTTagCompound forgeData = data.getCompoundTag("ForgeData");
        if (Objects.nonNull(forgeData)) {
            return entity.worldObj.getTotalWorldTime() - forgeData.getLong("spawnTime") > 20;
        }

        return false;
    }

    public static boolean entityHasNoSpawnTime(Entity entity) {
        NBTTagCompound data = new NBTTagCompound();
        entity.writeToNBT(data);

        NBTTagCompound forgeData = data.getCompoundTag("ForgeData");
        if (Objects.nonNull(forgeData)) {
            return forgeData.getLong("spawnTime") == 0L;
        }

        return true;
    }
}
