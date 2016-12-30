package com.reddit.user.koppeh.flamingo;

import com.google.common.base.Strings;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IWorldNameable;

import net.minecraftforge.common.util.Constants;

public class TileEntityFlamingo extends TileEntity implements ITickable, IWorldNameable {

	public static final float MAX_WIGGLE_STRENGTH = 40.0F;
	public static final float DEFAULT_WIGGLE_STRENGTH_PER_CLICK = 15.0F;

	float wiggle = 0;
	float wiggleStrength = 0.0F;
	String customName;

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

	@Override
	public String getName() {
		return hasCustomName() ? customName : "tile.flamingo.flamingo.name";
	}

	@Override
	public boolean hasCustomName() {
		return !Strings.isNullOrEmpty(customName);
	}

	@Override
	public ITextComponent getDisplayName() {
		return hasCustomName() ? new TextComponentString(getName()) : new TextComponentTranslation(getName());
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if(compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) {
			customName = compound.getString("CustomName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if(hasCustomName()) {
			compound.setString("CustomName", customName);
		}
	}
}
