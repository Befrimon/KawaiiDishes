package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.BlenderContainer;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.containers.IceCreamMachineContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ContainerRegister {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(Registries.MENU, KawaiiDishes.modId);

    public static final Supplier<MenuType<CoffeeMachineContainer>> coffeeMachine =
            CONTAINERS.register("coffee_machine", () -> IMenuTypeExtension.create(CoffeeMachineContainer::new));
    public static final Supplier<MenuType<IceCreamMachineContainer>> iceCreamMachine =
            CONTAINERS.register("ice_cream_machine", () -> IMenuTypeExtension.create(IceCreamMachineContainer::new));
    public static final Supplier<MenuType<BlenderContainer>> blenderContainer =
            CONTAINERS.register("blender", () -> IMenuTypeExtension.create(BlenderContainer::new));

    public static void register(IEventBus bus) {
        CONTAINERS.register(bus);
    }
}
