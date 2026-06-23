package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.blocks.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.hakimen.kawaiidishes.KawaiiDishes.modId;

public class BlockRegister {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(modId);

    public static final DeferredBlock<MugBlock> mug = BLOCKS.register("mug", () -> new MugBlock());
    public static final DeferredBlock<GlassCupBlock> glassCup = BLOCKS.register("glass_cup", () -> new GlassCupBlock());
    public static final DeferredBlock<MilkshakeCupBlock> milkshakeCup = BLOCKS.register("milkshake_cup", MilkshakeCupBlock::new);

    public static final DeferredBlock<MugWithCoffeeBlock> expressoMug = BLOCKS.register("expresso_coffee", MugWithCoffeeBlock::new);
    public static final DeferredBlock<MugWithCoffeeBlock> americanMug = BLOCKS.register("american_coffee", MugWithCoffeeBlock::new);
    public static final DeferredBlock<MugWithCoffeeBlock> latteMug = BLOCKS.register("latte_coffee", MugWithCoffeeBlock::new);
    public static final DeferredBlock<MugWithCoffeeBlock> mochaMug = BLOCKS.register("mocha_coffee", MugWithCoffeeBlock::new);
    public static final DeferredBlock<MugWithCoffeeBlock> cappuccinoMug = BLOCKS.register("cappuccino_coffee", MugWithCoffeeBlock::new);
    public static final DeferredBlock<MugWithCoffeeBlock> doppioMug = BLOCKS.register("doppio_coffee", MugWithCoffeeBlock::new);
    public static final DeferredBlock<MugWithCoffeeBlock> macchiatoMug = BLOCKS.register("macchiato_coffee", MugWithCoffeeBlock::new);

    public static final DeferredBlock<IceCreamBlock> sweetBerryIceCream = BLOCKS.register("sweet_berry_ice_cream", IceCreamBlock::new);
    public static final DeferredBlock<IceCreamBlock> napolitanoIceCream = BLOCKS.register("napolitano_ice_cream", IceCreamBlock::new);
    public static final DeferredBlock<IceCreamBlock> creamIceCream = BLOCKS.register("cream_ice_cream", IceCreamBlock::new);
    public static final DeferredBlock<IceCreamBlock> chocolateIceCream = BLOCKS.register("chocolate_ice_cream", IceCreamBlock::new);
    public static final DeferredBlock<IceCreamBlock> coffeeIceCream = BLOCKS.register("coffee_ice_cream", IceCreamBlock::new);
    public static final DeferredBlock<IceCreamBlock> mochaIceCream = BLOCKS.register("mocha_ice_cream", IceCreamBlock::new);
    public static final DeferredBlock<IceCreamBlock> glowBerryIceCream = BLOCKS.register("glow_berry_ice_cream", IceCreamBlock::new);

    public static final DeferredBlock<MilkshakeBlock> sweetBerryMilkshake = BLOCKS.register("sweet_berry_milkshake", MilkshakeBlock::new);
    public static final DeferredBlock<MilkshakeBlock> chocolateMilkshake = BLOCKS.register("chocolate_milkshake", MilkshakeBlock::new);
    public static final DeferredBlock<MilkshakeBlock> creamMilkshake = BLOCKS.register("cream_milkshake", MilkshakeBlock::new);
    public static final DeferredBlock<MilkshakeBlock> napolitanoMilkshake = BLOCKS.register("napolitano_milkshake", MilkshakeBlock::new);
    public static final DeferredBlock<MilkshakeBlock> coffeeMilkshake = BLOCKS.register("coffee_milkshake", MilkshakeBlock::new);
    public static final DeferredBlock<MilkshakeBlock> mochaMilkshake = BLOCKS.register("mocha_milkshake", MilkshakeBlock::new);
    public static final DeferredBlock<MilkshakeBlock> glowBerryMilkshake = BLOCKS.register("glow_berry_milkshake", MilkshakeBlock::new);


    public static final DeferredBlock<SmallCandy> beijinho = BLOCKS.register("beijinho", SmallCandy::new);
    public static final DeferredBlock<SmallCandy> brigadeiro = BLOCKS.register("brigadeiro", SmallCandy::new);


    public static final DeferredBlock<CakeBlock> cheeseCake = BLOCKS.register("cheese_cake", CakeBlock::new);
    public static final DeferredBlock<CakeBlock> chocolateCheeseCake = BLOCKS.register("chocolate_cheese_cake", CakeBlock::new);
    public static final DeferredBlock<CakeBlock> honeyCheeseCake = BLOCKS.register("honey_cheese_cake", CakeBlock::new);


    public static final DeferredBlock<StoolBlock> blackStool = BLOCKS.register("black_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> blueStool = BLOCKS.register("blue_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> brownStool = BLOCKS.register("brown_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> cyanStool = BLOCKS.register("cyan_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> grayStool = BLOCKS.register("gray_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> greenStool = BLOCKS.register("green_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> lightBlueStool = BLOCKS.register("light_blue_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> lightGrayStool = BLOCKS.register("light_gray_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> limeStool = BLOCKS.register("lime_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> magentaStool = BLOCKS.register("magenta_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> orangeStool = BLOCKS.register("orange_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> pinkStool = BLOCKS.register("pink_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> purpleStool = BLOCKS.register("purple_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> redStool = BLOCKS.register("red_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> whiteStool = BLOCKS.register("white_stool", StoolBlock::new);
    public static final DeferredBlock<StoolBlock> yellowStool = BLOCKS.register("yellow_stool", StoolBlock::new);

    public static final DeferredBlock<CoffeeMachineBlock> coffeeMachine = BLOCKS.register("coffee_machine", CoffeeMachineBlock::new);

    public static final DeferredBlock<IceCreamMachineBlock> iceCreamMachine = BLOCKS.register("ice_cream_machine", IceCreamMachineBlock::new);

    public static final DeferredBlock<BlenderBlock> blender = BLOCKS.register("blender", BlenderBlock::new);


    public static final DeferredBlock<CoffeePlantBlock> coffeePlant = BLOCKS.register("coffee_bush", CoffeePlantBlock::new);

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
