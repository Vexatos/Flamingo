package com.reddit.user.koppeh.flamingo;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

		bindTexture(resource);

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

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

		if(flamingo == null || !flamingo.hasCustomName()) {
			return;
		}

		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		if(renderManager == null || renderManager.renderViewEntity == null) {
			return;
		}

		if(renderManager.renderViewEntity.getDistanceSqToCenter(flamingo.getPos()) >= RenderLivingBase.NAME_TAG_RANGE * RenderLivingBase.NAME_TAG_RANGE) {
			return;
		}

		String customName = flamingo.getDisplayName().getFormattedText();

		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5D, y + 1.5D, z + 0.5D);
		GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate((renderManager.options.thirdPersonView == 2 ? -1.0F : 1.0F) * renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GlStateManager.scale(-0.025F, -0.025F, 0.025F);
		GlStateManager.disableLighting();
		GlStateManager.depthMask(false);

		GlStateManager.disableDepth();

		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

		FontRenderer fontrenderer = getFontRenderer();
		int offsetX = fontrenderer.getStringWidth(customName) / 2;

		GlStateManager.disableTexture2D();

		Tessellator t = Tessellator.getInstance();
		VertexBuffer vb = t.getBuffer();
		vb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
		vb.pos(-offsetX - 1.0D, -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
		vb.pos(-offsetX - 1.0D, 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
		vb.pos(offsetX + 1.0D, 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
		vb.pos(offsetX + 1.0D, -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
		t.draw();

		GlStateManager.enableTexture2D();

		fontrenderer.drawString(customName, -offsetX, 0, 553648127);

		GlStateManager.enableDepth();
		GlStateManager.depthMask(true);

		fontrenderer.drawString(customName, -offsetX, 0, -1);

		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();
	}
}

