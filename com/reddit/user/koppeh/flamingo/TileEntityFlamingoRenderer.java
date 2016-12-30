package com.reddit.user.koppeh.flamingo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class TileEntityFlamingoRenderer extends TileEntitySpecialRenderer {

	private ModelFlamingo model = new ModelFlamingo();

	public void renderTileEntityAt(TileEntityFlamingo flamingo, double x, double y, double z, float par8) {

		int rotation = 0;
		if(flamingo.getWorldObj() != null) {
			rotation = flamingo.getBlockMetadata() * 360 / 16;
		}

		float wiggle = (float) Math.sin(flamingo.wiggle + par8) * flamingo.wiggleStrength;

		ForgeHooksClient.bindTexture("/com/reddit/user/koppeh/flamingo/flamingo.png", 0);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
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
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float par8) {
		renderTileEntityAt((TileEntityFlamingo) entity, x, y, z, par8);
	}

}
