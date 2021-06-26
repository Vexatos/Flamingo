package com.reddit.user.koppeh.flamingo;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class Flamingo implements ModInitializer {

	public static final String MOD_ID = "flamingo";

	public static final Block FLAMINGO_BLOCK;
	public static final BlockEntityType<FlamingoBlockEntity> FLAMINGO_BLOCK_ENTITY;

	static {
		FLAMINGO_BLOCK = register("flamingo", new FlamingoBlock(FabricBlockSettings.of(Material.WOOL).materialColor(MapColor.PINK).hardness(1.5F).sounds(BlockSoundGroup.WOOL).build()), ItemGroup.DECORATIONS);
		FLAMINGO_BLOCK_ENTITY = register("flamingo", FabricBlockEntityTypeBuilder.create(FlamingoBlockEntity::new, FLAMINGO_BLOCK));
	}

	public static Block register(String name, Block block, ItemGroup tab) {
		Registry.register(Registry.BLOCK, MOD_ID + ":" + name, block);
		BlockItem item = new BlockItem(block, new Item.Settings().group(tab));
		item.appendBlocks(Item.BLOCK_ITEMS, item);
		register(name, item);
		return block;
	}

	public static Item register(String name, Item item) {
		Registry.register(Registry.ITEM, MOD_ID + ":" + name, item);
		return item;
	}

	public static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder<T> builder) {
		BlockEntityType<T> blockEntityType = builder.build(null);
		Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":" + name, blockEntityType);
		return blockEntityType;
	}

	@Override
	public void onInitialize() {

	}
}
