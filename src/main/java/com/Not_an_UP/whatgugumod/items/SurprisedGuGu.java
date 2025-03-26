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

public class SurprisedGuGu extends ItemBase{
	
	public SurprisedGuGu(String name, CreativeTabs tab) {
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
		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "这只咕咕产生了无限的灵光，在参与合成时不会被消耗。");
		tooltip.add(TextFormatting.GOLD+""+TextFormatting.ITALIC + "“咕咕咕！你说得对，但是《Minecraft》是由瑞典游戏设计师马库斯·佩尔松（Notch）创造的一款开放世界沙盒建造游戏。游戏发生在一个由方块构成的无限世界中，玩家可以在其中自由探索、采集资源、建造建筑并与各种生物互动。玩家将扮演一位名为“史蒂夫”或“艾利克斯”的角色，在这个充满创造与冒险的世界中，通过合成、建造和探索来打造属于自己的独特体验。同时，逐步发掘“Minecraft”世界的无限可能性与隐藏的秘密。”");
    }
}
