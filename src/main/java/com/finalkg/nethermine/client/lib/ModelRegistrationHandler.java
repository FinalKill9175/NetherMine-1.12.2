package com.finalkg.nethermine.client.lib;

import java.util.Locale;

import com.finalkg.nethermine.NetherMineCore;
import com.finalkg.nethermine.common.block.BlockNetherOre;
import com.finalkg.nethermine.common.lib.EnumNetherOre;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = NetherMineCore.MODID)
public class ModelRegistrationHandler {
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		//ALL ITEM RENDERS HERE
		int i = 0;
		for(Block b : NetherMineCore.blockNetherOres) {
			registerOreBlockModel(b, i);
			i++;
		}
	}
	public static void registerOreBlockModel(Block block, int arrayIndex) {
		int k = 0;
		for(int i = arrayIndex * 16; i < (arrayIndex * 16) + 16; i++) {
			if(i < EnumNetherOre.values().length) {
				EnumNetherOre ore = EnumNetherOre.values()[i];
				ModelLoader.setCustomModelResourceLocation(NetherMineCore.itemBlockNetherOres[arrayIndex], k, new ModelResourceLocation(block.getRegistryName(),  "ore=" + ore.name().toLowerCase(Locale.US)));
			}
			k++;
		}
	}
}
