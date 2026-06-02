package com.tfcgourmet.tfc_gourmet;

import com.mojang.logging.LogUtils;
import com.tfcgourmet.tfc_gourmet.blocks.ModBlockEntities;
import com.tfcgourmet.tfc_gourmet.blocks.ModBlocks;
import com.tfcgourmet.tfc_gourmet.fluids.ModFluids;
import com.tfcgourmet.tfc_gourmet.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;


@Mod(TFCGourmetMod.MODID)
public class TFCGourmetMod
{
    
    public static final String MODID = "tfc_gourmet";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> TFC_GOURMET_TAB = CREATIVE_MODE_TABS.register("tfc_gourmet", () -> CreativeModeTab.builder()
            .icon(() -> ModItems.MARGARITA_PIZZA.get().getDefaultInstance())
            .title(net.minecraft.network.chat.Component.translatable("itemGroup.tfc_gourmet.gourmet_tab"))
            .displayItems((parameters, output) -> {
                // Raw Pizza items
                output.accept(ModItems.RAW_MARGARITA_PIZZA.get());
                output.accept(ModItems.RAW_PEPPERONI_PIZZA.get());
                output.accept(ModItems.RAW_HAVAI_PIZZA.get());
                output.accept(ModItems.RAW_FOUR_CHEESES_PIZZA.get());
                output.accept(ModItems.RAW_FOUR_MEATS_PIZZA.get());
                output.accept(ModItems.RAW_NEAPOLITANO_PIZZA.get());
                // Cooked Pizza items
                output.accept(ModItems.MARGARITA_PIZZA.get());
                output.accept(ModItems.PEPPERONI_PIZZA.get());
                output.accept(ModItems.HAVAI_PIZZA.get());
                output.accept(ModItems.FOUR_CHEESES_PIZZA.get());
                output.accept(ModItems.FOUR_MEATS_PIZZA.get());
                output.accept(ModItems.NEAPOLITANO_PIZZA.get());
                // Placinda items
                output.accept(ModItems.RAW_PLACINDA.get());
                output.accept(ModItems.PLACINDA.get());
                output.accept(ModItems.RAMEN_WITH_BACON.get());
                output.accept(ModItems.RAMEN_WITH_BEEF.get());
                output.accept(ModItems.RAMEN_WITH_CAMELIDAE.get());
                output.accept(ModItems.RAMEN_WITH_CHEVON.get());
                output.accept(ModItems.PLANT_MIX.get());
                // Additional Food Items
                output.accept(ModItems.ADJIKA.get());
                output.accept(ModItems.ADJIKA_BREAD.get());
                output.accept(ModItems.PORRIDGE.get());
                output.accept(ModItems.PORRIDGE_WITH_HONEY.get());
                output.accept(ModItems.PORRIDGE_WITH_FRUITS.get());
                output.accept(ModItems.PASTA_WITH_ADJIKA.get());
                // Moldovan Traditional Food
                output.accept(ModItems.ZAMA.get());
                output.accept(ModItems.MAMALIGA.get());
                // Beverages
                output.accept(ModItems.COMPOTE_BUCKET.get());
                output.accept(ModItems.KVASS_BUCKET.get());
                output.accept(ModItems.LEMONADE_BUCKET.get());
                // Milk Items
                output.accept(ModItems.OX_MILK_BUCKET.get());
                output.accept(ModItems.SHEEP_MILK_BUCKET.get());
                output.accept(ModItems.ALPACA_MILK_BUCKET.get());
                // Curdled Milk Items
                output.accept(ModItems.CURDLED_OX_MILK_BUCKET.get());
                output.accept(ModItems.CURDLED_SHEEP_MILK_BUCKET.get());
                output.accept(ModItems.CURDLED_ALPACA_MILK_BUCKET.get());
                // Curd Items
                output.accept(ModItems.OX_CURD.get());
                output.accept(ModItems.SHEEP_CURD.get());
                output.accept(ModItems.ALPACA_CURD.get());
                // Brinza Items - REMOVED (keeping only wheels)
                // Brinza Slice Items (from cutting brinza wheels)
                output.accept(ModItems.OX_BRINZA_SLICE.get());
                output.accept(ModItems.SHEEP_BRINZA_SLICE.get());
                output.accept(ModItems.ALPACA_BRINZA_SLICE.get());
                // Brinza Wheel Blocks
                output.accept(ModBlocks.OX_BRINZA_WHEEL_ITEM.get());
                output.accept(ModBlocks.SHEEP_BRINZA_WHEEL_ITEM.get());
                output.accept(ModBlocks.ALPACA_BRINZA_WHEEL_ITEM.get());
                // Asian Cuisine
                output.accept(ModItems.FRIED_RICE.get());
                output.accept(ModItems.CURRY.get());
                output.accept(ModItems.PHO.get());
                // Mediterranean Cuisine
                output.accept(ModItems.HUMMUS.get());
                output.accept(ModItems.GREEK_SALAD.get());
                output.accept(ModItems.TZATZIKI.get());
                output.accept(ModItems.FALAFEL.get());
                output.accept(ModItems.DOLMA.get());
                output.accept(ModItems.RAW_FALAFEL.get());
                // European Cuisine
                output.accept(ModItems.GOULASH.get());
                output.accept(ModItems.RISOTTO.get());
                // Middle Eastern Cuisine
                output.accept(ModItems.RAW_LAVASH_WRAP.get());
                output.accept(ModItems.LAVASH_WRAP.get());
                // Russian Cuisine
                output.accept(ModItems.RAW_PELMENI.get());
                output.accept(ModItems.PELMENI.get());
                output.accept(ModItems.RAW_VARENIKI.get());
                output.accept(ModItems.VARENIKI.get());
                output.accept(ModItems.RAW_OLADYI.get());
                output.accept(ModItems.OLADYI.get());
                output.accept(ModItems.RAW_SYRNIKI.get());
                output.accept(ModItems.SYRNIKI.get());
                output.accept(ModItems.SAUERKRAUT.get());
                output.accept(ModItems.SLICED_CABBAGE.get());
                // Georgian/Caucasian Cuisine
                output.accept(ModItems.RAW_KHACHAPURI.get());
                output.accept(ModItems.KHACHAPURI.get());
                output.accept(ModItems.RAW_CHEBUREKI.get());
                output.accept(ModItems.CHEBUREKI.get());
                // Dried Leaves for Tea Making
                output.accept(ModItems.DRIED_MINT_LEAVES.get());
                output.accept(ModItems.DRIED_CHAMOMILE_LEAVES.get());
                output.accept(ModItems.DRIED_ROSEHIP_LEAVES.get());
                output.accept(ModItems.DRIED_NETTLE_LEAVES.get());
                // New Beverages
                output.accept(ModItems.COFFEE_BUCKET.get());
                output.accept(ModItems.TEA_MINT_BUCKET.get());
                output.accept(ModItems.TEA_CHAMOMILE_BUCKET.get());
                output.accept(ModItems.TEA_ROSEHIP_BUCKET.get());
                output.accept(ModItems.TEA_NETTLE_BUCKET.get());
                output.accept(ModItems.COCOA_BUCKET.get());
                output.accept(ModItems.NALIVKA_BUCKET.get());
                // Slavic Cuisine
                output.accept(ModItems.BORSCHT.get());
                output.accept(ModItems.SCHI.get());
                output.accept(ModItems.SOLYANKA.get());
                output.accept(ModItems.RAW_KIEV_CUTLETS.get());
                output.accept(ModItems.KIEV_CUTLETS.get());
                output.accept(ModItems.OKROSHKA.get());
                // Italian Cuisine
                output.accept(ModItems.SPAGHETTI_BOLOGNESE.get());
                output.accept(ModItems.PASTA_CARBONARA.get());
                output.accept(ModItems.TIRAMISU.get());
                // French Cuisine
                output.accept(ModItems.RAW_CROISSANTS.get());
                output.accept(ModItems.CROISSANTS.get());
                output.accept(ModItems.RATATOUILLE.get());
                output.accept(ModItems.RAW_QUICHE.get());
                output.accept(ModItems.QUICHE.get());
                output.accept(ModItems.BOUILLABAISSE.get());
                output.accept(ModItems.RAW_CREPES.get());
                output.accept(ModItems.CREPES.get());
                // German Cuisine
                output.accept(ModItems.RAW_BRATWURST.get());
                output.accept(ModItems.BRATWURST.get());
                // Japanese Cuisine
                output.accept(ModItems.RAW_TONKATSU.get());
                output.accept(ModItems.TONKATSU.get());
                output.accept(ModItems.RAW_TAKOYAKI.get());
                output.accept(ModItems.TAKOYAKI.get());
                // Korean Cuisine
                output.accept(ModItems.KIMCHI.get());
                output.accept(ModItems.RAW_BULGOGI.get());
                output.accept(ModItems.BULGOGI.get());
                // Indian Cuisine
                output.accept(ModItems.RAW_TANDOORI_CHICKEN.get());
                output.accept(ModItems.TANDOORI_CHICKEN.get());
                output.accept(ModItems.BIRYANI.get());
                // Soups
                output.accept(ModItems.FRESH_ONION_SOUP_BREAD.get());
                output.accept(ModItems.LENT_SOUP.get());
                output.accept(ModItems.GAZPACHO.get());
                output.accept(ModItems.KHARCHO.get());
                output.accept(ModItems.KHOLODNIK.get());
                output.accept(ModItems.SHURPA.get());
                output.accept(ModItems.FISH_SOUP_TOMATO.get());
                output.accept(ModItems.PEA_SOUP.get());
                output.accept(ModItems.MINESTRONE.get());
                output.accept(ModItems.FISH_SOUP.get());
                output.accept(ModItems.FRESH_ONION_SOUP.get());
                output.accept(ModItems.POTATO_STEW.get());
            }).build());

    public TFCGourmetMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        
        modEventBus.addListener(this::commonSetup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        ModFluids.FLUIDS.register(modEventBus);
        ModFluids.FLUID_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM TFC GOURMET MOD COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + String.valueOf(Config.magicNumber));

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        //lol
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from TFC Gourmet Mod server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM TFC GOURMET MOD CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
