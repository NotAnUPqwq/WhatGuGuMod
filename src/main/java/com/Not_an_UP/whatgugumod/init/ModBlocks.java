package com.Not_an_UP.whatgugumod.init;

import java.util.ArrayList;
import java.util.List;

import com.Not_an_UP.whatgugumod.Main;
import com.Not_an_UP.whatgugumod.blocks.CoalGuGuBlock;
import com.Not_an_UP.whatgugumod.blocks.GuGuBlock;
import com.Not_an_UP.whatgugumod.blocks.GuGuChestBlock;
import com.Not_an_UP.whatgugumod.blocks.GuGuHarderOre;
import com.Not_an_UP.whatgugumod.blocks.GuGuOre;
import com.Not_an_UP.whatgugumod.blocks.GuGuStoneBlock;
import com.Not_an_UP.whatgugumod.blocks.TetrisGuGuBlock;
import com.Not_an_UP.whatgugumod.blocks.TrimBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class ModBlocks {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final List<Block> ALL_TETRIS_BLOCKS = new ArrayList<Block>();
	// material
	private static class CoalBlockMaterial extends Material{
		public static final CoalBlockMaterial INSTANCE = new CoalBlockMaterial(MapColor.BLACK);
		public CoalBlockMaterial(MapColor color) {
			super(color);
			this.setRequiresTool();
			this.setBurning();
		}
	}
	
	// block
	public static final Block RAW_TRIM_BLOCK = new TrimBlock("raw_trim_block", Material.CLAY, Main.GUGU_TAB);
	public static final Block COOKED_TRIM_BLOCK = new TrimBlock("cooked_trim_block", Material.CLAY, Main.GUGU_TAB);
	public static final Block COMPRESSED_RAW_TRIM_BLOCK = new TrimBlock("compressed_raw_trim_block", Material.CLAY, Main.GUGU_TAB);
	public static final Block COMPRESSED_COOKED_TRIM_BLOCK = new TrimBlock("compressed_cooked_trim_block", Material.CLAY, Main.GUGU_TAB);

	public static final Block GUGU_BLOCK = new GuGuBlock("gugu_block", Material.CLOTH, Main.GUGU_TAB);
	public static final Block COMPRESSED_GUGU_BLOCK = new GuGuBlock("compressed_gugu_block", Material.CLOTH, Main.GUGU_TAB);

	public static final Block COAL_GUGU_BLOCK = new CoalGuGuBlock("coal_gugu_block", CoalBlockMaterial.INSTANCE, Main.GUGU_TAB);
	
	// ore
	public static final Block RAW_TRIM_ORE = new GuGuOre("raw_trim_ore", Material.ROCK, Main.GUGU_TAB);
	public static final Block GUGU_ORE = new GuGuOre("gugu_ore", Material.ROCK, Main.GUGU_TAB);
	public static final Block COAL_GUGU_ORE = new GuGuOre("coal_gugu_ore", Material.ROCK, Main.GUGU_TAB);
	public static final Block GUGU_PRIMOGEMS_ORE = new GuGuHarderOre("gugu_primogems_ore", Material.ROCK, Main.GUGU_TAB);
	
	// special
	public static final Block WHITE_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("white_tetris_gugu_block", Main.GUGU_TAB, 0);        
	public static final Block ORANGE_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("orange_tetris_gugu_block", Main.GUGU_TAB, 1);      
	public static final Block MAGENTA_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("magenta_tetris_gugu_block", Main.GUGU_TAB, 2);    
	public static final Block LIGHTBLUE_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("lightblue_tetris_gugu_block", Main.GUGU_TAB, 3);
	public static final Block YELLOW_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("yellow_tetris_gugu_block", Main.GUGU_TAB, 4);      
	public static final Block LIME_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("lime_tetris_gugu_block", Main.GUGU_TAB, 5);
	public static final Block PINK_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("pink_tetris_gugu_block", Main.GUGU_TAB, 6);
	public static final Block GRAY_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("gray_tetris_gugu_block", Main.GUGU_TAB, 7);
	public static final Block SILVER_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("silver_tetris_gugu_block", Main.GUGU_TAB, 8);
	public static final Block CYAN_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("cyan_tetris_gugu_block", Main.GUGU_TAB, 9);
	public static final Block PURPLE_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("purple_tetris_gugu_block", Main.GUGU_TAB, 10);
	public static final Block BLUE_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("blue_tetris_gugu_block", Main.GUGU_TAB, 11);
	public static final Block BROWN_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("brown_tetris_gugu_block", Main.GUGU_TAB, 12);
	public static final Block GREEN_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("green_tetris_gugu_block", Main.GUGU_TAB, 13);
	public static final Block RED_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("red_tetris_gugu_block", Main.GUGU_TAB, 14);
	public static final Block BLACK_TETRIS_GUGU_BLOCK = new TetrisGuGuBlock("black_tetris_gugu_block", Main.GUGU_TAB, 15);

	// added by python
	public static final Block GUGU_CHEST = new GuGuChestBlock("gugu_chest", Material.CLAY, Main.GUGU_TAB, 0);
	public static final Block GUGU_BIGGER_CHEST = new GuGuChestBlock("gugu_bigger_chest", Material.CLAY, Main.GUGU_TAB, 1);
	public static final Block GUGU_BIGGEST_CHEST = new GuGuChestBlock("gugu_biggest_chest", Material.CLAY, Main.GUGU_TAB, 2);
	public static final Block COMPRESSED_GUGU_PRIMOGEMS_BLOCK = new GuGuStoneBlock("compressed_gugu_primogems_block", Material.ROCK, Main.GUGU_TAB);
	public static final Block GUGU_PRIMOGEMS_BLOCK = new GuGuStoneBlock("gugu_primogems_block", Material.ROCK, Main.GUGU_TAB);
	public static final Block ORIROCK_BLOCK = new GuGuStoneBlock("orirock_block", Material.ROCK, Main.GUGU_TAB);

}
