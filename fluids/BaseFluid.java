package com.tfcgourmet.tfc_gourmet.fluids;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class BaseFluid {
    public static class Source extends ForgeFlowingFluid.Source {
        public Source(Properties properties) {
            super(properties);
        }
    }

    public static class Flowing extends ForgeFlowingFluid.Flowing {
        public Flowing(Properties properties) {
            super(properties);
        }
    }

    public static class Block extends LiquidBlock {
        public Block(FlowingFluid fluid) {
            super(() -> fluid, BlockBehaviour.Properties.of()
                    .strength(100.0f)
                    .noLootTable()
                    .replaceable()
                    .noCollission());
        }
    }
}
