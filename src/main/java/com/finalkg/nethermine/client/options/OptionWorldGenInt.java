package com.finalkg.nethermine.client.options;

import com.finalkg.nethermine.NetherMineCore;
import com.finalkg.nethermine.common.lib.EnumNetherOre;
import com.finalkg.wsbim.client.lib.option.OptionInteger;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OptionWorldGenInt extends OptionInteger {
	
	private final EnumNetherOre oreType;

	public OptionWorldGenInt(EnumNetherOre oreType, Class variableClass, Object instance, boolean gui, String variableName, String guiName,
			int min, int max, int stepping) {
		super(variableClass, instance, gui, variableName, guiName, min, max, stepping);
		this.oreType = oreType;
	}
	/**Is this option enabled?*/
	public boolean isOptionEnabled() { return oreType.getForced() || (oreType.isRegisteredSmelting() || oreType.isRegisteredMacerator() || NetherMineCore.options.forceAllOreSpawn &&!oreType.getDisabled()); }

}
