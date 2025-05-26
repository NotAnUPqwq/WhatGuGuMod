package com.Not_an_UP.whatgugumod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityHeartParticle extends Entity{

	public EntityHeartParticle(World worldIn) {
		super(worldIn);
		this.setSize(0.0F, 0.0F);
        this.setInvisible(true);
	}
	
	@Override
    public void onUpdate() {
        super.onUpdate();
        
        for (int i = 0; i < 10; i++) {
        	// 随机偏移粒子的位置
        	double offsetX = this.rand.nextDouble(); // X方向随机偏移
        	double offsetZ = this.rand.nextDouble(); // Z方向随机偏移
        	world.spawnParticle(EnumParticleTypes.HEART, posX+offsetX, posY+0.5, posZ+offsetZ, 0, 5, 0);
        }
        
        this.setDead();
    }

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}

}
