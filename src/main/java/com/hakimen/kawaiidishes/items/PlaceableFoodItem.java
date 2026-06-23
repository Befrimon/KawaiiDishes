package com.hakimen.kawaiidishes.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlaceableFoodItem extends BlockItem {

    Item remainder;

    public PlaceableFoodItem(Block pBlock, int nutrition, float saturation, Item remainder) {
        super(pBlock, new Properties().food(new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).fast().build())
                .stacksTo(16));
        this.remainder = remainder;
    }

    public ItemStack getStackWithEffects(MobEffectInstance effect1, MobEffectInstance effect2) {
        var stack = new ItemStack(this);
        var mainEff = effect1.save();
        var secondEff = effect2.save();
        CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> {
            tag.put("mainEffect", mainEff);
            tag.put("secondaryEffect", secondEff);
        });
        return stack;
    }

    CompoundTag mainEffect;
    CompoundTag secondaryEffect;

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player p && !p.getInventory().isEmpty()) {
            pLevel.addFreshEntity(new ItemEntity(
                    pLevel,
                    p.getX(),
                    p.getY(),
                    p.getZ(),
                    remainder.getDefaultInstance()));
        }
        if (pLivingEntity instanceof Player p && p.getInventory().isEmpty()) {
            p.addItem(remainder.getDefaultInstance());
        }
        loadItemData(pStack);

        if (!mainEffect.equals(new CompoundTag())) {
            pLivingEntity.addEffect(MobEffectInstance.load(mainEffect));
        }
        if (!secondaryEffect.equals(new CompoundTag())) {
            pLivingEntity.addEffect(MobEffectInstance.load(secondaryEffect));
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    public void loadItemData(ItemStack pStack) {
        CustomData customData = pStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = customData.copyTag();
        mainEffect = tag.getCompound("mainEffect");
        secondaryEffect = tag.getCompound("secondaryEffect");
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltip, TooltipFlag pFlag) {
        CustomData customData = pStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = customData.copyTag();

        if (!tag.getCompound("mainEffect").equals(new CompoundTag())) {
            var instance = MobEffectInstance.load(tag.getCompound("mainEffect"));
            var mainEffect = Component.translatable(instance.getDescriptionId());
            mainEffect.append(" ").append(Component.translatable("enchantment.level." + (instance.getAmplifier() + 1)));
            mainEffect.append(" (").append(MobEffectUtil.formatDuration(instance, 1f, 20f)).append(")");
            if (!instance.getEffect().value().isBeneficial())
                mainEffect.setStyle(Style.EMPTY.withColor(0xDD4444));
            else
                mainEffect.setStyle(Style.EMPTY.withColor(0x4455FF));
            pTooltip.add(mainEffect);
        }
        if (!tag.getCompound("secondaryEffect").equals(new CompoundTag())) {
            var instance = MobEffectInstance.load(tag.getCompound("secondaryEffect"));
            var mainEffect = Component.translatable(instance.getDescriptionId());
            mainEffect.append(" ").append(Component.translatable("enchantment.level." + (instance.getAmplifier() + 1)));
            mainEffect.append(" (").append(MobEffectUtil.formatDuration(instance, 1f, 20f)).append(")");
            if (!instance.getEffect().value().isBeneficial())
                mainEffect.setStyle(Style.EMPTY.withColor(0xDD4444));
            else
                mainEffect.setStyle(Style.EMPTY.withColor(0x4455FF));
            pTooltip.add(mainEffect);
        }

        super.appendHoverText(pStack, pContext, pTooltip, pFlag);
    }

    @Override
    public void onDestroyed(ItemEntity pItemEntity) {
        if (mainEffect != null) {
            CustomData.update(DataComponents.CUSTOM_DATA, pItemEntity.getItem(), tag -> {
                tag.put("mainEffect", mainEffect);
                tag.put("secondaryEffect", secondaryEffect);
            });
        }
        super.onDestroyed(pItemEntity);
    }

    @Override
    protected boolean canPlace(BlockPlaceContext pContext, BlockState pState) {
        return pContext.getPlayer().isShiftKeyDown();
    }

    @Override
    public SoundEvent getEatingSound() {
        return super.getDrinkingSound();
    }
}
