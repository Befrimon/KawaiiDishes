package com.hakimen.kawaiidishes.datagen.recipebuilder;

import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlenderRecipeBuilder implements RecipeBuilder {
    private final List<Item> ingredients = new ArrayList<>();
    private final ItemStack result;
    private final ItemStack onOutput;
    private final int ticks;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public BlenderRecipeBuilder(Item ingredient, ItemStack result, ItemStack onOutput, int ticks, int count) {
        this.ingredients.add(ingredient);
        this.result = result;
        this.onOutput = onOutput;
        this.ticks = ticks;
        this.count = count;
    }

    public BlenderRecipeBuilder(Item ingredient, Item ingredient2, ItemStack result, ItemStack onOutput, int ticks, int count) {
        this.ingredients.add(ingredient);
        this.ingredients.add(ingredient2);
        this.result = result;
        this.onOutput = onOutput;
        this.ticks = ticks;
        this.count = count;
    }

    public BlenderRecipeBuilder(Item ingredient, Item ingredient2, ItemStack result, ItemStack onOutput, int ticks, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect, int count) {
        this.ingredients.add(ingredient);
        this.ingredients.add(ingredient2);
        this.result = result;
        this.onOutput = onOutput;
        this.ticks = ticks;
        this.count = count;
    }

    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, Criterion<?> pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result.getItem();
    }

    @Override
    public void save(RecipeOutput pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        NonNullList<Ingredient> ingredientList = NonNullList.create();
        ItemStack[] stacks = ingredients.stream()
                .map(Item::getDefaultInstance)
                .toArray(ItemStack[]::new);
        ingredientList.add(Ingredient.of(stacks));

        ItemStack outputStack = count > 1 ? new ItemStack(result.getItem(), count) : result.copy();

        BlenderRecipe recipe = new BlenderRecipe(outputStack, ingredientList, ticks, onOutput.copy());
        AdvancementHolder advancementHolder = this.advancement.build(pRecipeId.withPrefix("recipes/"));
        pFinishedRecipeConsumer.accept(pRecipeId, recipe, advancementHolder);
    }
}
