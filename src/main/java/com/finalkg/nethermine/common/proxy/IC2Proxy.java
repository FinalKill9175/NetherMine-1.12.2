package com.finalkg.nethermine.common.proxy;

import com.finalkg.nethermine.NetherMineCore;
import com.finalkg.nethermine.common.lib.EnumNetherOre;

import net.minecraft.item.ItemStack;

public class IC2Proxy implements IIC2Proxy {

	public IC2Proxy() {
	}

	@Override
	public void registerMaceratorRecipe(EnumNetherOre ore, ItemStack maceStack) {
		if(ore.isRegisteredMacerator()) return;
		ore.setRegisteredMaceratorRecipe(true);
		if(NetherMineCore.options.getRegisterMaceratorRecipes()) {
			ItemStack input = ore.getItemStack(1);
			ItemStack maceTo = maceStack.copy();
			maceTo.setCount(ore.getMaceCount());
			ic2.api.recipe.Recipes.macerator.addRecipe(new com.finalkg.nethermine.common.lib.MaceratorRecipeInput(input), null, false, maceTo.copy());
		}
	}
}
