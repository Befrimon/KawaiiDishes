package com.hakimen.kawaiidishes.capabilities;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.networking.TailWagSyncS2CPacket;
import com.hakimen.kawaiidishes.registry.PacketRegister;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerTailWagCapability {
    private static final Map<UUID, Boolean> waggingStates = new HashMap<>();

    public static boolean getWagging(UUID uuid) {
        return waggingStates.getOrDefault(uuid, false);
    }

    public static void setWagging(UUID uuid, boolean wagging) {
        waggingStates.put(uuid, wagging);
    }

    @EventBusSubscriber(modid = KawaiiDishes.modId)
    public static class Events {
        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if (!event.getLevel().isClientSide() && event.getEntity() instanceof ServerPlayer player) {
                boolean wagging = getWagging(player.getUUID());
                PacketRegister.sendToClients(new TailWagSyncS2CPacket(wagging, player.getUUID()));
            }
        }
    }
}
