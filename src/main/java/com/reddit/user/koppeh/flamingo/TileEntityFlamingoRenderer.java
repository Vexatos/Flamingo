package com.reddit.user.koppeh.flamingo;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityFlamingoRenderer extends TileEntitySpecialRenderer {

	private ModelFlamingo model = new ModelFlamingo();
	private ResourceLocation resource = new ResourceLocation("flamingo", "textures/models/flamingo.png");

	public void renderTileEntityAt(TileEntityFlamingo flamingo, double x, double y, double z, float par8) {
		int rotation = 0;
		float wiggle = 0;

		bindTexture(resource);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		if (flamingo != null) {
			if (flamingo.getWorld() != null) {
				rotation = flamingo.getBlockMetadata() * 360 / 16;
			}

			wiggle = (float) Math.sin(flamingo.wiggle + par8) * flamingo.wiggleStrength;
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslated(x, y + 2.0, z + 1.0);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslated(0.5F, 0.5F, 0.5F);
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		GL11.glTranslated(0.0, 1.5, 0.0);
		GL11.glRotatef(wiggle, 0.0F, 0.0F, 1.0F);
		GL11.glTranslated(0.0, -1.5, 0.0);

		model.renderAll();

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);

		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

	}

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float par8, int destroyStage) {
		renderTileEntityAt((TileEntityFlamingo) entity, x, y, z, par8);
	}

}
