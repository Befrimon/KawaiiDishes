package com.hakimen.kawaiidishes.registry;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.util.TriConsumer;

public class ArmorTickRegister {
    public static TriConsumer<ItemStack, Level, Player> NULL = (stack, level, player) -> {
    };

    public static TriConsumer<ItemStack, Level, Player> maidArmorTick = NULL;
    public static TriConsumer<ItemStack, Level, Player> foxArmorTick = NULL;
    public static TriConsumer<ItemStack, Level, Player> bunnyArmorTick = NULL;
    public static TriConsumer<ItemStack, Level, Player> catArmorTick = NULL;
}
