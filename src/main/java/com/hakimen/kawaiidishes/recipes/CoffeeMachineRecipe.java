package com.hakimen.kawaiidishes.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class CoffeeMachineRecipe implements Recipe<ContainerRecipeInput> {

    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int ticks;
    private final boolean requireWater;
    private final boolean requireMilk;
    private final ItemStack itemOnOutput;

    public CoffeeMachineRecipe(ItemStack output,
                               NonNullList<Ingredient> recipeItems, int ticks,
    boolean water,boolean milk,ItemStack itemOnOutput) {
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.requireWater = water;
        this.requireMilk = milk;
        this.itemOnOutput = itemOnOutput;
    }

    public int getTicks() {
        return ticks;
    }

    public boolean requireWater() {
        return requireWater;
    }

    public boolean requireMilk() {
        return requireMilk;
    }

    public ItemStack getItemOnOutput() {
        return itemOnOutput;
    }

    @Override
    public boolean matches(ContainerRecipeInput pContainer, Level pLevel) {
        boolean match = true;
        if(requireWater)
            match &= pContainer.container().getItem(0).getItem().equals(Items.WATER_BUCKET);
        else if(pContainer.container().getItem(0).getItem().equals(Items.WATER_BUCKET)){
            return false;
        }
        if(requireMilk)
            match &= pContainer.container().getItem(1).getItem().equals(Items.MILK_BUCKET);
        else if(pContainer.container().getItem(1).getItem().equals(Items.MILK_BUCKET)){
            return false;
        }
        match &= pContainer.container().getItem(5).getItem().equals(itemOnOutput.getItem());
        boolean submatches[] = new boolean[] {false,false,false};
        if(recipeItems.get(0).getItems().length == 3){
            submatches[0] = recipeItems.get(0).getItems()[0].getItem().equals(pContainer.container().getItem(2).getItem());
            submatches[1] = recipeItems.get(0).getItems()[1].getItem().equals(pContainer.container().getItem(3).getItem());
            submatches[2] = recipeItems.get(0).getItems()[2].getItem().equals(pContainer.container().getItem(4).getItem());
        }else if(recipeItems.get(0).getItems().length == 2){
            submatches[0] = recipeItems.get(0).getItems()[0].getItem().equals(pContainer.container().getItem(2).getItem());
            submatches[1] = recipeItems.get(0).getItems()[1].getItem().equals(pContainer.container().getItem(3).getItem());
            submatches[2] = pContainer.container().getItem(4).getItem().equals(Items.AIR);
        }else if(recipeItems.get(0).getItems().length == 1){
            submatches[0] = recipeItems.get(0).getItems()[0].getItem().equals(pContainer.container().getItem(2).getItem());
            submatches[1] = pContainer.container().getItem(3).getItem().equals(Items.AIR);
            submatches[2] = pContainer.container().getItem(4).getItem().equals(Items.AIR);
        }
        for (int i = 0; i < submatches.length; i++) {
            match &= submatches[i];
        }
        return match;
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
    public ItemStack assemble(ContainerRecipeInput pContainer, HolderLookup.Provider registries) {
        return output;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    public static class Type implements RecipeType<CoffeeMachineRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer implements RecipeSerializer<CoffeeMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        private static final Codec<NonNullList<Ingredient>> INGREDIENTS_CODEC = Codec.list(Ingredient.CODEC)
                .xmap(NonNullList::copyOf, NonNullList::copyOf);

        private static final MapCodec<CoffeeMachineRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.CODEC.fieldOf("output").forGetter(r -> r.output),
                INGREDIENTS_CODEC.fieldOf("ingredients").forGetter(r -> r.recipeItems),
                Codec.INT.fieldOf("ticks").forGetter(r -> r.ticks),
                Codec.BOOL.fieldOf("water").forGetter(r -> r.requireWater),
                Codec.BOOL.fieldOf("milk").forGetter(r -> r.requireMilk),
                ItemStack.CODEC.fieldOf("itemOnOutput").forGetter(r -> r.itemOnOutput)
        ).apply(inst, CoffeeMachineRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, CoffeeMachineRecipe> STREAM_CODEC = StreamCodec.of(
                (buf, recipe) -> {
                    ItemStack.STREAM_CODEC.encode(buf, recipe.output);
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)).encode(buf, recipe.recipeItems);
                    ByteBufCodecs.VAR_INT.encode(buf, recipe.ticks);
                    ByteBufCodecs.BOOL.encode(buf, recipe.requireWater);
                    ByteBufCodecs.BOOL.encode(buf, recipe.requireMilk);
                    ItemStack.STREAM_CODEC.encode(buf, recipe.itemOnOutput);
                },
                (buf) -> {
                    ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
                    NonNullList<Ingredient> recipeItems = Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)).decode(buf);
                    int ticks = ByteBufCodecs.VAR_INT.decode(buf);
                    boolean requireWater = ByteBufCodecs.BOOL.decode(buf);
                    boolean requireMilk = ByteBufCodecs.BOOL.decode(buf);
                    ItemStack itemOnOutput = ItemStack.STREAM_CODEC.decode(buf);
                    return new CoffeeMachineRecipe(output, recipeItems, ticks, requireWater, requireMilk, itemOnOutput);
                }
        );

        @Override
        public MapCodec<CoffeeMachineRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CoffeeMachineRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
