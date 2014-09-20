package com.reddit.user.koppeh.flamingo;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

@Mod(modid = "Flamingo", version = "1.7.10-1.3", useMetadata = true)
public class Flamingo {

	@Instance("Flamingo")
	public static Flamingo instance;

	@SidedProxy(clientSide = "com.reddit.user.koppeh.flamingo.ClientProxy",
		serverSide = "com.reddit.user.koppeh.flamingo.CommonProxy")
	public static CommonProxy proxy;

	public static BlockFlamingo flamingo;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		initializeItems();
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
		addRecipes();
		proxy.init();
	}

	private void initializeItems() {
		flamingo = new BlockFlamingo();
	}

	private void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(flamingo),
			"  o",
			"ooo",
			" / ",

			'o', new ItemStack(Blocks.wool, 1, 6),
			'/', Items.stick);
		GameRegistry.addRecipe(new ItemStack(flamingo),
			"o  ",
			"ooo",
			" / ",

			'o', new ItemStack(Blocks.wool, 1, 6),
			'/', Items.stick);
	}

}
