package com.hakimen.kawaiidishes.mixin.clientside;

import net.minecraft.client.renderer.entity.GlowSquidRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.GlowSquid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GlowSquidRenderer.class)
public class GlowSquidRendererMixin {

    private static final ResourceLocation MAID_GLOW_SQUID_LOCATION = ResourceLocation.fromNamespaceAndPath("kawaiidishes", "textures/entity/glow_squid/maid_glow_squid.png");

    @Overwrite
    public ResourceLocation getTextureLocation(GlowSquid pEntity) {
        return (pEntity.hasCustomName() && pEntity.getCustomName().toString().toLowerCase().contains("maid"))
                ? MAID_GLOW_SQUID_LOCATION : ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/squid/glow_squid.png");
    }
}
