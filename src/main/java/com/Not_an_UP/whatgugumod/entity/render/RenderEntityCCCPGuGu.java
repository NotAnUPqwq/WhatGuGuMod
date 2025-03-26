package com.Not_an_UP.whatgugumod.entity.render;

import com.Not_an_UP.whatgugumod.entity.EntityCCCPGuGu;
import com.Not_an_UP.whatgugumod.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityCCCPGuGu extends RenderSnowball<EntityCCCPGuGu>{
	public RenderEntityCCCPGuGu(RenderManager renderManagerIn) {
        super(renderManagerIn, ModItems.CCCP_GUGU, Minecraft.getMinecraft().getRenderItem());
    }
}
