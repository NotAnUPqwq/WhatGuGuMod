package com.Not_an_UP.whatgugumod.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuGuBlock extends BlockBase{
	
	public GuGuBlock(String name, Material material, CreativeTabs tab) {
		
		super(name, material, tab);
		
		setSoundType(SoundType.CLOTH);
		setHardness(0.8f);
		setResistance(0.8f);
		setHarvestLevel("axe", 0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch(this.getUnlocalizedName()) {
    	case ("tile.gugu_block"):
    		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕咕咕咕咕咕咕咕咕咕咕！");
			break;
    	case ("tile.compressed_gugu_block"):
    		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕咕！.zip（内含“咕”x108）");
			break;
		}
    }
}
