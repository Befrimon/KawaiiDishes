package com.hakimen.kawaiidishes.datagen.recipebuilder;

import net.minecraft.advancements.Criterion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;

import javax.annotation.Nullable;

public class IceCreamMachineRecipeBuilder implements RecipeBuilder {
    public IceCreamMachineRecipeBuilder() {}
    public IceCreamMachineRecipeBuilder(Item item, Item item1, Item item2, Item item3, ItemStack result, int ticks, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {}
    public IceCreamMachineRecipeBuilder(Item item, Item item1, Item item2, ItemStack result, int ticks, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {}
    public IceCreamMachineRecipeBuilder(Item item, Item item1, ItemStack result, int ticks, ItemStack onOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {}

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
