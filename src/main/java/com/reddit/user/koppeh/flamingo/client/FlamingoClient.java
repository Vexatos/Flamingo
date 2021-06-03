package com.reddit.user.koppeh.flamingo.client;

import com.reddit.user.koppeh.flamingo.Flamingo;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;

public class FlamingoClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(Flamingo.FLAMINGO_BLOCK_ENTITY, FlamingoBlockEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(FlamingoBlockEntityRenderer.flamingoLayer, FlamingoModel::getModel);
    }
}
