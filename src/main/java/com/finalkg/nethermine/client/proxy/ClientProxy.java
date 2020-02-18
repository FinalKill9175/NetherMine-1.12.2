package com.finalkg.nethermine.client.proxy;

import com.finalkg.nethermine.client.renderer.entity.RenderEntityArmedOre;
import com.finalkg.nethermine.common.entity.EntityArmedOre;
import com.finalkg.nethermine.common.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {
	public void registerProxies(){
		RenderingRegistry.registerEntityRenderingHandler(EntityArmedOre.class, new RenderEntityArmedOre(Minecraft.getMinecraft().getRenderManager()));
	}

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return (ctx.side.isClient() ? Minecraft.getMinecraft().player : super.getPlayerEntity(ctx));
	}
}
