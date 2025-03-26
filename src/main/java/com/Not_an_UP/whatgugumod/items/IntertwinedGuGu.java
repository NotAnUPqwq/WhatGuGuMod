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

public class IntertwinedGuGu extends ItemBase {
	private int type = -1;
	public IntertwinedGuGu(String name, CreativeTabs tab) {
		super(name, tab);
	}
	
	public IntertwinedGuGu(String name, CreativeTabs tab, int type) {
		super(name, tab);
		this.type = type;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch (this.getUnlocalizedName()) {
		case ("item.intertwined_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕咕，右键开始咕咕抽卡！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "概率公示 ：");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "三星综合概率 ： 90%");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "四星综合概率 ： 8%");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "五星综合概率 ： 2%");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "没有保底系统。");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "抽卡的时候头朝天会更炫一点！");
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
        	if (this.type == -1) {
	        	double getGacha = itemRand.nextFloat();
	        	if (getGacha < 0.02) {
	        		snowball = new EntityIntertwinedGuGuFiveStar(worldIn, playerIn);
	        	}else if(getGacha < 0.15) {
	        		snowball = new EntityIntertwinedGuGuFourStar(worldIn, playerIn);
	        	}else {
	        		snowball = new EntityIntertwinedGuGuThreeStar(worldIn, playerIn);
	        	}
	        }else {
	        	switch (this.type) {
	        	case 0:
	        		snowball = new EntityIntertwinedGuGuThreeStar(worldIn, playerIn);
		        	break;
	        	case 1:
	        		snowball = new EntityIntertwinedGuGuFourStar(worldIn, playerIn);
		        	break;
	        	case 2:
	        		snowball = new EntityIntertwinedGuGuFiveStar(worldIn, playerIn);
	        		break;
	        	}
	        }
        	snowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(snowball);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
