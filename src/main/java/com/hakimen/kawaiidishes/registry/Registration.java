package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.items.armor.ArmorMaterials;
import net.neoforged.bus.api.IEventBus;

public class Registration {

    public static void init(IEventBus bus) {
        ArmorMaterials.register(bus);
        BlockRegister.register(bus);
        BlockEntityRegister.register(bus);
        ItemRegister.register(bus);
        ContainerRegister.register(bus);
        RecipeRegister.register(bus);
        EffectRegister.register(bus);
        EntityRegister.register(bus);
        VillagerWorkRegister.register(bus);
    }
}
