package com.finalkg.nethermine.common.lib;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;

public class MaceratorRecipeInput implements ic2.api.recipe.IRecipeInput {

	private final ItemStack input;
	public MaceratorRecipeInput(ItemStack input) {
		this.input = input;
	}

	@Override
	public boolean matches(ItemStack subject) {
		return ItemStack.areItemsEqual(input, subject);
	}

	@Override
	public int getAmount() {
		return input.getCount();
	}

	@Override
	public List<ItemStack> getInputs() {
		List<ItemStack> inputArr = new ArrayList<ItemStack>();
		inputArr.add(input.copy());
		return inputArr;
	}
}
