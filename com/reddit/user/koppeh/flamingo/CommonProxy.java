package com.reddit.user.koppeh.flamingo;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void init() {
		registerTileEntities();
	}

	protected void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityFlamingo.class, "flamingo.flamingo");
	}

}
