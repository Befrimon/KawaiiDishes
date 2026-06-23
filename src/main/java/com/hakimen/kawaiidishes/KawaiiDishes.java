package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.config.KawaiiDishesClientConfig;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import com.hakimen.kawaiidishes.registry.PacketRegister;
import com.hakimen.kawaiidishes.registry.Registration;
import com.hakimen.kawaiidishes.utils.MaidMobEventHandler;
import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import org.slf4j.Logger;

import java.util.Random;

@Mod("kawaiidishes")
public class KawaiiDishes {

    public static final Random RANDOM = new Random();
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String modId = "kawaiidishes";

    public KawaiiDishes(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, KawaiiDishesClientConfig.clientSpec, "kawaii-dishes-client.toml");
        modContainer.registerConfig(ModConfig.Type.COMMON, KawaiiDishesCommonConfig.commonSpec, "kawaii-dishes-common.toml");

        Registration.init(modEventBus);

        PacketRegister.register(modEventBus);

        modEventBus.addListener(this::setup);

        NeoForge.EVENT_BUS.addListener(this::onLivingSpecialSpawn);
    }

    public void onLivingSpecialSpawn(FinalizeSpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Monster monster && RANDOM.nextFloat() < KawaiiDishesCommonConfig.chanceToSpawnWithDress.get()) {
            if ((monster instanceof Skeleton
                    || monster instanceof WitherSkeleton
                    || monster instanceof Stray
                    || monster instanceof Zombie
                    || monster instanceof Piglin
                    || monster instanceof PiglinBrute) && KawaiiDishesCommonConfig.shouldMobSpawnWithDress.get()) {
                ItemStack[] stacks = MaidMobEventHandler.armorBuild(RANDOM);

                monster.setItemSlot(EquipmentSlot.HEAD, stacks[0]);
                monster.setItemSlot(EquipmentSlot.CHEST, stacks[1]);
                monster.setItemSlot(EquipmentSlot.LEGS, stacks[2]);
                monster.setItemSlot(EquipmentSlot.FEET, stacks[3]);

                monster.setDropChance(EquipmentSlot.HEAD, KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.CHEST, KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.LEGS, KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.FEET, KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());
            }
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ItemRegister.coffeeFruit.get(), 0.25f);
            ComposterBlock.COMPOSTABLES.put(ItemRegister.driedCoffeeBeans.get(), 0.50f);
            ComposterBlock.COMPOSTABLES.put(ItemRegister.roastedCoffeeBeans.get(), 0.75f);

            ComposterBlock.COMPOSTABLES.put(ItemRegister.driedCocoaBeans.get(), 0.50f);
            ComposterBlock.COMPOSTABLES.put(ItemRegister.roastedCocoaBeans.get(), 0.75f);

            ComposterBlock.COMPOSTABLES.put(ItemRegister.cakePiece.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(ItemRegister.honeyCheeseCakePiece.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(ItemRegister.chocolateCheeseCakePiece.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(ItemRegister.cheeseCakePiece.get(), 0.65f);

            ComposterBlock.COMPOSTABLES.put(ItemRegister.honeyCheeseCake.get(), 1f);
            ComposterBlock.COMPOSTABLES.put(ItemRegister.chocolateCheeseCake.get(), 1f);
            ComposterBlock.COMPOSTABLES.put(ItemRegister.cheeseCake.get(), 1f);
        });
    }
}
