package com.Not_an_UP.whatgugumod.blocks;

import java.util.List;
import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.init.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuGuHarderOre extends BlockBase{

	public GuGuHarderOre(String name, Material material, CreativeTabs tab) {
		super(name, material, tab);
		
		setSoundType(SoundType.STONE);
		setHardness(3.0f);
		setResistance(3.0f);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(1.0f);
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(this.getItemDropped(),
				                this.quantityDropped(fortune)));
		if (Math.random() < 0.08) {
			drops.add(new ItemStack(ModItems.GUGU_ORE_COIN, 1));
		}
	}
	
	public Item getItemDropped() {
		
		switch (this.getUnlocalizedName()) {
		case ("tile.gugu_primogems_ore"):
			return ModItems.GUGU_PRIMOGEMS;
		}
		return Item.getItemFromBlock(this);
	}
	
	public int quantityDropped(int fortune) {
		int min = 2 * (fortune * 2 + 10);
		
		return  min;
	}
	
    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        return RANDOM.nextInt(6)+3;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch(this.getUnlocalizedName()) {
        case ("tile.gugu_primogems_ore"):
        	tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "富含咕咕原石的发光矿物，采集后会掉落咕咕原石。");
    		break;
		}
    }
}
