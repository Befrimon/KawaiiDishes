package com.hakimen.kawaiidishes.mixin.clientside;

import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Squid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SquidRenderer.class)
public class SquidRendererMixin {

    private static final ResourceLocation MAID_SQUID_LOCATION = ResourceLocation.fromNamespaceAndPath("kawaiidishes", "textures/entity/squid/maid_squid.png");

    @Overwrite
    public ResourceLocation getTextureLocation(Squid pEntity) {
        return (pEntity.hasCustomName() && pEntity.getCustomName().toString().toLowerCase().contains("maid"))
                ? MAID_SQUID_LOCATION : ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/squid/squid.png");
    }
}
