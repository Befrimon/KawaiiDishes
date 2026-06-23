package com.hakimen.kawaiidishes.blocks;

import com.hakimen.kawaiidishes.blocks.block_entities.PlaceableFoodBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class IceCreamBlock extends Block implements EntityBlock {
    private static final MapCodec<IceCreamBlock> CODEC = simpleCodec(props -> new IceCreamBlock());

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    public IceCreamBlock(){
        super(Properties.ofFullCopy(Blocks.WHITE_WOOL)
                .sound(SoundType.GLASS)
                .strength(1,1)
                .isSuffocating((p_61036_, p_61037_, p_61038_) -> false));
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.join(
                Shapes.join(
                        Block.box(5.0D, 3.0D, 5.0D, 11.0D, 13.0D, 11.0D),
                        Block.box(5.0D, 0.0D, 5.0D, 11.0D, 1.0D, 11.0D),
                        BooleanOp.OR),
                Block.box(7d,1d,7d,9d,3d,9d), BooleanOp.OR);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        if(pPlayer.isCrouching()){
            var stack = this.asItem().getDefaultInstance();
            if(pLevel.getBlockEntity(pPos) instanceof PlaceableFoodBlockEntity entity){
                CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> {
                    if(!entity.mainEffect.equals(new net.minecraft.nbt.CompoundTag())){
                        tag.put("mainEffect",entity.mainEffect);
                    }
                    if(!entity.secondaryEffect.equals(new net.minecraft.nbt.CompoundTag())){
                        tag.put("secondaryEffect",entity.secondaryEffect);
                    }
                });
            }
            pLevel.removeBlock(pPos,false);
            pPlayer.addItem(stack);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        pLevel.getBlockEntity(pPos, BlockEntityRegister.placeableFood.get()).ifPresent((a) -> {
            CustomData customData = pStack.get(DataComponents.CUSTOM_DATA);
            if (customData != null) {
                a.loadCustomOnly(customData.copyTag(), pLevel.registryAccess());
            }
        });
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        var stack = this.asItem().getDefaultInstance();
        if(level.getBlockEntity(pos) instanceof PlaceableFoodBlockEntity entity){
            CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> {
                if(!entity.mainEffect.equals(new net.minecraft.nbt.CompoundTag())){
                    tag.put("mainEffect",entity.mainEffect);
                }
                if(!entity.secondaryEffect.equals(new net.minecraft.nbt.CompoundTag())){
                    tag.put("secondaryEffect",entity.secondaryEffect);
                }
            });
        }
        level.addFreshEntity(new ItemEntity(level,pos.getX(),pos.getY(),pos.getZ(),stack));
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PlaceableFoodBlockEntity(pPos,pState);
    }
}
