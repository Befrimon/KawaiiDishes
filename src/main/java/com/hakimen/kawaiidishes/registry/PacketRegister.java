package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.capabilities.PlayerTailWagCapability;
import com.hakimen.kawaiidishes.networking.TailWagC2SPacket;
import com.hakimen.kawaiidishes.networking.TailWagSyncS2CPacket;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class PacketRegister {
    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PacketRegister::onRegisterPayloads);
    }

    private static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar(KawaiiDishes.modId);

        registrar.playToServer(
                TailWagC2SPacket.TYPE,
                TailWagC2SPacket.STREAM_CODEC,
                (packet, context) -> {
                    context.enqueueWork(() -> {
                        var player = context.player();
                        boolean current = PlayerTailWagCapability.getWagging(player.getUUID());
                        PlayerTailWagCapability.setWagging(player.getUUID(), !current);
                        sendToClients(new TailWagSyncS2CPacket(!current, player.getUUID()));
                    });
                }
        );

        registrar.playToClient(
                TailWagSyncS2CPacket.TYPE,
                TailWagSyncS2CPacket.STREAM_CODEC,
                (packet, context) -> {
                    context.enqueueWork(() -> {
                        com.hakimen.kawaiidishes.client.data.ClientTailWagData.setState(packet.uuid(), packet.isWagging());
                    });
                }
        );
    }

    public static <MSG> void sendToClients(MSG msg) {
        PacketDistributor.sendToAllPlayers((net.minecraft.network.protocol.common.custom.CustomPacketPayload) msg);
    }
}
