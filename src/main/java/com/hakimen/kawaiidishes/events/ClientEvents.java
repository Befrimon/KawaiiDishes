package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.KeybindRegister;
import com.hakimen.kawaiidishes.networking.TailWagC2SPacket;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class ClientEvents {
    @EventBusSubscriber(modid = KawaiiDishes.modId, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeybindRegister.tailWagKey.consumeClick()) {
                PacketDistributor.sendToServer(new TailWagC2SPacket());
            }
        }
    }

    @EventBusSubscriber(modid = KawaiiDishes.modId, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeybindRegister.tailWagKey);
        }
    }
}
