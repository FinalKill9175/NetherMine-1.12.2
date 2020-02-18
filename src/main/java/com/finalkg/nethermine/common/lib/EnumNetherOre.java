package com.finalkg.nethermine.common.lib;

import java.util.Locale;

import com.finalkg.nethermine.NetherMineCore;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.IStringSerializable;

/**
 * Written from powercrystal's Ores.java
 * @author finalkg, powercrystals
 *
 */
public enum EnumNetherOre implements IStringSerializable{
	
	//Nerffed some of these mace count values, things like 5 DIAMOND or 24 REDSTONE for one ore is a bit OP, dont you think???
	
	COAL(       8,    16,     2,    4,      null,    2,   1),
	DIAMOND(    5,     4,     2,    4,     "gem",    2,   3), //Diamonds were very very hard to find, so I upped each gen value by one.
	GOLD(       7,     6,     2,    4), //Nerffed gold down to 7 groups per chunk. Fairly common
	IRON(       8,     8,     2,    4),
	LAPIS(      6,     6,     2,   10,     "gem",    10,   2),
	REDSTONE(   6,     8,     2,   10,    "dust",    6,   2),
	COPPER(     8,     8,     2,    4),
	TIN(        8,     8,     2,    4),
	EMERALD(    3,     2,     2,    4,     "gem",    2,   3),
	SILVER(     6,     4,     2,    4),
	LEAD(       6,     6,     2,    4),
	URANIUM(    3,     2,     2,    4, "crushed"),
	NIKOLITE(   8,     4,     2,   10,    "dust",    7,   2),
	RUBY(       6,     3,     2,    4,     "gem",    2,   3),
	PERIDOT(    6,     3,     2,    4,     "gem",    2,   3),
	SAPPHIRE(   6,     3,     2,    4,     "gem",    2,   3),

	PLATINUM(   1,     3,     2,    4),
	NICKEL(     4,     6,     2,    4),
	STEEL(      3,     4,     2,    4),
	IRIDIUM(    1,     2,     2,    4,    "drop"),
	OSMIUM(     8,     7,     2,    4),
	SULFUR(    	6,    12,     2,   10,    "dust",    8,   1, "crystal"), //Changed, wayyyy to much sulfur per chunk.
	TITANIUM(   3,     2,     2,    4),
	MITHRIL(    6,     6,     2,    4),
	ADAMANTIUM( 5,     4,     2,    4),
	RUTILE(     3,     4,     2,    4),
	TUNGSTEN(   8,     8,     2,    4),
	AMBER(      5,     6,     2,    4,     "gem",    2,   2),
	TENNANTITE( 8,     8,     2,    4),
	SALT(       5,     5,     2,   10,    "food",    4,   1),
	SALTPETER(  6,     4,     2,   10,    "dust",    3,   1, "crystal"),
	MAGNESIUM(  4,     5,     2,    8, "crushed");
	
	private int _blockIndex;
	private int _metadata;
	private String _primary;
	private String _secondary;

	private boolean _registeredSmelting;
	private boolean _registeredMacerator = false;

	private boolean _oreGenDisable = false;
	private boolean _oreGenForced = false;

	private boolean _silky = true;

	private int _oreGenMinY = 1;
	private int _oreGenMaxY = 127;

	private int _oreGenGroupsPerChunk = 6;
	private int _oreGenBlocksPerGroup = 14;

	private int _miningLevel;

	private int _smeltCount;
	private int _pulvCount;
	private int _dropCount;
	private int _exp;

	private ItemStack _itemDropped = ItemStack.EMPTY;
	private static final EnumNetherOre[] META_LOOKUP = new EnumNetherOre[values().length];

	private EnumNetherOre(int groupsPerChunk, int blocksPerGroup, int smeltCount, int maceCount)
	{
		this(groupsPerChunk, blocksPerGroup, smeltCount, maceCount, null);
		_silky = false;
	}

	private EnumNetherOre(int groupsPerChunk, int blocksPerGroup, int smeltCount, int maceCount, String secondaryType)
	{
		this(groupsPerChunk, blocksPerGroup, smeltCount, maceCount, null, 1, 0, secondaryType);
	}

	private EnumNetherOre(int groupsPerChunk, int blocksPerGroup, int smeltCount, int maceCount, String type, int dropCount, int exp)
	{
		this(groupsPerChunk, blocksPerGroup, smeltCount, maceCount, type, dropCount, exp, type);
	}
	
