package com.hakimen.kawaiidishes.items.armor;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.GenericGeoRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Function;

public class GenericGeoArmorItem extends ArmorItem implements GeoItem {

    public ResourceLocation textureLocation;
    public ResourceLocation modelLocation;
    public ResourceLocation animationLocation;

    public Function<AnimationState<GenericGeoArmorItem>, PlayState> animationControlling;
    public TriConsumer<ItemStack, Level, Player> armorTick;

    public boolean canStandOnPowderSnow;
    public boolean makesPiglinsNeutral;

    public GenericGeoArmorItem setCanStandOnPowderSnow(boolean canStandOnPowderSnow) {
        this.canStandOnPowderSnow = canStandOnPowderSnow;
        return this;
    }

    public GenericGeoArmorItem setMakesPiglinsNeutral(boolean makesPiglinsNeutral) {
        this.makesPiglinsNeutral = makesPiglinsNeutral;
        return this;
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GenericGeoArmorItem(Holder<ArmorMaterial> pMaterial, ArmorItem.Type pSlot, Properties pProperties, String textureLocation, String modelLocation, String animationLocation, Function<AnimationState<GenericGeoArmorItem>, PlayState> animationControlling, TriConsumer<ItemStack, Level, Player> armorTick) {
        super(pMaterial, pSlot, pProperties);
        this.textureLocation = ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "textures/models/armor/" + textureLocation);
        this.modelLocation = ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "geo/" + modelLocation);
        this.animationLocation = ResourceLocation.fromNamespaceAndPath(KawaiiDishes.modId, "animations/" + animationLocation);
        this.animationControlling = animationControlling;
        this.armorTick = armorTick;
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return canStandOnPowderSnow;
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return makesPiglinsNeutral;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getGenericArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new GenericGeoRenderer(modelLocation);

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return ItemAttributeModifiers.builder().build();
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GenericGeoArmorItem>(this, "controller", 20, this::predicate));
    }

    private PlayState predicate(AnimationState<GenericGeoArmorItem> state) {
        return animationControlling.apply(state);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
