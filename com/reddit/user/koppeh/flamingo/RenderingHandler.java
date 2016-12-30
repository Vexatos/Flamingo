package com.reddit.user.koppeh.flamingo;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class RenderingHandler implements ISimpleBlockRenderingHandler {

	private TileEntity tileEntity;
	private TileEntitySpecialRenderer tileEntityRenderer;

	public int renderId;
	public boolean render3dInInventory;

	public RenderingHandler(Class<? extends TileEntity> tileEntityClass,
		TileEntitySpecialRenderer tileEntityRenderer,
		boolean render3dInInventory) {
		try {
			tileEntity = tileEntityClass.newInstance();
		} catch(Exception e) {
			//NO-OP
		}
		this.tileEntityRenderer = tileEntityRenderer;
		this.render3dInInventory = render3dInInventory;

		tileEntityRenderer.setTileEntityRenderer(TileEntityRenderer.instance);
		renderId = RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		tileEntity.blockType = block;
		tileEntity.blockMetadata = metadata;
		GL11.glPushMatrix();
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		tileEntityRenderer.renderTileEntityAt(tileEntity, -0.5, -0.55, -0.5, 0);
		GL11.glPopMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return render3dInInventory;
	}

	@Override
	public int getRenderId() {
		return renderId;
	}

}
