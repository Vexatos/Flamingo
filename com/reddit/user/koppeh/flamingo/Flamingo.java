package com.reddit.user.koppeh.flamingo;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

@Mod(modid = "Flamingo", version = "1.7.10-1.3", useMetadata = true)
public class Flamingo {

	@Instance("Flamingo")
	public static Flamingo instance;

	@SidedProxy(clientSide = "com.reddit.user.koppeh.flamingo.ClientProxy",
		serverSide = "com.reddit.user.koppeh.flamingo.CommonProxy")
	public static CommonProxy proxy;

	public static Configuration config;
	public static BlockFlamingo flamingo;

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		flamingo = new BlockFlamingo(config.getBlock("flamingo", "flamingo", 3755).getInt());

		config.save();
	}

	@Mod.Init
	public void load(FMLInitializationEvent event) {
		addRecipes();
		proxy.init();
	}

	private void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(flamingo),
			"  o",
			"ooo",
			" / ",

			'o', new ItemStack(Block.cloth, 1, 6),
			'/', Item.stick);
		GameRegistry.addRecipe(new ItemStack(flamingo),
			"o  ",
			"ooo",
			" / ",

			'o', new ItemStack(Block.cloth, 1, 6),
			'/', Item.stick);
	}

}
