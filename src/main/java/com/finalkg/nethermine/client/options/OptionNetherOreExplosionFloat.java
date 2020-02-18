package com.finalkg.nethermine.client.options;

import com.finalkg.nethermine.NetherMineCore;
import com.finalkg.wsbim.client.lib.option.OptionFloat;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OptionNetherOreExplosionFloat extends OptionFloat {

	public OptionNetherOreExplosionFloat(Class variableClass, Object instance, boolean gui, String variableName,
			String guiName, float min, float max, float stepping) {
		super(variableClass, instance, gui, variableName, guiName, min, max, stepping);
	}
	
	public boolean isOptionEnabled() { return NetherMineCore.options.netherOreExplosions && !NetherMineCore.options.changeNetherOreSettingsByGameDifficulty; }
}
