package com.tfcgourmet.tfc_gourmet.items;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.slf4j.Logger;

public class BowlFoodItem extends Item {
    private static final ResourceLocation TFC_CERAMIC_BOWL = ResourceLocation.parse("tfc:ceramic/bowl");
    private static final Logger LOGGER = LogUtils.getLogger();

    public BowlFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        LOGGER.info("[BowlFoodItem] onCraftedBy called! Item: {}", BuiltInRegistries.ITEM.getKey(stack.getItem()));
        
        if (player.containerMenu != null) {
            LOGGER.info("[BowlFoodItem] onCraftedBy: Checking crafting menu...");
            ItemStack bowlInGrid = findBowlInMenu(player.containerMenu);
            if (!bowlInGrid.isEmpty()) {
                LOGGER.info("[BowlFoodItem] onCraftedBy: Found bowl in crafting menu: {}", BuiltInRegistries.ITEM.getKey(bowlInGrid.getItem()));
                saveBowlToNBT(stack, bowlInGrid);
                return; 
            }
        }
        
        attemptToSaveBowl(stack, player);
        
        if (stack.hasTag()) {
            LOGGER.info("[BowlFoodItem] Final NBT after onCraftedBy: {}", stack.getTag());
        }
    }
    
    private ItemStack findBowlInMenu(net.minecraft.world.inventory.AbstractContainerMenu menu) {
        for (int i = 0; i < menu.slots.size(); i++) {
            net.minecraft.world.inventory.Slot slot = menu.slots.get(i);
            ItemStack slotStack = slot.getItem();
            if (isValidBowl(slotStack)) {
                ItemStack bowlCopy = slotStack.copy();
                bowlCopy.setCount(1);
                LOGGER.info("[BowlFoodItem] Found bowl in menu slot {}: {}", i, BuiltInRegistries.ITEM.getKey(bowlCopy.getItem()));
                return bowlCopy;
            }
        }
        return ItemStack.EMPTY;
    }
    
    private ItemStack findBowlInPlayerInventory(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack slotStack = player.getInventory().getItem(i);
            if (isValidBowl(slotStack)) {
                ItemStack bowlCopy = slotStack.copy();
                bowlCopy.setCount(1);
                LOGGER.info("[BowlFoodItem] Found bowl in inventory slot {}: {}", i, BuiltInRegistries.ITEM.getKey(bowlCopy.getItem()));
                return bowlCopy;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, net.minecraft.world.entity.Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        
        if (stack.hasTag() && stack.getTag() != null && stack.getTag().contains("bowl")) {
            return;
        }
        
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.getBoolean("bowl_checked")) {
            return; 
        }
        tag.putBoolean("bowl_checked", true);
        
        if (entity instanceof Player player) {
            LOGGER.info("[BowlFoodItem] inventoryTick: Item without bowl NBT detected, attempting to save bowl from pot/container");
            attemptToSaveBowlFromPot(stack, player, level);
        }
    }
    
    private void attemptToSaveBowlFromPot(ItemStack stack, Player player, Level level) {
        LOGGER.info("[PotBowl] Attempting to save bowl for pot recipe item: {}", BuiltInRegistries.ITEM.getKey(stack.getItem()));
        
        if (stack.hasTag() && stack.getTag() != null) {
            CompoundTag tag = stack.getTag();
            LOGGER.info("[PotBowl] Item NBT keys: {}", tag.getAllKeys());
            
            if (tag.contains("ForgeCaps", 10)) {
                CompoundTag caps = tag.getCompound("ForgeCaps");
                LOGGER.info("[PotBowl] ForgeCaps keys: {}", caps.getAllKeys());
                
                for (String key : caps.getAllKeys()) {
                    CompoundTag capTag = caps.getCompound(key);
                    LOGGER.info("[PotBowl] Checking ForgeCaps[{}]: {}", key, capTag);
                    
                    if (capTag.contains("Item", 10)) {
                        ItemStack bowlStack = ItemStack.of(capTag.getCompound("Item"));
                        if (isValidBowl(bowlStack)) {
                            LOGGER.info("[PotBowl] Found bowl in ForgeCaps[{}]: {}", key, BuiltInRegistries.ITEM.getKey(bowlStack.getItem()));
                            ItemStack bowlCopy = bowlStack.copy();
                            bowlCopy.setCount(1);
                            saveBowlToNBT(stack, bowlCopy);
                            return;
                        }
                    }
                }
            }
        }
        
        com.tfcgourmet.tfc_gourmet.events.PotBowlEvents.findAndSaveBowlForStack(stack, player, null);
        
        if (stack.hasTag() && stack.getTag() != null && stack.getTag().contains("bowl", 10)) {
            ItemStack savedBowl = ItemStack.of(stack.getTag().getCompound("bowl"));
            LOGGER.info("[PotBowl] Bowl saved successfully: {}", BuiltInRegistries.ITEM.getKey(savedBowl.getItem()));
        } else {
            LOGGER.error("[PotBowl] Bowl was NOT saved! Item NBT: {}", stack.getTag());
        }
    }

    private void attemptToSaveBowl(ItemStack stack, Player player) {
        String itemName = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
        LOGGER.info("[BowlFoodItem] attemptToSaveBowl called for item: {}", itemName);
        
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("bowl")) {
            ItemStack existingBowl = ItemStack.of(tag.getCompound("bowl"));
            LOGGER.info("[BowlFoodItem] Bowl already saved in NBT: {}", BuiltInRegistries.ITEM.getKey(existingBowl.getItem()));
            return;
        }
        
        LOGGER.info("[BowlFoodItem] Bowl not found in NBT, searching...");
        LOGGER.info("[BowlFoodItem] ItemStack NBT: {}", stack.getTag());
        
        if (stack.hasTag()) {
            CompoundTag fullNbt = stack.getTag();
            LOGGER.info("[BowlFoodItem] Full NBT dump: {}", fullNbt);
            if (fullNbt != null && fullNbt.contains("ForgeCaps", 10)) {
                CompoundTag caps = fullNbt.getCompound("ForgeCaps");
                LOGGER.info("[BowlFoodItem] ForgeCaps keys: {}", caps.getAllKeys());
                for (String key : caps.getAllKeys()) {
                    LOGGER.info("[BowlFoodItem] ForgeCaps[{}]: {}", key, caps.getCompound(key));
                }
            }
        }
        
        ItemStack bowlStack = getBowlFromCapability(stack);
        if (!bowlStack.isEmpty()) {
            LOGGER.info("[BowlFoodItem] Found bowl in capability: {}", BuiltInRegistries.ITEM.getKey(bowlStack.getItem()));
            saveBowlToNBT(stack, bowlStack);
        } else {
            bowlStack = getBowlFromForgeCaps(stack);
            if (!bowlStack.isEmpty()) {
                LOGGER.info("[BowlFoodItem] Found bowl in ForgeCaps NBT: {}", BuiltInRegistries.ITEM.getKey(bowlStack.getItem()));
                saveBowlToNBT(stack, bowlStack);
            } else {
                LOGGER.warn("[BowlFoodItem] No bowl found in capability or ForgeCaps! Cannot determine which bowl was used.");
            }
        }
    }

    private ItemStack getBowlFromCapability(ItemStack stack) {
        try {
            IItemHandler containerHandler = stack.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
            if (containerHandler != null) {
                LOGGER.info("[BowlFoodItem] Capability handler found, slots: {}", containerHandler.getSlots());
                for (int i = 0; i < containerHandler.getSlots(); i++) {
                    ItemStack containerStack = containerHandler.getStackInSlot(i);
                    if (!containerStack.isEmpty()) {
                        LOGGER.info("[BowlFoodItem] Capability slot {} contains: {}", i, BuiltInRegistries.ITEM.getKey(containerStack.getItem()));
                        if (isValidBowl(containerStack)) {
                            ItemStack bowlCopy = containerStack.copy();
                            bowlCopy.setCount(1);
                            LOGGER.info("[BowlFoodItem] Found valid bowl in capability slot {}: {}", i, BuiltInRegistries.ITEM.getKey(bowlCopy.getItem()));
                            return bowlCopy;
                        }
                    }
                }
            } else {
                LOGGER.info("[BowlFoodItem] No capability handler available");
            }
        } catch (Exception e) {
            LOGGER.warn("[BowlFoodItem] Exception reading capability: {}", e.getMessage(), e);
        }
        return ItemStack.EMPTY;
    }

    private ItemStack getBowlFromForgeCaps(ItemStack stack) {
        if (!stack.hasTag()) {
            return ItemStack.EMPTY;
        }
        CompoundTag nbt = stack.getTag();
        if (nbt == null || !nbt.contains("ForgeCaps", 10)) {
            return ItemStack.EMPTY;
        }
        
        CompoundTag caps = nbt.getCompound("ForgeCaps");
        LOGGER.info("[BowlFoodItem] Searching ForgeCaps with keys: {}", caps.getAllKeys());
        
        String[] possibleKeys = {
            "firmalife:container",
            "firmalife:bowl_container",
            "firmalife:food_container",
            "firmalife:item_handler",
            "tfc:container"
        };
        
        for (String key : possibleKeys) {
            if (caps.contains(key, 10)) {
                CompoundTag containerCap = caps.getCompound(key);
                LOGGER.info("[BowlFoodItem] Found capability key: {}, content: {}", key, containerCap);
                
                String[] itemKeys = {"Item", "Stack", "container", "bowl", "Bowl", "item", "stack"};
                for (String itemKey : itemKeys) {
                    if (containerCap.contains(itemKey, 10)) {
                        ItemStack bowlStack = ItemStack.of(containerCap.getCompound(itemKey));
                        LOGGER.info("[BowlFoodItem] Found ItemStack in {}.{}: {}", key, itemKey, bowlStack.isEmpty() ? "EMPTY" : BuiltInRegistries.ITEM.getKey(bowlStack.getItem()));
                        if (isValidBowl(bowlStack)) {
                            return bowlStack;
                        }
                    }
                }
                
                if (containerCap.contains("id", 8)) {
                    String itemId = containerCap.getString("id");
                    LOGGER.info("[BowlFoodItem] Found id in {}: {}", key, itemId);
                    ResourceLocation itemLoc = ResourceLocation.tryParse(itemId);
                    if (itemLoc != null && BuiltInRegistries.ITEM.containsKey(itemLoc)) {
                        ItemStack bowlStack = new ItemStack(BuiltInRegistries.ITEM.get(itemLoc));
                        if (isValidBowl(bowlStack)) {
                            return bowlStack;
                        }
                    }
                }
            }
        }
        
        for (String key : caps.getAllKeys()) {
            CompoundTag testCap = caps.getCompound(key);
            LOGGER.info("[BowlFoodItem] Checking key: {}, type: {}", key, testCap.getType());
            
            if (testCap.contains("Item", 10)) {
                ItemStack bowlStack = ItemStack.of(testCap.getCompound("Item"));
                if (isValidBowl(bowlStack)) {
                    LOGGER.info("[BowlFoodItem] Found bowl in ForgeCaps[{}].Item: {}", key, BuiltInRegistries.ITEM.getKey(bowlStack.getItem()));
                    return bowlStack;
                }
            }
        }
        
        return ItemStack.EMPTY;
    }

    private void saveBowlToNBT(ItemStack stack, ItemStack bowl) {
        if (stack.isEmpty() || bowl.isEmpty()) {
            LOGGER.warn("[BowlFoodItem] saveBowlToNBT: Cannot save - foodStack empty: {}, bowl empty: {}", stack.isEmpty(), bowl.isEmpty());
            return;
        }
        
        ResourceLocation bowlId = BuiltInRegistries.ITEM.getKey(bowl.getItem());
        LOGGER.info("[BowlFoodItem] saveBowlToNBT: Saving bowl {} to NBT", bowlId);
        LOGGER.info("[BowlFoodItem] saveBowlToNBT: Bowl ItemStack: count={}, NBT={}", bowl.getCount(), bowl.getTag());
        
        CompoundTag tag = stack.getOrCreateTag();
        CompoundTag bowlTag = new CompoundTag();
        bowl.save(bowlTag);
        
        LOGGER.info("[BowlFoodItem] saveBowlToNBT: Bowl tag before save: {}", bowlTag);
        LOGGER.info("[BowlFoodItem] saveBowlToNBT: Bowl tag contains 'id': {}, value: {}", 
            bowlTag.contains("id", 8), 
            bowlTag.contains("id", 8) ? bowlTag.getString("id") : "N/A");
        
        tag.put("bowl", bowlTag);
        
        LOGGER.info("[BowlFoodItem] saveBowlToNBT: Saved! Full NBT after save: {}", tag);
        
        if (tag.contains("bowl", 10)) {
            CompoundTag savedBowl = tag.getCompound("bowl");
            String savedId = savedBowl.contains("id", 8) ? savedBowl.getString("id") : "NO ID";
            LOGGER.info("[BowlFoodItem] saveBowlToNBT: VERIFIED - Bowl tag saved correctly. ID in tag: {}", savedId);
            if (!savedId.equals(bowlId.toString())) {
                LOGGER.error("[BowlFoodItem] saveBowlToNBT: ERROR - Bowl ID mismatch! Expected: {}, Got: {}", bowlId, savedId);
            }
        } else {
            LOGGER.error("[BowlFoodItem] saveBowlToNBT: ERROR - Bowl tag not found after save!");
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        String itemName = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
        LOGGER.info("[BowlFoodItem] finishUsingItem called for: {}", itemName);
        LOGGER.info("[BowlFoodItem] ItemStack NBT before consumption: {}", stack.getTag());
        
        ItemStack bowlStack = ItemStack.EMPTY;

        if (entity instanceof Player player && !player.getAbilities().instabuild) {
            bowlStack = getBowlFromStack(stack);
            if (!bowlStack.isEmpty()) {
                LOGGER.info("[BowlFoodItem] Retrieved bowl: {}", BuiltInRegistries.ITEM.getKey(bowlStack.getItem()));
            } else {
                LOGGER.warn("[BowlFoodItem] NO BOWL FOUND IN STACK!");
            }
        }

        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (entity instanceof Player player && !player.getAbilities().instabuild && !bowlStack.isEmpty()) {
            if (result.isEmpty()) {
                LOGGER.info("[BowlFoodItem] Result empty, returning bowl");
                return bowlStack;
            }
            LOGGER.info("[BowlFoodItem] Adding bowl to inventory: {}", BuiltInRegistries.ITEM.getKey(bowlStack.getItem()));
            if (!player.getInventory().add(bowlStack)) {
                ItemHandlerHelper.giveItemToPlayer(player, bowlStack);
            }
        } else {
            LOGGER.warn("[BowlFoodItem] Not returning bowl - instabuild: {}, bowlEmpty: {}", 
                entity instanceof Player && ((Player)entity).getAbilities().instabuild,
                bowlStack.isEmpty());
        }
        
        return result;
    }
    
    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return !getBowlFromStack(stack).isEmpty();
    }
    
    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack bowl = getBowlFromStack(stack);
        return bowl.isEmpty() ? ItemStack.EMPTY : bowl;
    }
    
    private ItemStack getBowlFromStack(ItemStack foodStack) {
        if (foodStack.isEmpty()) {
            LOGGER.info("[BowlFoodItem] getBowlFromStack: foodStack is empty");
            return ItemStack.EMPTY;
        }

        LOGGER.info("[BowlFoodItem] getBowlFromStack: checking NBT tag first");
        ItemStack tagged = readBowlTag(foodStack);
        if (!tagged.isEmpty()) {
            LOGGER.info("[BowlFoodItem] Found bowl in NBT tag: {}", BuiltInRegistries.ITEM.getKey(tagged.getItem()));
            return tagged;
        }
        LOGGER.info("[BowlFoodItem] No bowl found in NBT tag, checking capability...");

        try {
        IItemHandler containerHandler = foodStack.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
        if (containerHandler != null && containerHandler.getSlots() > 0) {
            ItemStack containerStack = containerHandler.getStackInSlot(0);
                LOGGER.info("[BowlFoodItem] Capability slot 0: {}", containerStack.isEmpty() ? "EMPTY" : BuiltInRegistries.ITEM.getKey(containerStack.getItem()));
                if (isValidBowl(containerStack)) {
                    LOGGER.info("[BowlFoodItem] Found valid bowl in capability, persisting to NBT");
                    persistBowl(foodStack, containerStack);
                return containerStack;
                }
            } else {
                LOGGER.info("[BowlFoodItem] No capability handler available");
            }
        } catch (Exception e) {
            LOGGER.warn("[BowlFoodItem] Exception reading capability: {}", e.getMessage());
        }
        
        if (foodStack.hasTag()) {
            CompoundTag nbt = foodStack.getTag();
            if (nbt != null) {
                if (nbt.contains("ForgeCaps", 10)) {
                    CompoundTag caps = nbt.getCompound("ForgeCaps");
                    String[] possibleKeys = {
                        "firmalife:container",
                        "firmalife:bowl_container",
                        "firmalife:food_container"
                    };

                    for (String key : possibleKeys) {
                        if (caps.contains(key, 10)) {
                            CompoundTag containerCap = caps.getCompound(key);
                            String[] itemKeys = {"Item", "Stack", "container", "bowl", "Bowl"};
                            for (String itemKey : itemKeys) {
                                if (containerCap.contains(itemKey, 10)) {
                                    ItemStack bowlStack = ItemStack.of(containerCap.getCompound(itemKey));
                                    if (isValidBowl(bowlStack)) {
                                        persistBowl(foodStack, bowlStack);
                                        return bowlStack;
                                    }
                                }
                            }
                            if (containerCap.contains("id", 8)) {
                                String itemId = containerCap.getString("id");
                                ResourceLocation itemLoc = ResourceLocation.tryParse(itemId);
                                if (itemLoc != null && BuiltInRegistries.ITEM.containsKey(itemLoc)) {
                                    ItemStack bowlStack = new ItemStack(BuiltInRegistries.ITEM.get(itemLoc));
                                    if (isValidBowl(bowlStack)) {
                                        persistBowl(foodStack, bowlStack);
                                        return bowlStack;
                                    }
                                }
                            }
                        }
                    }

                    if (caps.contains("tfc:container", 10)) {
                        CompoundTag containerCap = caps.getCompound("tfc:container");
                        if (containerCap.contains("Item", 10)) {
                            ItemStack bowlStack = ItemStack.of(containerCap.getCompound("Item"));
                            if (isValidBowl(bowlStack)) {
                                persistBowl(foodStack, bowlStack);
                                return bowlStack;
                            }
                        }
                    }

                    for (String key : caps.getAllKeys()) {
                        CompoundTag testCap = caps.getCompound(key);
                        if (testCap.contains("Item", 10)) {
                            ItemStack bowlStack = ItemStack.of(testCap.getCompound("Item"));
                            if (isValidBowl(bowlStack)) {
                                persistBowl(foodStack, bowlStack);
                                return bowlStack;
                            }
                        }

                        for (String nestedKey : testCap.getAllKeys()) {
                            if (testCap.contains(nestedKey, 10)) {
                                CompoundTag nested = testCap.getCompound(nestedKey);
                                if (nested.contains("Item", 10)) {
                                    ItemStack bowlStack = ItemStack.of(nested.getCompound("Item"));
                                    if (isValidBowl(bowlStack)) {
                                        persistBowl(foodStack, bowlStack);
                        return bowlStack;
                                    }
                                }
                            }
                        }
                    }
                }
                
                if (nbt.contains("Container", 8)) { // 8 = String
                    String containerId = nbt.getString("Container");
                    if (!containerId.isEmpty()) {
                        ResourceLocation containerLoc = ResourceLocation.tryParse(containerId);
                        if (containerLoc != null && BuiltInRegistries.ITEM.containsKey(containerLoc)) {
                            ItemStack bowlStack = new ItemStack(BuiltInRegistries.ITEM.get(containerLoc));
                            if (isValidBowl(bowlStack)) {
                                persistBowl(foodStack, bowlStack);
                                return bowlStack;
                            }
                        }
                    }
                }

                if (nbt.contains("container", 8)) {
                    String containerId = nbt.getString("container");
                    if (!containerId.isEmpty()) {
                        ResourceLocation containerLoc = ResourceLocation.tryParse(containerId);
                        if (containerLoc != null && BuiltInRegistries.ITEM.containsKey(containerLoc)) {
                            ItemStack bowlStack = new ItemStack(BuiltInRegistries.ITEM.get(containerLoc));
                            if (isValidBowl(bowlStack)) {
                                persistBowl(foodStack, bowlStack);
                                return bowlStack;
                            }
                        }
                    }
                }
            }
        }
        
        LOGGER.warn("[BowlFoodItem] getBowlFromStack: NO BOWL FOUND ANYWHERE! Returning EMPTY");
        LOGGER.info("[BowlFoodItem] Full NBT dump: {}", foodStack.getTag());
        return ItemStack.EMPTY;
    }

    private ItemStack readBowlTag(ItemStack foodStack) {
        if (!foodStack.hasTag()) {
            LOGGER.info("[BowlFoodItem] readBowlTag: No NBT tag");
            return ItemStack.EMPTY;
        }
        CompoundTag tag = foodStack.getTag();
        if (tag == null) {
            LOGGER.info("[BowlFoodItem] readBowlTag: Tag is null");
            return ItemStack.EMPTY;
        }
        LOGGER.info("[BowlFoodItem] readBowlTag: Checking keys. All tag keys: {}", tag.getAllKeys());
        String[] keys = {"bowl", "Bowl"};
        for (String key : keys) {
            if (tag.contains(key, 10)) {
                LOGGER.info("[BowlFoodItem] readBowlTag: Found key '{}'", key);
                ItemStack bowl = ItemStack.of(tag.getCompound(key));
                LOGGER.info("[BowlFoodItem] readBowlTag: Bowl from tag: {}", bowl.isEmpty() ? "EMPTY" : BuiltInRegistries.ITEM.getKey(bowl.getItem()));
                if (isValidBowl(bowl)) {
                    return bowl;
                } else {
                    LOGGER.warn("[BowlFoodItem] readBowlTag: Bowl from tag is not valid!");
                }
            }
        }
        LOGGER.info("[BowlFoodItem] readBowlTag: No bowl/Bowl key found");
        return ItemStack.EMPTY;
    }

    private void persistBowl(ItemStack foodStack, ItemStack bowl) {
        if (foodStack.isEmpty() || bowl.isEmpty()) {
            LOGGER.warn("[BowlFoodItem] persistBowl: Cannot save - foodStack empty: {}, bowl empty: {}", foodStack.isEmpty(), bowl.isEmpty());
            return;
        }
        LOGGER.info("[BowlFoodItem] persistBowl: Saving bowl {} to NBT", BuiltInRegistries.ITEM.getKey(bowl.getItem()));
        CompoundTag tag = foodStack.getOrCreateTag();
        CompoundTag bowlTag = new CompoundTag();
        bowl.save(bowlTag);
        tag.put("bowl", bowlTag);
        LOGGER.info("[BowlFoodItem] persistBowl: Saved! New NBT: {}", tag);
    }

    private boolean isValidBowl(ItemStack bowlStack) {
        if (bowlStack.isEmpty()) {
            return false;
        }
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(bowlStack.getItem());
        if (id == null) {
            LOGGER.warn("[BowlFoodItem] isValidBowl: Item has no resource location!");
            return false;
        }
        boolean isValid = id.equals(TFC_CERAMIC_BOWL) || 
                         id.toString().equals("minecraft:bowl") ||
                         id.toString().equals("tfc:wooden/bowl");
        LOGGER.info("[BowlFoodItem] isValidBowl: {} - isValid: {}", id, isValid);
        return isValid;
    }
}
