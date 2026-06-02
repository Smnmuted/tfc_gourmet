package com.tfcgourmet.tfc_gourmet.blocks;

import com.tfcgourmet.tfc_gourmet.TFCGourmetMod;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TFCGourmetMod.MODID);

    public static final RegistryObject<BlockEntityType<BrinzaWheelBlockEntity>> BRINZA_WHEEL =
            BLOCK_ENTITIES.register("brinza_wheel", () ->
                    BlockEntityType.Builder.of(BrinzaWheelBlockEntity::new,
                            ModBlocks.OX_BRINZA_WHEEL.get(),
                            ModBlocks.SHEEP_BRINZA_WHEEL.get(),
                            ModBlocks.ALPACA_BRINZA_WHEEL.get()).build(null));
}
