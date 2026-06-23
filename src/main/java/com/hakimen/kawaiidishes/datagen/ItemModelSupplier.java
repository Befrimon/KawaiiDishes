package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelSupplier extends ItemModelProvider {
    public ItemModelSupplier(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), KawaiiDishes.modId, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (var block : BlockRegister.BLOCKS.getEntries().stream().toList()) {
            String path = BuiltInRegistries.BLOCK.getKey(block.get()).toString().replaceAll(KawaiiDishes.modId + ":", "");
            if (path.contains("_stool")) {
                stool(block.get());
            } else if (path.endsWith("milkshake")) {
                milkshake(block.get());
            } else if (path.endsWith("coffee")) {
                coffee(block.get());
            } else if (path.endsWith("ice_cream")) {
                iceCream(block.get());
            } else if (path.endsWith("cake")) {
                cake(block.get());
            } else if (path.contains("coffee_bush") || path.contains("mortar_and_pestle")) {
                continue;
            } else
                block(block.get());
        }

        simpleItem(ItemRegister.sweetBerryCookie.get());
        simpleItem(ItemRegister.chocolateCookie.get());
        simpleItem(ItemRegister.honeyCookie.get());
        simpleItem(ItemRegister.goldenCookie.get());
        simpleItem(ItemRegister.unbindingCookie.get());

        simpleItem(ItemRegister.cakePiece.get());
        simpleItem(ItemRegister.cheeseCakePiece.get());
        simpleItem(ItemRegister.chocolateCheeseCakePiece.get());
        simpleItem(ItemRegister.honeyCheeseCakePiece.get());

        simpleItem(ItemRegister.whiteChocolateBar.get());
        simpleItem(ItemRegister.darkChocolateBar.get());
        simpleItem(ItemRegister.milkChocolateBar.get());

        simpleItem(ItemRegister.condensedMilk.get());
        simpleItem(ItemRegister.brigadeiroMix.get());
        simpleItem(ItemRegister.creamCheese.get());

        simpleItem(ItemRegister.driedCocoaBeans.get());
        simpleItem(ItemRegister.roastedCocoaBeans.get());
        simpleItem(ItemRegister.cocoaPowder.get());

        simpleItem(ItemRegister.coffeeFruit.get());
        simpleItem(ItemRegister.driedCoffeeBeans.get());
        simpleItem(ItemRegister.roastedCoffeeBeans.get());
        simpleItem(ItemRegister.coffeePowder.get());

        simpleItem(ItemRegister.blackThighHighsShoes.get());
        simpleItem(ItemRegister.whiteThighHighsShoes.get());

        simpleItem(ItemRegister.bunnySuitBlackTail.get());
        simpleItem(ItemRegister.bunnySuitWhiteTail.get());
        simpleItem(ItemRegister.bunnySuitCaramelTail.get());
        simpleItem(ItemRegister.bunnySuitSocks.get());
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(BuiltInRegistries.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId + ":", ""),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "item/" + BuiltInRegistries.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId + ":", "")));
    }

    private ItemModelBuilder block(Block block) {
        return withExistingParent(BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", ""), ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "block/" + BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", "")));
    }

    private ItemModelBuilder cake(Block block) {
        return withExistingParent(BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", ""), ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "block/cake/cakes/" + BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", "")));
    }

    private ItemModelBuilder milkshake(Block block) {
        return withExistingParent(BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", ""), ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "block/milk_shakes/" + BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", "")));
    }

    private ItemModelBuilder coffee(Block block) {
        return withExistingParent(BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", ""), ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "block/coffees/" + BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", "")));
    }

    private ItemModelBuilder iceCream(Block block) {
        return withExistingParent(BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", ""), ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "block/ice_creams/" + BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", "")));
    }

    private ItemModelBuilder stool(Block block) {
        return withExistingParent(BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", ""), ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "block/stools/" + BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId + ":", "")));
    }
}
