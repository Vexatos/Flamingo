package com.reddit.user.koppeh.flamingo;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFlamingo extends BlockContainer {

	public BlockFlamingo() {
		super(Material.cloth);
		setHardness(1.5f);
		setStepSound(Block.soundTypeCloth);
		setBlockBounds(3 / 16.0F, 0, 3 / 16.0F, 13 / 16.0F, 1, 13 / 16.0F);
		setBlockName("flamingo.flamingo");
		GameRegistry.registerBlock(this, "flamingo.flamingo");
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("wool_colored_pink");
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
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TileEntityFlamingo();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		int metadata = Math.round((player.rotationYawHead + 180) * 16 / 360);
		world.setBlockMetadataWithNotify(x, y, z, metadata, 3);
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if(world.isRemote) {
			return;
		}
		TileEntity entity = world.getTileEntity(x, y, z);
		if(!(entity instanceof TileEntityFlamingo)) {
			return;
		}
		world.addBlockEvent(x, y, z, this, 0, 0);
	}
}
