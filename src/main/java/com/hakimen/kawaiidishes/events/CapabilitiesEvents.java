package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = KawaiiDishes.modId)
public class CapabilitiesEvents {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
    }
}
