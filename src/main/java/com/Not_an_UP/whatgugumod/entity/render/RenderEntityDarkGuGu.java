package com.Not_an_UP.whatgugumod.entity.render;

import com.Not_an_UP.whatgugumod.entity.EntityDarkGuGu;
import com.Not_an_UP.whatgugumod.util.Reference;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderEntityDarkGuGu extends RenderLiving<EntityDarkGuGu>{
	private static final ResourceLocation DARK_GUGU_TEXTURES = new ResourceLocation(Reference.MOD_ID,"textures/entity/entity_dark_gugu.png");

    public RenderEntityDarkGuGu(RenderManager renderer)
    {
    	super(renderer, new ModelChicken(), 0.3F);
    }

    protected float handleRotationFloat(EntityDarkGuGu livingBase, float partialTicks)
    {
        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }

	protected ResourceLocation getEntityTexture(EntityDarkGuGu entity) {
		return DARK_GUGU_TEXTURES;
	}
}
