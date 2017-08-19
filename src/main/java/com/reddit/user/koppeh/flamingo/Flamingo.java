package com.reddit.user.koppeh.flamingo;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.ThrowableImpactEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = "flamingo", version = "1.12-1.11", useMetadata = true, dependencies = "required-after:forge@[14.21.1.2395,)")
public class Flamingo {

	@Instance("Flamingo")
	public static Flamingo instance;

	@SidedProxy(clientSide = "com.reddit.user.koppeh.flamingo.ClientProxy",
		serverSide = "com.reddit.user.koppeh.flamingo.CommonProxy")
	public static CommonProxy proxy;

	public static BlockFlamingo flamingo;
	public static Item itemFlamingo;

	@SubscribeEvent
	public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(flamingo);
	}

	@SubscribeEvent
	public void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(itemFlamingo);
	}

	@SubscribeEvent
	public void onThrowableImpact(ThrowableImpactEvent event) {
		RayTraceResult result = event.getRayTraceResult();
		if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
			IBlockState state = event.getEntityThrowable().getEntityWorld().getBlockState(result.getBlockPos());
			if (state.getBlock() instanceof BlockFlamingo) {
				((BlockFlamingo) state.getBlock()).wiggle(event.getEntityThrowable().getEntityWorld(), result.getBlockPos());
			}
		}
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		flamingo = new BlockFlamingo();
		itemFlamingo = new ItemBlock(flamingo).setRegistryName(flamingo.getRegistryName());

		GameRegistry.registerTileEntity(TileEntityFlamingo.class, "flamingo.flamingo");

		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(proxy);
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
	}
}
