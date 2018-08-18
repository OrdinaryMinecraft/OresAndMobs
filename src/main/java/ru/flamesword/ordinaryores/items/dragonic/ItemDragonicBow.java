package ru.flamesword.ordinaryores.items.dragonic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.util.ConfigHelper;

import java.util.List;
import java.util.Objects;

public class ItemDragonicBow extends ItemBow
{
    public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
    private EntityPlayer player;
    private static final String __OBFID = "CL_00001777";
    private boolean isCharged;

    public ItemDragonicBow()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(1024);
        this.setCharged(false);
        this.setTextureName("ordinaryores:DragonicBow");
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        this.itemIcon = p_94581_1_.registerIcon(this.getIconString() + "_standby");
        this.iconArray = new IIcon[bowPullIconNameArray.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = p_94581_1_.registerIcon(this.getIconString() + "_" + bowPullIconNameArray[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        if (usingItem != null)
        {
            int time = 72000 - useRemaining;
            if (time < 8) {
                return iconArray[0];
            }

            if (time < 14) {
                return iconArray[1];
            }
            return iconArray[2];
        }
        return this.itemIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass) {
        if (Objects.isNull(this.player)) {
            return this.itemIcon;
        }

        if (this.player.getItemInUseDuration() == 0) {
            return getIcon(stack, renderPass, this.player, null, 72000 - this.player.getItemInUseDuration());
        } else {
            return getIcon(stack, renderPass, this.player, stack, 72000 - this.player.getItemInUseDuration());
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int par1)
    {
        return this.iconArray[par1];
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
        list.add(ConfigHelper.artifactIndicator);
        list.add(StatCollector.translateToLocal("item.dragonicbow.tooltip1"));
        list.add(StatCollector.translateToLocal("item.dragonicbow.tooltip2"));
    }

    public static int getEnemiesKilled(ItemStack item) {
        NBTTagCompound tagCompound = item.getTagCompound();
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            item.setTagCompound(tagCompound);
        }
        int enemiesKilled;
        if (Objects.nonNull(tagCompound.getInteger("Counter_EnemiesKilled"))) {
            enemiesKilled = tagCompound.getInteger("Counter_EnemiesKilled");
        } else {
            enemiesKilled = 0;
        }
        return enemiesKilled;
    }

    public static Integer getBonusValue(ItemStack itemstack) {
        int enemiesKilled = getEnemiesKilled(itemstack);
        int value = enemiesKilled / 250;
        if (value > 15) {
            value = 15;
        }
        return value;
    }

    public boolean isCharged() {
        return isCharged;
    }

    public void setCharged(boolean charged) {
        isCharged = charged;
    }
}