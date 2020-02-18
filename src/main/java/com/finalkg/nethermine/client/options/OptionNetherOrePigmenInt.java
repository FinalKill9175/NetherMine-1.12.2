package com.finalkg.nethermine.client.options;

import com.finalkg.nethermine.NetherMineCore;
import com.finalkg.wsbim.client.lib.option.OptionInteger;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OptionNetherOrePigmenInt extends OptionInteger {

	public OptionNetherOrePigmenInt(Class variableClass, Object instance, boolean gui, String variableName, String guiName,
			int min, int max, int stepping) {
		super( variableClass,  instance,  gui,  variableName,  guiName,
			 min,  max,  stepping);
	}
	
	public boolean isOptionEnabled() { return NetherMineCore.options.netherOreAngerZombiePigmen && !NetherMineCore.options.changeNetherOreSettingsByGameDifficulty; }
}
