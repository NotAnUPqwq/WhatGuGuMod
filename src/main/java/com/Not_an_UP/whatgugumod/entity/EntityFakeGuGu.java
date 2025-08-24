package com.Not_an_UP.whatgugumod.entity;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFakeGuGu extends EntityGuGu {

	public EntityFakeGuGu(World worldIn) {
		super(worldIn);
	}
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
		if (!world.isRemote) {
			if (!this.isChild() & player.getHeldItem(EnumHand.MAIN_HAND).isEmpty()) {
				
				this.attackEntityFrom(DamageSource.causePlayerDamage(player), 1145);
				
				if (player instanceof EntityPlayerMP) {
					PlayerAdvancements advancements = ((EntityPlayerMP)player).getAdvancements();
					Advancement advancement = ((EntityPlayerMP)player).mcServer.getAdvancementManager().getAdvancement(new ResourceLocation("whatgugumod", "fake_gugu"));
					if (advancement != null) {
				        AdvancementProgress progress = advancements.getProgress(advancement);
				        if (!progress.isDone()) {  // 防止重复触发
				            for (String criterion : progress.getRemaningCriteria()) {
				                advancements.grantCriterion(advancement, criterion);  // 完成条件
				            }
				        }
				    }
				}
			}
    	}
		return super.processInteract(player, hand);
    }

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		
		if (!this.isChild() & !world.isRemote) {
			EntityDarkGuGu dark = new EntityDarkGuGu(world);
			dark.motionY = 0.5;
			dark.setPosition(this.posX, this.posY, this.posZ);;
			world.spawnEntity(dark);
		}
	}
	
	@Override
	public EntityGuGu createChild(EntityAgeable ageable)
    {
        return new EntityFakeGuGu(this.world);
    }
}
