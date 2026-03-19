package net.collective.enchanced.common.payload;

import net.collective.enchanced.Enchanced;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record WeavingC2SPayload() implements CustomPayload {
    public static final Identifier LUNGE_PAYLOAD_ID = Identifier.of(Enchanced.MOD_ID, "weaving_payload");
    public static final Id<WeavingC2SPayload> ID = new Id<>(LUNGE_PAYLOAD_ID);
    public static final PacketCodec<RegistryByteBuf, WeavingC2SPayload> CODEC = PacketCodec.of(
            (value, buf) -> {
            },
            buf -> new WeavingC2SPayload()
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}