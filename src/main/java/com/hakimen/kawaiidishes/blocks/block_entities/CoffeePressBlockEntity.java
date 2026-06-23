package com.hakimen.kawaiidishes.blocks.block_entities;

import com.hakimen.kawaiidishes.blocks.CoffeePressBlock;
import com.hakimen.kawaiidishes.recipes.CoffeePressRecipe;
import com.hakimen.kawaiidishes.recipes.ContainerRecipeInput;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class CoffeePressBlockEntity extends BlockEntity {

    public final ItemStackHandler inventory = createHandler();

    public boolean coffeeGotMade;
    public ItemStack coffeeMade = ItemStack.EMPTY;

    public CoffeePressBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegister.coffeePress.get(), pWorldPosition, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider registries) {
        pTag.merge(this.inventory.serializeNBT(registries));
        pTag.put("coffee", coffeeMade.save(registries));
        pTag.putBoolean("isDone", coffeeGotMade);
        super.saveAdditional(pTag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider registries) {
        super.loadAdditional(pTag, registries);
        this.inventory.deserializeNBT(registries, pTag);
        coffeeMade = ItemStack.parseOptional(registries, pTag.getCompound("coffee"));
        coffeeGotMade = pTag.getBoolean("isDone");
    }

    public static boolean hasRecipe(CoffeePressBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
        for (int i = 0; i < entity.inventory.getSlots(); i++) {
            if (entity.inventory.getStackInSlot(i) != ItemStack.EMPTY) {
                inventory.setItem(i, entity.inventory.getStackInSlot(i));
            }
        }
        Optional<CoffeePressRecipe> match = level.getRecipeManager()
                .getRecipeFor(CoffeePressRecipe.Type.INSTANCE, new ContainerRecipeInput(inventory), level)
                .map(holder -> holder.value());
        return match.isPresent();
    }

    public static void craft(CoffeePressBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
        for (int i = 0; i < entity.inventory.getSlots(); i++) {
            inventory.setItem(i, entity.inventory.getStackInSlot(i));
        }
        Optional<CoffeePressRecipe> match = level.getRecipeManager()
                .getRecipeFor(CoffeePressRecipe.Type.INSTANCE, new ContainerRecipeInput(inventory), level)
                .map(holder -> holder.value());
        if (match.isPresent()) {
            for (int i = 0; i < entity.inventory.getSlots(); i++) {
                var stack = entity.inventory.getStackInSlot(i).getItem().getCraftingRemainingItem();
                if (stack == null) {
                    entity.inventory.setStackInSlot(i, ItemStack.EMPTY);
                } else {
                    entity.level.addFreshEntity(new ItemEntity(entity.level,
                            entity.getBlockPos().getX(),
                            entity.getBlockPos().getY(),
                            entity.getBlockPos().getZ(),
                            stack.getDefaultInstance()));
                    entity.inventory.setStackInSlot(i, ItemStack.EMPTY);
                }
            }
            entity.coffeeMade = match.get().getResultItem(null);
            entity.coffeeGotMade = true;
            level.setBlockAndUpdate(entity.getBlockPos(), entity.getBlockState().setValue(
                    CoffeePressBlock.PRESSED, true
            ));
        }
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(3) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }
}
