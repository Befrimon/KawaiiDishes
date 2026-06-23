package com.hakimen.kawaiidishes.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class CoffeePressRecipe implements Recipe<ContainerRecipeInput> {

    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public CoffeePressRecipe(ItemStack output,
                                   NonNullList<Ingredient> recipeItems) {
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(ContainerRecipeInput pContainer, Level pLevel) {
        var matches = new boolean[]{
                true,true,true
        };
        for (int i = 0; i < recipeItems.get(0).getItems().length; i++) {
            matches[i] = recipeItems.get(0).getItems()[i].getItem().equals(pContainer.container().getItem(i).getItem());
        }
        return (matches[0]&&matches[1]&&matches[2]);
    }

    @Override
    public ItemStack assemble(ContainerRecipeInput pContainer, HolderLookup.Provider registries) {
        return output;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output.copy();
    }
    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    public static class Type implements RecipeType<CoffeePressRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer implements RecipeSerializer<CoffeePressRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        private static final Codec<NonNullList<Ingredient>> INGREDIENTS_CODEC = Codec.list(Ingredient.CODEC)
                .xmap(NonNullList::copyOf, NonNullList::copyOf);

        private static final MapCodec<CoffeePressRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.CODEC.fieldOf("output").forGetter(r -> r.output),
                INGREDIENTS_CODEC.fieldOf("ingredients").forGetter(r -> r.recipeItems)
        ).apply(inst, CoffeePressRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, CoffeePressRecipe> STREAM_CODEC = StreamCodec.composite(
                ItemStack.STREAM_CODEC, r -> r.output,
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)),
                r -> r.recipeItems,
                CoffeePressRecipe::new
        );

        @Override
        public MapCodec<CoffeePressRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CoffeePressRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
