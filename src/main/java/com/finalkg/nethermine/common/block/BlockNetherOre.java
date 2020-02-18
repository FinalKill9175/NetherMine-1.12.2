package com.finalkg.nethermine.common.block;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

import com.finalkg.nethermine.NetherMineCore;
import com.finalkg.nethermine.common.entity.EntityArmedOre;
import com.finalkg.nethermine.common.lib.EnumNetherOre;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;

/**
 * Block Nether Ore
 * @author finalkg, PowerCrystals
 *
 */
public class BlockNetherOre extends Block {

	private final int arrayIndex;
	private EnumNetherOre[] ores;

	public static final PropertyEnum<EnumNetherOre> ORE = PropertyEnum.<EnumNetherOre>create("ore", EnumNetherOre.class);
	
	public BlockNetherOre(int arrayIndexIn) {
		super(Material.ROCK, MapColor.NETHERRACK);
		this.arrayIndex = arrayIndexIn;
		this.setHardness(5.0F);
		this.setResistance(1.0F);
		this.setSoundType(SoundType.STONE);
		EnumNetherOre[] enums = EnumNetherOre.values(); //Split blocks off into other blocks if there are more than 16 sub blocks. 
		Object[] ores = copyOfRange(enums, arrayIndexIn * 16, Math.min(arrayIndexIn + 16 * 16, enums.length)); //PowerCrystals had this but I am not sure if it's really needed.
		this.ores = new EnumNetherOre[ores.length];
		int i = 0;
		for(Object o : ores) {
			if(o instanceof EnumNetherOre) this.ores[i] = (EnumNetherOre)o;
			i++;
		}
		this.setDefaultState(this.blockState.getBaseState().withProperty(ORE, EnumNetherOre.byMetadata(arrayIndexIn, 0)));
	}
	
    /**
     * @since 1.6
     */
    public static Object[] copyOfRange(Object[] original, int from, int to) {
        return copyOfRange(original, from, to, original.getClass());
    }

