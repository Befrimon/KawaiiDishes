package com.hakimen.kawaiidishes.networking;

import com.hakimen.kawaiidishes.client.data.ClientTailWagData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public class TailWagSyncS2CPacket implements CustomPacketPayload {
    public static final Type<TailWagSyncS2CPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("kawaiidishes", "tail_wag_sync_s2c"));

    public static final StreamCodec<FriendlyByteBuf, TailWagSyncS2CPacket> STREAM_CODEC = StreamCodec.of(
            (buf, pkt) -> {
                buf.writeBoolean(pkt.isWagging);
                buf.writeUUID(pkt.uuid);
            },
            (buf) -> new TailWagSyncS2CPacket(buf.readBoolean(), buf.readUUID())
    );

    private final boolean isWagging;
    private final UUID uuid;

    public TailWagSyncS2CPacket(boolean isWagging, UUID uuid) {
        this.isWagging = isWagging;
        this.uuid = uuid;
    }

    public boolean isWagging() {
        return isWagging;
    }

    public UUID uuid() {
        return uuid;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
