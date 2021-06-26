package com.reddit.user.koppeh.flamingo;

import net.fabricmc.fabric.api.block.BlockAttackInteractionAware;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;

public class FlamingoBlock extends Block implements BlockEntityProvider, BlockAttackInteractionAware {

	public static final IntProperty ROTATION = IntProperty.of("rotation", 0, 15);
	private static final VoxelShape OUTLINE_SHAPE = Block.createCuboidShape(3, 0, 3, 13, 17, 13);

	public FlamingoBlock(Settings settings) {
		super(settings);
	}

	@Override
	@Deprecated
	public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, ShapeContext context) {
		return OUTLINE_SHAPE;
	}

	@Override
	@Deprecated
	public BlockRenderType getRenderType(BlockState var1) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(ROTATION);
	}

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity player, ItemStack stack) {
		int rotation = ((Math.round((((player.getYaw() + 180) % 360) * 16 / 360)) % 16) + 16) % 16;
		world.setBlockState(pos, state.with(ROTATION, rotation), 3);
	}

	@Override
	public boolean onAttackInteraction(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, Direction direction) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (!world.isClient && blockEntity instanceof FlamingoBlockEntity) {
			world.addSyncedBlockEvent(pos, this, 0, 0);
		}
		return false;
	}

	@Override
	public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
		if (world.getBlockEntity(pos) != null) {
			((FlamingoBlockEntity) world.getBlockEntity(pos)).wiggle();
			return true;
		}
		return false;
	}

	@Override
	public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new FlamingoBlockEntity(pos, state);
	}

	@Override
	public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return world.isClient() ? FlamingoBlockEntity::tick : null;
	}

	@Override
	public @Nullable <T extends BlockEntity> GameEventListener getGameEventListener(World world, T blockEntity) {
		return null;
	}
}