    /**
     * @since 1.6
     */
    public static Object[] copyOfRange(Object[] original, int from, int to, Class newType) {
        int newLength = to - from;
        if (newLength < 0) throw new IllegalArgumentException(from + " > " + to);
        Object[] arr = (newType == Object[].class) ? new Object[newLength] :
            (Object[])Array.newInstance(newType.getComponentType(), newLength);
        int ceil = original.length-from;
        int len = (ceil < newLength) ? ceil : newLength;
        System.arraycopy(original, from, arr, 0, len);
        return arr;
    }

	
	public int getBlockArrayIndex() { return this.arrayIndex; }

	
    /**
     * State and fortune sensitive version, this replaces the old (int meta, Random rand)
     * version in 1.1.
     *
     * @param state Current state
     * @param fortune Current item fortune level
     * @param random Random number generator
     * @return The number of items to drop
     */
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
    	EnumNetherOre ore = (EnumNetherOre) state.getValue(ORE);
    	int i = ore.getDropCount();
    	if(i > 1) {
    		int j = fortune > 0 ? random.nextInt(fortune + 1) + 1 : 1;
			return random.nextInt(i * j) + j;
    	}
        return quantityDroppedWithBonus(fortune, random);
    }
    /**
     * Return true from this function if the player with silk touch can harvest this block directly, and not it's normal drops.
     *
     * @param world The world
     * @param pos Block position in world
     * @param state current block state
     * @param player The player doing the harvesting
     * @return True if the block can be directly harvested using silk touch
     */
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player){
        return true;
    }
    /**
     * This gets a complete list of items dropped from this block.
     *
     * @param drops add all items this block drops to this drops list
     * @param world The current world
     * @param pos Block position in world
     * @param state Current state
     * @param fortune Breakers fortune level
     */
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;

        int count = quantityDropped(state, fortune, rand);
        ItemStack stack = new ItemStack(this.getItemDropped(state, rand, fortune), 1, this.damageDropped(state));
        EnumNetherOre ore = state.getValue(ORE);
        if(ore.getDropItem() != ItemStack.EMPTY) {
        	stack = ore.getDropItem();
        }
        else {
        	count = 1;
        }
        for (int i = 0; i < count; i++)
        {
        	drops.add(stack.copy());
        }
    }
	
	private Random rand = new Random();
	@Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune){
		int exp = 0;
		int meta = state.getBlock().getMetaFromState(state);
		if (ores[meta].getDropCount() > 1 && ores[meta].getDropItem() != ItemStack.EMPTY)
		{
			exp = ores[meta].getExp();
			exp = MathHelper.getInt(rand, exp, exp * 2 + 1) + (exp > 1 ? rand.nextInt(exp) : 0);
		}
		return exp;
	}
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items){
        for (EnumNetherOre ore : this.ores){
            items.add(new ItemStack(this, 1, ore.getMetadata()));
        }
    }
    public int damageDropped(IBlockState state){
        return ((EnumNetherOre)state.getValue(ORE)).getMetadata();
    }
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(ORE, EnumNetherOre.byMetadata(getBlockArrayIndex(), meta));
    }
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state){
        return ((EnumNetherOre)state.getValue(ORE)).getMetadata();
    }
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {ORE});
    }
    
    public EnumNetherOre[] getOreTypes() { return this.ores; }
    
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side){
        return side == EnumFacing.UP;
    }
    
    private ThreadLocal<Boolean> explode = new ThreadLocal<Boolean>(), willAnger = new ThreadLocal<Boolean>();

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest){
		boolean silky = player == null || !(player.getHeldItemMainhand() != ItemStack.EMPTY && EnchantmentHelper.getEnchantments(player.getHeldItemMainhand()).containsKey(Enchantments.SILK_TOUCH)); //rewrite for 1.12.2
		explode.set(silky);
		willAnger.set(true);
		boolean r = super.removedByPlayer(state, world, pos, player, willHarvest);
		if(silky) angerPigmen(player, world, pos);
		willAnger.set(false);
		explode.set(true);
		return r;
	}
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
		if (explode.get() != Boolean.FALSE)
			checkExplosionChances(worldIn, pos);
		if (willAnger.get() != Boolean.TRUE)
			angerPigmen(null, worldIn, pos);
		super.breakBlock(worldIn, pos, state);
	}
	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion)
	{
		explode.set(false);
		willAnger.set(NetherMineCore.options.oreAngerZombiePigmen || explosion == null || !(explosion.getExplosivePlacedBy() instanceof EntityLiving));
		super.onBlockExploded(world, pos, explosion);
		willAnger.set(true);
		explode.set(true);
		if(NetherMineCore.options.oreExplosionChainReactions) checkExplosionChances(world, pos);
	}
	private void checkExplosionChances(World world, BlockPos pos){
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if(!world.isRemote && NetherMineCore.options.oreExplosions){
			for (int xOffset = -1; xOffset <= 1; xOffset++){
				for (int yOffset = -1; yOffset <= 1; yOffset++){
					for (int zOffset = -1; zOffset <= 1; zOffset++){
						if ((xOffset | yOffset | zOffset) == 0) continue;
						int tx = x + xOffset;
						int ty = y + yOffset;
						int tz = z + zOffset;
						IBlockState blockState = world.getBlockState(new BlockPos(tx, ty, tz));
						if(blockState == null) continue;
						if (blockState.getBlock() instanceof BlockNetherOre && ((world.rand.nextFloat() * 100F) <= NetherMineCore.options.oreExplosionChance)){
							EntityArmedOre eao = new EntityArmedOre(world, tx + 0.5, ty + 0.5, tz + 0.5, blockState.getBlock());
							world.spawnEntity(eao);
							world.playSound(null, pos, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
					}
				}
			}
		}
	}
	public void angerPigmen(EntityPlayer player, World world, BlockPos pos){
		final int range = NetherMineCore.options.oreAngerZombiePigmenRange; 
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (NetherMineCore.options.oreAngerZombiePigmen){
			AxisAlignedBB bb = new AxisAlignedBB(x - range, y - range, z - range, x + range + 1, y + range + 1, z + range + 1);
			List<EntityPigZombie> list = world.getEntitiesWithinAABB(EntityPigZombie.class, bb);
			for (int j = 0; j < list.size(); j++) this.setPigmanAngryAt(list.get(j), player);
		}
	}
	
	private void setPigmanAngryAt(EntityPigZombie pig, EntityLivingBase target) {
	    pig.angerLevel = 350 + this.rand.nextInt(400);
	    pig.randomSoundDelay = this.rand.nextInt(40);
	    if(target != null && target instanceof EntityLivingBase) {
	    	pig.setRevengeTarget(target);
	    }
	}
}
