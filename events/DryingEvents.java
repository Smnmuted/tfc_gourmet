package com.tfcgourmet.tfc_gourmet.events;

import com.tfcgourmet.tfc_gourmet.TFCGourmetMod;
import com.tfcgourmet.tfc_gourmet.items.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TFCGourmetMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DryingEvents {
    
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide()) return;
        
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();
        
        
        if (heldItem.getItem() == net.minecraft.world.item.Items.AIR) return;
        if (event.getLevel().getBlockState(event.getPos()).getBlock().getDescriptionId().contains("drying_rack")) {
            if (heldItem.getItem() == net.minecraft.world.item.Items.AIR) { 
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
                player.getInventory().add(new ItemStack(ModItems.DRIED_MINT_LEAVES.get(), 1));
            }
            
            if (heldItem.getItem() == net.minecraft.world.item.Items.AIR) { 
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
                player.getInventory().add(new ItemStack(ModItems.DRIED_CHAMOMILE_LEAVES.get(), 1));
            }
            
            if (heldItem.getItem() == net.minecraft.world.item.Items.AIR) { 
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
                player.getInventory().add(new ItemStack(ModItems.DRIED_ROSEHIP_LEAVES.get(), 1));
            }
            
            if (heldItem.getItem() == net.minecraft.world.item.Items.AIR) { 
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
                player.getInventory().add(new ItemStack(ModItems.DRIED_NETTLE_LEAVES.get(), 1));
            }
        }
    }
}
