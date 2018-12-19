package com.reddit.user.koppeh.flamingo;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Tickable;

public class FlamingoBlockEntity extends BlockEntity implements Tickable {

	public static final float MAX_WIGGLE_STRENGTH = 40.0F;
	public static final float DEFAULT_WIGGLE_STRENGTH_PER_CLICK = 15.0F;

	public float wiggle = 0;
	public float wiggleStrength = 0.0F;

	public FlamingoBlockEntity(BlockEntityType<?> type) {
		super(type);
	}

	public FlamingoBlockEntity() {
		this(Flamingo.FLAMINGO_BLOCK_ENTITY);
	}

	@Override
	public void tick() {
		wiggle++;
		wiggleStrength = Math.max(0.0F, wiggleStrength * 0.9F - 0.1F);
	}

	public void wiggle() {
		wiggleStrength = Math.max(0.0F, Math.min(MAX_WIGGLE_STRENGTH, wiggleStrength + DEFAULT_WIGGLE_STRENGTH_PER_CLICK));
	}

	public float getWiggleStrength() {
		return this.wiggleStrength;
	}

}
