package com.hakimen.kawaiidishes.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MilkshakeCupBlock extends Block  {
    private static final MapCodec<MilkshakeCupBlock> CODEC = simpleCodec(props -> new MilkshakeCupBlock());

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    public MilkshakeCupBlock(){
        super(Properties.ofFullCopy(Blocks.WHITE_WOOL)
                .sound(SoundType.STONE)
                .strength(1,1)
                .isSuffocating((p_61036_, p_61037_, p_61038_) -> false));
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(5.0D, 0D, 5.0D, 11.0D, 13.0D, 11.0D);
    }

}
