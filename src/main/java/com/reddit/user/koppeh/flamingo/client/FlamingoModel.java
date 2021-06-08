package com.reddit.user.koppeh.flamingo.client;

import net.minecraft.client.model.*;

public class FlamingoModel {

	public static TexturedModelData getModel() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		addPart(root, "beak", 0, 3, -0.5F, 0F, 0F, 1, 2, 1, 0F, 8F, -5F);
		addPart(root, "beakBase", 4, 3, -0.5F, 0F, 0F, 1, 1, 1, 0F, 8F, -4F);
		addPart(root, "head", 8, 0, -1F, 0F, 0F, 2, 2, 3, 0F, 7F, -3F);
		addPart(root, "neck", 10, 5, -1F, 0F, 0F, 2, 3, 2, 0F, 9F, -2F);
		addPart(root, "neckBase", 6, 10, -1F, 0F, 0F, 2, 5, 2, 0F, 11F, -3F);
		addPart(root, "bodyMain", 26, 17, -2F, 0F, 0F, 4, 4, 6, 0F, 14F, -2F);
		addPart(root, "bodyWingsMain", 6, 17, -3F, 0F, 0F, 6, 3, 4, 0F, 14F, -2F);
		addPart(root, "bodyWingsSecond", 12, 24, -3F, 0F, 0F, 6, 1, 1, 0, 16F, 2F);
		addPart(root, "bodyTail", 46, 17, -2F, 0F, 0F, 4, 1, 2, 0F, 14F, 4F);
		addPart(root, "legRight", 24, 27, -1F, 0F, 0F, 1, 6, 1, 0F, 18F, 1F);
		addPart(root, "legLeft1", 20, 27, 0F, 0F, 0F, 1, 1, 1, 0F, 18F, 0F);
		addPart(root, "legLeft2", 16, 29, 0F, 0F, 0F, 1, 1, 1, 0F, 19F, -1F);
		addPart(root, "legLeft3", 12, 31, 0F, 0F, 0F, 1, 1, 5, 0F, 20F, -2F);

		return TexturedModelData.of(modelData, 64, 64);
	}

	private static void addPart(ModelPartData root, String name, int u, int v, float oX, float oY, float oZ, int sX, int sY, int sZ, float pX, float pY, float pZ) {
		root.addChild(name, ModelPartBuilder.create()
						.uv(u, v)
						.cuboid(oX, oY, oZ, sX, sY, sZ),
				ModelTransform.pivot(pX, pY, pZ));
	}
}