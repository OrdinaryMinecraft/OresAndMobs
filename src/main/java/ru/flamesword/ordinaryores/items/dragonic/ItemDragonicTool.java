package ru.flamesword.ordinaryores.items.dragonic;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.util.ConfigHelper;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ItemDragonicTool extends ItemTool {
    private static final Set field_150917_c = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium, Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});

    public ItemDragonicTool() {
        super(3.0F, OrdinaryOresBase.ARTIFACTTOOL, field_150917_c);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:DragonicTool");
        toolClass1 = "pickaxe";
        toolClass2 = "axe";
        toolClass3 = "shovel";
    }

    private String toolClass1;
    private String toolClass2;
    private String toolClass3;

    private boolean toolClassEquals(String toolClass) {
        return toolClass.equals(this.toolClass1) || toolClass.equals(this.toolClass2) || toolClass.equals(this.toolClass3);
    }

    @Override
    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        String toolClass = p_150893_2_.getHarvestTool(0);
        return toolClassEquals(toolClass) ? this.efficiencyOnProperMaterial : 1.0F;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass) {
        int level = super.getHarvestLevel(stack, toolClass);
        if (level == -1 && toolClass != null && toolClassEquals(toolClass)) {
            return this.toolMaterial.getHarvestLevel();
        } else {
            return level;
        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack item, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        boolean result = super.onBlockDestroyed(item, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_, p_150894_7_);
        if (result) {
            int blocksDigged = getBlocksDigged(item);
            blocksDigged++;
            NBTTagCompound tagCompound = item.getTagCompound();
            tagCompound.setInteger("Counter_BlocksDigged", blocksDigged);
            item.setTagCompound(tagCompound);
        }
        return result;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
        list.add(ConfigHelper.artifactIndicator);
        list.add(StatCollector.translateToLocal("item.dragonictool.tooltip"));
    }

    public static int getBlocksDigged(ItemStack item) {
        NBTTagCompound tagCompound = item.getTagCompound();
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            item.setTagCompound(tagCompound);
        }
        int blocksDigged;
        if (Objects.nonNull(tagCompound.getInteger("Counter_BlocksDigged"))) {
            blocksDigged = tagCompound.getInteger("Counter_BlocksDigged");
        } else {
            blocksDigged = 0;
        }
        return blocksDigged;
    }
}
