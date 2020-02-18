package com.finalkg.nethermine.common.lib;

import com.finalkg.nethermine.NetherMineCore;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

/**Not sure if I actually need to do this or not. Right now it's disabled because postInit seems to handle it fine.*/
@EventBusSubscriber(modid = NetherMineCore.MODID)
@Deprecated 
public class OreDictionaryRegisterEvent {

	@SubscribeEvent
	public void registerOreEvent(OreRegisterEvent event){
		registerOreDictionaryEntry(event.getName(), event.getOre());
	}
	private void registerOreDictionaryEntry(String oreName, ItemStack stack){
		for (EnumNetherOre ore : EnumNetherOre.values()){
			if(NetherMineCore.options.enableSmeltToOres) {
				NetherMineCore.registerOreDictOre(ore, oreName, stack);
			}
			NetherMineCore.registerOreDictSmelt(ore, oreName, stack);
			NetherMineCore.registerOreDictDust(ore, oreName, stack);
			NetherMineCore.registerOreDictGem(ore, oreName, stack);
		}
	}
}
