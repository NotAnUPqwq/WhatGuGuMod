package com.Not_an_UP.whatgugumod.entity;

import com.Not_an_UP.whatgugumod.init.ModItems;

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
    		int i = 0;
    		
    		if (this.getTags().contains("shining")) {
    			i += 6;
    		}else {
	    		for (int k = 0; k < 4; k++) {
	        		if (this.rand.nextInt(8) == 0) 
	        			i++;
	        		else break;
	        	}
    		}
    		
    		for (int j = 0; j < i; j++) {
        		EntityGuGu entitygugu = new EntityGuGu(this.world);
                entitygugu.setGrowingAge(-24000);
                entitygugu.setLocationAndAngles(this.posX + this.rand.nextFloat()/20, this.posY, this.posZ + this.rand.nextFloat()/20, this.rotationYaw, 0.0F);
                this.world.spawnEntity(entitygugu);
            }
    		
    		this.dropItem(ModItems.GUGU_EGG_SHELL, 1);
    		
    		this.setDead();
        }
    }
}
