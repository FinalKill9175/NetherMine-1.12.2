package com.finalkg.nethermine.client.options;

import com.finalkg.wsbim.client.lib.option.OptionBoolean;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OptionIC2Boolean extends OptionBoolean {

	public OptionIC2Boolean(Class variableClass, Object instance, boolean gui, String variableName, String guiName) {
		super(variableClass, instance, gui, variableName, guiName);
	}
	/**Is this option enabled?*/
	public boolean isOptionEnabled() { return Loader.isModLoaded("ic2"); }
}
