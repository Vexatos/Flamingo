package com.reddit.user.koppeh.flamingo;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@SuppressWarnings("deprecation")
	@Override
	protected void registerItemModels() {
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(Flamingo.flamingo), 0, TileEntityFlamingo.class);
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Flamingo.flamingo), 0, new ModelResourceLocation("flamingo:flamingo.flamingo", "inventory"));
	}

	@Override
	protected void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFlamingo.class, new TileEntityFlamingoRenderer());
	}
}
