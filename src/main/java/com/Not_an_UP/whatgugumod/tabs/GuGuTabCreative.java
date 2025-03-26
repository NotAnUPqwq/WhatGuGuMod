package com.Not_an_UP.whatgugumod.tabs;

import com.Not_an_UP.whatgugumod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class GuGuTabCreative extends CreativeTabs {
	
	public GuGuTabCreative() {
		super("gugu_tab_creative");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.SURPRISED_GUGU);
	}

}
