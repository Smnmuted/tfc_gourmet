package com.tfcgourmet.tfc_gourmet.items;

import com.tfcgourmet.tfc_gourmet.TFCGourmetMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TFCGourmetMod.MODID);
    public static final RegistryObject<Item> RAW_MARGARITA_PIZZA = ITEMS.register("raw_margarita_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationMod(0.6f)
                    .build())));

    public static final RegistryObject<Item> RAW_PEPPERONI_PIZZA = ITEMS.register("raw_pepperoni_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(0.7f)
                    .build())));
                    public static final RegistryObject<Item>  SLICED_CABBAGE  = ITEMS.register("sliced_cabbage",
                    () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(0.3f)
                    .build())));
                    
    public static final RegistryObject<Item> RAW_FALAFEL = ITEMS.register("raw_falafel",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(0.3f)
                    .build())));

    public static final RegistryObject<Item> RAW_HAVAI_PIZZA = ITEMS.register("raw_hawaiian_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationMod(0.6f)
                    .build())));

    public static final RegistryObject<Item> RAW_FOUR_CHEESES_PIZZA = ITEMS.register("raw_four_cheeses_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(0.8f)
                    .build())));

    public static final RegistryObject<Item> RAW_FOUR_MEATS_PIZZA = ITEMS.register("raw_four_meats_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(0.9f)
                    .build())));

    public static final RegistryObject<Item> RAW_NEAPOLITANO_PIZZA = ITEMS.register("raw_neapolitano_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationMod(0.6f)
                    .build())));

    public static final RegistryObject<Item> MARGARITA_PIZZA = ITEMS.register("margarita_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(1.2f)
                    .build())));

    public static final RegistryObject<Item> PEPPERONI_PIZZA = ITEMS.register("pepperoni_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(1.5f)
                    .build())));

    public static final RegistryObject<Item> HAVAI_PIZZA = ITEMS.register("hawaiian_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(1.2f)
                    .build())));

    public static final RegistryObject<Item> FOUR_CHEESES_PIZZA = ITEMS.register("four_cheeses_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(1.6f)
                    .build())));

    public static final RegistryObject<Item> FOUR_MEATS_PIZZA = ITEMS.register("four_meats_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(12)
                    .saturationMod(1.8f)
                    .build())));

    public static final RegistryObject<Item> NEAPOLITANO_PIZZA = ITEMS.register("neapolitano_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(1.2f)
                    .build())));

    public static final RegistryObject<Item> RAW_PLACINDA = ITEMS.register("raw_placinda",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationMod(0.4f)
                    .build())));

    public static final RegistryObject<Item> PLACINDA = ITEMS.register("placinda",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(0.8f)
                    .build())));

    public static final RegistryObject<Item> RAMEN_WITH_BACON = ITEMS.register("ramen_with_bacon",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(1.0f)
                    .build())));

    public static final RegistryObject<Item> RAMEN_WITH_BEEF = ITEMS.register("ramen_with_beef",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(1.0f)
                    .build())));

    public static final RegistryObject<Item> RAMEN_WITH_CAMELIDAE = ITEMS.register("ramen_with_camelidae",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(1.0f)
                    .build())));

    public static final RegistryObject<Item> RAMEN_WITH_CHEVON = ITEMS.register("ramen_with_chevon",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(1.0f)
                    .build())));

    public static final RegistryObject<Item> PLANT_MIX = ITEMS.register("plant_mix",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationMod(0.4f)
                    .build())));

    public static final RegistryObject<Item> ADJIKA = ITEMS.register("adjika",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationMod(2.5f)
                    .build())));

    public static final RegistryObject<Item> ADJIKA_BREAD = ITEMS.register("adjika_bread",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(3.0f)
                    .build())));

    public static final RegistryObject<Item> PORRIDGE = ITEMS.register("porridge",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationMod(3.0f)
                    .build())));

    public static final RegistryObject<Item> PORRIDGE_WITH_HONEY = ITEMS.register("porridge_with_honey",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(3.5f)
                    .build())));

    public static final RegistryObject<Item> PORRIDGE_WITH_FRUITS = ITEMS.register("porridge_with_fruits",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(3.2f)
                    .build())));

    public static final RegistryObject<Item> PASTA_WITH_ADJIKA = ITEMS.register("pasta_with_adjika",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(3.5f)
                    .build())));

    public static final RegistryObject<Item> ZAMA = ITEMS.register("zama",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(3.8f)
                    .build())));

    public static final RegistryObject<Item> MAMALIGA = ITEMS.register("mamaliga",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(3.0f)
                    .build())));

    public static final RegistryObject<Item> FRIED_RICE = ITEMS.register("fried_rice",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(3.5f)
                    .build())));

    public static final RegistryObject<Item> CURRY = ITEMS.register("curry",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(4.0f)
                    .build())));

    public static final RegistryObject<Item> PHO = ITEMS.register("pho",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(9)
                    .saturationMod(3.8f)
                    .build())));

    public static final RegistryObject<Item> HUMMUS = ITEMS.register("hummus",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(2.8f)
                    .build())));

    public static final RegistryObject<Item> GREEK_SALAD = ITEMS.register("greek_salad",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationMod(2.5f)
                    .build())));

    public static final RegistryObject<Item> TZATZIKI = ITEMS.register("tzatziki",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationMod(2.0f)
                    .build())));

    public static final RegistryObject<Item> GOULASH = ITEMS.register("goulash",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(4.0f)
                    .build())));

    public static final RegistryObject<Item> RISOTTO = ITEMS.register("risotto",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(3.5f)
                    .build())));

    public static final RegistryObject<Item> RAW_LAVASH_WRAP = ITEMS.register("raw_lavash_wrap",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LAVASH_WRAP = ITEMS.register("lavash_wrap",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(3.2f)
                    .build())));

    public static final RegistryObject<Item> RAW_PELMENI = ITEMS.register("raw_pelmeni",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELMENI = ITEMS.register("pelmeni",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(3.5f)
                    .build())));

    public static final RegistryObject<Item> RAW_VARENIKI = ITEMS.register("raw_vareniki",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VARENIKI = ITEMS.register("vareniki",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(3.2f)
                    .build())));

    public static final RegistryObject<Item> RAW_OLADYI = ITEMS.register("raw_oladyi",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> OLADYI = ITEMS.register("oladyi",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(2.8f)
                    .build())));

    public static final RegistryObject<Item> RAW_SYRNIKI = ITEMS.register("raw_syrniki",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRNIKI = ITEMS.register("syrniki",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(3.0f)
                    .build())));

    public static final RegistryObject<Item> SAUERKRAUT = ITEMS.register("sauerkraut",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(1.5f)
                    .build())));

    public static final RegistryObject<Item> RAW_KHACHAPURI = ITEMS.register("raw_khachapuri",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KHACHAPURI = ITEMS.register("khachapuri",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(9)
                    .saturationMod(3.8f)
                    .build())));

    public static final RegistryObject<Item> RAW_CHEBUREKI = ITEMS.register("raw_chebureki",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHEBUREKI = ITEMS.register("chebureki",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(3.5f)
                    .build())));

    public static final RegistryObject<Item> FALAFEL = ITEMS.register("falafel",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(2.8f)
                    .build())));

    public static final RegistryObject<Item> DOLMA = ITEMS.register("dolma",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(3.2f)
                    .build())));

    public static final RegistryObject<Item> COMPOTE_BUCKET = ITEMS.register("compote_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.COMPOTE_SOURCE, new Item.Properties().stacksTo(1)));

    // Kvass Bucket
    public static final RegistryObject<Item> KVASS_BUCKET = ITEMS.register("kvass_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.KVASS_SOURCE, new Item.Properties().stacksTo(1)));

    // Lemonade Bucket
    public static final RegistryObject<Item> LEMONADE_BUCKET = ITEMS.register("lemonade_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.LEMONADE_SOURCE, new Item.Properties().stacksTo(1)));

    // Milk Items - Using Forge's bucket system
    public static final RegistryObject<Item> OX_MILK_BUCKET = ITEMS.register("ox_milk_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.OX_MILK_SOURCE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SHEEP_MILK_BUCKET = ITEMS.register("sheep_milk_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.SHEEP_MILK_SOURCE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> ALPACA_MILK_BUCKET = ITEMS.register("alpaca_milk_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.ALPACA_MILK_SOURCE, new Item.Properties().stacksTo(1)));

    // Curdled Milk Items
    public static final RegistryObject<Item> CURDLED_OX_MILK_BUCKET = ITEMS.register("curdled_ox_milk_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.CURDLED_OX_MILK_SOURCE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CURDLED_SHEEP_MILK_BUCKET = ITEMS.register("curdled_sheep_milk_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.CURDLED_SHEEP_MILK_SOURCE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CURDLED_ALPACA_MILK_BUCKET = ITEMS.register("curdled_alpaca_milk_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.CURDLED_ALPACA_MILK_SOURCE, new Item.Properties().stacksTo(1)));

    // Curd Items
    public static final RegistryObject<Item> OX_CURD = ITEMS.register("ox_curd",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(0.3f)
                    .build())));

    public static final RegistryObject<Item> SHEEP_CURD = ITEMS.register("sheep_curd",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(0.3f)
                    .build())));

    public static final RegistryObject<Item> ALPACA_CURD = ITEMS.register("alpaca_curd",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(0.3f)
                    .build())));

    // Brinza Items - REMOVED (keeping only wheels)
    
    // Brinza Slice Items (from cutting brinza wheels)
    public static final RegistryObject<Item> OX_BRINZA_SLICE = ITEMS.register("ox_brinza_slice",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationMod(0.5f)
                    .build())));

    public static final RegistryObject<Item> SHEEP_BRINZA_SLICE = ITEMS.register("sheep_brinza_slice",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationMod(0.5f)
                    .build())));

    public static final RegistryObject<Item> ALPACA_BRINZA_SLICE = ITEMS.register("alpaca_brinza_slice",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationMod(0.5f)
                    .build())));

    // Dried Leaves for Tea Making
    public static final RegistryObject<Item> DRIED_MINT_LEAVES = ITEMS.register("dried_mint_leaves",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationMod(0.5f)
                    .build())));

    public static final RegistryObject<Item> DRIED_CHAMOMILE_LEAVES = ITEMS.register("dried_chamomile_leaves",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationMod(0.5f)
                    .build())));

    public static final RegistryObject<Item> DRIED_ROSEHIP_LEAVES = ITEMS.register("dried_rosehip_leaves",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationMod(0.5f)
                    .build())));

    public static final RegistryObject<Item> DRIED_NETTLE_LEAVES = ITEMS.register("dried_nettle_leaves",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationMod(0.5f)
                    .build())));

    // New Beverage Buckets
    public static final RegistryObject<Item> COFFEE_BUCKET = ITEMS.register("coffee_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.COFFEE_SOURCE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TEA_MINT_BUCKET = ITEMS.register("tea_mint_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.TEA_MINT_SOURCE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TEA_CHAMOMILE_BUCKET = ITEMS.register("tea_chamomile_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.TEA_CHAMOMILE_SOURCE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TEA_ROSEHIP_BUCKET = ITEMS.register("tea_rosehip_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.TEA_ROSEHIP_SOURCE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TEA_NETTLE_BUCKET = ITEMS.register("tea_nettle_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.TEA_NETTLE_SOURCE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> COCOA_BUCKET = ITEMS.register("cocoa_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.COCOA_SOURCE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> NALIVKA_BUCKET = ITEMS.register("nalivka_bucket",
            () -> new net.minecraft.world.item.BucketItem(com.tfcgourmet.tfc_gourmet.fluids.ModFluids.NALIVKA_SOURCE, new Item.Properties().stacksTo(1)));

    // Slavic Cuisine 
    public static final RegistryObject<Item> BORSCHT = ITEMS.register("borscht",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(6.0f)
                    .build())));

    public static final RegistryObject<Item> SCHI = ITEMS.register("schi",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(5.5f)
                    .build())));

    public static final RegistryObject<Item> SOLYANKA = ITEMS.register("solyanka",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(4.5f)
                    .build())));
                                        
    public static final RegistryObject<Item> RAW_KIEV_CUTLETS = ITEMS.register("raw_kiev_cutlets",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KIEV_CUTLETS = ITEMS.register("kiev_cutlets",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(8.0f)
                    .build())));

    public static final RegistryObject<Item> OKROSHKA = ITEMS.register("okroshka",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(4.0f)
                    .build())));

    // Italian Cuisine
    public static final RegistryObject<Item> SPAGHETTI_BOLOGNESE = ITEMS.register("spaghetti_bolognese",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(6.0f)
                    .build())));

    public static final RegistryObject<Item> PASTA_CARBONARA = ITEMS.register("pasta_carbonara",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(9)
                    .saturationMod(7.0f)
                    .build())));

    public static final RegistryObject<Item> TIRAMISU = ITEMS.register("tiramisu",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(8.0f)
                    .build())));

    // French Cuisine
    public static final RegistryObject<Item> RAW_CROISSANTS = ITEMS.register("raw_croissants",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CROISSANTS = ITEMS.register("croissants",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(4.0f)
                    .build())));

    public static final RegistryObject<Item> RATATOUILLE = ITEMS.register("ratatouille",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(5.0f)
                    .build())));

    public static final RegistryObject<Item> RAW_QUICHE = ITEMS.register("raw_quiche",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> QUICHE = ITEMS.register("quiche",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(6.0f)
                    .build())));

    public static final RegistryObject<Item> BOUILLABAISSE = ITEMS.register("bouillabaisse",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(6.5f)
                    .build())));

    public static final RegistryObject<Item> RAW_CREPES = ITEMS.register("raw_crepes",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CREPES = ITEMS.register("crepes",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationMod(3.5f)
                    .build())));

    // German Cuisine
    public static final RegistryObject<Item> RAW_BRATWURST = ITEMS.register("raw_bratwurst",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BRATWURST = ITEMS.register("bratwurst",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(5.0f)
                    .build())));

    // Japanese Cuisine
    public static final RegistryObject<Item> RAW_TONKATSU = ITEMS.register("raw_tonkatsu",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TONKATSU = ITEMS.register("tonkatsu",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(6.0f)
                    .build())));

    public static final RegistryObject<Item> RAW_TAKOYAKI = ITEMS.register("raw_takoyaki",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TAKOYAKI = ITEMS.register("takoyaki",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(5.0f)
                    .build())));

    // Korean Cuisine
    public static final RegistryObject<Item> KIMCHI = ITEMS.register("kimchi",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationMod(2.5f)
                    .build())));

    public static final RegistryObject<Item> RAW_BULGOGI = ITEMS.register("raw_bulgogi",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BULGOGI = ITEMS.register("bulgogi",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(5.5f)
                    .build())));

    // Indian Cuisine
    public static final RegistryObject<Item> RAW_TANDOORI_CHICKEN = ITEMS.register("raw_tandoori_chicken",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TANDOORI_CHICKEN = ITEMS.register("tandoori_chicken",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(6.5f)
                    .build())));

    public static final RegistryObject<Item> BIRYANI = ITEMS.register("biryani",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(9)
                    .saturationMod(7.0f)
                    .build())));

    // New Soups
    public static final RegistryObject<Item> LENT_SOUP = ITEMS.register("lent_soup",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(5.5f)
                    .build())));

    public static final RegistryObject<Item> FRESH_ONION_SOUP = ITEMS.register("fresh_onion_soup",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(4.5f)
                    .build())));

    public static final RegistryObject<Item> FRESH_ONION_SOUP_BREAD = ITEMS.register("fresh_onion_soup_bread",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(4.5f)
                    .build())));

    public static final RegistryObject<Item> MINESTRONE = ITEMS.register("minestrone",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(6.0f)
                    .build())));

    public static final RegistryObject<Item> PEA_SOUP = ITEMS.register("pea_soup",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(5.5f)
                    .build())));

    public static final RegistryObject<Item> FISH_SOUP = ITEMS.register("fish_soup",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(6.5f)
                    .build())));

    public static final RegistryObject<Item> FISH_SOUP_TOMATO = ITEMS.register("fish_soup_tomato",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(6.5f)
                    .build())));

                    public static final RegistryObject<Item> GAZPACHO = ITEMS.register("gazpacho",
                    () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                            .nutrition(7)
                            .saturationMod(5.5f)
                            .build())));


                    public static final RegistryObject<Item> KHARCHO = ITEMS.register("kharcho",
                    () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                            .nutrition(7)
                            .saturationMod(5.5f)
                            .build())));

                    public static final RegistryObject<Item> KHOLODNIK = ITEMS.register("kholodnik",
                    () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                            .nutrition(7)
                            .saturationMod(5.5f)
                            .build())));

                    public static final RegistryObject<Item> SHURPA = ITEMS.register("shurpa",
                    () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                            .nutrition(7)
                            .saturationMod(5.5f)
                            .build())));

    public static final RegistryObject<Item> POTATO_STEW = ITEMS.register("potato_stew",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(5.0f)
                    .build())));

}
