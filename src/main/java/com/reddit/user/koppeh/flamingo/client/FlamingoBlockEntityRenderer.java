package com.reddit.user.koppeh.flamingo.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.reddit.user.koppeh.flamingo.FlamingoBlock;
import com.reddit.user.koppeh.flamingo.FlamingoBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;
import org.lwjgl.opengl.GL11;

public class FlamingoBlockEntityRenderer extends BlockEntityRenderer<FlamingoBlockEntity> {

	private FlamingoModel model = new FlamingoModel();
	private Identifier resource = new Identifier("flamingo", "textures/model/flamingo.png");

	@Override
	public void render(FlamingoBlockEntity flamingo, double x, double y, double z, float partialTicks, int destroyStage) {
		int rotation = 0;
		float wiggle = 0;

		GlStateManager.enableDepthTest();
		GlStateManager.depthFunc(GL11.GL_LEQUAL);
		GlStateManager.depthMask(true);

		bindTexture(resource);

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();

		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		if (flamingo != null) {
			final BlockView world = flamingo.getWorld();
			if (world != null) {
				rotation = world.getBlockState(flamingo.getPos()).get(FlamingoBlock.ROTATION) * 360 / 16;
			}

			wiggle = (float) Math.sin(flamingo.wiggle + partialTicks) * flamingo.wiggleStrength;
		}

		GlStateManager.translated(x, y, z);
		GlStateManager.rotated(1.0F, 1.0F, -1.0F, -1.0F);
		GlStateManager.translated(0.5F, 0F, 0.5F);
		GlStateManager.rotated(rotation, 0.0F, -1.0F, 0.0F);
		GlStateManager.rotated(1, 1F, 0, 0.0F);
		GlStateManager.translated(0.0, 1.5, 0.0);
		GlStateManager.rotated(180, 1.0F, 0.0F, 0.0F);
		GlStateManager.translated(0.0, 1.5, 0.0);
		GlStateManager.rotated(wiggle, 0.0F, 0.0F, 1.0F);
		GlStateManager.translated(0.0, -1.5, 0.0);

		model.renderAll();

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
