package com.tfcgourmet.tfc_gourmet.blocks;

import com.tfcgourmet.tfc_gourmet.TFCGourmetMod;
import com.tfcgourmet.tfc_gourmet.fluids.ModFluids;
import com.tfcgourmet.tfc_gourmet.items.ModItems;
import com.tfcgourmet.tfc_gourmet.items.NonPerishableBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TFCGourmetMod.MODID);

    public static final RegistryObject<Block> OX_BRINZA_WHEEL = BLOCKS.register("ox_brinza_wheel",
            () -> new BrinzaWheelBlock(ModItems.OX_BRINZA_SLICE));

    public static final RegistryObject<Block> SHEEP_BRINZA_WHEEL = BLOCKS.register("sheep_brinza_wheel",
            () -> new BrinzaWheelBlock(ModItems.SHEEP_BRINZA_SLICE));

    public static final RegistryObject<Block> ALPACA_BRINZA_WHEEL = BLOCKS.register("alpaca_brinza_wheel",
            () -> new BrinzaWheelBlock(ModItems.ALPACA_BRINZA_SLICE));

    public static final RegistryObject<Item> OX_BRINZA_WHEEL_ITEM = ModItems.ITEMS.register("ox_brinza_wheel",
            () -> new NonPerishableBlockItem(OX_BRINZA_WHEEL.get(), new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SHEEP_BRINZA_WHEEL_ITEM = ModItems.ITEMS.register("sheep_brinza_wheel",
            () -> new NonPerishableBlockItem(SHEEP_BRINZA_WHEEL.get(), new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> ALPACA_BRINZA_WHEEL_ITEM = ModItems.ITEMS.register("alpaca_brinza_wheel",
            () -> new NonPerishableBlockItem(ALPACA_BRINZA_WHEEL.get(), new Item.Properties().stacksTo(1)));

}