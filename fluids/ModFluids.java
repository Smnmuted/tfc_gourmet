package com.tfcgourmet.tfc_gourmet.fluids;

import com.tfcgourmet.tfc_gourmet.TFCGourmetMod;
import com.tfcgourmet.tfc_gourmet.blocks.ModBlocks;
import com.tfcgourmet.tfc_gourmet.items.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<net.minecraft.world.level.material.Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, TFCGourmetMod.MODID);
    public static final DeferredRegister<net.minecraftforge.fluids.FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TFCGourmetMod.MODID);

    public static final RegistryObject<BaseFluidType> OX_MILK_FLUID_TYPE = FLUID_TYPES.register("ox_milk",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.95f, 0.95f, 0.95f), // White fog
                    0xFFFFFFFF  // White tint
            ));

    public static final RegistryObject<FlowingFluid> OX_MILK_SOURCE = FLUIDS.register("ox_milk",
            () -> new BaseFluid.Source(createOxMilkProperties()));

    public static final RegistryObject<FlowingFluid> OX_MILK_FLOWING = FLUIDS.register("flowing_ox_milk",
            () -> new BaseFluid.Flowing(createOxMilkProperties()));

    public static final RegistryObject<Block> OX_MILK_BLOCK = ModBlocks.BLOCKS.register("fluid/ox_milk",
            () -> new BaseFluid.Block(OX_MILK_SOURCE.get()));

    public static final RegistryObject<BaseFluidType> SHEEP_MILK_FLUID_TYPE = FLUID_TYPES.register("sheep_milk",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.95f, 0.95f, 0.95f), // White fog
                    0xFFFFFFFF  // White tint
            ));

    public static final RegistryObject<FlowingFluid> SHEEP_MILK_SOURCE = FLUIDS.register("sheep_milk",
            () -> new BaseFluid.Source(createSheepMilkProperties()));

    public static final RegistryObject<FlowingFluid> SHEEP_MILK_FLOWING = FLUIDS.register("flowing_sheep_milk",
            () -> new BaseFluid.Flowing(createSheepMilkProperties()));

    public static final RegistryObject<Block> SHEEP_MILK_BLOCK = ModBlocks.BLOCKS.register("fluid/sheep_milk",
            () -> new BaseFluid.Block(SHEEP_MILK_SOURCE.get()));

    public static final RegistryObject<BaseFluidType> ALPACA_MILK_FLUID_TYPE = FLUID_TYPES.register("alpaca_milk",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.95f, 0.95f, 0.95f), // White fog
                    0xFFFFFFFF  // White tint
            ));

    public static final RegistryObject<FlowingFluid> ALPACA_MILK_SOURCE = FLUIDS.register("alpaca_milk",
            () -> new BaseFluid.Source(createAlpacaMilkProperties()));

    public static final RegistryObject<FlowingFluid> ALPACA_MILK_FLOWING = FLUIDS.register("flowing_alpaca_milk",
            () -> new BaseFluid.Flowing(createAlpacaMilkProperties()));

    public static final RegistryObject<Block> ALPACA_MILK_BLOCK = ModBlocks.BLOCKS.register("fluid/alpaca_milk",
            () -> new BaseFluid.Block(ALPACA_MILK_SOURCE.get()));

    public static final RegistryObject<BaseFluidType> CURDLED_OX_MILK_FLUID_TYPE = FLUID_TYPES.register("curdled_ox_milk",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.95f, 0.92f, 0.75f), // Yellowish fog
                    0xFFFFF5CC  // Cream/yellowish tint
            ));

    public static final RegistryObject<FlowingFluid> CURDLED_OX_MILK_SOURCE = FLUIDS.register("curdled_ox_milk",
            () -> new BaseFluid.Source(createCurdledOxMilkProperties()));

    public static final RegistryObject<FlowingFluid> CURDLED_OX_MILK_FLOWING = FLUIDS.register("flowing_curdled_ox_milk",
            () -> new BaseFluid.Flowing(createCurdledOxMilkProperties()));

    public static final RegistryObject<Block> CURDLED_OX_MILK_BLOCK = ModBlocks.BLOCKS.register("fluid/curdled_ox_milk",
            () -> new BaseFluid.Block(CURDLED_OX_MILK_SOURCE.get()));

    public static final RegistryObject<BaseFluidType> CURDLED_SHEEP_MILK_FLUID_TYPE = FLUID_TYPES.register("curdled_sheep_milk",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.95f, 0.92f, 0.75f), // Yellowish fog
                    0xFFFFF5CC  // Cream/yellowish tint
            ));

    public static final RegistryObject<FlowingFluid> CURDLED_SHEEP_MILK_SOURCE = FLUIDS.register("curdled_sheep_milk",
            () -> new BaseFluid.Source(createCurdledSheepMilkProperties()));

    public static final RegistryObject<FlowingFluid> CURDLED_SHEEP_MILK_FLOWING = FLUIDS.register("flowing_curdled_sheep_milk",
            () -> new BaseFluid.Flowing(createCurdledSheepMilkProperties()));

    public static final RegistryObject<Block> CURDLED_SHEEP_MILK_BLOCK = ModBlocks.BLOCKS.register("fluid/curdled_sheep_milk",
            () -> new BaseFluid.Block(CURDLED_SHEEP_MILK_SOURCE.get()));

    public static final RegistryObject<BaseFluidType> CURDLED_ALPACA_MILK_FLUID_TYPE = FLUID_TYPES.register("curdled_alpaca_milk",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.95f, 0.92f, 0.75f), // Yellowish fog
                    0xFFFFF5CC  // Cream/yellowish tint
            ));

    public static final RegistryObject<FlowingFluid> CURDLED_ALPACA_MILK_SOURCE = FLUIDS.register("curdled_alpaca_milk",
            () -> new BaseFluid.Source(createCurdledAlpacaMilkProperties()));

    public static final RegistryObject<FlowingFluid> CURDLED_ALPACA_MILK_FLOWING = FLUIDS.register("flowing_curdled_alpaca_milk",
            () -> new BaseFluid.Flowing(createCurdledAlpacaMilkProperties()));

    public static final RegistryObject<Block> CURDLED_ALPACA_MILK_BLOCK = ModBlocks.BLOCKS.register("fluid/curdled_alpaca_milk",
            () -> new BaseFluid.Block(CURDLED_ALPACA_MILK_SOURCE.get()));

    private static ForgeFlowingFluid.Properties createOxMilkProperties() {
        return new ForgeFlowingFluid.Properties(
                OX_MILK_FLUID_TYPE,
                OX_MILK_SOURCE,
                OX_MILK_FLOWING
        ).bucket(ModItems.OX_MILK_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) OX_MILK_BLOCK.get());
    }

    private static ForgeFlowingFluid.Properties createSheepMilkProperties() {
        return new ForgeFlowingFluid.Properties(
                SHEEP_MILK_FLUID_TYPE,
                SHEEP_MILK_SOURCE,
                SHEEP_MILK_FLOWING
        ).bucket(ModItems.SHEEP_MILK_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) SHEEP_MILK_BLOCK.get());
    }

    private static ForgeFlowingFluid.Properties createAlpacaMilkProperties() {
        return new ForgeFlowingFluid.Properties(
                ALPACA_MILK_FLUID_TYPE,
                ALPACA_MILK_SOURCE,
                ALPACA_MILK_FLOWING
        ).bucket(ModItems.ALPACA_MILK_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) ALPACA_MILK_BLOCK.get());
    }

    private static ForgeFlowingFluid.Properties createCurdledOxMilkProperties() {
        return new ForgeFlowingFluid.Properties(
                CURDLED_OX_MILK_FLUID_TYPE,
                CURDLED_OX_MILK_SOURCE,
                CURDLED_OX_MILK_FLOWING
        ).bucket(ModItems.CURDLED_OX_MILK_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) CURDLED_OX_MILK_BLOCK.get());
    }

    private static ForgeFlowingFluid.Properties createCurdledSheepMilkProperties() {
        return new ForgeFlowingFluid.Properties(
                CURDLED_SHEEP_MILK_FLUID_TYPE,
                CURDLED_SHEEP_MILK_SOURCE,
                CURDLED_SHEEP_MILK_FLOWING
        ).bucket(ModItems.CURDLED_SHEEP_MILK_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) CURDLED_SHEEP_MILK_BLOCK.get());
    }

    private static ForgeFlowingFluid.Properties createCurdledAlpacaMilkProperties() {
        return new ForgeFlowingFluid.Properties(
                CURDLED_ALPACA_MILK_FLUID_TYPE,
                CURDLED_ALPACA_MILK_SOURCE,
                CURDLED_ALPACA_MILK_FLOWING
        ).bucket(ModItems.CURDLED_ALPACA_MILK_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) CURDLED_ALPACA_MILK_BLOCK.get());
    }

    public static final RegistryObject<BaseFluidType> COMPOTE_FLUID_TYPE = FLUID_TYPES.register("compote",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.8f, 0.3f, 0.3f), // Reddish fog
                    0xFFCC4444  // Red tint for compote
            ));

    public static final RegistryObject<FlowingFluid> COMPOTE_SOURCE = FLUIDS.register("compote",
            () -> new BaseFluid.Source(createCompoteProperties()));

    public static final RegistryObject<FlowingFluid> COMPOTE_FLOWING = FLUIDS.register("flowing_compote",
            () -> new BaseFluid.Flowing(createCompoteProperties()));

    public static final RegistryObject<net.minecraft.world.level.block.Block> COMPOTE_BLOCK = ModBlocks.BLOCKS.register("fluid/compote",
            () -> new BaseFluid.Block(COMPOTE_SOURCE.get()));

    private static ForgeFlowingFluid.Properties createCompoteProperties() {
        return new ForgeFlowingFluid.Properties(
                COMPOTE_FLUID_TYPE,
                COMPOTE_SOURCE,
                COMPOTE_FLOWING
        ).bucket(ModItems.COMPOTE_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) COMPOTE_BLOCK.get());
    }

    public static final RegistryObject<BaseFluidType> KVASS_FLUID_TYPE = FLUID_TYPES.register("kvass",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.5f, 0.4f, 0.3f), // Brownish fog
                    0xFF886644  // Brown tint for kvass
            ));

    public static final RegistryObject<FlowingFluid> KVASS_SOURCE = FLUIDS.register("kvass",
            () -> new BaseFluid.Source(createKvassProperties()));

    public static final RegistryObject<FlowingFluid> KVASS_FLOWING = FLUIDS.register("flowing_kvass",
            () -> new BaseFluid.Flowing(createKvassProperties()));

    public static final RegistryObject<net.minecraft.world.level.block.Block> KVASS_BLOCK = ModBlocks.BLOCKS.register("fluid/kvass",
            () -> new BaseFluid.Block(KVASS_SOURCE.get()));

    private static ForgeFlowingFluid.Properties createKvassProperties() {
        return new ForgeFlowingFluid.Properties(
                KVASS_FLUID_TYPE,
                KVASS_SOURCE,
                KVASS_FLOWING
        ).bucket(ModItems.KVASS_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) KVASS_BLOCK.get());
    }

    public static final RegistryObject<BaseFluidType> LEMONADE_FLUID_TYPE = FLUID_TYPES.register("lemonade",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.9f, 0.9f, 0.4f), // Yellowish fog
                    0xFFFFDD44  // Yellow tint for lemonade
            ));

    public static final RegistryObject<FlowingFluid> LEMONADE_SOURCE = FLUIDS.register("lemonade",
            () -> new BaseFluid.Source(createLemonadeProperties()));

    public static final RegistryObject<FlowingFluid> LEMONADE_FLOWING = FLUIDS.register("flowing_lemonade",
            () -> new BaseFluid.Flowing(createLemonadeProperties()));

    public static final RegistryObject<net.minecraft.world.level.block.Block> LEMONADE_BLOCK = ModBlocks.BLOCKS.register("fluid/lemonade",
            () -> new BaseFluid.Block(LEMONADE_SOURCE.get()));

    private static ForgeFlowingFluid.Properties createLemonadeProperties() {
        return new ForgeFlowingFluid.Properties(
                LEMONADE_FLUID_TYPE,
                LEMONADE_SOURCE,
                LEMONADE_FLOWING
        ).bucket(ModItems.LEMONADE_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) LEMONADE_BLOCK.get());
    }

    public static final RegistryObject<BaseFluidType> COFFEE_FLUID_TYPE = FLUID_TYPES.register("coffee",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.4f, 0.3f, 0.2f), // Dark brown fog
                    0xFF4A2C1A  // Dark brown tint for coffee
            ));

    public static final RegistryObject<FlowingFluid> COFFEE_SOURCE = FLUIDS.register("coffee",
            () -> new BaseFluid.Source(createCoffeeProperties()));

    public static final RegistryObject<FlowingFluid> COFFEE_FLOWING = FLUIDS.register("flowing_coffee",
            () -> new BaseFluid.Flowing(createCoffeeProperties()));

    public static final RegistryObject<Block> COFFEE_BLOCK = ModBlocks.BLOCKS.register("fluid/coffee",
            () -> new BaseFluid.Block(COFFEE_SOURCE.get()));

    private static ForgeFlowingFluid.Properties createCoffeeProperties() {
        return new ForgeFlowingFluid.Properties(
                COFFEE_FLUID_TYPE,
                COFFEE_SOURCE,
                COFFEE_FLOWING
        ).bucket(ModItems.COFFEE_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) COFFEE_BLOCK.get());
    }

    public static final RegistryObject<BaseFluidType> TEA_MINT_FLUID_TYPE = FLUID_TYPES.register("tea_mint",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.7f, 0.9f, 0.7f), // Light green fog
                    0xFFB8E6B8  // Light green tint for mint tea
            ));

    public static final RegistryObject<FlowingFluid> TEA_MINT_SOURCE = FLUIDS.register("tea_mint",
            () -> new BaseFluid.Source(createTeaMintProperties()));

    public static final RegistryObject<FlowingFluid> TEA_MINT_FLOWING = FLUIDS.register("flowing_tea_mint",
            () -> new BaseFluid.Flowing(createTeaMintProperties()));

    public static final RegistryObject<Block> TEA_MINT_BLOCK = ModBlocks.BLOCKS.register("fluid/tea_mint",
            () -> new BaseFluid.Block(TEA_MINT_SOURCE.get()));

    private static ForgeFlowingFluid.Properties createTeaMintProperties() {
        return new ForgeFlowingFluid.Properties(
                TEA_MINT_FLUID_TYPE,
                TEA_MINT_SOURCE,
                TEA_MINT_FLOWING
        ).bucket(ModItems.TEA_MINT_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) TEA_MINT_BLOCK.get());
    }

    public static final RegistryObject<BaseFluidType> TEA_CHAMOMILE_FLUID_TYPE = FLUID_TYPES.register("tea_chamomile",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.9f, 0.9f, 0.6f), // Light yellow fog
                    0xFFFFE6B8  // Light yellow tint for chamomile tea
            ));

    public static final RegistryObject<FlowingFluid> TEA_CHAMOMILE_SOURCE = FLUIDS.register("tea_chamomile",
            () -> new BaseFluid.Source(createTeaChamomileProperties()));

    public static final RegistryObject<FlowingFluid> TEA_CHAMOMILE_FLOWING = FLUIDS.register("flowing_tea_chamomile",
            () -> new BaseFluid.Flowing(createTeaChamomileProperties()));

    public static final RegistryObject<Block> TEA_CHAMOMILE_BLOCK = ModBlocks.BLOCKS.register("fluid/tea_chamomile",
            () -> new BaseFluid.Block(TEA_CHAMOMILE_SOURCE.get()));

    private static ForgeFlowingFluid.Properties createTeaChamomileProperties() {
        return new ForgeFlowingFluid.Properties(
                TEA_CHAMOMILE_FLUID_TYPE,
                TEA_CHAMOMILE_SOURCE,
                TEA_CHAMOMILE_FLOWING
        ).bucket(ModItems.TEA_CHAMOMILE_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) TEA_CHAMOMILE_BLOCK.get());
    }

    // Rosehip Tea Fluid - Pink rosehip tea color
    public static final RegistryObject<BaseFluidType> TEA_ROSEHIP_FLUID_TYPE = FLUID_TYPES.register("tea_rosehip",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.9f, 0.7f, 0.7f), // Pink fog
                    0xFFFFB8D8  // Pink tint for rosehip tea
            ));

    public static final RegistryObject<FlowingFluid> TEA_ROSEHIP_SOURCE = FLUIDS.register("tea_rosehip",
            () -> new BaseFluid.Source(createTeaRosehipProperties()));

    public static final RegistryObject<FlowingFluid> TEA_ROSEHIP_FLOWING = FLUIDS.register("flowing_tea_rosehip",
            () -> new BaseFluid.Flowing(createTeaRosehipProperties()));

    public static final RegistryObject<Block> TEA_ROSEHIP_BLOCK = ModBlocks.BLOCKS.register("fluid/tea_rosehip",
            () -> new BaseFluid.Block(TEA_ROSEHIP_SOURCE.get()));

    private static ForgeFlowingFluid.Properties createTeaRosehipProperties() {
        return new ForgeFlowingFluid.Properties(
                TEA_ROSEHIP_FLUID_TYPE,
                TEA_ROSEHIP_SOURCE,
                TEA_ROSEHIP_FLOWING
        ).bucket(ModItems.TEA_ROSEHIP_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) TEA_ROSEHIP_BLOCK.get());
    }

    public static final RegistryObject<BaseFluidType> TEA_NETTLE_FLUID_TYPE = FLUID_TYPES.register("tea_nettle",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.6f, 0.8f, 0.5f), // Green fog
                    0xFF99CC88  // Green tint for nettle tea
            ));

    public static final RegistryObject<FlowingFluid> TEA_NETTLE_SOURCE = FLUIDS.register("tea_nettle",
            () -> new BaseFluid.Source(createTeaNettleProperties()));

    public static final RegistryObject<FlowingFluid> TEA_NETTLE_FLOWING = FLUIDS.register("flowing_tea_nettle",
            () -> new BaseFluid.Flowing(createTeaNettleProperties()));

    public static final RegistryObject<Block> TEA_NETTLE_BLOCK = ModBlocks.BLOCKS.register("fluid/tea_nettle",
            () -> new BaseFluid.Block(TEA_NETTLE_SOURCE.get()));

    private static ForgeFlowingFluid.Properties createTeaNettleProperties() {
        return new ForgeFlowingFluid.Properties(
                TEA_NETTLE_FLUID_TYPE,
                TEA_NETTLE_SOURCE,
                TEA_NETTLE_FLOWING
        ).bucket(ModItems.TEA_NETTLE_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) TEA_NETTLE_BLOCK.get());
    }

    public static final RegistryObject<BaseFluidType> COCOA_FLUID_TYPE = FLUID_TYPES.register("cocoa",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.6f, 0.4f, 0.3f), // Brown fog
                    0xFF996644  // Brown tint for cocoa
            ));

    public static final RegistryObject<FlowingFluid> COCOA_SOURCE = FLUIDS.register("cocoa",
            () -> new BaseFluid.Source(createCocoaProperties()));

    public static final RegistryObject<FlowingFluid> COCOA_FLOWING = FLUIDS.register("flowing_cocoa",
            () -> new BaseFluid.Flowing(createCocoaProperties()));

    public static final RegistryObject<Block> COCOA_BLOCK = ModBlocks.BLOCKS.register("fluid/cocoa",
            () -> new BaseFluid.Block(COCOA_SOURCE.get()));

    private static ForgeFlowingFluid.Properties createCocoaProperties() {
        return new ForgeFlowingFluid.Properties(
                COCOA_FLUID_TYPE,
                COCOA_SOURCE,
                COCOA_FLOWING
        ).bucket(ModItems.COCOA_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) COCOA_BLOCK.get());
    }

    public static final RegistryObject<BaseFluidType> NALIVKA_FLUID_TYPE = FLUID_TYPES.register("nalivka",
            () -> new BaseFluidType(
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                    new org.joml.Vector3f(0.8f, 0.4f, 0.4f), // Reddish fog
                    0xFFCC6666  // Red tint for nalivka
            ));

    public static final RegistryObject<FlowingFluid> NALIVKA_SOURCE = FLUIDS.register("nalivka",
            () -> new BaseFluid.Source(createNalivkaProperties()));

    public static final RegistryObject<FlowingFluid> NALIVKA_FLOWING = FLUIDS.register("flowing_nalivka",
            () -> new BaseFluid.Flowing(createNalivkaProperties()));

    public static final RegistryObject<Block> NALIVKA_BLOCK = ModBlocks.BLOCKS.register("fluid/nalivka",
            () -> new BaseFluid.Block(NALIVKA_SOURCE.get()));

    private static ForgeFlowingFluid.Properties createNalivkaProperties() {
        return new ForgeFlowingFluid.Properties(
                NALIVKA_FLUID_TYPE,
                NALIVKA_SOURCE,
                NALIVKA_FLOWING
        ).bucket(ModItems.NALIVKA_BUCKET)
         .block(() -> (net.minecraft.world.level.block.LiquidBlock) NALIVKA_BLOCK.get());
    }
}