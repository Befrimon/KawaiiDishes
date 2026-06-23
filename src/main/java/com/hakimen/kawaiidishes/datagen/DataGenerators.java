package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = KawaiiDishes.modId, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var holder = CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor());
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        generator.addProvider(true, new ItemModelSupplier(generator, fileHelper));
        generator.addProvider(true, new BlockStateSupplier(generator, fileHelper));
        generator.addProvider(true, new LangSupplier(generator, "en_us"));
        generator.addProvider(true, new CraftingRecipeSupplier(generator, holder));
        var blockTagSupplier = new BlockTagSupplier(generator, holder, fileHelper);
        generator.addProvider(true, blockTagSupplier);
        generator.addProvider(true, new ItemTagSupplier(generator, holder, blockTagSupplier, fileHelper));
        generator.addProvider(true, LootTableSupplier.create(generator.getPackOutput(), holder));

        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new WorldGenSupplier(packOutput, lookupProvider));
    }
}
