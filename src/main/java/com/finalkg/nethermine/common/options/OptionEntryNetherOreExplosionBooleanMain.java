package com.finalkg.nethermine.common.options;

import com.finalkg.nethermine.client.options.OptionNetherOreExplosionBooleanMain;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.client.lib.option.Option;
import com.finalkg.wsbim.common.lib.OptionEntry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OptionEntryNetherOreExplosionBooleanMain extends OptionEntry {

	private final NetherMineOptionFile opFile;
	
	public OptionEntryNetherOreExplosionBooleanMain(NetherMineOptionFile opFile, String variableName, Object defaultValue, boolean serverSided) {
		super(OptionType.BOOLEAN, variableName, defaultValue, opFile.getOptionFileInstance(), opFile.getOptionFileInstance().getClass(), serverSided);
		this.opFile = opFile;
	}
	@SideOnly(Side.CLIENT)
	public Option getGUIOption() {
		return new OptionNetherOreExplosionBooleanMain(this.opFile.getOptionFileInstance().getClass(), this.opFile.getOptionFileInstance(), this.isClientSided(), this.getVariableName(), this.opFile.getModID() + ".option." + this.getVariableName());
	}
}
