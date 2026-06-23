package com.hakimen.kawaiidishes.integration.jei;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.integration.jei.categories.*;
import com.hakimen.kawaiidishes.recipes.*;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.mojang.serialization.JsonOps;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIIntegration implements IModPlugin {

    public static RecipeType<CoffeeMachineRecipe> coffeeMachining = RecipeType.create(KawaiiDishes.modId, "coffee_machining", CoffeeMachineRecipe.class);
    public static RecipeType<IceCreamMachineRecipe> iceCreamMaking = RecipeType.create(KawaiiDishes.modId, "ice_cream_making", IceCreamMachineRecipe.class);
    public static RecipeType<BlenderRecipe> blending = RecipeType.create(KawaiiDishes.modId, "blending", BlenderRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                CoffeeMachineRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                IceCreamMachineRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                BlendingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ItemRegister.coffeeMachine.get().getDefaultInstance(), coffeeMachining);
        registration.addRecipeCatalyst(ItemRegister.iceCreamMachine.get().getDefaultInstance(), iceCreamMaking);
        registration.addRecipeCatalyst(ItemRegister.blender.get().getDefaultInstance(), blending);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void registerRecipes(IRecipeRegistration registration) {
        if (Minecraft.getInstance().level == null) return;

        RecipeManager rm = Minecraft.getInstance().level.getServer() != null
                ? Minecraft.getInstance().level.getServer().getRecipeManager()
                : Minecraft.getInstance().level.getRecipeManager();

        List<CoffeeMachineRecipe> coffeeMachineRecipes = new ArrayList<>(rm.getAllRecipesFor(CoffeeMachineRecipe.Type.INSTANCE).stream().map(holder -> holder.value()).toList());
        List<IceCreamMachineRecipe> iceCreamMachineRecipes = new ArrayList<>(rm.getAllRecipesFor(IceCreamMachineRecipe.Type.INSTANCE).stream().map(holder -> holder.value()).toList());
        List<BlenderRecipe> blenderRecipes = new ArrayList<>(rm.getAllRecipesFor(BlenderRecipe.Type.INSTANCE).stream().map(holder -> holder.value()).toList());

        if (coffeeMachineRecipes.isEmpty()
                && iceCreamMachineRecipes.isEmpty() && blenderRecipes.isEmpty()) {
            List<Recipe<?>> vanillaRecipes = new ArrayList<>();
            loadRecipesFromFileSystem(coffeeMachineRecipes, iceCreamMachineRecipes, blenderRecipes, vanillaRecipes);

            registerVanillaRecipes(registration, vanillaRecipes);
        }

        registration.addRecipes(coffeeMachining, coffeeMachineRecipes);
        registration.addRecipes(iceCreamMaking, iceCreamMachineRecipes);
        registration.addRecipes(blending, blenderRecipes);
    }

    @SuppressWarnings("unchecked")
    private void registerVanillaRecipes(IRecipeRegistration registration, List<Recipe<?>> vanillaRecipes) {
        List<RecipeHolder<CraftingRecipe>> crafting = new ArrayList<>();
        List<RecipeHolder<SmeltingRecipe>> smelting = new ArrayList<>();
        List<RecipeHolder<SmokingRecipe>> smoking = new ArrayList<>();
        List<RecipeHolder<BlastingRecipe>> blasting = new ArrayList<>();
        List<RecipeHolder<CampfireCookingRecipe>> campfire = new ArrayList<>();
        List<RecipeHolder<StonecutterRecipe>> stonecutting = new ArrayList<>();

        for (Recipe<?> recipe : vanillaRecipes) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId,
                    recipe.getResultItem(Minecraft.getInstance().level.registryAccess()).getItem().builtInRegistryHolder().key().location().getPath());

            if (recipe instanceof CraftingRecipe r) {
                crafting.add(new RecipeHolder<>(id, r));
            } else if (recipe instanceof SmeltingRecipe r) {
                smelting.add(new RecipeHolder<>(id, r));
            } else if (recipe instanceof SmokingRecipe r) {
                smoking.add(new RecipeHolder<>(id, r));
            } else if (recipe instanceof BlastingRecipe r) {
                blasting.add(new RecipeHolder<>(id, r));
            } else if (recipe instanceof CampfireCookingRecipe r) {
                campfire.add(new RecipeHolder<>(id, r));
            } else if (recipe instanceof StonecutterRecipe r) {
                stonecutting.add(new RecipeHolder<>(id, r));
            }
        }

        if (!crafting.isEmpty()) registration.addRecipes(RecipeTypes.CRAFTING, crafting);
        if (!smelting.isEmpty()) registration.addRecipes(RecipeTypes.SMELTING, smelting);
        if (!smoking.isEmpty()) registration.addRecipes(RecipeTypes.SMOKING, smoking);
        if (!blasting.isEmpty()) registration.addRecipes(RecipeTypes.BLASTING, blasting);
        if (!campfire.isEmpty()) registration.addRecipes(RecipeTypes.CAMPFIRE_COOKING, campfire);
        if (!stonecutting.isEmpty()) registration.addRecipes(RecipeTypes.STONECUTTING, stonecutting);
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private void loadRecipesFromFileSystem(
            List<CoffeeMachineRecipe> coffeeMachineRecipes,
            List<IceCreamMachineRecipe> iceCreamMachineRecipes,
            List<BlenderRecipe> blenderRecipes,
            List<Recipe<?>> vanillaRecipes) {

        var registryAccess = Minecraft.getInstance().level.registryAccess();
        var registryOps = registryAccess.createSerializationContext(JsonOps.INSTANCE);

        List<Path> scanPaths = new ArrayList<>();
        if (FMLLoader.isProduction()) {
            scanPaths.add(FMLPaths.GAMEDIR.get());
        } else {
            scanPaths.add(FMLPaths.GAMEDIR.get().resolve("..").resolve("src").resolve("main").resolve("resources"));
            scanPaths.add(FMLPaths.GAMEDIR.get().resolve("..").resolve("src").resolve("generated").resolve("resources"));
        }

        String recipesDir = "data/" + KawaiiDishes.modId + "/recipes";

        for (Path basePath : scanPaths) {
            Path recipeDir = basePath.resolve(recipesDir);
            if (!Files.isDirectory(recipeDir)) continue;

            try (var stream = Files.list(recipeDir)) {
                stream.filter(p -> p.toString().endsWith(".json")).forEach(jsonFile -> {
                    try (var reader = new InputStreamReader(Files.newInputStream(jsonFile), StandardCharsets.UTF_8)) {
                        JsonElement json = GSON.fromJson(reader, JsonElement.class);
                        String fileName = jsonFile.getFileName().toString().replace(".json", "");
                        Recipe.CONDITIONAL_CODEC.parse(registryOps, json)
                                .resultOrPartial(error -> {})
                                .ifPresent(opt -> opt.ifPresent(wc -> {
                                    Recipe<?> recipe = wc.carrier();
                                    if (recipe instanceof CoffeeMachineRecipe r) coffeeMachineRecipes.add(r);
                                    else if (recipe instanceof IceCreamMachineRecipe r) iceCreamMachineRecipes.add(r);
                                    else if (recipe instanceof BlenderRecipe r) blenderRecipes.add(r);
                                    else vanillaRecipes.add(recipe);
                                }));
                    } catch (Exception ignored) {}
                });
            } catch (Exception ignored) {}
        }
    }
}
