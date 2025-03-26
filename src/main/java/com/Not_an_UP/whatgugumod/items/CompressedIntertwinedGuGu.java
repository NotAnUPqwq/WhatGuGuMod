package com.Not_an_UP.whatgugumod.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuFiveStar;
import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuFourStar;
import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuThreeStar;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CompressedIntertwinedGuGu extends ItemBase {
	public CompressedIntertwinedGuGu(String name, CreativeTabs tab) {
		super(name, tab);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch (this.getUnlocalizedName()) {
		case ("item.compressed_intertwined_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕咕，右键开始十连咕咕抽卡！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "概率公示 ：");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "三星综合概率 ： 90%");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "四星综合概率 ： 8%");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "五星综合概率 ： 2%");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "没有保底系统。");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "抽卡的时候头朝天会更炫一点！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "（十连九折很合理吧）");
		}
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_SNOWMAN_SHOOT, SoundCategory.NEUTRAL, 1.0f, new Random().nextFloat()/2 + 0.8f);
		ItemStack itemstack = playerIn.getHeldItem(handIn);
        
        if (!playerIn.capabilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        if (!worldIn.isRemote) {
        	EntitySnowball snowball = null;
        	for (int i = 0; i < 10; i++) {
        		double getGacha = itemRand.nextFloat();
	        	if (getGacha < 0.02) {
	        		snowball = new EntityIntertwinedGuGuFiveStar(worldIn, playerIn);
	        	}else if(getGacha < 0.15) {
	        		snowball = new EntityIntertwinedGuGuFourStar(worldIn, playerIn);
	        	}else {
	        		snowball = new EntityIntertwinedGuGuThreeStar(worldIn, playerIn);
	        	}
	        	snowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
	            worldIn.spawnEntity(snowball);
        	}
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
