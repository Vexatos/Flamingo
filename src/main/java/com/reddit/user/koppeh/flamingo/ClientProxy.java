package com.reddit.user.koppeh.flamingo;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	public static int flamingoRenderId;

	@Override
	protected void registerTileEntities() {
		super.registerTileEntities();
		flamingoRenderId = registerTileEntityRenderer(TileEntityFlamingo.class, new TileEntityFlamingoRenderer());
	}

	private int registerTileEntityRenderer(Class<? extends TileEntity> tileEntityClass,
		TileEntitySpecialRenderer renderer,
		boolean render3dInInventory) {
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, renderer);
		RenderingHandler renderingHandler =
			new RenderingHandler(tileEntityClass, renderer, render3dInInventory);
		RenderingRegistry.registerBlockHandler(renderingHandler);
		return renderingHandler.getRenderId();
	}

	private int registerTileEntityRenderer(Class<? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer renderer) {
		return registerTileEntityRenderer(tileEntityClass, renderer, true);
	}

}
