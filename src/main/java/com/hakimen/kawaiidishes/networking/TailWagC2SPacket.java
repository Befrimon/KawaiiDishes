package com.hakimen.kawaiidishes.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class TailWagC2SPacket implements CustomPacketPayload {
    public static final Type<TailWagC2SPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("kawaiidishes", "tail_wag_c2s"));

    public static final StreamCodec<FriendlyByteBuf, TailWagC2SPacket> STREAM_CODEC = StreamCodec.of(
            (buf, pkt) -> {},
            (buf) -> new TailWagC2SPacket()
    );

    public TailWagC2SPacket() {
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
