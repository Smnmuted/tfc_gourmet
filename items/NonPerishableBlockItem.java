package com.tfcgourmet.tfc_gourmet.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class NonPerishableBlockItem extends BlockItem {
    public NonPerishableBlockItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public boolean isEdible() {
        return false;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }
}

