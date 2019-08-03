package ru.flamesword.ordinaryores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.util.ConfigHelper;

import java.util.List;
import java.util.Objects;

public class ItemAnimalCrate extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon iconEmpty;

    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public ItemAnimalCrate() {
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:AnimalCrate");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_) {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.icon = p_94581_1_.registerIcon("ordinaryores:AnimalCrate");
        this.iconEmpty = p_94581_1_.registerIcon( "ordinaryores:AnimalCrateEmpty");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_) {
        return p_77617_1_ == 0 ? this.iconEmpty : this.icon;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
        if (crateHasAnimal(stack)) {
            list.add(ConfigHelper.animalCrateIndicator + " " + getAnimalName(stack));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));
        p_150895_3_.add(new ItemStack(p_150895_1_, 1, 1));
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
        String animalName = "Error";
        String classString = getAnimalClass(item);
        if (classString != null) {
            try {
                Class animalClass = Class.forName(classString);
                String s = (String) EntityList.classToStringMapping.get(animalClass);
                if (s == null) {
                    s = "generic";
                }
                animalName = StatCollector.translateToLocal("entity." + s + ".name");
            } catch (ClassNotFoundException e) {
                System.out.println("Can't find class: " + classString);
            }
        }

        return animalName;
    }

    public Boolean crateHasAnimal(ItemStack item) {
        /*
        NBTTagCompound tagCompound = getNBT(item);
        if (Objects.nonNull(tagCompound.getString("AnimalClass"))) {
            return !StringUtils.isNullOrEmpty(tagCompound.getString("AnimalClass"));
        }
        */
        return item.getItemDamage() != 0;
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
        this.setDamage(item, 1);
    }

    public void setCrateEmpty(ItemStack item) {
        NBTTagCompound tagCompound = getNBT(item);
        tagCompound.removeTag("AnimalClass");
        tagCompound.removeTag("AnimalNBT");
        item.setTagCompound(tagCompound);
        this.setDamage(item, 0);
    }
}
