package com.reddit.user.koppeh.flamingo.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class FlamingoModel extends EntityModel {

	ModelPart beak;
	ModelPart beakBase;
	ModelPart head;
	ModelPart neck;
	ModelPart neckBase;
	ModelPart bodyWingsMain;
	ModelPart bodyWingsSecond;
	ModelPart bodyMain;
	ModelPart bodyTail;
	ModelPart legRight;
	ModelPart legLeft1;
	ModelPart legLeft2;
	ModelPart legLeft3;

	public FlamingoModel() {
		beak = new ModelPart(this, 0, 3).setTextureSize(64, 64);
		beak.addCuboid(-0.5F, 0F, 0F, 1, 2, 1);
		beak.setPivot(0F, 8F, -5F);
		beak.setTextureSize(64, 64);

		beakBase = new ModelPart(this, 4, 3).setTextureSize(64, 64);
		beakBase.addCuboid(-0.5F, 0F, 0F, 1, 1, 1);
		beakBase.setPivot(0F, 8F, -4F);
		beakBase.setTextureSize(64, 64);

		head = new ModelPart(this, 8, 0).setTextureSize(64, 64);
		head.addCuboid(-1F, 0F, 0F, 2, 2, 3);
		head.setPivot(0F, 7F, -3F);
		head.setTextureSize(64, 64);

		neck = new ModelPart(this, 10, 5).setTextureSize(64, 64);
		neck.addCuboid(-1F, 0F, 0F, 2, 3, 2);
		neck.setPivot(0F, 9F, -2F);
		neck.setTextureSize(64, 64);

		neckBase = new ModelPart(this, 6, 10).setTextureSize(64, 64);
		neckBase.addCuboid(-1F, 0F, 0F, 2, 5, 2);
		neckBase.setPivot(0F, 11F, -3F);
		neckBase.setTextureSize(64, 64);

		bodyMain = new ModelPart(this, 26, 17).setTextureSize(64, 64);
		bodyMain.addCuboid(-2F, 0F, 0F, 4, 4, 6);
		bodyMain.setPivot(0F, 14F, -2F);
		bodyMain.setTextureSize(64, 64);

		bodyWingsMain = new ModelPart(this, 6, 17).setTextureSize(64, 64);
		bodyWingsMain.addCuboid(-3F, 0F, 0F, 6, 3, 4);
		bodyWingsMain.setPivot(0F, 14F, -2F);
		bodyWingsMain.setTextureSize(64, 64);

		bodyWingsSecond = new ModelPart(this, 12, 24).setTextureSize(64, 64);
		bodyWingsSecond.addCuboid(-3F, 0F, 0F, 6, 1, 1);
		bodyWingsSecond.setPivot(0F, 16F, 2F);
		bodyWingsSecond.setTextureSize(64, 64);

		bodyTail = new ModelPart(this, 46, 17).setTextureSize(64, 64);
		bodyTail.addCuboid(-2F, 0F, 0F, 4, 1, 2);
		bodyTail.setPivot(0F, 14F, 4F);
		bodyTail.setTextureSize(64, 64);

		legRight = new ModelPart(this, 24, 27).setTextureSize(64, 64);
		legRight.addCuboid(-1F, 0F, 0F, 1, 6, 1);
		legRight.setPivot(0F, 18F, 1F);
		legRight.setTextureSize(64, 64);

		legLeft1 = new ModelPart(this, 20, 27).setTextureSize(64, 64);
		legLeft1.addCuboid(0F, 0F, 0F, 1, 1, 1);
		legLeft1.setPivot(0F, 18F, 0F);
		legLeft1.setTextureSize(64, 64);

		legLeft2 = new ModelPart(this, 16, 29).setTextureSize(64, 64);
		legLeft2.addCuboid(0F, 0F, 0F, 1, 1, 1);
		legLeft2.setPivot(0F, 19F, -1F);
		legLeft2.setTextureSize(64, 64);

		legLeft3 = new ModelPart(this, 12, 31).setTextureSize(64, 64);
		legLeft3.addCuboid(0F, 0F, 0F, 1, 1, 5);
		legLeft3.setPivot(0F, 20F, -2F);
		legLeft3.setTextureSize(64, 64);

	}

	@Override
	public void setAngles(Entity entity, float limbAngle, float limbDistance, float age, float headYaw, float headPitch) {
		
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer vertexConsumer, int i, int j, float r, float g, float b, float f) {
		beak.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		beakBase.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		head.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		neck.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		neckBase.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		bodyWingsMain.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		bodyWingsSecond.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		bodyMain.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		bodyTail.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		legRight.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		legLeft1.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		legLeft2.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		legLeft3.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
	}
}
