package com.reddit.user.koppeh.flamingo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Method;

public class BlockFlamingo extends BlockContainer {
	public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);
	private static final Method GET_ARROW_STACK = ReflectionHelper.findMethod(EntityArrow.class, "getArrowStack", "func_184550_j");
	private static final AxisAlignedBB FLAMINGO_AABB = new AxisAlignedBB(3 / 16.0F, 0, 3 / 16.0F, 13 / 16.0F, 1, 13 / 16.0F);

	public BlockFlamingo() {
		super(Material.CLOTH);
		setHardness(1.5f);
		setSoundType(SoundType.CLOTH);
		setUnlocalizedName("flamingo.flamingo");
		setRegistryName("flamingo.flamingo");
		setCreativeTab(CreativeTabs.DECORATIONS);
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return face == EnumFacing.DOWN ? BlockFaceShape.CENTER_SMALL : BlockFaceShape.UNDEFINED;
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FLAMINGO_AABB;
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(ROTATION);
	}

	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(ROTATION, meta);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, ROTATION);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TileEntityFlamingo();
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack) {
		int metadata = ((Math.round((((player.rotationYawHead + 180) % 360) * 16 / 360)) % 16) + 16) % 16;
		world.setBlockState(pos, state.withProperty(ROTATION, metadata), 3);
	}

	protected void wiggle(World world, BlockPos pos) {
		if(world.isRemote) {
			return;
		}
		TileEntity entity = world.getTileEntity(pos);
		if(!(entity instanceof TileEntityFlamingo)) {
			return;
		}
		world.addBlockEvent(pos, this, 0, 0);
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		wiggle(world, pos);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof IProjectile && !(entityIn instanceof EntityThrowable) && !entityIn.isDead) {
			wiggle(worldIn, pos);
			if (entityIn instanceof EntityArrow && !worldIn.isRemote) {
				try {
					if (((EntityArrow) entityIn).pickupStatus == EntityArrow.PickupStatus.ALLOWED) {
						entityIn.entityDropItem((ItemStack) GET_ARROW_STACK.invoke(entityIn), 0.1F);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				entityIn.setDead();
			}
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (fromPos.equals(pos.up()) && !worldIn.isRemote) {
			boolean set = false;
			IBlockState fromState = worldIn.getBlockState(fromPos);
			if (worldIn.getTileEntity(fromPos) == null) {
				for (EntityFallingBlock entity : worldIn.getEntitiesWithinAABB(EntityFallingBlock.class, new AxisAlignedBB(fromPos))) {
					if (fromState == entity.getBlock()) {
						set = true;
					}
				}

				if (set) {
					wiggle(worldIn, pos);
					fromState.getBlock().dropBlockAsItem(worldIn, fromPos, fromState, 0);
					worldIn.setBlockToAir(fromPos);
				}
			}
		}
	}
}
