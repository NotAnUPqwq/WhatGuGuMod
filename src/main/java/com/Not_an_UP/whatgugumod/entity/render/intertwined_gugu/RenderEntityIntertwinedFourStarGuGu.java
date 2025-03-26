package com.Not_an_UP.whatgugumod.entity.render.intertwined_gugu;

import com.Not_an_UP.whatgugumod.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityIntertwinedFourStarGuGu extends RenderSnowball<EntitySnowball>{

	public RenderEntityIntertwinedFourStarGuGu(RenderManager renderManagerIn) {
		super(renderManagerIn, ModItems.FOUR_STAR_GUGU, Minecraft.getMinecraft().getRenderItem());
	}

}
