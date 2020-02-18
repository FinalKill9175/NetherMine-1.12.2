package com.finalkg.nethermine.client.renderer.entity;

import com.finalkg.nethermine.common.entity.EntityArmedOre;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityArmedOre extends Render<EntityArmedOre> {

	public RenderEntityArmedOre(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.0F;
	}
	public void renderArmedOre(EntityArmedOre ore, double f1, double f2, double f3, float par8, float par9){}

	@Override
	public void doRender(EntityArmedOre entity, double x, double y, double z, float par8, float par9){
		renderArmedOre(entity, x, y, z, par8, par9);
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityArmedOre entity) {
		return null;
	}

}
