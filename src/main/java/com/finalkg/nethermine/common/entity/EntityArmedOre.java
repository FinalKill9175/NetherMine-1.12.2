package com.finalkg.nethermine.common.entity;

import com.finalkg.nethermine.NetherMineCore;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Updated to 1.12.2
 * @author finalkg, PowerCrystals
 *
 */
public class EntityArmedOre extends Entity{

	private int _fuse;
	private Block _target;
	private double yOffset;

	public EntityArmedOre(World world)
	{
		super(world);
		_fuse = 80;
		noClip = true;
		preventEntitySpawning = false;
		setSize(0.0F, 0.0F);
		yOffset = height / 2.0F;

	}

	public EntityArmedOre(World world, double x, double y, double z, Block block)
	{
		this(world);
		setPosition(x, y, z);
		motionX = 0.0F;
		motionY = 0.0F;
		motionZ = 0.0F;
		_fuse = 80;
		prevPosX = x;
		prevPosY = y;
		prevPosZ = z;
		_target = block;
		if (_target != null)
			setAir(Block.getIdFromBlock(_target));
		else
			setAir(-1);
	}
	@Override
	protected void entityInit() {}

	@Override
	public boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return false;
	}

	@Override
	public void onUpdate()
	{
		if (_fuse-- <= 0)
		{
			setDead();

			if(!this.world.isRemote)
			{
				setInvisible(true);
				explode();
			}
		}
		else if (world.isRemote)
		{
			if (isInvisible())
				setDead();
			Block block = world.getBlockState(new BlockPos(MathHelper.floor(posX),
					MathHelper.floor(posY), MathHelper.floor(posZ))).getBlock();
			if (Block.getIdFromBlock(block) == getAir())
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	private void explode()
	{
		Block block = world.getBlockState(new BlockPos(MathHelper.floor(posX),
				MathHelper.floor(posY), MathHelper.floor(posZ))).getBlock();
		if (Block.isEqualTo(block, _target))
		{
			int exp_power = NetherMineCore.options.oreExplosionPower; //TODO create option. Max 8, min 0.
			if(NetherMineCore.options.changeNetherOreSettingsByGameDifficulty && this.world.getDifficulty() == EnumDifficulty.HARD) exp_power += this.rand.nextInt(2);
			world.newExplosion(null, posX, posY, posZ, exp_power, true, true);
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag)
	{
		tag.setByte("Fuse", (byte)_fuse);
		tag.setString("STarget", Block.REGISTRY.getNameForObject(_target).toString());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag)
	{
		_fuse = tag.getByte("Fuse");
		_target = Block.getBlockFromName(tag.hasKey("Target") ?
				Integer.toString(tag.getInteger("Target")) : tag.getString("STarget"));
		setAir(Block.getIdFromBlock(_target));
	}

    public double getYOffset(){return yOffset;}
}
