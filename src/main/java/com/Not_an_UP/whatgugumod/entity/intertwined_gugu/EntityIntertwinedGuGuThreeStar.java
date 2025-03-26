package com.Not_an_UP.whatgugumod.entity.intertwined_gugu;

import com.Not_an_UP.whatgugumod.init.ModItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityIntertwinedGuGuThreeStar extends EntityIntertwinedGuGu {
	protected void setter() {
		this.setDrop(ModItems.THREE_STAR_COIN);
		this.setParticle(EnumParticleTypes.WATER_WAKE);
	}
	
	public EntityIntertwinedGuGuThreeStar(World worldIn) {
		super(worldIn);
		this.setter();
	}
	
	public EntityIntertwinedGuGuThreeStar(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
        this.setter();
	}
}
