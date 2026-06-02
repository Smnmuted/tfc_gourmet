package com.tfcgourmet.tfc_gourmet.fluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class BaseFluidType extends FluidType {
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;
    private final Vector3f fogColor;
    private final int tintColor;

    public BaseFluidType(ResourceLocation stillTexture, ResourceLocation flowingTexture) {
        this(stillTexture, flowingTexture, new Vector3f(1.0f, 1.0f, 1.0f), 0xFFFFFFFF);
    }

    public BaseFluidType(ResourceLocation stillTexture, ResourceLocation flowingTexture, Vector3f fogColor, int tintColor) {
        super(Properties.create()
                .density(1024)
                .viscosity(1024)
                .canConvertToSource(false)
                .supportsBoating(false)
                .canHydrate(false)
                .canSwim(false)
                .canDrown(false)
                .fallDistanceModifier(0f)
                .motionScale(0.014)
                .canPushEntity(false)
                .canExtinguish(false)
                .lightLevel(0)
                .temperature(300)
                .rarity(net.minecraft.world.item.Rarity.COMMON)
        );
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
        this.fogColor = fogColor;
        this.tintColor = tintColor;
    }

    public void initializeClient(Consumer<net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions> consumer) {
        consumer.accept(new net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return stillTexture;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return flowingTexture;
            }

            public Vector3f modifyFogColor(net.minecraft.client.Camera camera, float partialTick, net.minecraft.world.level.material.FogType fogType, float renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                return fogColor;
            }

            public int getTintColor() {
                return tintColor;
            }
        });
    }
}