package com.Not_an_UP.whatgugumod.items.food;

import java.util.List;

import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.Main;
import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FoodBase extends ItemFood implements IHasModel{
	
	public FoodBase(String name, int amount, float saturation, boolean isWolfFood, CreativeTabs tab) {
		
		super(amount, saturation, isWolfFood);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		ModItems.ITEMS.add(this);
		
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch(this.getUnlocalizedName()) {
		case ("item.raw_trim"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "烤一下会更好吃。");
			break;
		case ("item.compressed_raw_trim"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "压缩小麦？");
			break;
		case ("item.cooked_trim"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "好吃！");
			break;
		case ("item.compressed_cooked_trim"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "压缩饼干？");
			break;
		case ("item.cooked_gugu_egg"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "煮……蛋qwq");
			break;
    	}
    }
}
