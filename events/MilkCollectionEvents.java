package com.tfcgourmet.tfc_gourmet.events;

import com.tfcgourmet.tfc_gourmet.fluids.ModFluids;
import com.tfcgourmet.tfc_gourmet.items.ModItems;
import com.tfcgourmet.tfc_gourmet.util.TFCAnimalAccessor;
import com.tfcgourmet.tfc_gourmet.util.TFCTimeHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

@Mod.EventBusSubscriber(modid = "tfc_gourmet")
public class MilkCollectionEvents {
    
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Animal animal)) {
            return;
        }
        
        Player player = event.getEntity();
        ItemStack heldItem = player.getItemInHand(event.getHand());
        
        String itemId = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(heldItem.getItem()).toString();
        
        if (!itemId.contains("bucket")) {
            return;
        }
        
        if (heldItem.getCount() != 1) {
            return; 
        }
        
        if (heldItem.hasTag()) {
            CompoundTag tag = heldItem.getTag();
            if (tag.contains("fluid") || tag.contains("Fluid")) {
                return; 
            }
        }
        
        String entityType = net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(animal.getType()).toString();
        
        net.minecraft.world.level.material.Fluid milkFluid = null;
        Item milkBucket = null;
        
        if (entityType.equals("tfc:musk_ox")) {
            milkFluid = ModFluids.OX_MILK_SOURCE.get();
            milkBucket = ModItems.OX_MILK_BUCKET.get();
        } else if (entityType.equals("tfc:sheep")) {
            milkFluid = ModFluids.SHEEP_MILK_SOURCE.get();
            milkBucket = ModItems.SHEEP_MILK_BUCKET.get();
        } else if (entityType.equals("tfg:glacian_ram")) {
            milkFluid = ModFluids.SHEEP_MILK_SOURCE.get();
            milkBucket = ModItems.SHEEP_MILK_BUCKET.get();
        } else if (entityType.equals("tfc:alpaca")) {
            milkFluid = ModFluids.ALPACA_MILK_SOURCE.get();
            milkBucket = ModItems.ALPACA_MILK_BUCKET.get();
        } else {
            return;
        }
        
        if (animal.isBaby()) {
            return;
        }
        
        if (animal instanceof TFCAnimalAccessor accessor) {
            Object gender = accessor.tfcGourmet$getGender();
            if (gender != null && "MALE".equals(gender.toString())) {
                player.displayClientMessage(net.minecraft.network.chat.Component.translatable("tfc_gourmet.milk_collection.male_animal"), true);
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
                return;
            }
            
            Float familiarity = accessor.tfcGourmet$getFamiliarity();
            if (familiarity != null && familiarity < 0.18f) { 
                player.displayClientMessage(net.minecraft.network.chat.Component.translatable("tfc_gourmet.milk_collection.not_familiar"), true);
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
                return;
            }
        }
        
        CompoundTag animalData = animal.getPersistentData();
        long currentTime = TFCTimeHelper.getGameTime(animal.level());
        
        if (animalData.contains("tfc_gourmet:last_milked")) {
            long lastMilked = animalData.getLong("tfc_gourmet:last_milked");
            long timeSinceMilking = currentTime - lastMilked;
            long cooldownTicks = 48000; 
            
            if (timeSinceMilking < cooldownTicks) {
                player.displayClientMessage(net.minecraft.network.chat.Component.translatable("tfc_gourmet.milk_collection.not_ready"), true);
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
                return;
            }
        }
        
        animalData.putLong("tfc_gourmet:last_milked", currentTime);
        
        if (itemId.equals("minecraft:bucket")) {
            heldItem.shrink(1);
            ItemStack milkStack = new ItemStack(milkBucket);
            
            if (!player.getInventory().add(milkStack)) {
                player.drop(milkStack, false);
            }
        } else {
            ItemStack bucketCopy = heldItem.copy();
            
            IFluidHandlerItem fluidHandler = bucketCopy.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(null);
            
            if (fluidHandler != null) {
                FluidStack fluidStack = new FluidStack(milkFluid, 1000);
                int filled = fluidHandler.fill(fluidStack, IFluidHandlerItem.FluidAction.EXECUTE);
                
                if (filled > 0) {
                    ItemStack filledBucket = fluidHandler.getContainer();
                    player.setItemInHand(event.getHand(), filledBucket);
                }
            } else {
                heldItem.shrink(1);
                ItemStack milkStack = new ItemStack(milkBucket);
                
                if (!player.getInventory().add(milkStack)) {
                    player.drop(milkStack, false);
                }
            }
        }
        animal.level().playSound(
            null,
            animal.getX(), animal.getY(), animal.getZ(),
            SoundEvents.COW_MILK,
            SoundSource.NEUTRAL,
            1.0f, 1.0f
        );
        
        try {
            Class<?> tfcProps = Class.forName("net.dries007.tfc.common.entities.livestock.TFCAnimalProperties");
            if (tfcProps.isInstance(animal)) {
                java.lang.reflect.Method addUses = tfcProps.getMethod("addUses", int.class);
                addUses.invoke(animal, 1);
            }
        } catch (Exception ignored) {}

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }
}
