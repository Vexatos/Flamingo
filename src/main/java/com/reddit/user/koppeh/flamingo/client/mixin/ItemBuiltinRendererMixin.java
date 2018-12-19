package com.reddit.user.koppeh.flamingo.client.mixin;

import com.reddit.user.koppeh.flamingo.Flamingo;
import com.reddit.user.koppeh.flamingo.FlamingoBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.ItemBuiltinRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemBuiltinRenderer.class)
public class ItemBuiltinRendererMixin {
	private final FlamingoBlockEntity flamingoRender = new FlamingoBlockEntity();

	@Inject(at = @At("HEAD"), method = "method_3166", cancellable = true)
	public void method_3166(ItemStack stack, CallbackInfo info) {
		if (Registry.ITEM.getId(stack.getItem()).equals(new Identifier(Flamingo.MOD_ID, "flamingo"))) {
			BlockEntityRenderDispatcher.INSTANCE.renderEntity(this.flamingoRender);
			info.cancel();
		}
	}
}
