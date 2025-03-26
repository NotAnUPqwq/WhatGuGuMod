package com.Not_an_UP.whatgugumod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityGuGuEgg extends EntityEgg{
	public EntityGuGuEgg(World worldIn)
    {
        super(worldIn);
    }

    public EntityGuGuEgg(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityGuGuEgg(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
        	if (this.rand.nextInt(8) == 0) {
        		int i = 1;
        		
        		for (int k = 0; k < 3; k++) {
	        		if (this.rand.nextInt(8) == 0) {
	        			i++;
	        		}else {break;}
        		}
        		
        		for (int j = 0; j < i; j++) {
	        		EntityGuGu entitygugu = new EntityGuGu(this.world);
	                entitygugu.setGrowingAge(-24000);
	                entitygugu.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
	                this.world.spawnEntity(entitygugu);
	            }
            }
        	this.setDead();
        }
    }
}
