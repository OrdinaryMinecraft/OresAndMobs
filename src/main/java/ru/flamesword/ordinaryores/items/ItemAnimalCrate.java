package ru.flamesword.ordinaryores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.util.ConfigHelper;

import java.util.List;
import java.util.Objects;

public class ItemAnimalCrate extends Item {

    private String animalName;
    private IIcon iconEmpty;
    private IIcon icon;

    public ItemAnimalCrate() {
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:AnimalCrate");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_) {
        super.registerIcons(p_94581_1_);
        this.icon = p_94581_1_.registerIcon("ordinaryores:AnimalCrate");
        this.iconEmpty = p_94581_1_.registerIcon( "ordinaryores:AnimalCrateEmpty");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_) {
        return Objects.nonNull(animalName) ? this.itemIcon :  this.iconEmpty;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
        if (crateHasAnimal(stack)) {
            list.add(ConfigHelper.animalCrateIndicator + " " + getAnimalName(stack));
        }
    }

    private NBTTagCompound getNBT(ItemStack item) {
        NBTTagCompound tagCompound = item.getTagCompound();
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            item.setTagCompound(tagCompound);
        }
        return tagCompound;
    }

    public String getAnimalName(ItemStack item) {
        try {
            Class animalClass = Class.forName(getAnimalClass(item));
            String s = (String) EntityList.classToStringMapping.get(animalClass);
            if (s == null) {
                s = "generic";
            }
            animalName = StatCollector.translateToLocal("entity." + s + ".name");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find class: " + getAnimalClass(item));
            animalName = "Error";
        }

        return animalName;
    }

    public Boolean crateHasAnimal(ItemStack item) {
        NBTTagCompound tagCompound = getNBT(item);
        if (Objects.nonNull(tagCompound.getString("AnimalClass"))) {
            return !StringUtils.isNullOrEmpty(tagCompound.getString("AnimalClass"));
        }
        return false;
    }

    public String getAnimalClass(ItemStack item) {
        NBTTagCompound tagCompound = getNBT(item);
        String animalClass = null;
        if (Objects.nonNull(tagCompound.getString("AnimalClass"))) {
            animalClass = tagCompound.getString("AnimalClass");
        }
        return animalClass;
    }

    public String getAnimalNBT(ItemStack item) {
        NBTTagCompound tagCompound = getNBT(item);
        String animalNBT = null;
        if (Objects.nonNull(tagCompound.getString("AnimalNBT"))) {
            animalNBT = tagCompound.getString("AnimalNBT");
        }
        return animalNBT;
    }

    public void putAnimalInCrate(EntityAnimal animal, ItemStack item) {
        if (animal == null) {
            System.out.println("Entity is null!");
            return;
        }
        NBTTagCompound tagCompound = getNBT(item);
        String animalClass = animal.getClass().getName();
        tagCompound.setString("AnimalClass", animalClass);

        NBTTagCompound animalTagCompound = new NBTTagCompound();
        animal.writeEntityToNBT(animalTagCompound);
        String animalNBT = animalTagCompound.toString();
        tagCompound.setString("AnimalNBT", animalNBT);

        item.setTagCompound(tagCompound);
        this.itemIcon = this.icon;
    }

    public void setCrateEmpty(ItemStack item) {
        NBTTagCompound tagCompound = getNBT(item);
        tagCompound.removeTag("AnimalClass");
        tagCompound.removeTag("AnimalNBT");
        item.setTagCompound(tagCompound);
        animalName = null;
        this.itemIcon = this.iconEmpty;
    }
}
