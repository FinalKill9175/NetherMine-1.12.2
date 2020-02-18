package com.finalkg.nethermine.common.options;

import com.finalkg.nethermine.client.options.OptionWorldGenInt;
import com.finalkg.nethermine.common.lib.EnumNetherOre;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.client.lib.option.Option;
import com.finalkg.wsbim.common.lib.OptionEntry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OptionEntryWorldGenInt extends OptionEntry {
	
	private final NetherMineOptionFile opFile;
	private final EnumNetherOre oreType;
	
	public OptionEntryWorldGenInt(NetherMineOptionFile optionFile, EnumNetherOre ore, String variableName, Object defaultValue, Object min,
			Object max, Object stepping) {
		super(OptionType.INT, variableName, defaultValue, min, max, stepping, optionFile.getOptionFileInstance(), optionFile.getOptionFileInstance().getClass(), true);
		this.opFile = optionFile;
		this.oreType = ore;
	}
	
	@SideOnly(Side.CLIENT)
	public Option getGUIOption() {
		return new OptionWorldGenInt(oreType, this.opFile.getOptionFileInstance().getClass(), this.opFile.getOptionFileInstance(), this.isClientSided(), this.getVariableName(), this.opFile.getModID()+".option."+this.getVariableName(), (int) this.getMinValue(), (int) this.getMaxValue(), (int) this.getStepping());
	}
}
