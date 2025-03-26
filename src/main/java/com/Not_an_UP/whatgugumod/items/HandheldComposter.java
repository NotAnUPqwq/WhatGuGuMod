package com.Not_an_UP.whatgugumod.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HandheldComposter extends ItemBase{
	
	public HandheldComposter(String name, CreativeTabs tab) {
		super(name, tab);
		this.setMaxStackSize(1);
	}
	
	@Override
	public Item getContainerItem() {
		return this;
	}
	
	@Override
	public boolean hasContainerItem() {
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "因为名字实在太长，在参与合成时不会被消耗。");
		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "具体使用方法写在中文名里，但是我还是要提一嘴：");
		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "与八个在高版本中可以堆肥的物品在工作台内合成就可以获得一个骨粉。");
	}
}
