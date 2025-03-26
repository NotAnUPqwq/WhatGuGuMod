package com.Not_an_UP.whatgugumod.entity;

import java.util.Random;

import com.Not_an_UP.whatgugumod.init.ModBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityCCCPGuGu extends EntitySnowball{
	public EntityCCCPGuGu(World worldIn) {
        super(worldIn);
    }

    public EntityCCCPGuGu(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
        	world.playSound(null, this.getPosition(), SoundEvents.ENTITY_CHICKEN_DEATH, SoundCategory.NEUTRAL, 1.0f, new Random().nextFloat()/2 + 0.8f);
        	this.entityDropItem(new ItemStack(Item.getItemFromBlock(ModBlocks.ALL_TETRIS_BLOCKS.get(this.rand.nextInt(ModBlocks.ALL_TETRIS_BLOCKS.size()))),4), 0.0F);
            this.setDead();
        }
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();

        // 在每一帧更新时生成红石粒子
        if (this.world.isRemote) {
            this.world.spawnParticle(EnumParticleTypes.REDSTONE, 
                this.posX, this.posY, this.posZ, 
                0.0, 0.0, 0.0);
        }
    }
}
