package ru.flamesword.ordinaryores.items.magicore;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ru.flamesword.ordinaryores.OrdinaryOresBase;
import ru.flamesword.ordinaryores.util.ConfigHelper;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ItemMagicOrePickaxeAxe extends ItemPickaxe {

    //private static final Set field_150917_c = Sets.newHashSet(new Block[]{Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});

    public ItemMagicOrePickaxeAxe() {
        super(OrdinaryOresBase.MAGICORETOOL);
        this.setCreativeTab(OrdinaryOresBase.tabOrdinaryOres);
        this.setTextureName("ordinaryores:MagicOrePickaxeAxe");
        toolClass1 = "pickaxe";
        toolClass2 = "axe";
    }

    private String toolClass1;
    private String toolClass2;

    private boolean toolClassEquals(String toolClass) {
        if (Objects.nonNull(toolClass)) {
            return toolClass.equals(this.toolClass1) || toolClass.equals(this.toolClass2);
        }
        return false;
    }

    @Override
    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        String toolClass = p_150893_2_.getHarvestTool(0);
        return p_150893_2_.getMaterial() == Material.wood ||
                p_150893_2_.getMaterial() == Material.plants ||
                p_150893_2_.getMaterial() == Material.vine ||
                p_150893_2_.getMaterial() == Material.iron ||
                p_150893_2_.getMaterial() == Material.anvil ||
                p_150893_2_.getMaterial() == Material.rock ||
                toolClassEquals(toolClass) ? this.efficiencyOnProperMaterial : super.func_150893_a(p_150893_1_, p_150893_2_);
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

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemMagicOrePickaxeAxe)
            {
                player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20));
            }
        }
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
        list.add(ConfigHelper.digSpeedEffectName + " I " + ConfigHelper.effectIndicator);
    }
}
