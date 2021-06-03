package com.reddit.user.koppeh.flamingo;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlamingoBlockEntity extends BlockEntity {

    public static final float MAX_WIGGLE_STRENGTH = 40.0F;
    public static final float DEFAULT_WIGGLE_STRENGTH_PER_CLICK = 15.0F;

    public float wiggle = 0;
    public float wiggleStrength = 0.0F;

    public FlamingoBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public FlamingoBlockEntity(BlockPos pos, BlockState state) {
        this(Flamingo.FLAMINGO_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity be) {
        FlamingoBlockEntity flamingo = (FlamingoBlockEntity) be;
        flamingo.wiggle++;
        flamingo.wiggleStrength = Math.max(0.0F, flamingo.wiggleStrength * 0.9F - 0.1F);
    }

    public void wiggle() {
        wiggleStrength = Math.max(0.0F, Math.min(MAX_WIGGLE_STRENGTH, wiggleStrength + DEFAULT_WIGGLE_STRENGTH_PER_CLICK));
    }

    public float getWiggleStrength() {
        return wiggleStrength;
    }

}
