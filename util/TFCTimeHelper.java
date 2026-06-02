package com.tfcgourmet.tfc_gourmet.util;

import net.minecraft.world.level.Level;

public final class TFCTimeHelper {

    private TFCTimeHelper() {}

    public static long getGameTime(Level level) {
        return level != null ? level.getDayTime() : 0;
    }
}
