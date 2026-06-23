package com.hakimen.kawaiidishes.datagen.recipebuilder;

import net.minecraft.advancements.Criterion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;

import javax.annotation.Nullable;

public class CoffeeMachineRecipeBuilder implements RecipeBuilder {
    public CoffeeMachineRecipeBuilder() {}
    public CoffeeMachineRecipeBuilder(Item item, Item item1, Item item2, ItemStack result, int ticks, boolean needWater, boolean needMilk, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {}
    public CoffeeMachineRecipeBuilder(Item item, Item item1, ItemStack result, int ticks, boolean needWater, boolean needMilk, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {}
    public CoffeeMachineRecipeBuilder(Item item, ItemStack result, int ticks, boolean needWater, boolean needMilk, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {}

    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, Criterion<?> pCriterionTrigger) {
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return null;
    }

    @Override
    public void save(RecipeOutput pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
    }
}
