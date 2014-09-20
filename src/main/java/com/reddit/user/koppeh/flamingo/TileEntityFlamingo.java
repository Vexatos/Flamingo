package com.reddit.user.koppeh.flamingo;

import net.minecraft.tileentity.TileEntity;

public class TileEntityFlamingo extends TileEntity {

	public float wiggle = 0;
	public float wiggleStrength = 0.0F;

	@Override
	public void updateEntity() {
		wiggle++;
		wiggleStrength = Math.max(0.0F, wiggleStrength * 0.9F - 0.1F);
	}

	@Override
	public boolean receiveClientEvent(int par1, int par2) {
		wiggleStrength = Math.min(40.0F, wiggleStrength + 15.0F);
		return true;
	}

}
