package ru.flamesword.ordinaryores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import ru.flamesword.ordinaryores.OrdinaryOresBase;

import java.util.Objects;

public class ItemReinforcedBow extends ItemBow {

    public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
    private EntityPlayer player;
    private static final String __OBFID = "CL_00001777";

    public ItemReinforcedBow()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(768);
        this.setTextureName("ordinaryores:ReinforcedBow");
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
}
