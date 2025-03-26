package com.Not_an_UP.whatgugumod.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGuGuStar extends ItemBase{

	public ItemGuGuStar(String name, CreativeTabs tab) {
		super(name, tab);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch(this.getUnlocalizedName()) {
		case ("item.five_star_star"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "五星装备和工具命座的解锁材料！");
			break;
		case ("item.four_star_star"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "四星装备命座的解锁材料！");
			break;
		case ("item.three_star_star"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "三星工具命座的解锁材料！");
			break;
		}
    }
}
