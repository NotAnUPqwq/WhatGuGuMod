package com.Not_an_UP.whatgugumod.items;

import java.util.List;

import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.Main;
import com.Not_an_UP.whatgugumod.util.handlers.GuiHandler;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CraftingGuGu extends ItemBase{
	public CraftingGuGu(String name, CreativeTabs tab) {
		super(name, tab);
		
		setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world,EntityPlayer player, EnumHand hand){
		if (!world.isRemote) {
			player.openGui(Main.instance, GuiHandler.GUGU_WORKBENCH, world, 0, 0, 0);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕咕！咕咕怕你没有手持工作台模组，给你做了一个简易的，用起来和原版一模一样！（出bug也一模一样）快说谢谢咕咕qwq！");
	}
}
