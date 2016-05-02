package com.reddit.user.koppeh.flamingo;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class TileEntityFlamingoRenderer extends TileEntitySpecialRenderer<TileEntityFlamingo> {

	private ModelFlamingo model = new ModelFlamingo();
	private ResourceLocation resource = new ResourceLocation("flamingo", "textures/models/flamingo.png");

	@Override
	public void renderTileEntityAt(TileEntityFlamingo flamingo, double x, double y, double z, float partialTicks, int destroyStage) {
		int rotation = 0;
		float wiggle = 0;

		GlStateManager.enableDepth();
		GlStateManager.depthFunc(GL11.GL_LEQUAL);
		GlStateManager.depthMask(true);

		if (destroyStage >= 0) {
			bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(GL11.GL_TEXTURE);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 4.0F, 1.0F);
			GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		} else {
			bindTexture(resource);
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();

		if (destroyStage < 0) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		}

		if(flamingo != null) {
			if(flamingo.getWorld() != null) {
				rotation = flamingo.getBlockMetadata() * 360 / 16;
			}

			wiggle = (float) Math.sin(flamingo.wiggle + partialTicks) * flamingo.wiggleStrength;
		}

		GlStateManager.translate(x, y + 2.0, z + 1.0);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(0.0, 1.5, 0.0);
		GlStateManager.rotate(wiggle, 0.0F, 0.0F, 1.0F);
		GlStateManager.translate(0.0, -1.5, 0.0);

		model.renderAll();

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		if (destroyStage >= 0) {
			GlStateManager.matrixMode(GL11.GL_TEXTURE);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		}
	}

}
