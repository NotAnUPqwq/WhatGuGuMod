package com.Not_an_UP.whatgugumod.entity.render;

import com.Not_an_UP.whatgugumod.entity.EntityGuGu;
import com.Not_an_UP.whatgugumod.util.Reference;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderEntityGuGu extends RenderLiving<EntityGuGu>{
	private static final ResourceLocation GUGU_TEXTURES = new ResourceLocation(Reference.MOD_ID,"textures/entity/entity_gugu.png");

    public RenderEntityGuGu(RenderManager renderer)
    {
    	super(renderer, new ModelChicken(), 0.3F);
    }

    protected float handleRotationFloat(EntityGuGu livingBase, float partialTicks)
    {
        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }

	protected ResourceLocation getEntityTexture(EntityGuGu entity) {
		return GUGU_TEXTURES;
	}
}
