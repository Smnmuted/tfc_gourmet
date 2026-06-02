package com.tfcgourmet.tfc_gourmet.events;

import com.tfcgourmet.tfc_gourmet.TFCGourmetMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = TFCGourmetMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RemoveItemEvents {

    @SubscribeEvent
    public static void onRegisterEvent(RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)) {
            if (net.minecraftforge.fml.ModList.get().isLoaded("firmalife")) {
                removeFirmalifePizza(event);
            } else {
                TFCGourmetMod.LOGGER.info("Firmalife not loaded, skipping pizza removal");
            }
        }
    }

    private static void removeFirmalifePizza(RegisterEvent event) {
        String[] pizzaItemsToRemove = {
            "firmalife:food/raw_pizza",
            "firmalife:food/cooked_pizza",
            "tfc:food/grain_soup", 
            "tfc:food/dairy_soup", 
            "tfc:food/protein_soup", 
            "tfc:food/vegetables_soup"
        };

        for (String itemId : pizzaItemsToRemove) {
            ResourceLocation itemLocation = ResourceLocation.parse(itemId);
            try {
                TFCGourmetMod.LOGGER.info("Marking Firmalife item for removal: {}", itemId);
            } catch (Exception e) {
                TFCGourmetMod.LOGGER.warn("Could not process Firmalife item {}: {}", itemId, e.getMessage());
            }
        }
    }
}
