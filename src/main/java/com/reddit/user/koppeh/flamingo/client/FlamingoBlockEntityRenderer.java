package com.reddit.user.koppeh.flamingo.client;

import com.reddit.user.koppeh.flamingo.Flamingo;
import com.reddit.user.koppeh.flamingo.FlamingoBlock;
import com.reddit.user.koppeh.flamingo.FlamingoBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class FlamingoBlockEntityRenderer implements BlockEntityRenderer<FlamingoBlockEntity> {

	public static final EntityModelLayer flamingoLayer = new EntityModelLayer(new Identifier(Flamingo.MOD_ID, "flamingo"), "flamingo");
	private final Identifier resource = new Identifier("flamingo", "textures/model/flamingo.png");
	private final ModelPart model;
	private static final FlamingoBlockEntity flamingoRender = new FlamingoBlockEntity(BlockPos.ORIGIN, Flamingo.FLAMINGO_BLOCK.getDefaultState());

	public FlamingoBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
		this.model = ctx.getLayerModelPart(flamingoLayer);
	}

	@Override
	public void render(FlamingoBlockEntity flamingo, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
		int rotation = 0;
		float wiggle = 0;

		if (flamingo != null) {
			final BlockView world = flamingo.getWorld();
			if (world != null) {
				BlockState state = world.getBlockState(flamingo.getPos());
				if (!(state.getBlock() instanceof FlamingoBlock)) return;
				rotation = state.get(FlamingoBlock.ROTATION) * 360 / 16;
			}

			wiggle = (float) Math.sin(flamingo.wiggle + partialTicks) * flamingo.wiggleStrength;
		}

		matrixStack.push();

		matrixStack.translate(0.5F, 0, 0.5F);
		matrixStack.multiply(Vec3f.NEGATIVE_Y.getDegreesQuaternion(rotation));
		matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(1F));
		matrixStack.translate(0.0, 1.5, 0.0);
		matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(180F));
		matrixStack.translate(0.0, 1.5, 0.0);
		matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(wiggle));
		matrixStack.translate(0.0, -1.5, 0.0);

		model.render(matrixStack, vertexConsumerProvider.getBuffer(RenderLayer.getEntitySolid(resource)), i, j, 1F, 1F, 1F, 1F);

		matrixStack.pop();
	}

	public static void renderItem(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrix, VertexConsumerProvider vcp, int light, int overlay) {
		if (Registry.ITEM.getId(stack.getItem()).equals(new Identifier(Flamingo.MOD_ID, "flamingo"))) {
			MinecraftClient.getInstance().getBlockEntityRenderDispatcher().renderEntity(flamingoRender, matrix, vcp, light, overlay);
		}
	}
}
