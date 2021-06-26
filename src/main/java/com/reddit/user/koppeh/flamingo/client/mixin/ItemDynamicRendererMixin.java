package com.reddit.user.koppeh.flamingo.client.mixin;

import com.reddit.user.koppeh.flamingo.Flamingo;
import com.reddit.user.koppeh.flamingo.FlamingoBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltinModelItemRenderer.class)
public class ItemDynamicRendererMixin {
	private final MinecraftClient mc = MinecraftClient.getInstance();
	private final FlamingoBlockEntity flamingoRender = new FlamingoBlockEntity(BlockPos.ORIGIN, Flamingo.FLAMINGO_BLOCK.getDefaultState());

	@Inject(at = @At("HEAD"), method = "render", cancellable = true)
	public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider, int light, int overlay, CallbackInfo info) {
		if (Registry.ITEM.getId(stack.getItem()).equals(new Identifier(Flamingo.MOD_ID, "flamingo"))) {
			mc.getBlockEntityRenderDispatcher().renderEntity(this.flamingoRender, matrix, vertexConsumerProvider, light, overlay);
			info.cancel();
		}
	}
}
