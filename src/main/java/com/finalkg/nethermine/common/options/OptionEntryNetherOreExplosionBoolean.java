package com.finalkg.nethermine.common.options;

import com.finalkg.nethermine.client.options.OptionNetherOreExplosionBoolean;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.client.lib.option.Option;
import com.finalkg.wsbim.common.lib.OptionEntry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OptionEntryNetherOreExplosionBoolean extends OptionEntry {

	private final NetherMineOptionFile opFile;
	
	public OptionEntryNetherOreExplosionBoolean(NetherMineOptionFile opFile, String variableName, Object defaultValue, boolean serverSided) {
		super(OptionType.BOOLEAN, variableName, defaultValue, opFile.getOptionFileInstance(), opFile.getOptionFileInstance().getClass(), serverSided);
		this.opFile = opFile;
	}
	@SideOnly(Side.CLIENT)
	public Option getGUIOption() {
		return new OptionNetherOreExplosionBoolean(this.opFile.getOptionFileInstance().getClass(), this.opFile.getOptionFileInstance(), this.isClientSided(), this.getVariableName(), this.opFile.getModID() + ".option." + this.getVariableName());
	}
}
