package com.reddit.user.koppeh.flamingo;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.helpers.FabricBlockBuilder;
import net.fabricmc.fabric.networking.CustomPayloadHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.block.BlockItem;
import net.minecraft.server.network.packet.CustomPayloadServerPacket;
import net.minecraft.sortme.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

public class Flamingo implements ModInitializer {

	public static final String MOD_ID = "flamingo";

	public static final Block FLAMINGO_BLOCK;
	public static final BlockEntityType<FlamingoBlockEntity> FLAMINGO_BLOCK_ENTITY;

	public static final Identifier WIGGLE = new Identifier(Flamingo.MOD_ID, "wiggle");

	static {
		FLAMINGO_BLOCK = register("flamingo", new FlamingoBlock(FabricBlockBuilder.create(Material.WOOL).setMapColor(MaterialColor.PINK).setHardness(1.5F).setSoundGroup(BlockSoundGroup.WOOL).build()), ItemGroup.DECORATIONS);
		FLAMINGO_BLOCK_ENTITY = register("flamingo", BlockEntityType.Builder.create(FlamingoBlockEntity::new));
	}

	public static Block register(String name, Block block, ItemGroup tab) {
		Registry.register(Registry.BLOCKS, MOD_ID + ":" + name, block);
		BlockItem item = new BlockItem(block, new Item.Builder().itemGroup(tab));
		item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
		register(name, item);
		return block;
	}

	public static Item register(String name, Item item) {
		Registry.register(Registry.ITEMS, MOD_ID + ":" + name, item);
		return item;
	}

	public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> builder) {
		BlockEntityType<T> blockEntityType = builder.method_11034(null);
		Registry.register(Registry.BLOCK_ENTITIES, MOD_ID + ":" + name, blockEntityType);
		return blockEntityType;
	}

	@Override
	public void onInitialize() {
		BlockEntityRendererRegistry.INSTANCE.register(FlamingoBlockEntity.class, new FlamingoBlockEntityRenderer());
		CustomPayloadHandlerRegistry.CLIENT.register(WIGGLE, (packetContext, packetByteBuf) -> {
			BlockPos pos = packetByteBuf.readBlockPos();
			if (packetContext.getPlayer() != null && packetContext.getPlayer().getEntityWorld() != null) {
				BlockEntity blockEntity = packetContext.getPlayer().getEntityWorld().getBlockEntity(pos);
				if (blockEntity instanceof FlamingoBlockEntity) {
					((FlamingoBlockEntity) blockEntity).wiggle();
				}
			}
		});
	}

	@Environment(EnvType.CLIENT)
	public static void sendWiggle(FlamingoBlockEntity flamingo) {
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		buf.writeBlockPos(flamingo.getPos());
		MinecraftClient.getInstance().getNetworkHandler().getClientConnection().sendPacket(new CustomPayloadServerPacket(WIGGLE, buf));
	}
}
