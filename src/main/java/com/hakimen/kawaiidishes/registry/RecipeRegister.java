package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.recipes.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RecipeRegister {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, KawaiiDishes.modId);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, KawaiiDishes.modId);

    public static final Supplier<RecipeSerializer<CoffeePressRecipe>> CoffeePressRecipeSerializer =
            SERIALIZERS.register("coffee_pressing", () -> CoffeePressRecipe.Serializer.INSTANCE);

    public static final Supplier<RecipeSerializer<CoffeeMachineRecipe>> CoffeeMachineRecipeSerializer =
            SERIALIZERS.register("coffee_machining", () -> CoffeeMachineRecipe.Serializer.INSTANCE);

    public static final Supplier<RecipeSerializer<IceCreamMachineRecipe>> IceCreamMakingRecipe =
            SERIALIZERS.register("ice_cream_making", () -> IceCreamMachineRecipe.Serializer.INSTANCE);

    public static final Supplier<RecipeSerializer<BlenderRecipe>> BlendingRecipe =
            SERIALIZERS.register("blending", () -> BlenderRecipe.Serializer.INSTANCE);

    public static final Supplier<RecipeType<CoffeePressRecipe>> CoffeePressingType =
            TYPES.register("coffee_pressing", () -> CoffeePressRecipe.Type.INSTANCE);

    public static final Supplier<RecipeType<CoffeeMachineRecipe>> CoffeeMachiningType =
            TYPES.register("coffee_machining", () -> CoffeeMachineRecipe.Type.INSTANCE);

    public static final Supplier<RecipeType<IceCreamMachineRecipe>> IceCreamMakingType =
            TYPES.register("ice_cream_making", () -> IceCreamMachineRecipe.Type.INSTANCE);

    public static final Supplier<RecipeType<BlenderRecipe>> BlendingType =
            TYPES.register("blending", () -> BlenderRecipe.Type.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
