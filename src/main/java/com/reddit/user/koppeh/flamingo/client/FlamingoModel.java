package com.reddit.user.koppeh.flamingo.client;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

public class FlamingoModel extends EntityModel {

	Cuboid beak;
	Cuboid beakBase;
	Cuboid head;
	Cuboid neck;
	Cuboid neckBase;
	Cuboid bodyWingsMain;
	Cuboid bodyWingsSecond;
	Cuboid bodyMain;
	Cuboid bodyTail;
	Cuboid legRight;
	Cuboid legLeft1;
	Cuboid legLeft2;
	Cuboid legLeft3;

	public FlamingoModel() {
		beak = new Cuboid(this, 0, 3).setTextureSize(64, 64);
		beak.addBox(-0.5F, 0F, 0F, 1, 2, 1);
		beak.setRotationPoint(0F, 8F, -5F);
		beak.setTextureSize(64, 64);

		beakBase = new Cuboid(this, 4, 3).setTextureSize(64, 64);
		beakBase.addBox(-0.5F, 0F, 0F, 1, 1, 1);
		beakBase.setRotationPoint(0F, 8F, -4F);
		beakBase.setTextureSize(64, 64);

		head = new Cuboid(this, 8, 0).setTextureSize(64, 64);
		head.addBox(-1F, 0F, 0F, 2, 2, 3);
		head.setRotationPoint(0F, 7F, -3F);
		head.setTextureSize(64, 64);

		neck = new Cuboid(this, 10, 5).setTextureSize(64, 64);
		neck.addBox(-1F, 0F, 0F, 2, 3, 2);
		neck.setRotationPoint(0F, 9F, -2F);
		neck.setTextureSize(64, 64);

		neckBase = new Cuboid(this, 6, 10).setTextureSize(64, 64);
		neckBase.addBox(-1F, 0F, 0F, 2, 5, 2);
		neckBase.setRotationPoint(0F, 11F, -3F);
		neckBase.setTextureSize(64, 64);

		bodyMain = new Cuboid(this, 26, 17).setTextureSize(64, 64);
		bodyMain.addBox(-2F, 0F, 0F, 4, 4, 6);
		bodyMain.setRotationPoint(0F, 14F, -2F);
		bodyMain.setTextureSize(64, 64);

		bodyWingsMain = new Cuboid(this, 6, 17).setTextureSize(64, 64);
		bodyWingsMain.addBox(-3F, 0F, 0F, 6, 3, 4);
		bodyWingsMain.setRotationPoint(0F, 14F, -2F);
		bodyWingsMain.setTextureSize(64, 64);

		bodyWingsSecond = new Cuboid(this, 12, 24).setTextureSize(64, 64);
		bodyWingsSecond.addBox(-3F, 0F, 0F, 6, 1, 1);
		bodyWingsSecond.setRotationPoint(0F, 16F, 2F);
		bodyWingsSecond.setTextureSize(64, 64);

		bodyTail = new Cuboid(this, 46, 17).setTextureSize(64, 64);
		bodyTail.addBox(-2F, 0F, 0F, 4, 1, 2);
		bodyTail.setRotationPoint(0F, 14F, 4F);
		bodyTail.setTextureSize(64, 64);

		legRight = new Cuboid(this, 24, 27).setTextureSize(64, 64);
		legRight.addBox(-1F, 0F, 0F, 1, 6, 1);
		legRight.setRotationPoint(0F, 18F, 1F);
		legRight.setTextureSize(64, 64);

		legLeft1 = new Cuboid(this, 20, 27).setTextureSize(64, 64);
		legLeft1.addBox(0F, 0F, 0F, 1, 1, 1);
		legLeft1.setRotationPoint(0F, 18F, 0F);
		legLeft1.setTextureSize(64, 64);

		legLeft2 = new Cuboid(this, 16, 29).setTextureSize(64, 64);
		legLeft2.addBox(0F, 0F, 0F, 1, 1, 1);
		legLeft2.setRotationPoint(0F, 19F, -1F);
		legLeft2.setTextureSize(64, 64);

		legLeft3 = new Cuboid(this, 12, 31).setTextureSize(64, 64);
		legLeft3.addBox(0F, 0F, 0F, 1, 1, 5);
		legLeft3.setRotationPoint(0F, 20F, -2F);
		legLeft3.setTextureSize(64, 64);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);

		//		setRotationAngles(f, f1, f2, f3, f4, f5, null);
		beak.render(f5);
		beakBase.render(f5);
		head.render(f5);
		neck.render(f5);
		neckBase.render(f5);
		bodyWingsMain.render(f5);
		bodyWingsSecond.render(f5);
		bodyMain.render(f5);
		bodyTail.render(f5);
		legRight.render(f5);
		legLeft1.render(f5);
		legLeft2.render(f5);
		legLeft3.render(f5);
	}

	public void renderAll() {
		render(null, 0, 0, 0, 0, 0, 1 / 16.0F);
	}

}
