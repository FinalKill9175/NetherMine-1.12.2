package com.finalkg.nethermine.client.options;

import com.finalkg.nethermine.NetherMineCore;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.client.lib.option.OptionBoolean;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OptionNetherOreExplosionBoolean extends OptionBoolean {
	public OptionNetherOreExplosionBoolean(Class variableClass, Object instance, boolean gui, String variableName, String guiName) {
		super(variableClass, instance, gui, variableName, guiName);
		
	}
	public boolean isOptionEnabled() { return NetherMineCore.options.netherOreExplosions && !NetherMineCore.options.changeNetherOreSettingsByGameDifficulty; }
}
