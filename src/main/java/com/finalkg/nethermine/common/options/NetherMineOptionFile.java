package com.finalkg.nethermine.common.options;

import java.lang.reflect.Field;

import com.finalkg.nethermine.NetherMineCore;
import com.finalkg.nethermine.common.lib.EnumNetherOre;
import com.finalkg.wsbim.WSBIMOptions;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.common.lib.IOptionsFile;
import com.finalkg.wsbim.common.lib.OptionFile;

import net.minecraftforge.fml.common.Loader;

public class NetherMineOptionFile extends OptionFile {
	
	//Create option variables here with default values
	public boolean requireSilkTouch = false;
	public boolean registerMaceratorRecipes = true;
	public boolean enableSmeltToOres = true;
	public boolean enableStandardFurnaceRecipes = true;
	public boolean enableWorldGen = true;
	public boolean forceAllOreSpawn = false; //Force all ores to spawn regardless of whether they have a use or not
	public boolean forceMaceratingIntoGems = true; //Force macerator recipes to macerate nether ores into gems rather than dusts when possible. 
	public int netherOreMinY = 0;
	public int netherOreMaxY = 127;
	public boolean netherOreExplosions = true;
	public int netherOreExplosionPower = 2; //Explosion Power. 3 is creepers, 4 is tnt, 6 is god knows what.
	public float netherOreExplosionChance = 7.5F; //Explosion Chance, out of 100.0F
	public boolean netherOreExplosionChainReactions = true; //Will other nether ores be set off by one explosion?
	public boolean netherOreAngerZombiePigmen = true; //Will nether ores anger surrounding zombie pigmen when mined?
	public int netherOreAngerZombiePigmenRange = 24; //How far away can a zombie pigman be to become angered? (1 min, max 64)
	public boolean changeNetherOreSettingsByGameDifficulty = true;
	//Groups per chunk
	public int oreCoalGroupsPerChunk = EnumNetherOre.COAL.getGroupsPerChunk();
	public int oreDiamondGroupsPerChunk = EnumNetherOre.DIAMOND.getGroupsPerChunk();
	public int oreGoldGroupsPerChunk = EnumNetherOre.GOLD.getGroupsPerChunk();
	public int oreIronGroupsPerChunk = EnumNetherOre.IRON.getGroupsPerChunk();
	public int oreLapisGroupsPerChunk = EnumNetherOre.LAPIS.getGroupsPerChunk();
	public int oreRedstoneGroupsPerChunk = EnumNetherOre.REDSTONE.getGroupsPerChunk();
	public int oreCopperGroupsPerChunk = EnumNetherOre.COPPER.getGroupsPerChunk();
	public int oreTinGroupsPerChunk = EnumNetherOre.TIN.getGroupsPerChunk();
	public int oreEmeraldGroupsPerChunk = EnumNetherOre.EMERALD.getGroupsPerChunk();
	public int oreSilverGroupsPerChunk = EnumNetherOre.SILVER.getGroupsPerChunk();
	public int oreLeadGroupsPerChunk = EnumNetherOre.LEAD.getGroupsPerChunk();
	public int oreUraniumGroupsPerChunk = EnumNetherOre.URANIUM.getGroupsPerChunk();
	public int oreNikoliteGroupsPerChunk = EnumNetherOre.NIKOLITE.getGroupsPerChunk();
	public int oreRubyGroupsPerChunk = EnumNetherOre.RUBY.getGroupsPerChunk();
	public int orePeridotGroupsPerChunk = EnumNetherOre.PERIDOT.getGroupsPerChunk();
	public int oreSapphireGroupsPerChunk = EnumNetherOre.SAPPHIRE.getGroupsPerChunk();
	public int orePlatinumGroupsPerChunk = EnumNetherOre.PLATINUM.getGroupsPerChunk();
	public int oreNickelGroupsPerChunk = EnumNetherOre.NICKEL.getGroupsPerChunk();
	public int oreSteelGroupsPerChunk = EnumNetherOre.STEEL.getGroupsPerChunk();
	public int oreIridiumGroupsPerChunk = EnumNetherOre.IRIDIUM.getGroupsPerChunk();
	public int oreOsmiumGroupsPerChunk = EnumNetherOre.OSMIUM.getGroupsPerChunk();
	public int oreSulfurGroupsPerChunk = EnumNetherOre.SULFUR.getGroupsPerChunk();
	public int oreTitaniumGroupsPerChunk = EnumNetherOre.TITANIUM.getGroupsPerChunk();
	public int oreMithrilGroupsPerChunk = EnumNetherOre.MITHRIL.getGroupsPerChunk();
	public int oreAdamantiumGroupsPerChunk = EnumNetherOre.ADAMANTIUM.getGroupsPerChunk();
	public int oreRutileGroupsPerChunk = EnumNetherOre.RUTILE.getGroupsPerChunk();
	public int oreTungstenGroupsPerChunk = EnumNetherOre.TUNGSTEN.getGroupsPerChunk();
	public int oreAmberGroupsPerChunk = EnumNetherOre.AMBER.getGroupsPerChunk();
	public int oreTennantiteGroupsPerChunk = EnumNetherOre.TENNANTITE.getGroupsPerChunk();
	public int oreSaltGroupsPerChunk = EnumNetherOre.SALT.getGroupsPerChunk();
	public int oreSaltpeterGroupsPerChunk = EnumNetherOre.SALTPETER.getGroupsPerChunk();
	public int oreMagnesiumGroupsPerChunk = EnumNetherOre.MAGNESIUM.getGroupsPerChunk();
	//Blocks per group
	public int oreCoalBlocksPerGroup = EnumNetherOre.COAL.getBlocksPerGroup();
	public int oreDiamondBlocksPerGroup = EnumNetherOre.DIAMOND.getBlocksPerGroup();
	public int oreGoldBlocksPerGroup = EnumNetherOre.GOLD.getBlocksPerGroup();
	public int oreIronBlocksPerGroup = EnumNetherOre.IRON.getBlocksPerGroup();
	public int oreLapisBlocksPerGroup = EnumNetherOre.LAPIS.getBlocksPerGroup();
	public int oreRedstoneBlocksPerGroup = EnumNetherOre.REDSTONE.getBlocksPerGroup();
	public int oreCopperBlocksPerGroup = EnumNetherOre.COPPER.getBlocksPerGroup();
	public int oreTinBlocksPerGroup = EnumNetherOre.TIN.getBlocksPerGroup();
	public int oreEmeraldBlocksPerGroup = EnumNetherOre.EMERALD.getBlocksPerGroup();
	public int oreSilverBlocksPerGroup = EnumNetherOre.SILVER.getBlocksPerGroup();
	public int oreLeadBlocksPerGroup = EnumNetherOre.LEAD.getBlocksPerGroup();
	public int oreUraniumBlocksPerGroup = EnumNetherOre.URANIUM.getBlocksPerGroup();
	public int oreNikoliteBlocksPerGroup = EnumNetherOre.NIKOLITE.getBlocksPerGroup();
	public int oreRubyBlocksPerGroup = EnumNetherOre.RUBY.getBlocksPerGroup();
	public int orePeridotBlocksPerGroup = EnumNetherOre.PERIDOT.getBlocksPerGroup();
	public int oreSapphireBlocksPerGroup = EnumNetherOre.SAPPHIRE.getBlocksPerGroup();
	public int orePlatinumBlocksPerGroup = EnumNetherOre.PLATINUM.getBlocksPerGroup();
	public int oreNickelBlocksPerGroup = EnumNetherOre.NICKEL.getBlocksPerGroup();
	public int oreSteelBlocksPerGroup = EnumNetherOre.STEEL.getBlocksPerGroup();
	public int oreIridiumBlocksPerGroup = EnumNetherOre.IRIDIUM.getBlocksPerGroup();
	public int oreOsmiumBlocksPerGroup = EnumNetherOre.OSMIUM.getBlocksPerGroup();
	public int oreSulfurBlocksPerGroup = EnumNetherOre.SULFUR.getBlocksPerGroup();
	public int oreTitaniumBlocksPerGroup = EnumNetherOre.TITANIUM.getBlocksPerGroup();
	public int oreMithrilBlocksPerGroup = EnumNetherOre.MITHRIL.getBlocksPerGroup();
	public int oreAdamantiumBlocksPerGroup = EnumNetherOre.ADAMANTIUM.getBlocksPerGroup();
	public int oreRutileBlocksPerGroup = EnumNetherOre.RUTILE.getBlocksPerGroup();
	public int oreTungstenBlocksPerGroup = EnumNetherOre.TUNGSTEN.getBlocksPerGroup();
	public int oreAmberBlocksPerGroup = EnumNetherOre.AMBER.getBlocksPerGroup();
	public int oreTennantiteBlocksPerGroup = EnumNetherOre.TENNANTITE.getBlocksPerGroup();
	public int oreSaltBlocksPerGroup = EnumNetherOre.SALT.getBlocksPerGroup();
	public int oreSaltpeterBlocksPerGroup = EnumNetherOre.SALTPETER.getBlocksPerGroup();
	public int oreMagnesiumBlocksPerGroup = EnumNetherOre.MAGNESIUM.getBlocksPerGroup();
	
