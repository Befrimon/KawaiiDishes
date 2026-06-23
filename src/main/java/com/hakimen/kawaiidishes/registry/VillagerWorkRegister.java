package com.hakimen.kawaiidishes.registry;

import com.google.common.collect.ImmutableSet;
import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VillagerWorkRegister {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, KawaiiDishes.modId);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(Registries.VILLAGER_PROFESSION, KawaiiDishes.modId);

    public static final Supplier<PoiType> baristaPOI = POI_TYPES.register("barista",
            () -> new PoiType(ImmutableSet.copyOf(BlockRegister.coffeeMachine.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final ResourceKey<PoiType> baristaPOIKey = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "barista"));

    public static final Supplier<VillagerProfession> baristaVillagerProfession = VILLAGER_PROFESSIONS.register("barista",
            () -> new VillagerProfession(KawaiiDishes.modId + ":barista",
                    holder -> holder.is(baristaPOIKey),
                    holder -> holder.is(baristaPOIKey),
                    ImmutableSet.of(), ImmutableSet.of(BlockRegister.coffeeMachine.get()),
                    SoundEvents.VILLAGER_WORK_CLERIC));

    public static void register(IEventBus bus) {
        VILLAGER_PROFESSIONS.register(bus);
        POI_TYPES.register(bus);
    }
}
