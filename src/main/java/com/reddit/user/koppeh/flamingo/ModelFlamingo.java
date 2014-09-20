package com.reddit.user.koppeh.flamingo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelFlamingo extends ModelBase {

	ModelRenderer Beak;
	ModelRenderer BeakBase;
	ModelRenderer Head;
	ModelRenderer Neck;
	ModelRenderer NeckBase;
	ModelRenderer BodyWingsMain;
	ModelRenderer BodyWingsSecond;
	ModelRenderer BodyMain;
	ModelRenderer BodyTail;
	ModelRenderer LegRight;
	ModelRenderer LegLeft1;
	ModelRenderer LegLeft2;
	ModelRenderer LegLeft3;

	public ModelFlamingo() {

		textureWidth = 64;
		textureHeight = 64;

		Beak = new ModelRenderer(this, 0, 3);
		Beak.addBox(-0.5F, 0F, 0F, 1, 2, 1);
		Beak.setRotationPoint(0F, 8F, -5F);
		Beak.setTextureSize(64, 64);

		BeakBase = new ModelRenderer(this, 4, 3);
		BeakBase.addBox(-0.5F, 0F, 0F, 1, 1, 1);
		BeakBase.setRotationPoint(0F, 8F, -4F);
		BeakBase.setTextureSize(64, 64);

		Head = new ModelRenderer(this, 8, 0);
		Head.addBox(-1F, 0F, 0F, 2, 2, 3);
		Head.setRotationPoint(0F, 7F, -3F);
		Head.setTextureSize(64, 64);

		Neck = new ModelRenderer(this, 10, 5);
		Neck.addBox(-1F, 0F, 0F, 2, 3, 2);
		Neck.setRotationPoint(0F, 9F, -2F);
		Neck.setTextureSize(64, 64);

		NeckBase = new ModelRenderer(this, 6, 10);
		NeckBase.addBox(-1F, 0F, 0F, 2, 5, 2);
		NeckBase.setRotationPoint(0F, 11F, -3F);
		NeckBase.setTextureSize(64, 64);

		BodyMain = new ModelRenderer(this, 26, 17);
		BodyMain.addBox(-2F, 0F, 0F, 4, 4, 6);
		BodyMain.setRotationPoint(0F, 14F, -2F);
		BodyMain.setTextureSize(64, 64);

		BodyWingsMain = new ModelRenderer(this, 6, 17);
		BodyWingsMain.addBox(-3F, 0F, 0F, 6, 3, 4);
		BodyWingsMain.setRotationPoint(0F, 14F, -2F);
		BodyWingsMain.setTextureSize(64, 64);

		BodyWingsSecond = new ModelRenderer(this, 12, 24);
		BodyWingsSecond.addBox(-3F, 0F, 0F, 6, 1, 1);
		BodyWingsSecond.setRotationPoint(0F, 16F, 2F);
		BodyWingsSecond.setTextureSize(64, 64);

		BodyTail = new ModelRenderer(this, 46, 17);
		BodyTail.addBox(-2F, 0F, 0F, 4, 1, 2);
		BodyTail.setRotationPoint(0F, 14F, 4F);
		BodyTail.setTextureSize(64, 64);

		LegRight = new ModelRenderer(this, 24, 27);
		LegRight.addBox(-1F, 0F, 0F, 1, 6, 1);
		LegRight.setRotationPoint(0F, 18F, 1F);
		LegRight.setTextureSize(64, 64);

		LegLeft1 = new ModelRenderer(this, 20, 27);
		LegLeft1.addBox(0F, 0F, 0F, 1, 1, 1);
		LegLeft1.setRotationPoint(0F, 18F, 0F);
		LegLeft1.setTextureSize(64, 64);

		LegLeft2 = new ModelRenderer(this, 16, 29);
		LegLeft2.addBox(0F, 0F, 0F, 1, 1, 1);
		LegLeft2.setRotationPoint(0F, 19F, -1F);
		LegLeft2.setTextureSize(64, 64);

		LegLeft3 = new ModelRenderer(this, 12, 31);
		LegLeft3.addBox(0F, 0F, 0F, 1, 1, 5);
		LegLeft3.setRotationPoint(0F, 20F, -2F);
		LegLeft3.setTextureSize(64, 64);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, null);
		Beak.render(f5);
		BeakBase.render(f5);
		Head.render(f5);
		Neck.render(f5);
		NeckBase.render(f5);
		BodyWingsMain.render(f5);
		BodyWingsSecond.render(f5);
		BodyMain.render(f5);
		BodyTail.render(f5);
		LegRight.render(f5);
		LegLeft1.render(f5);
		LegLeft2.render(f5);
		LegLeft3.render(f5);
	}

	public void renderAll() {
		render(null, 0, 0, 0, 0, 0, 1 / 16.0F);
	}

}