	//Create option category names with public static final here.
	public static final String worldGenCategory = "worldGen";
	public static final String recipeCategory = "recipes";
	public static final String blockCategory = "block";
	public static final String advancedWorldGenCategory = "advancedWorldGen";
	
	//Changing Variables
	public boolean oreExplosions = true;
	public int oreExplosionPower = 2; //Explosion Power. 3 is creepers, 4 is tnt, 6 is god knows what.
	public float oreExplosionChance = 7.5F; //Explosion Chance, out of 100.0F
	public boolean oreExplosionChainReactions = true; //Will other nether ores be set off by one explosion?
	public boolean oreAngerZombiePigmen = true; //Will nether ores anger surrounding zombie pigmen when mined?
	public int oreAngerZombiePigmenRange = 24; //How far away can a zombie pigman be to become angered? (1 min, max 64)
	
	public NetherMineOptionFile() {
		super(NetherMineCore.MODID, NetherMineCore.NAME, NetherMineCore.VERSION);
	}

	@Override
	public void addEntries() {
		this.registerOptionEntryCategory(worldGenCategory, "#####WORLD_GEN#####");
		this.registerOptionEntry("enableWorldGen", true, OptionType.BOOLEAN, worldGenCategory, true);
		this.registerOptionEntry("forceAllOreSpawn", false, OptionType.BOOLEAN, worldGenCategory, true);
		this.registerNumberOptionEntry(OptionType.INT, "netherOreMinY", 1, 1, 40, 1, worldGenCategory, true);
		this.registerNumberOptionEntry(OptionType.INT, "netherOreMaxY", 127, 45, 150, 1, worldGenCategory, true);
		this.registerOptionEntryCategory(advancedWorldGenCategory, "#####ADVANCED_ORE_GEN_SETTINGS#####", true);
		for(EnumNetherOre ore : EnumNetherOre.values()) {
			try {
				if(this.getClass().getField(ore.getOreName()+"GroupsPerChunk") != null && this.getClass().getField(ore.getOreName()+"BlocksPerGroup") != null) {
					this.registerCustomOptionEntry(ore.getOreName()+"GroupsPerChunk", advancedWorldGenCategory, new OptionEntryWorldGenInt(this, ore, ore.getOreName()+"GroupsPerChunk", ore.getGroupsPerChunk(), (int)0, (int)32, (int)1));
					this.registerCustomOptionEntry(ore.getOreName()+"BlocksPerGroup", advancedWorldGenCategory, new OptionEntryWorldGenInt(this, ore, ore.getOreName()+"BlocksPerGroup", ore.getBlocksPerGroup(), (int)1, (int)25, (int)1));
				}
			} catch (NoSuchFieldException e) {
				NetherMineCore.logger.warn("No BlocksPerGroup or GroupsPerChunk variables defined for "+ore.getOreName()+" in the NetherMineOptionFile class. Option will not be created.");
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		this.registerOptionEntryCategory(recipeCategory, "#####RECIPE_OPTIONS#####");
		this.registerOptionEntry("enableSmeltToOres", true, OptionType.BOOLEAN, recipeCategory, true);
		this.registerOptionEntry("enableStandardFurnaceRecipes", true, OptionType.BOOLEAN, recipeCategory, true);
		this.registerCustomOptionEntry("registerMaceratorRecipes", recipeCategory, new OptionEntryIC2Boolean(this, "registerMaceratorRecipes", true, true));
		this.registerCustomOptionEntry("forceMaceratingIntoGems", recipeCategory, new OptionEntryIC2Boolean(this, "forceMaceratingIntoGems", true, true));
		this.registerOptionEntryCategory(blockCategory, "#####ORE_OPTIONS#####");
		this.registerCustomOptionEntry("requireSilkTouch", blockCategory, new OptionEntrySilkTouch("requireSilkTouch", false, this.getOptionFileInstance(), this.getOptionFileInstance().getClass()));
		this.registerOptionEntry("changeNetherOreSettingsByGameDifficulty", true, OptionType.BOOLEAN, blockCategory, true);
		this.registerCustomOptionEntry("netherOreExplosions", blockCategory, new OptionEntryNetherOreExplosionBooleanMain(this, "netherOreExplosions", true, true));
		this.registerCustomOptionEntry("netherOreExplosionPower", blockCategory, new OptionEntryNetherOreExplosionInt(this, "netherOreExplosionPower", 2, 1, 8, 1, this.getOptionFileInstance(), this.getOptionFileInstance().getClass(), true));
		this.registerCustomOptionEntry("netherOreExplosionChance", blockCategory, new OptionEntryNetherOreExplosionFloat(this, "netherOreExplosionChance", 7.5F, 1F, 100F, 0.5F, this.getOptionFileInstance(), this.getOptionFileInstance().getClass(), true));
		this.registerCustomOptionEntry("netherOreExplosionChainReactions", blockCategory, new OptionEntryNetherOreExplosionBoolean(this, "netherOreExplosionChainReactions", true, true));
		this.registerCustomOptionEntry("netherOreAngerZombiePigmen", blockCategory, new OptionEntryNetherOreExplosionBooleanMain(this, "netherOreAngerZombiePigmen", true, true));
		this.registerCustomOptionEntry("netherOreAngerZombiePigmenRange", blockCategory, new OptionEntryNetherOrePigmenInt(this, "netherOreAngerZombiePigmenRange", 24, 4, 64, 1, this.getOptionFileInstance(), this.getOptionFileInstance().getClass(), true));
	}

	public int getGroupsPerChunk(EnumNetherOre ore) {
		try {
			if(this.getClass().getField(ore.getOreName()+"GroupsPerChunk") != null) {
				int integer = (int) this.getObjectFromClass(ore.getOreName()+"GroupsPerChunk", this.getOptionFileInstance().getClass(), this.getOptionFileInstance());
				return integer;
			}
		} catch (NoSuchFieldException | SecurityException e) {}
		return ore.getGroupsPerChunk();
	}
	public int getBlocksPerGroup(EnumNetherOre ore) {
		try {
			if(this.getClass().getField(ore.getOreName()+"BlocksPerGroup") != null) {
				int integer = (int) this.getObjectFromClass(ore.getOreName()+"BlocksPerGroup", this.getOptionFileInstance().getClass(), this.getOptionFileInstance());
				return integer;
			}
		} catch (NoSuchFieldException | SecurityException e) {}
		return ore.getBlocksPerGroup();
	}
	@Override
	public IOptionsFile getOptionFileInstance() {return this;}

	@Override
	public void registerGuiOptionDescriptions() {
		WSBIMOptions.registerOptionDescription("enableWorldGen", "Enable nether ore generation?");
		WSBIMOptions.registerOptionDescription("forceAllOreSpawn", "If true, all ores will spawn reguardless of", "whether the ore was found in the ore dictionary.");
		WSBIMOptions.registerOptionDescription("enableSmeltToOres", "If true, nether ores will smelt into regular ores.", "Otherwise, they will smelt to their normal items.", "ie. Nether Iron Ore -> 2x Iron Ore -> Iron Ingot", "and when false, Nether Iron Ore -> 2x Iron Ingot");
		WSBIMOptions.registerOptionDescription("enableStandardFurnaceRecipes", "Change this to false to remove smelting any nether", "ores to regular ores.");
		WSBIMOptions.registerOptionDescription("registerMaceratorRecipes", "If true and IC2 is loaded, macerator recipes for", "nether ores will be registered.");
		WSBIMOptions.registerOptionDescription("requireSilkTouch", "If true, blocks will drop their ores and gems", "rather than dropping the nether ore, to get the nether ore","you need to have silk touch equipped.");
		WSBIMOptions.registerOptionDescription("forceMaceratingIntoGems", "If true, macerator recipes will macerate", "nether ores into gems over their dust", "counterparts. ie. Nether Diamond Ore", "-> 4x Diamonds vs. Nether Diamond Ore", "-> 4x Diamond Dust.");
		WSBIMOptions.registerOptionDescription("netherOreMinY", "Minimum Y-Cordinate to spawn nether ores.");
		WSBIMOptions.registerOptionDescription("netherOreMaxY", "Maximum Y-Cordinate to spawn nether ores.");
		WSBIMOptions.registerOptionDescription("netherOreExplosions", "Enable nether ore explosions when you mine a", "nether ore?");
		WSBIMOptions.registerOptionDescription("netherOreExplosionPower", "Explosion Power (1-8) for the ore explosions.", "Reference: 4 is like TNT, 3 is for creepers");
		WSBIMOptions.registerOptionDescription("netherOreExplosionChainReactions", "If true, nether ores that explode", "will set off other nearby nether ores.");
		WSBIMOptions.registerOptionDescription("changeNetherOreSettingsByGameDifficulty", "Will change the block settings by difficulty.", "Set this to false to customize the block settings.");
	}

	@Override
	public int getPrimarySlotSpacing() {
		return 25;
	}
	
	public boolean getRegisterMaceratorRecipes() {
		return this.registerMaceratorRecipes && Loader.isModLoaded("ic2");
	}
	
    /**
     * Uses java reflect to get a variable from a initialized class.
     * @param varName
     * @param clazz
     * @param instance
     * @return
     */
	public static Object getObjectFromClass(String varName, Class<?> clazz, Object instance){
		Field f = null;
		try {
			f = clazz.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		f.setAccessible(true);
		
		try {
			return f.get(instance);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
