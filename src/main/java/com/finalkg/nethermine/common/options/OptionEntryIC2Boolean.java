package com.finalkg.nethermine.common.options;

import com.finalkg.nethermine.client.options.OptionIC2Boolean;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.client.lib.option.Option;
import com.finalkg.wsbim.client.lib.option.OptionBoolean;
import com.finalkg.wsbim.common.lib.OptionEntry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OptionEntryIC2Boolean extends OptionEntry {

	private final NetherMineOptionFile opFile;
	
	public OptionEntryIC2Boolean(NetherMineOptionFile opFile, String variableName, Object defaultValue, boolean serverSided) {
		super(OptionType.BOOLEAN, variableName, defaultValue, opFile.getOptionFileInstance(), opFile.getOptionFileInstance().getClass(), serverSided);
		this.opFile = opFile;
	}
	@SideOnly(Side.CLIENT)
	public Option getGUIOption() {
		return new OptionIC2Boolean(this.opFile.getOptionFileInstance().getClass(), this.opFile.getOptionFileInstance(), this.isClientSided(), this.getVariableName(), this.opFile.getModID() + ".option." + this.getVariableName());
	}
}
