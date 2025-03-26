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

public class GuGuOre extends BlockBase{

	public GuGuOre(String name, Material material, CreativeTabs tab) {
		super(name, material, tab);
		
		setSoundType(SoundType.STONE);
		setHardness(3.0f);
		setResistance(3.0f);
		setHarvestLevel("pickaxe", 0);
		setLightLevel(1.0f);
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(this.getItemDropped(),
				                this.quantityDropped(fortune)));
		if (Math.random() < 0.01 * (fortune + 2)) {
			drops.add(new ItemStack(ModItems.GUGU_ORE_COIN, 1));
		}
	}
	
	public Item getItemDropped() {
		
		switch (this.getUnlocalizedName()) {
		case ("tile.raw_trim_ore"):
			return ModItems.PIECE_OF_RAW_TRIM;
		case ("tile.gugu_ore"):
			return ModItems.PIECE_OF_GUGU;
		case ("tile.coal_gugu_ore"):
			return ModItems.PIECE_OF_COAL_GUGU;
		}
		return Item.getItemFromBlock(this);
	}
	
	public int quantityDropped(int fortune) {
		int min = 3 * (fortune + 2);
		int max = 9 * (fortune + 2);
		
		int delta = max - min;
		
		return RANDOM.nextInt(delta+1) + min;
	}
	
    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        return RANDOM.nextInt(6) + 3 + fortune;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch(this.getUnlocalizedName()) {
    	case ("tile.gugu_ore"):
        	tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "富含咕咕的发光矿物，采集后会掉落咕咕碎片。");
        	break;
        case ("tile.raw_trim_ore"):
        	tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "富含生只因的发光矿物，采集后会掉落生只因碎片。");
        	break;
        case ("tile.coal_gugu_ore"):
        	tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "富含碳化咕咕的发光矿物，采集后会掉落碳化咕咕碎片。");
    		break;
		}
    }
}
