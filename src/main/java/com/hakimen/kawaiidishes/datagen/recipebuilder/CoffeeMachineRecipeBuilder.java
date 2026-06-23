package com.hakimen.kawaiidishes.datagen.recipebuilder;

import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
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

public class CoffeeMachineRecipeBuilder implements RecipeBuilder {
    private final List<Item> ingredients = new ArrayList<>();
    private final ItemStack result;
    private final int ticks;
    private final boolean needWater;
    private final boolean needMilk;
    private final ItemStack onOutput;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public CoffeeMachineRecipeBuilder(Item item, ItemStack result, int ticks, boolean needWater, boolean needMilk, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        this.ingredients.add(item);
        this.result = result;
        this.ticks = ticks;
        this.needWater = needWater;
        this.needMilk = needMilk;
        this.onOutput = onOutput;
    }

    public CoffeeMachineRecipeBuilder(Item item, Item item1, ItemStack result, int ticks, boolean needWater, boolean needMilk, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        this.ingredients.add(item);
        this.ingredients.add(item1);
        this.result = result;
        this.ticks = ticks;
        this.needWater = needWater;
        this.needMilk = needMilk;
        this.onOutput = onOutput;
    }

    public CoffeeMachineRecipeBuilder(Item item, Item item1, Item item2, ItemStack result, int ticks, boolean needWater, boolean needMilk, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        this.ingredients.add(item);
        this.ingredients.add(item1);
        this.ingredients.add(item2);
        this.result = result;
        this.ticks = ticks;
        this.needWater = needWater;
        this.needMilk = needMilk;
        this.onOutput = onOutput;
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

        CoffeeMachineRecipe recipe = new CoffeeMachineRecipe(
                result.copy(), ingredientList, ticks, needWater, needMilk, onOutput.copy()
        );
        AdvancementHolder advancementHolder = this.advancement.build(pRecipeId.withPrefix("recipes/"));
        pFinishedRecipeConsumer.accept(pRecipeId, recipe, advancementHolder);
    }
}
