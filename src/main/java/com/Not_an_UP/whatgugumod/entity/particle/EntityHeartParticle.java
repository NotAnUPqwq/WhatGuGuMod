package com.Not_an_UP.whatgugumod.entity.particle;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityHeartParticle extends EntityParticleBase {
	public EntityHeartParticle(World worldIn) {
		super(worldIn, EnumParticleTypes.HEART);
	}
}