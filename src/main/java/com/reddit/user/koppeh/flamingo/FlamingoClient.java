package com.reddit.user.koppeh.flamingo;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.client.render.BlockEntityRendererRegistry;

public class FlamingoClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRendererRegistry.INSTANCE.register(FlamingoBlockEntity.class, new FlamingoBlockEntityRenderer());
	}
}
