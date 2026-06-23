package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.entity.SittableEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, KawaiiDishes.modId);

    public static final Supplier<EntityType<SittableEntity>> SEAT = ENTITIES.register("seat",
            () -> EntityType.Builder.<SittableEntity>of(SittableEntity::new, MobCategory.MISC).sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "seat").toString()));

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}
