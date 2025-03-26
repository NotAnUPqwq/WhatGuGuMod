package com.Not_an_UP.whatgugumod.entity.intertwined_gugu;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public abstract class EntityIntertwinedGuGu extends EntitySnowball{
	public EntityIntertwinedGuGu(World worldIn) {super(worldIn);}
	public EntityIntertwinedGuGu(World worldIn, EntityLivingBase throwerIn) {super(worldIn, throwerIn);}
	protected Item drop;
	public Item getDrop() {return this.drop;}
	protected void setDrop(Item getDrop) {this.drop = getDrop;}
	protected EnumParticleTypes particle;
	public EnumParticleTypes getParticle() {return this.particle;}
	protected void setParticle(EnumParticleTypes getParticle) {this.particle = getParticle;}
	
	@Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
        	world.playSound(null, this.getPosition(), SoundEvents.ENTITY_CHICKEN_DEATH, SoundCategory.NEUTRAL, 1.0f, new Random().nextFloat()/2 + 0.8f);
        	this.entityDropItem(new ItemStack(this.drop), 0.0F);

        	if (this.getClass() == EntityIntertwinedGuGuFiveStar.class) {
	    		String message = TextFormatting.GOLD+""+TextFormatting.BOLD  + "喜报 ： " + (this.thrower != null ? this.thrower.getName() : "梅友仁") + "在咕咕抽卡中抽出了金色咕咕币！";
	            Minecraft.getMinecraft().getIntegratedServer().getPlayerList().sendMessage(new TextComponentString(message));
        	}
        	
        	this.setDead();
        }
    }
	
	@Override
    public void onUpdate() {
        super.onUpdate();
        // 在每一帧更新时生成粒子
        if (this.world.isRemote) {
        	for (int i = 0; i < 5; i++) {
    			this.world.spawnParticle(this.particle, 
    					this.posX, this.posY, this.posZ, 
    	                (Math.random() - 0.5) * 0.2,
    	                (Math.random() - 0.5) * 0.2,
    	                (Math.random() - 0.5) * 0.2);
    		}
        }
    }
}