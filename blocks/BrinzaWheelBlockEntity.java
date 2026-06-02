package com.tfcgourmet.tfc_gourmet.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;


public class BrinzaWheelBlockEntity extends BlockEntity {

    private static final String KEY_SMOKE_TICKS = "SmokeTicks";
    public static final int TICKS_TO_SMOKE = 8 * 24000;

    private int smokeTicks;

    public BrinzaWheelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BRINZA_WHEEL.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, BrinzaWheelBlockEntity be) {
        if (!state.getValue(BrinzaWheelBlock.RACK) || state.getValue(BrinzaWheelBlock.SMOKED)) {
            return;
        }
        if (!isSmokeSourceBelow(level, pos, 4)) {
            return;
        }
        be.smokeTicks++;
        if (be.smokeTicks >= TICKS_TO_SMOKE) {
            level.setBlock(pos, state.setValue(BrinzaWheelBlock.SMOKED, true), 2);
            be.smokeTicks = 0;
        }
        be.setChanged();
    }

    public static boolean hasFirepitBelow(Level level, BlockPos pos) {
        return isSmokeSourceBelow(level, pos, 4);
    }

    private static boolean isSmokeSourceBelow(Level level, BlockPos pos, int range) {
        for (int i = 1; i <= range; i++) {
            BlockPos below = pos.below(i);
            BlockState state = level.getBlockState(below);
            if (state.getBlock() instanceof FireBlock) {
                return true;
            }
            if (state.getBlock() instanceof CampfireBlock && state.getValue(CampfireBlock.LIT)) {
                return true;
            }
            String id = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString();
            if (id.contains("firepit") || id.contains("campfire")) {
                if (state.getLightEmission() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt(KEY_SMOKE_TICKS, smokeTicks);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        smokeTicks = tag.getInt(KEY_SMOKE_TICKS);
    }

    public int getSmokeTicks() {
        return smokeTicks;
    }
}
