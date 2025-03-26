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

public class CoalGuGuBlock extends BlockBase{
	
	public CoalGuGuBlock(String name, Material material, CreativeTabs tab) {
		super(name, material, tab);
		
		setSoundType(SoundType.STONE);
		setHardness(5.0f);
		setResistance(6.0f);
		setHarvestLevel("pickaxe", 0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch(this.getUnlocalizedName()) {
    	case ("tile.coal_gugu_block"):
    		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "这块咕咕可以作为燃料，提供燃烧18个物品的燃烧时间。");
	    	break;
		}
    }
}
