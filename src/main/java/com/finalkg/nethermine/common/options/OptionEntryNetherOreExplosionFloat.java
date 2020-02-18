package com.finalkg.nethermine.common.options;

import com.finalkg.nethermine.client.options.OptionNetherOreExplosionFloat;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.client.lib.option.Option;
import com.finalkg.wsbim.client.lib.option.OptionInteger;
import com.finalkg.wsbim.common.lib.OptionEntry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OptionEntryNetherOreExplosionFloat extends OptionEntry{

	private NetherMineOptionFile opFile;
	
	public OptionEntryNetherOreExplosionFloat(NetherMineOptionFile optionFile, String variableName, Object defaultValue,
			Object min, Object max, Object stepping, Object instance, Class c, boolean serverSided) {
		super(OptionType.FLOAT, variableName, defaultValue, min, max, stepping, instance, c, serverSided);
		this.opFile = optionFile;
	}

	@SideOnly(Side.CLIENT)
	public Option getGUIOption() {
		return new OptionNetherOreExplosionFloat(this.opFile.getOptionFileInstance().getClass(), this.opFile.getOptionFileInstance(), this.isClientSided(), this.getVariableName(), this.opFile.getModID()+".option."+this.getVariableName(), (float) this.getMinValue(), (float) this.getMaxValue(), (float) this.getStepping());
	}
}
