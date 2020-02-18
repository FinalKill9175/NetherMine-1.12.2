package com.finalkg.nethermine.common.proxy;

import com.finalkg.nethermine.common.lib.EnumNetherOre;

import net.minecraft.item.ItemStack;

public class NoIC2Proxy implements IIC2Proxy {

	public NoIC2Proxy() {
	}

	@Override
	public void registerMaceratorRecipe(EnumNetherOre ore, ItemStack maceStack) {
		return; //No IC2? no macerator recipes.
	}

}
