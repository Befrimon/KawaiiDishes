package com.hakimen.kawaiidishes.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;

public class CandyBlock extends Block {
    private static final MapCodec<CandyBlock> CODEC = simpleCodec(props -> new CandyBlock());

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    public CandyBlock(){
        super(Properties.ofFullCopy(Blocks.CAKE)
                .strength(.25f,0)
                .isSuffocating((p_61036_, p_61037_, p_61038_) -> false));
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        if(pPlayer.isCrouching()){
            var stack = this.asItem().getDefaultInstance();
            pLevel.addFreshEntity(new ItemEntity(pLevel,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(),stack));
            pLevel.removeBlock(pPos,false);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        var stack = this.asItem().getDefaultInstance();
        level.addFreshEntity(new ItemEntity(level,pos.getX(),pos.getY(),pos.getZ(),stack));
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

}
