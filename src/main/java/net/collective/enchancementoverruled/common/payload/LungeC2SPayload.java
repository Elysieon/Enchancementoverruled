package net.collective.enchancementoverruled.common.payload;

import net.collective.enchancementoverruled.Enchancementoverruled;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record LungeC2SPayload(int entityId) implements CustomPayload {
    public static final Identifier LUNGE_PAYLOAD_ID = Identifier.of(Enchancementoverruled.MOD_ID, "lunge_packet");
    public static final Id<LungeC2SPayload> ID = new Id<>(LUNGE_PAYLOAD_ID);
    public static final PacketCodec<RegistryByteBuf, LungeC2SPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, LungeC2SPayload::entityId, LungeC2SPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}