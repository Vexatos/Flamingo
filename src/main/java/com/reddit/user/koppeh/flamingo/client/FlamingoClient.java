package com.reddit.user.koppeh.flamingo.client;

import com.reddit.user.koppeh.flamingo.FlamingoBlockEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.client.render.BlockEntityRendererRegistry;

public class FlamingoClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRendererRegistry.INSTANCE.register(FlamingoBlockEntity.class, new FlamingoBlockEntityRenderer());
		System.out.println("hi");
	}
}
