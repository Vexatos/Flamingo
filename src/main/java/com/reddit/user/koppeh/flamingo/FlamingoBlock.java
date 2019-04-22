package com.reddit.user.koppeh.flamingo;

import net.fabricmc.fabric.api.block.BlockAttackInteractionAware;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntegerProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FlamingoBlock extends Block implements BlockEntityProvider, BlockAttackInteractionAware {

	public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 15);
	private static final VoxelShape OUTLINE_SHAPE = Block.createCuboidShape(3, 0, 3, 13, 17, 13);

	public FlamingoBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1) {
		return OUTLINE_SHAPE;
	}

	@Override
	public BlockRenderType getRenderType(BlockState var1) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
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
	public boolean onAttackInteraction(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, Direction direction) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (!world.isClient && blockEntity instanceof FlamingoBlockEntity) {
			world.addBlockAction(pos, this, 0, 0);
		}
		return false;
	}

	@Override
	public boolean onBlockAction(BlockState state, World world, BlockPos pos, int id, int value) {
		if (world.getBlockEntity(pos) != null) {
			((FlamingoBlockEntity) world.getBlockEntity(pos)).wiggle();
			return true;
		}
		return false;
	}
}
