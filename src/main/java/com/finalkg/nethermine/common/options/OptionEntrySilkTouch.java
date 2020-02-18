package com.finalkg.nethermine.common.options;

import java.io.PrintWriter;

import com.finalkg.nethermine.common.lib.EnumNetherOre;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.common.lib.OptionEntry;

import net.minecraftforge.oredict.OreDictionary;

public class OptionEntrySilkTouch extends OptionEntry {

	public OptionEntrySilkTouch(String variableName, Object defaultValue, Object instance, Class c) {
		super(OptionType.BOOLEAN, variableName, defaultValue, instance, c, true);

	}
	protected void writeOptionEntry(PrintWriter pw) {
		super.writeOptionEntry(pw);
		for(EnumNetherOre ore : EnumNetherOre.values()) { //Sets drop items when options change.
			String oreName = ore.getSmeltName(); // Ingot
			if (OreDictionary.getOres(oreName).size() > 0) ore.setDropItem(OreDictionary.getOres(oreName).get(0));
		}
	}
}
