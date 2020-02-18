package com.finalkg.nethermine.common.item;

import java.util.List;
import java.util.Locale;

import com.finalkg.nethermine.NetherMineCore;
import com.finalkg.nethermine.common.block.BlockNetherOre;
import com.finalkg.nethermine.common.lib.EnumNetherOre;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * Rewrite from PowerCrystal's ItemBlockNetherOre
 * @author finalkg
 *
 */
public class ItemBlockNetherOre extends ItemBlock {
	
	private final BlockNetherOre block;

	public ItemBlockNetherOre(int arrayIndex) {
		super(NetherMineCore.blockNetherOres[arrayIndex]);
		this.block = (BlockNetherOre) NetherMineCore.blockNetherOres[arrayIndex];
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	@Override
	public String getUnlocalizedName(ItemStack stack){
		int index = this.block.getBlockArrayIndex();
		EnumNetherOre[] ores = EnumNetherOre.values();
		int md = Math.min(index * 16 + stack.getItemDamage(), ores.length - 1);
		return "tile.netherore.ore." + ores[md].name().toLowerCase(Locale.US);
	}
	@Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items){
		if(tab != this.block.getCreativeTabToDisplayOn()) return; //bug fix from power crystal's version.
		int index = this.block.getBlockArrayIndex();
		EnumNetherOre[] ores = EnumNetherOre.values();
		for (int i = 0, e = Math.min(index * 16 + 15, ores.length - 1) % 16; i <= e; ++i)
		{
			items.add(new ItemStack(this.block, 1, i));
		}
	}
	@Override
	public int getMetadata(int i){
		return i;
	}
}
