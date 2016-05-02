package com.reddit.user.koppeh.flamingo;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityFlamingo extends TileEntity implements ITickable {

	public static final float MAX_WIGGLE_STRENGTH = 40.0F;
	public static final float DEFAULT_WIGGLE_STRENGTH_PER_CLICK = 15.0F;

	float wiggle = 0;
	float wiggleStrength = 0.0F;

	@Override
	public void update() {
		wiggle++;
		wiggleStrength = Math.max(0.0F, wiggleStrength * 0.9F - 0.1F);
	}

	@Override
	public boolean receiveClientEvent(int par1, int par2) {
		wiggle();
		return true;
	}

	public void wiggle() {
		wiggleStrength = Math.max(0.0F, Math.min(MAX_WIGGLE_STRENGTH, wiggleStrength + DEFAULT_WIGGLE_STRENGTH_PER_CLICK));
	}

	public float getWiggleStrength() {
		return this.wiggleStrength;
	}

}
