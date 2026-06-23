package com.hakimen.kawaiidishes.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class KawaiiDishesClientConfig {
    public static final ModConfigSpec.Builder clientConfigBuilder = new ModConfigSpec.Builder();

    public static final ModConfigSpec clientSpec;
    static {
        clientConfigBuilder.push("Client Side Configs for Kawaii Dishes");
        clientConfigBuilder.pop();
        clientSpec = clientConfigBuilder.build();
    }
}
