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

public class IceCreamMachineRecipe implements Recipe<ContainerRecipeInput> {

    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int ticks;
    private final ItemStack itemOnOutput;

    public IceCreamMachineRecipe(ItemStack output,
                                 NonNullList<Ingredient> recipeItems, int ticks, ItemStack itemOnOutput) {
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.itemOnOutput = itemOnOutput;
    }

    public int getTicks() {
        return ticks;
    }

    public ItemStack getItemOnOutput() {
        return itemOnOutput;
    }

    @Override
    public boolean matches(ContainerRecipeInput pContainer, Level pLevel) {
        boolean match = true;
        int all = 0;
        for (int i = 0; i < pContainer.container().getContainerSize()-2; i++) {
            if(!pContainer.container().getItem(i).getItem().equals(Items.AIR)){
                all++;
            }
        }
        if(recipeItems.get(0).getItems().length == all){
            for (int i = 0; i < recipeItems.get(0).getItems().length; i++) {
                match &= recipeItems.get(0).getItems()[i].getItem().equals(pContainer.container().getItem(i).getItem());
            }
        }else{
            return false;
        }
        match &= pContainer.container().getItem(4).getItem().equals(itemOnOutput.getItem());

        return match;
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
    public static class Type implements RecipeType<IceCreamMachineRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer implements RecipeSerializer<IceCreamMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        private static final Codec<NonNullList<Ingredient>> INGREDIENTS_CODEC = Codec.list(Ingredient.CODEC)
                .xmap(NonNullList::copyOf, NonNullList::copyOf);

        private static final MapCodec<IceCreamMachineRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.CODEC.fieldOf("output").forGetter(r -> r.output),
                INGREDIENTS_CODEC.fieldOf("ingredients").forGetter(r -> r.recipeItems),
                Codec.INT.fieldOf("ticks").forGetter(r -> r.ticks),
                ItemStack.CODEC.fieldOf("itemOnOutput").forGetter(r -> r.itemOnOutput)
        ).apply(inst, IceCreamMachineRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, IceCreamMachineRecipe> STREAM_CODEC = StreamCodec.composite(
                ItemStack.STREAM_CODEC, r -> r.output,
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)),
                r -> r.recipeItems,
                ByteBufCodecs.VAR_INT, r -> r.ticks,
                ItemStack.STREAM_CODEC, r -> r.itemOnOutput,
                IceCreamMachineRecipe::new
        );

        @Override
        public MapCodec<IceCreamMachineRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, IceCreamMachineRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
