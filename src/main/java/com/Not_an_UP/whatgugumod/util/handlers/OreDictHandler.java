package com.Not_an_UP.whatgugumod.util.handlers;

import com.Not_an_UP.whatgugumod.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {
	
	public static void init() {
		OreDictionary.registerOre("composterAccepted", Blocks.YELLOW_FLOWER);
		OreDictionary.registerOre("composterAccepted", Blocks.RED_MUSHROOM);
		OreDictionary.registerOre("composterAccepted", Blocks.RED_MUSHROOM_BLOCK);
		OreDictionary.registerOre("composterAccepted", Blocks.BROWN_MUSHROOM);
		OreDictionary.registerOre("composterAccepted", Blocks.BROWN_MUSHROOM_BLOCK);
		OreDictionary.registerOre("composterAccepted", Blocks.CACTUS);
		OreDictionary.registerOre("composterAccepted", Blocks.VINE);
		OreDictionary.registerOre("composterAccepted", Blocks.WATERLILY);
		OreDictionary.registerOre("composterAccepted", Blocks.PUMPKIN);
		OreDictionary.registerOre("composterAccepted", Blocks.NETHER_WART_BLOCK);
		
		OreDictionary.registerOre("composterAccepted", Items.WHEAT_SEEDS);
		OreDictionary.registerOre("composterAccepted", Items.WHEAT);
		OreDictionary.registerOre("composterAccepted", Items.REEDS);
		OreDictionary.registerOre("composterAccepted", Items.CARROT);
		OreDictionary.registerOre("composterAccepted", Items.POTATO);
		OreDictionary.registerOre("composterAccepted", Items.MELON_SEEDS);
		OreDictionary.registerOre("composterAccepted", Items.PUMPKIN_SEEDS);
		OreDictionary.registerOre("composterAccepted", Items.MELON);
		OreDictionary.registerOre("composterAccepted", Items.PUMPKIN_PIE);
		OreDictionary.registerOre("composterAccepted", Items.NETHER_WART);
		OreDictionary.registerOre("composterAccepted", Items.APPLE);
		OreDictionary.registerOre("composterAccepted", Items.BREAD);
		OreDictionary.registerOre("composterAccepted", Items.COOKIE);
		OreDictionary.registerOre("composterAccepted", Items.BAKED_POTATO);
		OreDictionary.registerOre("composterAccepted", Items.BEETROOT);
		OreDictionary.registerOre("composterAccepted", Items.BEETROOT_SEEDS);
		OreDictionary.registerOre("composterAccepted", Items.CAKE);
		
		OreDictionary.registerOre("composterAccepted", new ItemStack(Items.DYE,1,3));
		
		for (int i = 0 ; i <= 8 ; i++) {
			OreDictionary.registerOre("composterAccepted", new ItemStack(Blocks.RED_FLOWER,1,i));
		}
		for (int i = 0 ; i <= 5 ; i++) {
			OreDictionary.registerOre("composterAccepted", new ItemStack(Blocks.SAPLING,1,i));
			OreDictionary.registerOre("composterAccepted", new ItemStack(Blocks.DOUBLE_PLANT,1,i));
		}
		for (int i = 0 ; i <= 3 ; i++) {
			OreDictionary.registerOre("composterAccepted", new ItemStack(Blocks.LEAVES,1,i));
		}
		for (int i = 0 ; i <= 1 ; i++) {
			OreDictionary.registerOre("composterAccepted", new ItemStack(Blocks.LEAVES2,1,i));
			OreDictionary.registerOre("composterAccepted", new ItemStack(Blocks.TALLGRASS,1,i+1));
		}
		
		OreDictionary.registerOre("ingotSteel", ModItems.GUGU_STEEL_INGOT);
		OreDictionary.registerOre("ingotGuGuSteel", ModItems.GUGU_STEEL_INGOT);
		OreDictionary.registerOre("paperLightBlue", ModItems.LIGHTBLUE_PAPER);
		
	}

}
