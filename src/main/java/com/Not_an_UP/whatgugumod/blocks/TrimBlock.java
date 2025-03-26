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

public class TrimBlock extends BlockBase{
	
	public TrimBlock(String name, Material material, CreativeTabs tab) {
		super(name, material, tab);
		
		setSoundType(SoundType.SLIME);
		setHardness(0.6f);
		setResistance(3.0f);
		setHarvestLevel("shovel", 0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch(this.getUnlocalizedName()) {
        case ("tile.raw_trim_block"):
    		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "压缩小麦？");
    		break;
    	case ("tile.cooked_trim_block"):
    		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "压缩饼干？");
    		break;
    	case ("tile.compressed_raw_trim_block"):
    		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "压缩压缩小麦？");
    		break;
    	case ("tile.compressed_cooked_trim_block"):
    		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "压缩压缩饼干？");
    		break;
		}
    }
}
