package com.reddit.user.koppeh.flamingo;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFlamingo extends BlockContainer {

	public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);
	private static final AxisAlignedBB FLAMINGO_AABB = new AxisAlignedBB(3 / 16.0F, 0, 3 / 16.0F, 13 / 16.0F, 1, 13 / 16.0F);

	public BlockFlamingo() {
		super(Material.CLOTH);
		setHardness(1.5f);
		setSoundType(SoundType.CLOTH);
		setUnlocalizedName("flamingo.flamingo");
		GameRegistry.registerBlock(this, "flamingo.flamingo");
		setCreativeTab(CreativeTabs.DECORATIONS);
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FLAMINGO_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
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

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		if(world.isRemote) {
			return;
		}
		TileEntity entity = world.getTileEntity(pos);
		if(!(entity instanceof TileEntityFlamingo)) {
			return;
		}
		world.addBlockEvent(pos, this, 0, 0);
	}
}
