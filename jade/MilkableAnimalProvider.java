package com.tfcgourmet.tfc_gourmet.jade;

import com.tfcgourmet.tfc_gourmet.util.TFCAnimalAccessor;
import com.tfcgourmet.tfc_gourmet.util.TFCTimeHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;


public enum MilkableAnimalProvider implements IEntityComponentProvider, IServerDataProvider<EntityAccessor> {

    INSTANCE;

    private static final long COOLDOWN_TICKS = 48000L; 
    private static final String ENTITY_LAST_MILKED = "tfc_gourmet:last_milked";
    private static final String SERVER_GAME_TIME = "tfc_gourmet:game_time";

    @Override
    public void appendServerData(CompoundTag data, EntityAccessor accessor) {
        Entity entity = accessor.getEntity();
        if (entity != null && entity.level() != null) {
            data.putLong(SERVER_GAME_TIME, TFCTimeHelper.getGameTime(entity.level()));
        }
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig config) {
        Entity entity = accessor.getEntity();
        if (!(entity instanceof Animal animal)) {
            return;
        }
        String type = net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(animal.getType()).toString();
        if (!type.equals("tfc:musk_ox") && !type.equals("tfc:sheep") && !type.equals("tfg:glacian_ram") && !type.equals("tfc:alpaca")) {
            return;
        }
        if (animal.isBaby()) {
            return;
        }
        if (animal instanceof TFCAnimalAccessor tfcAnimal) {
            Object gender = tfcAnimal.tfcGourmet$getGender();
            if (gender != null && "MALE".equals(gender.toString())) {
                return; 
            }
            Float familiarity = tfcAnimal.tfcGourmet$getFamiliarity();
            if (familiarity == null || familiarity < 0.18f) {
                return; 
            }
        }
        long lastMilked = animal.getPersistentData().contains(ENTITY_LAST_MILKED)
            ? animal.getPersistentData().getLong(ENTITY_LAST_MILKED)
            : 0;
        long current = accessor.getServerData().contains(SERVER_GAME_TIME)
            ? accessor.getServerData().getLong(SERVER_GAME_TIME)
            : TFCTimeHelper.getGameTime(animal.level());
        long elapsed = current - lastMilked;
        long nextIn = COOLDOWN_TICKS - elapsed;

    }

    @Override
    public ResourceLocation getUid() {
        return TFCGourmetJadePlugin.MILKABLE_ANIMAL;
    }
}
