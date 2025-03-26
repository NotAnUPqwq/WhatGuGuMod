package com.Not_an_UP.whatgugumod.entity.intertwined_gugu;

import com.Not_an_UP.whatgugumod.init.ModItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityIntertwinedGuGuFourStar extends EntityIntertwinedGuGu {
	protected void setter() {
		this.setDrop(ModItems.FOUR_STAR_COIN);
		this.setParticle(EnumParticleTypes.DRAGON_BREATH);
	}
	
	public EntityIntertwinedGuGuFourStar(World worldIn) {
		super(worldIn);
		this.setter();
	}
	
	public EntityIntertwinedGuGuFourStar(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
		this.setter();
	}
}
