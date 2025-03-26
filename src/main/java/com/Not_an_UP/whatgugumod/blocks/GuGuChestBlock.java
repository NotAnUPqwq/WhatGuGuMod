package com.Not_an_UP.whatgugumod.blocks;

import java.util.List;
import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.init.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuGuChestBlock extends BlockBase{
	public final int type;
	public static final int[] primogemsNum = {5, 10, 20};
	
	public GuGuChestBlock(String name, Material material, CreativeTabs tab, int type) {
		super(name, material, tab);
		this.type = type;
		
		setSoundType(SoundType.WOOD);
		setHardness(2.5f);
		setResistance(2.5f);
		setHarvestLevel("axe", 0);
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		switch(this.type) {
		case(2):
			drops.add(new ItemStack(Items.DIAMOND, 1 + (RANDOM.nextInt(4) <= fortune ? 2 : 0) ));
		case(1):
			drops.add(new ItemStack(ModItems.GUGU_STEEL_INGOT, this.type + RANDOM.nextInt(1 + fortune) ));
			drops.add(new ItemStack(Items.GOLD_INGOT, this.type + RANDOM.nextInt(1 + fortune) ));
		default:
			drops.add(new ItemStack(ModItems.GUGU, 1 + this.type + RANDOM.nextInt(2 + fortune) ));
			drops.add(new ItemStack(Items.IRON_INGOT, 1 + this.type + RANDOM.nextInt(2 + fortune) ));
			drops.add(new ItemStack(ModItems.GUGU_PRIMOGEMS, primogemsNum[this.type] ));
		}
		
		if (RANDOM.nextFloat() < 0.03 * (this.type + fortune + 1)) {
			drops.add(new ItemStack(ModItems.GUGU_CHEST_COIN, 1));
		}
	}
	
    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
    	switch (this.type) {
		case (0):
			return 5 * (fortune + 1);
		case (1):
			return 10 * (fortune + 1);
		case (2):
			return 20 * (fortune + 1);
		default:
			return 0;
		}
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch(this.type) {
    	case (0):
        	tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "精致咕咕宝箱，掉5咕咕原石！");
        	break;
        case (1):
        	tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "珍贵咕咕宝箱，掉10咕咕原石！");
        	break;
        case (2):
        	tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "华丽咕咕宝箱，掉20咕咕原石！");
    		break;
		}
    }
}
