package com.hakimen.kawaiidishes.mixin.clientside;

import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Fox;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FoxRenderer.class)
public class FoxRendererMixin {

    @Shadow @Final private static ResourceLocation RED_FOX_TEXTURE;
    @Shadow @Final private static ResourceLocation RED_FOX_SLEEP_TEXTURE;

    @Overwrite
    public ResourceLocation getTextureLocation(Fox pEntity) {
        if (pEntity.hasCustomName() && pEntity.getCustomName().toString().toLowerCase().contains("maid")) {
            return ResourceLocation.fromNamespaceAndPath("kawaiidishes", "textures/entity/fox/maid_fox.png");
        }
        return pEntity.getVariant() == Fox.Type.RED ? (pEntity.isSleeping() ? RED_FOX_SLEEP_TEXTURE : RED_FOX_TEXTURE) : (pEntity.isSleeping() ? RED_FOX_SLEEP_TEXTURE : RED_FOX_TEXTURE);
    }
}
