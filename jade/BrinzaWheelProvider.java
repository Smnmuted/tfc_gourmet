package com.tfcgourmet.tfc_gourmet.jade;

import com.tfcgourmet.tfc_gourmet.blocks.BrinzaWheelBlock;
import com.tfcgourmet.tfc_gourmet.blocks.BrinzaWheelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum BrinzaWheelProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        BlockState state = accessor.getBlockState();
        Level level = accessor.getLevel();
        BlockPos pos = accessor.getPosition();

        if (state.getValue(BrinzaWheelBlock.RACK)) {
            tooltip.add(Component.translatable("tfc_gourmet.jade.aging"));
        } else {
            tooltip.add(Component.translatable("tfc_gourmet.jade.not_aging"));
        }
        int count = state.getValue(BrinzaWheelBlock.COUNT);
        int slicesLeft = 5 - count;
        tooltip.add(Component.translatable("tfc_gourmet.jade.slices", slicesLeft));
        String ageKey = "tfc_gourmet.jade.food_age." + state.getValue(BrinzaWheelBlock.AGE).getSerializedName();
        tooltip.add(Component.translatable("tfc_gourmet.jade.food_age", Component.translatable(ageKey)));

        if (state.getValue(BrinzaWheelBlock.SMOKED)) {
            tooltip.add(Component.translatable("tfc_gourmet.tooltip.food_trait.smoked"));
        } else if (state.getValue(BrinzaWheelBlock.RACK) && accessor.getBlockEntity() instanceof BrinzaWheelBlockEntity be) {
            int smokeTicks = be.getSmokeTicks();
            if (smokeTicks > 0) {
                long ticksLeft = BrinzaWheelBlockEntity.TICKS_TO_SMOKE - smokeTicks;
                if (ticksLeft > 0) {
                    tooltip.add(Component.translatable("tfc_gourmet.jade.time_left", formatTicksAsTime(ticksLeft)));
                }
            } else {
                boolean hasFire = BrinzaWheelBlockEntity.hasFirepitBelow(level, pos);
                tooltip.add(Component.translatable(hasFire ? "tfc_gourmet.jade.has_firepit" : "tfc_gourmet.jade.no_firepit"));
            }
        }
    }

    private static String formatTicksAsTime(long ticks) {
        long hours = ticks / 24000;
        long minutes = (ticks % 24000) / 20 / 60;
        if (hours > 0) {
            return hours + "h " + minutes + "m";
        }
        return minutes + "m";
    }

    @Override
    public ResourceLocation getUid() {
        return TFCGourmetJadePlugin.BRINZA_WHEEL;
    }
}
