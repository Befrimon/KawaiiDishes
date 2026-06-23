package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.items.Hat;
import com.hakimen.kawaiidishes.items.PlaceableFoodItem;
import com.hakimen.kawaiidishes.items.UnbindingCookie;
import com.hakimen.kawaiidishes.items.armor.ArmorMaterials;
import com.hakimen.kawaiidishes.items.armor.GenericGeoArmorItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

import static com.hakimen.kawaiidishes.KawaiiDishes.modId;
import static com.hakimen.kawaiidishes.registry.ArmorTickRegister.*;

public class ItemRegister {

    public static FoodProperties cake = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).build();
    public static FoodProperties chocolate = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).build();
    public static FoodProperties iceCream = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).build();
    public static FoodProperties milkShake = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).build();
    public static FoodProperties candy = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).build();

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(modId);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, modId);

    public static final DeferredItem<Item> mug = ITEMS.register("mug", () -> new BlockItem(BlockRegister.mug.get(), new Item.Properties()));
    public static final DeferredItem<Item> glassCup = ITEMS.register("glass_cup", () -> new BlockItem(BlockRegister.glassCup.get(), new Item.Properties()));
    public static final DeferredItem<Item> milkshakeCup = ITEMS.register("milkshake_cup", () -> new BlockItem(BlockRegister.milkshakeCup.get(), new Item.Properties()));

    public static final DeferredItem<Item> coffeeMachine = ITEMS.register("coffee_machine", () -> new BlockItem(BlockRegister.coffeeMachine.get(), new Item.Properties()));
    public static final DeferredItem<Item> blender = ITEMS.register("blender", () -> new BlockItem(BlockRegister.blender.get(), new Item.Properties()));
    public static final DeferredItem<Item> iceCreamMachine = ITEMS.register("ice_cream_machine", () -> new BlockItem(BlockRegister.iceCreamMachine.get(), new Item.Properties()));

    public static final DeferredItem<Hat> blackCatEars = ITEMS.register("black_cat_ears", () -> new Hat());
    public static final DeferredItem<Hat> caramelCatEars = ITEMS.register("caramel_cat_ears", () -> new Hat());
    public static final DeferredItem<Hat> whiteCatEars = ITEMS.register("white_cat_ears", () -> new Hat());

    public static final DeferredItem<Hat> blackBunnyEars = ITEMS.register("black_bunny_ears", () -> new Hat());
    public static final DeferredItem<Hat> caramelBunnyEars = ITEMS.register("caramel_bunny_ears", () -> new Hat());
    public static final DeferredItem<Hat> whiteBunnyEars = ITEMS.register("white_bunny_ears", () -> new Hat());

    public static final DeferredItem<Hat> blackFoxEars = ITEMS.register("black_fox_ears", () -> new Hat());
    public static final DeferredItem<Hat> redFoxEars = ITEMS.register("red_fox_ears", () -> new Hat());
    public static final DeferredItem<Hat> whiteFoxEars = ITEMS.register("white_fox_ears", () -> new Hat());

    public static final DeferredItem<Hat> brownFoxEars = ITEMS.register("brown_fox_ears", () -> new Hat());

    public static final DeferredItem<Hat> lightGrayHorns = ITEMS.register("light_gray_horns", () -> new Hat().setMakesPiglinsNeutral(true));
    public static final DeferredItem<Hat> grayHorns = ITEMS.register("gray_horns", () -> new Hat().setMakesPiglinsNeutral(true));
    public static final DeferredItem<Hat> whiteHorns = ITEMS.register("white_horns", () -> new Hat().setMakesPiglinsNeutral(true));

    public static final DeferredItem<Hat> redHorns = ITEMS.register("red_horns", () -> new Hat().setMakesPiglinsNeutral(true));
    public static final DeferredItem<Hat> purpleHorns = ITEMS.register("purple_horns", () -> new Hat().setMakesPiglinsNeutral(true));
    public static final DeferredItem<Hat> blackHorns = ITEMS.register("black_horns", () -> new Hat().setMakesPiglinsNeutral(true));

    public static final DeferredItem<GenericGeoArmorItem> blackCatTail = ITEMS.register("black_cat_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "black_cat_tail.png", "cat_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.catTail, catArmorTick));
    public static final DeferredItem<GenericGeoArmorItem> caramelCatTail = ITEMS.register("caramel_cat_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "caramel_cat_tail.png", "cat_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.catTail, catArmorTick));
    public static final DeferredItem<GenericGeoArmorItem> whiteCatTail = ITEMS.register("white_cat_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "white_cat_tail.png", "cat_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.catTail, catArmorTick));

    public static final DeferredItem<GenericGeoArmorItem> blackBunnyTail = ITEMS.register("black_bunny_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "black_bunny_tail.png", "bunny_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.NULL, bunnyArmorTick));
    public static final DeferredItem<GenericGeoArmorItem> caramelBunnyTail = ITEMS.register("caramel_bunny_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "caramel_bunny_tail.png", "bunny_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.NULL, bunnyArmorTick));
    public static final DeferredItem<GenericGeoArmorItem> whiteBunnyTail = ITEMS.register("white_bunny_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "white_bunny_tail.png", "bunny_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.NULL, bunnyArmorTick));

    public static final DeferredItem<GenericGeoArmorItem> blackFoxTail = ITEMS.register("black_fox_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "black_fox_tail.png", "fox_tail.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));
    public static final DeferredItem<GenericGeoArmorItem> redFoxTail = ITEMS.register("red_fox_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "red_fox_tail.png", "fox_tail.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));
    public static final DeferredItem<GenericGeoArmorItem> whiteFoxTail = ITEMS.register("white_fox_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "white_fox_tail.png", "fox_tail.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));

    public static final DeferredItem<GenericGeoArmorItem> brownFoxTail = ITEMS.register("brown_fox_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "brown_fox_tail.png", "fox_tail.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));

    public static final DeferredItem<GenericGeoArmorItem> blackDevilTail = ITEMS.register("black_devil_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "black_devil_tail.png", "devil_tail.geo.json", "devil_tail_animations.json", AnimationsRegister.devilTail, NULL).setMakesPiglinsNeutral(true));
    public static final DeferredItem<GenericGeoArmorItem> redDevilTail = ITEMS.register("red_devil_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "red_devil_tail.png", "devil_tail.geo.json", "devil_tail_animations.json", AnimationsRegister.devilTail, NULL).setMakesPiglinsNeutral(true));
    public static final DeferredItem<GenericGeoArmorItem> purpleDevilTail = ITEMS.register("purple_devil_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "purple_devil_tail.png", "devil_tail.geo.json", "devil_tail_animations.json", AnimationsRegister.devilTail, NULL).setMakesPiglinsNeutral(true));

    public static final DeferredItem<GenericGeoArmorItem> whiteThighHighsShoes = ITEMS.register("brown_shoes",
            () -> new GenericGeoArmorItem(ArmorMaterials.maidDress, ArmorItem.Type.BOOTS, new Item.Properties(), "thigh_highs/white_thigh_highs.png", "thigh_highs.geo.json", "cat_tail_animation.json", AnimationsRegister.NULL, NULL).setCanStandOnPowderSnow(true));
    public static final DeferredItem<GenericGeoArmorItem> blackThighHighsShoes = ITEMS.register("dark_brown_shoes",
            () -> new GenericGeoArmorItem(ArmorMaterials.maidDress, ArmorItem.Type.BOOTS, new Item.Properties(), "thigh_highs/black_thigh_highs.png", "thigh_highs.geo.json", "cat_tail_animation.json", AnimationsRegister.NULL, NULL).setCanStandOnPowderSnow(true));

    public static final DeferredItem<Item> coffeeFruit = ITEMS.register("coffee_fruit", () -> new BlockItem(
            BlockRegister.coffeePlant.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(1.125f).build()
    )));
    public static final DeferredItem<Item> driedCoffeeBeans = ITEMS.register("dried_coffee_beans", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> roastedCoffeeBeans = ITEMS.register("roasted_coffee_beans", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> coffeePowder = ITEMS.register("coffee_powder", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> driedCocoaBeans = ITEMS.register("dried_cocoa_beans", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> roastedCocoaBeans = ITEMS.register("roasted_cocoa_beans", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> cocoaPowder = ITEMS.register("cocoa_powder", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> whiteChocolateBar = ITEMS.register("white_chocolate_bar", () -> new Item(new Item.Properties().food(chocolate)));
    public static final DeferredItem<Item> darkChocolateBar = ITEMS.register("dark_chocolate_bar", () -> new Item(new Item.Properties().food(chocolate)));
    public static final DeferredItem<Item> milkChocolateBar = ITEMS.register("milk_chocolate_bar", () -> new Item(new Item.Properties().food(chocolate)));

    public static final DeferredItem<PlaceableFoodItem> expressoCoffee = ITEMS.register("expresso_coffee", () -> new PlaceableFoodItem(BlockRegister.expressoMug.get(), 3, 1.2f, ItemRegister.mug.get()));
    public static final DeferredItem<PlaceableFoodItem> americanCoffee = ITEMS.register("american_coffee", () -> new PlaceableFoodItem(BlockRegister.americanMug.get(), 3, 1.2f, ItemRegister.mug.get()));
    public static final DeferredItem<PlaceableFoodItem> latteCoffee = ITEMS.register("latte_coffee", () -> new PlaceableFoodItem(BlockRegister.latteMug.get(), 3, 1.2f, ItemRegister.mug.get()));
    public static final DeferredItem<PlaceableFoodItem> mochaCoffee = ITEMS.register("mocha_coffee", () -> new PlaceableFoodItem(BlockRegister.mochaMug.get(), 3, 1.2f, ItemRegister.mug.get()));
    public static final DeferredItem<PlaceableFoodItem> macchiatoCoffee = ITEMS.register("macchiato_coffee", () -> new PlaceableFoodItem(BlockRegister.macchiatoMug.get(), 3, 1.2f, ItemRegister.mug.get()));
    public static final DeferredItem<PlaceableFoodItem> doppioCoffee = ITEMS.register("doppio_coffee", () -> new PlaceableFoodItem(BlockRegister.doppioMug.get(), 3, 1.2f, ItemRegister.mug.get()));
    public static final DeferredItem<PlaceableFoodItem> cappuccinoCoffee = ITEMS.register("cappuccino_coffee", () -> new PlaceableFoodItem(BlockRegister.cappuccinoMug.get(), 3, 1.2f, ItemRegister.mug.get()));

    public static final DeferredItem<PlaceableFoodItem> sweetBerryIceCream = ITEMS.register("sweet_berry_ice_cream", () -> new PlaceableFoodItem(BlockRegister.sweetBerryIceCream.get(), 3, 1.2f, ItemRegister.glassCup.get()));
    public static final DeferredItem<PlaceableFoodItem> napolitanoIceCream = ITEMS.register("napolitano_ice_cream", () -> new PlaceableFoodItem(BlockRegister.napolitanoIceCream.get(), 3, 1.2f, ItemRegister.glassCup.get()));
    public static final DeferredItem<PlaceableFoodItem> creamIceCream = ITEMS.register("cream_ice_cream", () -> new PlaceableFoodItem(BlockRegister.creamIceCream.get(), 3, 1.2f, ItemRegister.glassCup.get()));
    public static final DeferredItem<PlaceableFoodItem> chocolateIceCream = ITEMS.register("chocolate_ice_cream", () -> new PlaceableFoodItem(BlockRegister.chocolateIceCream.get(), 3, 1.2f, ItemRegister.glassCup.get()));
    public static final DeferredItem<PlaceableFoodItem> coffeeIceCream = ITEMS.register("coffee_ice_cream", () -> new PlaceableFoodItem(BlockRegister.coffeeIceCream.get(), 3, 1.2f, ItemRegister.glassCup.get()));
    public static final DeferredItem<PlaceableFoodItem> mochaIceCream = ITEMS.register("mocha_ice_cream", () -> new PlaceableFoodItem(BlockRegister.mochaIceCream.get(), 3, 1.2f, ItemRegister.glassCup.get()));
    public static final DeferredItem<PlaceableFoodItem> glowBerryIceCream = ITEMS.register("glow_berry_ice_cream", () -> new PlaceableFoodItem(BlockRegister.glowBerryIceCream.get(), 3, 1.2f, ItemRegister.glassCup.get()));

    public static final DeferredItem<PlaceableFoodItem> sweetBerryMilkshake = ITEMS.register("sweet_berry_milkshake", () -> new PlaceableFoodItem(BlockRegister.sweetBerryMilkshake.get(), 3, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final DeferredItem<PlaceableFoodItem> chocolateMilkshake = ITEMS.register("chocolate_milkshake", () -> new PlaceableFoodItem(BlockRegister.chocolateMilkshake.get(), 3, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final DeferredItem<PlaceableFoodItem> creamMilkshake = ITEMS.register("cream_milkshake", () -> new PlaceableFoodItem(BlockRegister.creamMilkshake.get(), 3, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final DeferredItem<PlaceableFoodItem> napolitanoMilkshake = ITEMS.register("napolitano_milkshake", () -> new PlaceableFoodItem(BlockRegister.napolitanoMilkshake.get(), 3, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final DeferredItem<PlaceableFoodItem> coffeeMilkshake = ITEMS.register("coffee_milkshake", () -> new PlaceableFoodItem(BlockRegister.coffeeMilkshake.get(), 3, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final DeferredItem<PlaceableFoodItem> mochaMilkshake = ITEMS.register("mocha_milkshake", () -> new PlaceableFoodItem(BlockRegister.mochaMilkshake.get(), 3, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final DeferredItem<PlaceableFoodItem> glowBerryMilkshake = ITEMS.register("glow_berry_milkshake", () -> new PlaceableFoodItem(BlockRegister.glowBerryMilkshake.get(), 3, 1.2f, ItemRegister.milkshakeCup.get()));

    public static final DeferredItem<Item> condensedMilk = ITEMS.register("condensed_milk", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> brigadeiroMix = ITEMS.register("brigadeiro_mix", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> creamCheese = ITEMS.register("cream_cheese", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> cakePiece = ITEMS.register("piece_of_cake", () -> new Item(new Item.Properties().food(cake)));
    public static final DeferredItem<Item> cheeseCakePiece = ITEMS.register("piece_of_cheesecake", () -> new Item(new Item.Properties().food(cake)));
    public static final DeferredItem<Item> chocolateCheeseCakePiece = ITEMS.register("piece_of_chocolate_cheesecake", () -> new Item(new Item.Properties().food(cake)));
    public static final DeferredItem<Item> honeyCheeseCakePiece = ITEMS.register("piece_of_honey_cheesecake", () -> new Item(new Item.Properties().food(cake)));

    public static final DeferredItem<Item> cheeseCake = ITEMS.register("cheese_cake", () -> new BlockItem(BlockRegister.cheeseCake.get(), new Item.Properties()));
    public static final DeferredItem<Item> chocolateCheeseCake = ITEMS.register("chocolate_cheese_cake", () -> new BlockItem(BlockRegister.chocolateCheeseCake.get(), new Item.Properties()));
    public static final DeferredItem<Item> honeyCheeseCake = ITEMS.register("honey_cheese_cake", () -> new BlockItem(BlockRegister.honeyCheeseCake.get(), new Item.Properties()));

    public static final DeferredItem<PlaceableFoodItem> beijinho = ITEMS.register("beijinho", () -> new PlaceableFoodItem(BlockRegister.beijinho.get(), 3, 1.2f, Items.AIR));
    public static final DeferredItem<PlaceableFoodItem> brigadeiro = ITEMS.register("brigadeiro", () -> new PlaceableFoodItem(BlockRegister.brigadeiro.get(), 3, 1.2f, Items.AIR));

    public static final DeferredItem<Item> sweetBerryCookie = ITEMS.register("sweet_berry_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1).nutrition(3).build())));
    public static final DeferredItem<Item> honeyCookie = ITEMS.register("honey_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1).nutrition(3).build())));
    public static final DeferredItem<Item> chocolateCookie = ITEMS.register("chocolate_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1).nutrition(3).build())));
    public static final DeferredItem<Item> goldenCookie = ITEMS.register("golden_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1).nutrition(6).alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 20 * 120, 0), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 5, 1), 1f).build())));

    public static final DeferredItem<Item> unbindingCookie = ITEMS.register("cookie_of_unbinding", () -> new UnbindingCookie(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1).nutrition(3).alwaysEdible().build())));

    public static final DeferredItem<Item> blackStool = ITEMS.register("black_stool", () -> new BlockItem(BlockRegister.blackStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> blueStool = ITEMS.register("blue_stool", () -> new BlockItem(BlockRegister.blueStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> brownStool = ITEMS.register("brown_stool", () -> new BlockItem(BlockRegister.brownStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> cyanStool = ITEMS.register("cyan_stool", () -> new BlockItem(BlockRegister.cyanStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> grayStool = ITEMS.register("gray_stool", () -> new BlockItem(BlockRegister.grayStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> greenStool = ITEMS.register("green_stool", () -> new BlockItem(BlockRegister.greenStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> lightBlueStool = ITEMS.register("light_blue_stool", () -> new BlockItem(BlockRegister.lightBlueStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> lightGrayStool = ITEMS.register("light_gray_stool", () -> new BlockItem(BlockRegister.lightGrayStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> limeStool = ITEMS.register("lime_stool", () -> new BlockItem(BlockRegister.limeStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> magentaStool = ITEMS.register("magenta_stool", () -> new BlockItem(BlockRegister.magentaStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> orangeStool = ITEMS.register("orange_stool", () -> new BlockItem(BlockRegister.orangeStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> pinkStool = ITEMS.register("pink_stool", () -> new BlockItem(BlockRegister.pinkStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> purpleStool = ITEMS.register("purple_stool", () -> new BlockItem(BlockRegister.purpleStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> redStool = ITEMS.register("red_stool", () -> new BlockItem(BlockRegister.redStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> whiteStool = ITEMS.register("white_stool", () -> new BlockItem(BlockRegister.whiteStool.get(), new Item.Properties()));
    public static final DeferredItem<Item> yellowStool = ITEMS.register("yellow_stool", () -> new BlockItem(BlockRegister.yellowStool.get(), new Item.Properties()));

    public static final DeferredItem<GenericGeoArmorItem> bunnySuitWhiteTail = ITEMS.register("bunny_suit_white_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    "bunny_suit/bunny_suit_white_tail.png", "bunny_suit.geo.json", "maid_dress_animation.json", AnimationsRegister.NULL, bunnyArmorTick));
    public static final DeferredItem<GenericGeoArmorItem> bunnySuitBlackTail = ITEMS.register("bunny_suit_black_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    "bunny_suit/bunny_suit_black_tail.png", "bunny_suit.geo.json", "maid_dress_animation.json", AnimationsRegister.NULL, bunnyArmorTick));
    public static final DeferredItem<GenericGeoArmorItem> bunnySuitCaramelTail = ITEMS.register("bunny_suit_caramel_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    "bunny_suit/bunny_suit_caramel_tail.png", "bunny_suit.geo.json", "maid_dress_animation.json", AnimationsRegister.NULL, bunnyArmorTick));
    public static final DeferredItem<GenericGeoArmorItem> bunnySuitSocks = ITEMS.register("bunny_suit_socks",
            () -> new GenericGeoArmorItem(ArmorMaterials.maidDress, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    "bunny_suit/bunny_suit_white_tail.png", "bunny_suit.geo.json", "maid_dress_animation.json", AnimationsRegister.NULL, bunnyArmorTick));

    public static HashMap<String, DeferredItem<GenericGeoArmorItem>> dresses = new HashMap<>();

    public static DeferredItem<GenericGeoArmorItem> registerDresses(String color) {
        final var toReturn = ITEMS.register(color + "_maid_dress", () -> new GenericGeoArmorItem(
                ArmorMaterials.maidDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                "cat_tailed/" + color + "_maid_dress_cat_tail_black.png",
                "maid_dress.geo.json", "maid_dress_animation.json", AnimationsRegister.NULL, maidArmorTick));

        ITEMS.register(color + "_maid_dress_cat_tail_black",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "cat_tailed/" + color + "_maid_dress_cat_tail_black.png",
                        "cat_tailed_maid_dress.geo.json", "cat_tail_animation.json", AnimationsRegister.catTail, catArmorTick));
        ITEMS.register(color + "_maid_dress_cat_tail_caramel",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "cat_tailed/" + color + "_maid_dress_cat_tail_caramel.png",
                        "cat_tailed_maid_dress.geo.json", "cat_tail_animation.json", AnimationsRegister.catTail, catArmorTick));
        ITEMS.register(color + "_maid_dress_cat_tail_white",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "cat_tailed/" + color + "_maid_dress_cat_tail_white.png",
                        "cat_tailed_maid_dress.geo.json", "cat_tail_animation.json", AnimationsRegister.catTail, catArmorTick));
        ITEMS.register(color + "_maid_dress_fox_tail_black",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "fox_tailed/" + color + "_maid_dress_fox_tail_black.png",
                        "fox_maid_dress.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));
        ITEMS.register(color + "_maid_dress_fox_tail_red",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "fox_tailed/" + color + "_maid_dress_fox_tail_red.png",
                        "fox_maid_dress.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));
        ITEMS.register(color + "_maid_dress_fox_tail_white",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "fox_tailed/" + color + "_maid_dress_fox_tail_white.png",
                        "fox_maid_dress.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));
        ITEMS.register(color + "_maid_dress_fox_tail_brown",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "fox_tailed/" + color + "_maid_dress_fox_tail_brown.png",
                        "fox_maid_dress.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));
        ITEMS.register(color + "_maid_dress_bunny_tail_black",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "bunny_tailed/" + color + "_maid_dress_bunny_tail_black.png",
                        "bunny_tailed_maid_dress.geo.json", "", AnimationsRegister.NULL, bunnyArmorTick));
        ITEMS.register(color + "_maid_dress_bunny_tail_caramel",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "bunny_tailed/" + color + "_maid_dress_bunny_tail_caramel.png",
                        "bunny_tailed_maid_dress.geo.json", "", AnimationsRegister.NULL, bunnyArmorTick));
        ITEMS.register(color + "_maid_dress_bunny_tail_white",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "bunny_tailed/" + color + "_maid_dress_bunny_tail_white.png",
                        "bunny_tailed_maid_dress.geo.json", "", AnimationsRegister.NULL, bunnyArmorTick));
        ITEMS.register(color + "_maid_dress_devil_tail_black",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "devil_tailed/" + color + "_maid_dress_devil_tail_black.png",
                        "devil_tailed_maid_dress.geo.json", "devil_tail_animations.json", AnimationsRegister.devilTail, NULL).setMakesPiglinsNeutral(true));
        ITEMS.register(color + "_maid_dress_devil_tail_purple",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "devil_tailed/" + color + "_maid_dress_devil_tail_purple.png",
                        "devil_tailed_maid_dress.geo.json", "devil_tail_animations.json", AnimationsRegister.devilTail, NULL).setMakesPiglinsNeutral(true));
        ITEMS.register(color + "_maid_dress_devil_tail_red",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                        "devil_tailed/" + color + "_maid_dress_devil_tail_red.png",
                        "devil_tailed_maid_dress.geo.json", "devil_tail_animations.json", AnimationsRegister.devilTail, NULL).setMakesPiglinsNeutral(true));

        return toReturn;
    }

    public static HashMap<String, DeferredItem<Hat>> headbands = new HashMap<>();

    public static DeferredItem<Hat> registerHeadbands(String color) {
        var toReturn = ITEMS.register(color + "_headband", () -> new Hat(Items.AIR));
        ITEMS.register(color + "_headband_cat_ears_black", () -> new Hat(toReturn.get()));
        ITEMS.register(color + "_headband_cat_ears_white", () -> new Hat(toReturn.get()));
        ITEMS.register(color + "_headband_cat_ears_caramel", () -> new Hat(toReturn.get()));
        ITEMS.register(color + "_headband_fox_ears_black", () -> new Hat(toReturn.get()));
        ITEMS.register(color + "_headband_fox_ears_white", () -> new Hat(toReturn.get()));
        ITEMS.register(color + "_headband_fox_ears_red", () -> new Hat(toReturn.get()));
        ITEMS.register(color + "_headband_fox_ears_brown", () -> new Hat(toReturn.get()));
        ITEMS.register(color + "_headband_bunny_ears_black", () -> new Hat(toReturn.get()));
        ITEMS.register(color + "_headband_bunny_ears_white", () -> new Hat(toReturn.get()));
        ITEMS.register(color + "_headband_bunny_ears_caramel", () -> new Hat(toReturn.get()));
        ITEMS.register(color + "_headband_horns_light_gray", () -> new Hat(toReturn.get()).setMakesPiglinsNeutral(true));
        ITEMS.register(color + "_headband_horns_gray", () -> new Hat(toReturn.get()).setMakesPiglinsNeutral(true));
        ITEMS.register(color + "_headband_horns_white", () -> new Hat(toReturn.get()).setMakesPiglinsNeutral(true));
        ITEMS.register(color + "_headband_horns_red", () -> new Hat(toReturn.get()).setMakesPiglinsNeutral(true));
        ITEMS.register(color + "_headband_horns_purple", () -> new Hat(toReturn.get()).setMakesPiglinsNeutral(true));
        ITEMS.register(color + "_headband_horns_black", () -> new Hat(toReturn.get()).setMakesPiglinsNeutral(true));
        return toReturn;
    }

    public static HashMap<String, DeferredItem<GenericGeoArmorItem>> thighHighs = new HashMap<>();

    public static DeferredItem<GenericGeoArmorItem> registerThighHighs(String color) {
        return ITEMS.register(color + "_thigh_highs", () -> new GenericGeoArmorItem(
                ArmorMaterials.maidDress, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                "thigh_highs/" + color + "_thigh_highs.png",
                "thigh_highs.geo.json", "", AnimationsRegister.NULL, NULL)
        );
    }

    public static void preGen() {
        ArrayList<String> nameMap = new ArrayList<>();
        nameMap.add("blue");
        nameMap.add("brown");
        nameMap.add("cyan");
        nameMap.add("gray");
        nameMap.add("green");
        nameMap.add("light_blue");
        nameMap.add("light_gray");
        nameMap.add("lime");
        nameMap.add("magenta");
        nameMap.add("orange");
        nameMap.add("pink");
        nameMap.add("red");
        nameMap.add("white");
        nameMap.add("black");
        nameMap.add("yellow");
        nameMap.add("purple");

        for (String color : nameMap) {
            thighHighs.put(color, registerThighHighs(color));
        }
        for (String color : nameMap) {
            dresses.put(color, registerDresses(color));
        }
        for (String color : nameMap) {
            headbands.put(color, registerHeadbands(color));
        }
    }

    public static final Supplier<CreativeModeTab> COSMETICS_TAB = TABS.register("cosmetics", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.kawaiidishes.cosmetics"))
            .icon(() -> new ItemStack(dresses.get("black").get()))
            .displayItems((enabledFeatures, entries) -> {
                ITEMS.getEntries().forEach(x -> {
                    var key = x.get().toString();
                    if (key.contains("maid") || key.contains("ears") || key.contains("horns") ||
                            key.contains("tail") || key.contains("thigh") || key.contains("shoes") ||
                            key.contains("bunny_suit") || key.contains("socks")) {
                        entries.accept(x.get());
                    }
                });
            })
            .build());

    public static final Supplier<CreativeModeTab> BLOCKS_TAB = TABS.register("blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.kawaiidishes.blocks"))
            .icon(() -> new ItemStack(coffeeMachine.get()))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(ItemRegister.mug.get());
                entries.accept(ItemRegister.glassCup.get());
                entries.accept(ItemRegister.milkshakeCup.get());
                entries.accept(ItemRegister.coffeeMachine.get());
                entries.accept(ItemRegister.blender.get());
                entries.accept(ItemRegister.iceCreamMachine.get());
            })
            .build());

    public static final Supplier<CreativeModeTab> DECORATION_TAB = TABS.register("decoration", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.kawaiidishes.decoration"))
            .icon(() -> new ItemStack(whiteStool.get()))
            .displayItems((enabledFeatures, entries) -> {
                ITEMS.getEntries().forEach(x -> {
                    String item = x.get().toString();
                    if (item.contains("stool")) {
                        entries.accept(x.get());
                    }
                });
            })
            .build());

    public static final Supplier<CreativeModeTab> FOOD_TAB = TABS.register("foods", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.kawaiidishes.foods"))
            .icon(() -> new ItemStack(roastedCoffeeBeans.get()))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(ItemRegister.brigadeiroMix.get());
                entries.accept(ItemRegister.condensedMilk.get());
                entries.accept(ItemRegister.creamCheese.get());
                entries.accept(ItemRegister.coffeePowder.get());
                entries.accept(ItemRegister.driedCoffeeBeans.get());
                entries.accept(ItemRegister.roastedCoffeeBeans.get());
                entries.accept(ItemRegister.cocoaPowder.get());
                entries.accept(ItemRegister.driedCocoaBeans.get());
                entries.accept(ItemRegister.roastedCocoaBeans.get());
                ITEMS.getEntries().forEach(x -> {
                    String item = x.get().toString();
                    if (new ItemStack(x.get()).has(DataComponents.FOOD)) {
                        entries.accept(x.get());
                    } else if (item.contains("cake")) {
                        entries.accept(x.get());
                    }
                });
            })
            .build());

    public static void register(IEventBus bus) {
        preGen();
        TABS.register(bus);
        ITEMS.register(bus);
    }
}
