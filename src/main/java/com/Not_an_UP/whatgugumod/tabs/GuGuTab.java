package com.Not_an_UP.whatgugumod.tabs;

import com.Not_an_UP.whatgugumod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class GuGuTab extends CreativeTabs {
	
	public GuGuTab() {
		super("gugu_tab");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.GUGU);
	}

}
