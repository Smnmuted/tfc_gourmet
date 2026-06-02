package com.tfcgourmet.tfc_gourmet.jade;

import com.tfcgourmet.tfc_gourmet.TFCGourmetMod;
import com.tfcgourmet.tfc_gourmet.blocks.BrinzaWheelBlock;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin(TFCGourmetMod.MODID)
public class TFCGourmetJadePlugin implements IWailaPlugin {

    public static final ResourceLocation BRINZA_WHEEL = ResourceLocation.fromNamespaceAndPath(TFCGourmetMod.MODID, "brinza_wheel");
    public static final ResourceLocation MILKABLE_ANIMAL = ResourceLocation.fromNamespaceAndPath(TFCGourmetMod.MODID, "milkable_animal");

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerEntityDataProvider(MilkableAnimalProvider.INSTANCE, net.minecraft.world.entity.animal.Animal.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(BrinzaWheelProvider.INSTANCE, BrinzaWheelBlock.class);
        registration.registerEntityComponent(MilkableAnimalProvider.INSTANCE, net.minecraft.world.entity.animal.Animal.class);
    }
}
