package ru.flamesword.ordinaryores.util;

import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Objects;

public class EntityUtils {

    public static void writeEntitySpawnTime(EntityCreature entity) {
        NBTTagCompound data = new NBTTagCompound();
        entity.writeToNBT(data);

        NBTTagCompound forgeData = data.getCompoundTag("ForgeData");
        if (Objects.isNull(forgeData)) {
            forgeData = new NBTTagCompound();
        }
        forgeData.setLong("firstSpawnTime", entity.worldObj.getTotalWorldTime());
        data.setTag("ForgeData", forgeData);

        entity.readFromNBT(data);
    }

    public static boolean entityIsNotNew(EntityCreature entity) {
        NBTTagCompound data = new NBTTagCompound();
        entity.writeToNBT(data);
        NBTTagCompound forgeData = data.getCompoundTag("ForgeData");
        if (Objects.isNull(forgeData)) {
            return false;
        }
        return entity.worldObj.getTotalWorldTime() - forgeData.getLong("firstSpawnTime") > 20;
    }

    public static boolean entityHasNoSpawnTime(EntityCreature entity) {
        NBTTagCompound data = new NBTTagCompound();
        entity.writeToNBT(data);
        NBTTagCompound forgeData = data.getCompoundTag("ForgeData");
        if (Objects.isNull(forgeData)) {
            return true;
        }
        return forgeData.getLong("firstSpawnTime") == 0L;
    }
}
