package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.effects.KawaiiEffect;
import com.hakimen.kawaiidishes.effects.NekoEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EffectRegister {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, KawaiiDishes.modId);

    public static final DeferredHolder<MobEffect, KawaiiEffect> kawaiiEffect = MOB_EFFECTS.register("kawaii", KawaiiEffect::new);
    public static final DeferredHolder<MobEffect, NekoEffect> nekoEffect = MOB_EFFECTS.register("neko", NekoEffect::new);

    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }
}
