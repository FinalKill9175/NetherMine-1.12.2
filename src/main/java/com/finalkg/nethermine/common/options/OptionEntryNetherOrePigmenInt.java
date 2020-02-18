package com.finalkg.nethermine.common.options;

import com.finalkg.nethermine.client.options.OptionNetherOrePigmenInt;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.client.lib.option.Option;
import com.finalkg.wsbim.common.lib.OptionEntry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OptionEntryNetherOrePigmenInt extends OptionEntry{

	private NetherMineOptionFile opFile;
	
	public OptionEntryNetherOrePigmenInt(NetherMineOptionFile optionFile, String variableName, Object defaultValue,
			Object min, Object max, Object stepping, Object instance, Class c, boolean serverSided) {
		super(OptionType.INT, variableName, defaultValue, min, max, stepping, instance, c, serverSided);
		this.opFile = optionFile;
	}

	@SideOnly(Side.CLIENT)
	public Option getGUIOption() {
		return new OptionNetherOrePigmenInt(this.opFile.getOptionFileInstance().getClass(), this.opFile.getOptionFileInstance(), this.isClientSided(), this.getVariableName(), this.opFile.getModID()+".option."+this.getVariableName(), (int) this.getMinValue(), (int) this.getMaxValue(), (int) this.getStepping());
	}
}
