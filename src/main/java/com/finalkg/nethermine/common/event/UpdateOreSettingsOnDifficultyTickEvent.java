package com.finalkg.nethermine.common.event;

import com.finalkg.nethermine.NetherMineCore;

import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

@EventBusSubscriber(modid = NetherMineCore.MODID)
public class UpdateOreSettingsOnDifficultyTickEvent {
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent e) {
		if(e.world != null && e.phase == Phase.END) {
			if(NetherMineCore.options.changeNetherOreSettingsByGameDifficulty) {
				EnumDifficulty d = e.world.getDifficulty();
				if(d == EnumDifficulty.PEACEFUL) { //Peaceful ore mine settings
					NetherMineCore.options.oreExplosions = false;
					NetherMineCore.options.oreExplosionPower = 1;
					NetherMineCore.options.oreExplosionChance = 1.0F;
					NetherMineCore.options.oreExplosionChainReactions = false;
					NetherMineCore.options.oreAngerZombiePigmen = false;
					NetherMineCore.options.oreAngerZombiePigmenRange = 4;
				}
				else if(d == EnumDifficulty.EASY) {
					NetherMineCore.options.oreExplosions = true;
					NetherMineCore.options.oreExplosionPower = 1;
					NetherMineCore.options.oreExplosionChance = 9.0F;
					NetherMineCore.options.oreExplosionChainReactions = false;
					NetherMineCore.options.oreAngerZombiePigmen = true;
					NetherMineCore.options.oreAngerZombiePigmenRange = 12;
				}
				else if(d == EnumDifficulty.NORMAL) {
					NetherMineCore.options.oreExplosions = true;
					NetherMineCore.options.oreExplosionPower = 2;
					NetherMineCore.options.oreExplosionChance = 7.5F;
					NetherMineCore.options.oreExplosionChainReactions = true;
					NetherMineCore.options.oreAngerZombiePigmen = true;
					NetherMineCore.options.oreAngerZombiePigmenRange = 24;
				}
				else if(d == EnumDifficulty.HARD) {
					NetherMineCore.options.oreExplosions = true;
					NetherMineCore.options.oreExplosionPower = 3;
					NetherMineCore.options.oreExplosionChance = 15F;
					NetherMineCore.options.oreExplosionChainReactions = true;
					NetherMineCore.options.oreAngerZombiePigmen = true;
					NetherMineCore.options.oreAngerZombiePigmenRange = 32;
				}
				else {
					NetherMineCore.options.oreExplosions = NetherMineCore.options.netherOreExplosions;
					NetherMineCore.options.oreExplosionPower = NetherMineCore.options.netherOreExplosionPower;
					NetherMineCore.options.oreExplosionChance = NetherMineCore.options.netherOreExplosionChance;
					NetherMineCore.options.oreExplosionChainReactions = NetherMineCore.options.netherOreExplosionChainReactions;
					NetherMineCore.options.oreAngerZombiePigmen = NetherMineCore.options.netherOreAngerZombiePigmen;
					NetherMineCore.options.oreAngerZombiePigmenRange = NetherMineCore.options.netherOreAngerZombiePigmenRange;
				}
			}
			else {
				NetherMineCore.options.oreExplosions = NetherMineCore.options.netherOreExplosions;
				NetherMineCore.options.oreExplosionPower = NetherMineCore.options.netherOreExplosionPower;
				NetherMineCore.options.oreExplosionChance = NetherMineCore.options.netherOreExplosionChance;
				NetherMineCore.options.oreExplosionChainReactions = NetherMineCore.options.netherOreExplosionChainReactions;
				NetherMineCore.options.oreAngerZombiePigmen = NetherMineCore.options.netherOreAngerZombiePigmen;
				NetherMineCore.options.oreAngerZombiePigmenRange = NetherMineCore.options.netherOreAngerZombiePigmenRange;
			}
		}
	}

}
