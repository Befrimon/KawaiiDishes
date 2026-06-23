package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.client.block_entity_renderers.CoffeeMachineRenderer;
import com.hakimen.kawaiidishes.client.block_entity_renderers.CoffeePressRenderer;
import com.hakimen.kawaiidishes.client.block_entity_renderers.IceCreamMachineRenderer;
import com.hakimen.kawaiidishes.client.entity.SeatRenderer;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import com.hakimen.kawaiidishes.registry.EntityRegister;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = KawaiiDishes.modId, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KawaiiDishesClient {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegister.coffeePress.get(), CoffeePressRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegister.coffeeMachine.get(), CoffeeMachineRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegister.iceCreamMachine.get(), IceCreamMachineRenderer::new);

        event.registerEntityRenderer(EntityRegister.SEAT.get(), SeatRenderer::new);
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ContainerRegister.coffeeMachine.get(), com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen::new);
        event.register(ContainerRegister.iceCreamMachine.get(), com.hakimen.kawaiidishes.client.screens.IceCreamScreen::new);
        event.register(ContainerRegister.blenderContainer.get(), com.hakimen.kawaiidishes.client.screens.BlenderScreen::new);
    }
}
