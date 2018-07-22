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

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
    {
        int j = this.getMaxItemUseDuration(p_77615_1_) - p_77615_4_;

        ArrowLooseEvent event = new ArrowLooseEvent(p_77615_3_, p_77615_1_, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        boolean flag = p_77615_3_.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, p_77615_1_) > 0;
        boolean hasArrows = flag || p_77615_3_.inventory.hasItem(Items.arrow);

        if (hasArrows) {
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(p_77615_2_, p_77615_3_, f * 2.0F);

            double damageBonus = 0.1;
            if (this.isCharged()) {
                damageBonus = 0.1 + ((double) getBonusValue(p_77615_1_) * 0.01);
                entityarrow.setFire(10);
            }

            if (f == 1.0F)
            {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, p_77615_1_);

            if (k > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
            }
            // apply damage bonus
            entityarrow.setDamage(entityarrow.getDamage() * ((double) 1 + damageBonus));
            System.out.println("BONUS " + damageBonus);

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, p_77615_1_);

            if (l > 0)
            {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, p_77615_1_) > 0)
            {
                entityarrow.setFire(100);
            }

            p_77615_1_.damageItem(1, p_77615_3_);
            p_77615_2_.playSoundAtEntity(p_77615_3_, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
                p_77615_3_.inventory.consumeInventoryItem(Items.arrow);
            }

            if (!p_77615_2_.isRemote)
            {
                p_77615_2_.spawnEntityInWorld(entityarrow);
            }

            entityarrow.motionX *= 1.0F + 50 / 100F;
            entityarrow.motionY *= 1.0F + 50 / 100F;
            entityarrow.motionZ *= 1.0F + 50 / 100F;

            return;
        }
    }

    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        return p_77654_1_;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.bow;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        ArrowNockEvent event = new ArrowNockEvent(p_77659_3_, p_77659_1_);
        MinecraftForge.EVENT_BUS.post(event);
        this.player = p_77659_3_;
        if (event.isCanceled())
        {
            return event.result;
        }

        if (p_77659_3_.capabilities.isCreativeMode || p_77659_3_.inventory.hasItem(Items.arrow))
        {
            p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        }

        return p_77659_1_;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
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