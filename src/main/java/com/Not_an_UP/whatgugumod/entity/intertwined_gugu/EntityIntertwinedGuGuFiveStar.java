package com.Not_an_UP.whatgugumod.entity.intertwined_gugu;

import com.Not_an_UP.whatgugumod.init.ModItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityIntertwinedGuGuFiveStar extends EntityIntertwinedGuGu {
	protected void setter() {
		this.setDrop(ModItems.FIVE_STAR_GUGU_COIN);
		this.setParticle(EnumParticleTypes.FLAME);
	}
	
	public EntityIntertwinedGuGuFiveStar(World worldIn) {
		super(worldIn);
		this.setter();
	}
	
	public EntityIntertwinedGuGuFiveStar(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
		this.setter();
	}
}
