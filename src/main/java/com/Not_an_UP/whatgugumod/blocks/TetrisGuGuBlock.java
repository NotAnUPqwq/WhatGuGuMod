package com.Not_an_UP.whatgugumod.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.init.ModBlocks;
import com.Not_an_UP.whatgugumod.init.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TetrisGuGuBlock extends BlockBase {
	public final EnumDyeColor colorType;
	public TetrisGuGuBlock(String name, CreativeTabs tab, int getColor) {
        super(name, Material.CLAY, tab);
        ModBlocks.ALL_TETRIS_BLOCKS.add(this);
        
        setSoundType(SoundType.STONE);
		setHardness(0.5f);
		setResistance(0.1f);
		setHarvestLevel("pickaxe", 0);
		
		this.colorType = EnumDyeColor.values()[getColor];
	}

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);
        checkForLine(world, pos);
    }

    private void checkForLine(World world, BlockPos pos) {
        // 检查水平方向（X轴）是否有十个连成一行
        checkLine(world, pos, 1, 0);
        // 检查水平方向（Z轴）是否有十个连成一行
        checkLine(world, pos, 0, 1);
    }

    private void checkLine(World world, BlockPos pos, int dx, int dz) {
        int count = 1; // 当前方块
        BlockPos startPos = pos;
        // 向一个方向查找
        while (true) {
            BlockPos nextPos = startPos.add(dx, 0, dz);
            if (world.getBlockState(nextPos).getBlock() instanceof TetrisGuGuBlock) {
                count++;
                startPos = nextPos;
            } else {break;}
        }
        // 向相反方向查找
        startPos = pos;
        while (true) {
            BlockPos nextPos = startPos.add(-dx, 0, -dz);
            if (world.getBlockState(nextPos).getBlock() instanceof TetrisGuGuBlock) {
                count++;
                startPos = nextPos;
            } else {break;}
        }
        if (count >= 10) {
        	clearLine(world, startPos, dx, dz);
        }

    }

    private void clearLine(World world, BlockPos pos, int dx, int dz) {
        // 向一个方向清除
    	BlockPos nextPos = new BlockPos(pos);
        while (true) {
            if (world.getBlockState(nextPos).getBlock() instanceof TetrisGuGuBlock) {
                world.setBlockToAir(nextPos);
                world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(ModItems.TETRIS_GUGU_COIN)));
            } else {break;}
            nextPos = nextPos.add(dx, 0, dz);
        }
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
//		switch(this.getUnlocalizedName()) {
//    	case ("tile.tetris_gugu_block"):
//    		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "十个俄罗斯咕块连成一行，会发生什么呢？");
//			break;
//		}
    	tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "像是什么东西的碎片，有什么用呢？");
    }
}
