package com.hakimen.kawaiidishes.items.armor;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, KawaiiDishes.modId);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> tail = ARMOR_MATERIALS.register("tail",
            () -> new ArmorMaterial(
                    Map.of(),
                    0,
                    SoundEvents.ARMOR_EQUIP_LEATHER,
                    () -> Ingredient.EMPTY,
                    List.of(new ArmorMaterial.Layer(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "tail"))),
                    0,
                    0
            ));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> maidDress = ARMOR_MATERIALS.register("maid_dress",
            () -> new ArmorMaterial(
                    Map.of(),
                    0,
                    SoundEvents.ARMOR_EQUIP_LEATHER,
                    () -> Ingredient.EMPTY,
                    List.of(new ArmorMaterial.Layer(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "maid_dress"))),
                    0,
                    0
            ));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> tailedDress = ARMOR_MATERIALS.register("tailed_maid_dress",
            () -> new ArmorMaterial(
                    Map.of(),
                    0,
                    SoundEvents.ARMOR_EQUIP_LEATHER,
                    () -> Ingredient.EMPTY,
                    List.of(new ArmorMaterial.Layer(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "tailed_maid_dress"))),
                    0,
                    0
            ));

    public static void register(IEventBus bus) {
        ARMOR_MATERIALS.register(bus);
    }
}
