package com.hakimen.kawaiidishes.datagen.recipebuilder;

import net.minecraft.advancements.Criterion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;

import javax.annotation.Nullable;

public class BlenderRecipeBuilder implements RecipeBuilder {
    public BlenderRecipeBuilder() {}
    public BlenderRecipeBuilder(Item ingredient, ItemStack result, ItemStack onOut, int tick, int count) {}
    public BlenderRecipeBuilder(Item ingredient, Item ingredient2, ItemStack result, ItemStack onOut, int tick, int count) {}
    public BlenderRecipeBuilder(Item ingredient, Item ingredient2, ItemStack result, ItemStack onOut, int tick, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect, int count) {}

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
