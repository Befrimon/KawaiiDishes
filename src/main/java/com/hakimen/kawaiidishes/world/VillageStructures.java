package com.hakimen.kawaiidishes.world;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

@EventBusSubscriber(modid = KawaiiDishes.modId)
public class VillageStructures {

    @SubscribeEvent
    public static void addNewVillageBuilding(final ServerAboutToStartEvent event) {
        // TODO: Re-implement village structure addition for 1.21.1
        // The StructurePoolElement API changed significantly in 1.21.1
    }
}
