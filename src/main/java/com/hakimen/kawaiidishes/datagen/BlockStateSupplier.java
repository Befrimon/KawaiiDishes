package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateSupplier extends BlockStateProvider {
    public BlockStateSupplier(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen.getPackOutput(), KawaiiDishes.modId, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }
}
