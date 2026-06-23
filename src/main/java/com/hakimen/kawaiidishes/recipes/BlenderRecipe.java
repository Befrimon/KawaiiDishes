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

public class BlenderRecipe implements Recipe<ContainerRecipeInput> {

    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int ticks;
    private final ItemStack onOutput;

    public ItemStack getOnOutput() {
        return onOutput;
    }

    public BlenderRecipe(ItemStack output,
                         NonNullList<Ingredient> recipeItems, int ticks, ItemStack onOutput) {
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.onOutput = onOutput;
    }

    public int getTicks() {
        return ticks;
    }

    @Override
    public boolean matches(ContainerRecipeInput pContainer, Level pLevel) {
        boolean match = true;

        if(!pContainer.container().getItem(pContainer.container().getContainerSize()-1).getItem().equals(getResultItem(null).getItem())
                && !pContainer.container().getItem(pContainer.container().getContainerSize()-1).is(ItemStack.EMPTY.getItem())) {
            match = false;
        }
        if(!onOutput.equals(ItemStack.EMPTY)) {
            if(onOutput.getItem() == pContainer.container().getItem(pContainer.container().getContainerSize()-1).getItem()){
                match = true;
            }else
                return false;
        }

        boolean submatches[] = new boolean[] {false,false};

        if(recipeItems.get(0).getItems().length == 2) {
            submatches[0] = recipeItems.get(0).getItems()[0].getItem().equals(pContainer.container().getItem(0).getItem());
            submatches[1] = recipeItems.get(0).getItems()[1].getItem().equals(pContainer.container().getItem(1).getItem());
        }else if(recipeItems.get(0).getItems().length == 1) {
            submatches[0] = recipeItems.get(0).getItems()[0].getItem().equals(pContainer.container().getItem(0).getItem());
            submatches[1] = pContainer.container().getItem(1).getItem().equals(Items.AIR);
        }
        for (int i = 0; i < submatches.length; i++) {
            match &= submatches[i];
        }
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
    public static class Type implements RecipeType<BlenderRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer implements RecipeSerializer<BlenderRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        private static final Codec<NonNullList<Ingredient>> INGREDIENTS_CODEC = Codec.list(Ingredient.CODEC)
                .xmap(NonNullList::copyOf, NonNullList::copyOf);

        private static final MapCodec<BlenderRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.CODEC.fieldOf("output").forGetter(r -> r.output),
                INGREDIENTS_CODEC.fieldOf("ingredients").forGetter(r -> r.recipeItems),
                Codec.INT.fieldOf("ticks").forGetter(r -> r.ticks),
                ItemStack.CODEC.optionalFieldOf("onOutput", ItemStack.EMPTY).forGetter(r -> r.onOutput)
        ).apply(inst, BlenderRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, BlenderRecipe> STREAM_CODEC = new StreamCodec<>() {
            @Override
            public BlenderRecipe decode(RegistryFriendlyByteBuf buf) {
                ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
                var ingredients = Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)).decode(buf);
                int ticks = ByteBufCodecs.VAR_INT.decode(buf);
                boolean hasOnOutput = buf.readBoolean();
                ItemStack onOutput = hasOnOutput ? ItemStack.STREAM_CODEC.decode(buf) : ItemStack.EMPTY;
                return new BlenderRecipe(output, ingredients, ticks, onOutput);
            }

            @Override
            public void encode(RegistryFriendlyByteBuf buf, BlenderRecipe recipe) {
                ItemStack.STREAM_CODEC.encode(buf, recipe.output);
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)).encode(buf, recipe.recipeItems);
                ByteBufCodecs.VAR_INT.encode(buf, recipe.ticks);
                buf.writeBoolean(!recipe.onOutput.isEmpty());
                if (!recipe.onOutput.isEmpty()) {
                    ItemStack.STREAM_CODEC.encode(buf, recipe.onOutput);
                }
            }
        };

        @Override
        public MapCodec<BlenderRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, BlenderRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
