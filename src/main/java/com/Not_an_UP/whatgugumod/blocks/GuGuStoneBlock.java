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

public class GuGuStoneBlock extends BlockBase{
	
	public GuGuStoneBlock(String name, Material material, CreativeTabs tab) {
		
		super(name, material, tab);
		
		setSoundType(SoundType.STONE);
		setHardness(2.0f);
		setResistance(6.0f);
		setHarvestLevel("pickaxe", 0);
		setLightLevel(1.0f);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch(this.getUnlocalizedName()) {
    	case ("tile.compressed_gugu_primogems_block"):
    		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "内含36咕咕原石！");
			break;
    	case ("tile.gugu_primogems_block"):
    		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "内含4咕咕原石！");
			break;
    	case ("tile.orirock_block"):
    		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕使用大力压缩而成的圆石，有点变色了。");
			break;
		}
    }
}
