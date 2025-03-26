package com.Not_an_UP.whatgugumod.entity.render;

import com.Not_an_UP.whatgugumod.entity.EntityGuGuEgg;
import com.Not_an_UP.whatgugumod.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityGuGuEgg extends RenderSnowball<EntityGuGuEgg>{
	public RenderEntityGuGuEgg(RenderManager renderManagerIn) {
        super(renderManagerIn, ModItems.GUGU_EGG, Minecraft.getMinecraft().getRenderItem());
    }
}
