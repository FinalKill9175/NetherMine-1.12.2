package com.finalkg.nethermine.common.proxy;

import com.finalkg.nethermine.common.lib.EnumNetherOre;

import net.minecraft.item.ItemStack;

/**
 * Interface used to load macerator recipes depending on IC2 being loaded or not.
 * @author finalkg
 *
 */
public interface IIC2Proxy {
	public void registerMaceratorRecipe(EnumNetherOre ore, ItemStack maceStack);
}
