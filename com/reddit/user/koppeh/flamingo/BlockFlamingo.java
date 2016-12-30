package com.reddit.user.koppeh.flamingo;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFlamingo extends BlockContainer {

	public BlockFlamingo(int id) {
		super(id, Material.cloth);
		setHardness(1.5f);
		setStepSound(Block.soundClothFootstep);
		setBlockBounds(3 / 16.0F, 0, 3 / 16.0F, 13 / 16.0F, 1, 13 / 16.0F);
		setBlockName("flamingo.flamingo");
		GameRegistry.registerBlock(this, "flamingo.flamingo");
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return 130;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.flamingoRenderId;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityFlamingo();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving player) {
		int metadata = Math.round((player.rotationYawHead + 180) * 16 / 360);
		world.setBlockMetadataWithNotify(x, y, z, metadata);
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if(world.isRemote) {
			return;
		}
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(!(entity instanceof TileEntityFlamingo)) {
			return;
		}
		world.addBlockEvent(x, y, z, this.blockID, 0, 0);
	}
}
