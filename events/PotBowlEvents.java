package com.tfcgourmet.tfc_gourmet.events;

import com.mojang.logging.LogUtils;
import com.tfcgourmet.tfc_gourmet.items.BowlFoodItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = "tfc_gourmet", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PotBowlEvents {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ResourceLocation TFC_CERAMIC_BOWL = ResourceLocation.parse("tfc:ceramic/bowl");
    private static final ResourceLocation TFC_WOODEN_BOWL = ResourceLocation.parse("tfc:wooden/bowl");
    private static final ResourceLocation MINECRAFT_BOWL = ResourceLocation.parse("minecraft:bowl");
    
    private static final Map<BlockPos, ItemStack> potBowlCache = new HashMap<>();
    
    private static boolean isValidBowl(ResourceLocation itemId) {
        if (itemId == null) return false;
        return itemId.equals(TFC_CERAMIC_BOWL) || 
               itemId.equals(TFC_WOODEN_BOWL) ||
               itemId.equals(MINECRAFT_BOWL);
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide) {
            return; 
        }
        
        Player player = event.getEntity();
        ItemStack heldItem = player.getItemInHand(event.getHand());
        BlockPos pos = event.getPos();
        BlockEntity be = event.getLevel().getBlockEntity(pos);
        
        if (!heldItem.isEmpty() && be != null) {
            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(heldItem.getItem());
            if (isValidBowl(itemId)) {
                IItemHandler handler = be.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
                if (handler != null) {
                    ItemStack bowlCopy = heldItem.copy();
                    bowlCopy.setCount(1);
                    potBowlCache.put(pos, bowlCopy);
                }
            }
        }
    }
    
    @SubscribeEvent
    public static void onContainerOpen(PlayerContainerEvent.Open event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }
        
        Player player = event.getEntity();
        AbstractContainerMenu menu = event.getContainer();
        
        for (int i = 0; i < menu.slots.size(); i++) {
            Slot slot = menu.slots.get(i);
            ItemStack slotStack = slot.getItem();
            if (!slotStack.isEmpty()) {
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(slotStack.getItem());
                if (itemId != null && (itemId.equals(TFC_CERAMIC_BOWL) || itemId.toString().equals("minecraft:bowl"))) {
                }
            }
        }
    }
    
    @SubscribeEvent
    public static void onItemPickup(PlayerEvent.ItemPickupEvent event) {
        if (event.getEntity().level().isClientSide) {
            return; 
        }
        
        ItemStack stack = event.getStack();
        Player player = event.getEntity();
        
        if (stack.getItem() instanceof BowlFoodItem) {
            if (!stack.hasTag() || stack.getTag() == null || !stack.getTag().contains("bowl")) {
                findAndSaveBowlForStack(stack, player, null);
            } else {
                CompoundTag tag = stack.getTag();
                if (tag != null && tag.contains("bowl", 10)) {
                    ItemStack savedBowl = ItemStack.of(tag.getCompound("bowl"));
                }
            }
        }
    }
    
    @SubscribeEvent
    public static void onContainerClose(PlayerContainerEvent.Close event) {
        if (event.getEntity().level().isClientSide) {
            return; 
        }
        
        Player player = event.getEntity();
        AbstractContainerMenu menu = event.getContainer();
        
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() instanceof BowlFoodItem) {
                if (!stack.hasTag() || stack.getTag() == null || !stack.getTag().contains("bowl")) {
                    findAndSaveBowlForStack(stack, player, menu);
                }
            }
        }
    }

    public static void findAndSaveBowlForStack(ItemStack foodStack, Player player, AbstractContainerMenu menu) {
        
        if (menu != null) {
            for (int i = 0; i < menu.slots.size(); i++) {
                Slot slot = menu.slots.get(i);
                ItemStack slotStack = slot.getItem();
                
                if (!slotStack.isEmpty()) {
                    ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(slotStack.getItem());
                    if (isValidBowl(itemId)) {
                        LOGGER.info("[PotBowlEvents] Found bowl in menu slot {}: {}", i, itemId);
                        ItemStack bowlCopy = slotStack.copy();
                        bowlCopy.setCount(1);
                        saveBowlToNBT(foodStack, bowlCopy);
                        return;
                    }
                }
            }
        }
        
        LOGGER.info("[PotBowlEvents] Checking cached bowls. Cache size: {}", potBowlCache.size());
        net.minecraft.core.BlockPos playerPos = player.blockPosition();
        BlockPos closestCachedPos = null;
        double closestDistance = Double.MAX_VALUE;
        
        for (Map.Entry<BlockPos, ItemStack> entry : potBowlCache.entrySet()) {
            BlockPos cachedPos = entry.getKey();
            ItemStack cachedBowl = entry.getValue();
            
            if (!cachedBowl.isEmpty()) {
                double distance = playerPos.distSqr(cachedPos);
                if (distance < closestDistance && distance <= 64) { 
                    closestDistance = distance;
                    closestCachedPos = cachedPos;
                }
            }
        }
        
        if (closestCachedPos != null) {
            ItemStack cachedBowl = potBowlCache.get(closestCachedPos);
            ResourceLocation bowlId = BuiltInRegistries.ITEM.getKey(cachedBowl.getItem());
            ItemStack bowlCopy = cachedBowl.copy();
            bowlCopy.setCount(1);
            saveBowlToNBT(foodStack, bowlCopy);
            return;
        }
        
        for (int x = -8; x <= 8; x++) {
            for (int y = -3; y <= 3; y++) {
                for (int z = -8; z <= 8; z++) {
                    BlockPos checkPos = playerPos.offset(x, y, z);
                    BlockEntity be = player.level().getBlockEntity(checkPos);
                    if (be != null) {
                        IItemHandler handler = be.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
                        if (handler != null) {
                            for (int i = 0; i < handler.getSlots(); i++) {
                                ItemStack slotStack = handler.getStackInSlot(i);
                                if (!slotStack.isEmpty()) {
                                    ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(slotStack.getItem());
                                    if (isValidBowl(itemId)) {
                                        ItemStack bowlCopy = slotStack.copy();
                                        bowlCopy.setCount(1);
                                        saveBowlToNBT(foodStack, bowlCopy);
                                        potBowlCache.put(checkPos, bowlCopy);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
    }

    private static void saveBowlToNBT(ItemStack stack, ItemStack bowl) {
        if (stack.isEmpty() || bowl.isEmpty()) {
            return;
        }
        
        ResourceLocation bowlId = BuiltInRegistries.ITEM.getKey(bowl.getItem());
        
        net.minecraft.nbt.CompoundTag tag = stack.getOrCreateTag();
        net.minecraft.nbt.CompoundTag bowlTag = new net.minecraft.nbt.CompoundTag();
        bowl.save(bowlTag);
        tag.put("bowl", bowlTag);
        
    }
}
