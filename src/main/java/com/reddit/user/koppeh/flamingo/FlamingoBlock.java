package com.reddit.user.koppeh.flamingo;

import net.fabricmc.fabric.block.BreakInteractable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.RenderTypeBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntegerProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Facing;
import net.minecraft.util.shape.VoxelShapeContainer;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FlamingoBlock extends Block implements BlockEntityProvider, BreakInteractable {

	public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 15);
	private static final VoxelShapeContainer BOUNDING_SHAPE = Block.createCubeShape(3, 0, 3, 13, 1, 13);

	public FlamingoBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShapeContainer getBoundingShape(BlockState var1, BlockView var2, BlockPos var3) {
		return BOUNDING_SHAPE;
	}

	@Override
	public RenderTypeBlock getRenderType(BlockState var1) {
		return RenderTypeBlock.ENTITYBLOCK_ANIMATED;
	}

	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.with(ROTATION);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView var1) {
		return new FlamingoBlockEntity();
	}

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity player, ItemStack stack) {
		int rotation = ((Math.round((((player.yaw + 180) % 360) * 16 / 360)) % 16) + 16) % 16;
		world.setBlockState(pos, state.with(ROTATION, rotation), 3);
	}

	@Override
	public boolean onBreakInteract(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, Facing facing) {
		BlockEntity blockEntity = world.getBlockEntity(blockPos);
		if (blockEntity instanceof FlamingoBlockEntity) {
			Flamingo.sendWiggle((FlamingoBlockEntity) blockEntity);
		}
		return false;
	}
}