	private EnumNetherOre(int groupsPerChunk, int blocksPerGroup, int smeltCount, int maceCount, String primaryType, int dropCount, int exp, String secondaryType)
	{
		int meta = ordinal();
		_blockIndex = meta / 16;
		_metadata = meta % 16;
		_oreGenGroupsPerChunk = groupsPerChunk;
		_oreGenBlocksPerGroup = blocksPerGroup;
		_smeltCount = smeltCount;
		_pulvCount = maceCount;
		_dropCount = dropCount;
		_miningLevel = 2;
		_primary = primaryType != null ? primaryType : "ingot";
		_secondary = secondaryType != null ? secondaryType : "crystalline";
		_exp = exp;
		_registeredMacerator = false;
		_registeredSmelting = false;
	}
	
	public int getBlockIndex()
	{
		return _blockIndex;
	}

	public int getMetadata()
	{
		return _metadata;
	}

	public String getOreName()
	{
		String name = name();
		String s = "ore"+(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(Locale.US)); 
		return s;//Fix for ore dictionary
	}

	public String getSmeltName()
	{
		String name = name();
		String s = _primary+(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(Locale.US)); 
		return s; //Fix for ore dictionary
	}

	public String getDustName()
	{
		String name = name();
		String prefix = this._primary != null && this._primary.equals("gem") && NetherMineCore.options.forceMaceratingIntoGems ? "gem" : "dust"; //Fix for macerating things such as nether diamond ore into diamond dust, which cannot be used for much.
		String s = prefix + (name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(Locale.US)); 
		return s; //Fix for ore dictionary
	}

	public String getAltName()
	{
		String name = name();
		String s = _secondary+(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(Locale.US)); 
		return s; //Fix for ore dictionary
	}

	public boolean isRegisteredSmelting()
	{
		return _registeredSmelting;
	}

	public boolean isRegisteredMacerator()
	{
		return _registeredMacerator;
	}

	public int getMaxY()
	{
		return NetherMineCore.options.netherOreMaxY; //config support
	}

	public int getMinY()
	{
		return NetherMineCore.options.netherOreMinY; //config support
	}

	public int getGroupsPerChunk()
	{
		return _oreGenGroupsPerChunk;
	}

	public int getBlocksPerGroup()
	{
		return _oreGenBlocksPerGroup;
	}

	public boolean getDisabled()
	{
		return _oreGenDisable;
	}

	public boolean getForced()
	{
		return _oreGenForced;
	}

	public int getSmeltCount()
	{
		return _smeltCount;
	}

	public int getMaceCount()
	{
		return _pulvCount;
	}

	public int getDropCount()
	{
		return _dropCount;
	}

	public ItemStack getDropItem()
	{
		return _itemDropped;
	}

	public int getExp()
	{
		return _exp;
	}
	
	public void setDropItem(ItemStack stack)
	{
		if (_silky && stack != null && stack != ItemStack.EMPTY && NetherMineCore.options.requireSilkTouch) {
			_itemDropped = stack.copy();
			_itemDropped.setCount(1);
		}
		else {
			if(this._itemDropped != ItemStack.EMPTY) {
				this._itemDropped = ItemStack.EMPTY;
			}
		}
	}
	
	public ItemStack getItemStack(int amt)
	{
		return new ItemStack(NetherMineCore.blockNetherOres[this._blockIndex], amt, _metadata);
	}

	public void registerSmelting(ItemStack smeltStack)
	{
		if (_registeredSmelting)
			return;
		_registeredSmelting = true;
		if (NetherMineCore.options.enableStandardFurnaceRecipes){
			ItemStack smeltTo = smeltStack.copy();
			smeltTo.setCount(_smeltCount);
			//GameRegistry.addSmelting(getItemStack(1), smeltTo, 1.0F);
			FurnaceRecipes.instance().addSmeltingRecipe(getItemStack(1), smeltTo, 3.0F);
		}
	}
	
	public void setRegisteredMaceratorRecipe(boolean done) {
		this._registeredMacerator = done;
	}
	
    public static EnumNetherOre byMetadata(int arrayIndex, int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }
        for(EnumNetherOre type : EnumNetherOre.values()) {
        	if(type.getMetadata() == meta && type.getBlockIndex() == arrayIndex) return type; 
        }
        return META_LOOKUP[meta];
    }


	@Override
	public String getName() {
		return name().toLowerCase(Locale.US);
	}
}
