package com.Not_an_UP.whatgugumod.init;

import java.util.ArrayList;
import java.util.List;

import com.Not_an_UP.whatgugumod.Main;
import com.Not_an_UP.whatgugumod.items.CCCPGuGu;
import com.Not_an_UP.whatgugumod.items.CompressedIntertwinedGuGu;
import com.Not_an_UP.whatgugumod.items.CraftingGuGu;
import com.Not_an_UP.whatgugumod.items.GuGuChest;
import com.Not_an_UP.whatgugumod.items.GuGuEgg;
import com.Not_an_UP.whatgugumod.items.HandheldComposter;
import com.Not_an_UP.whatgugumod.items.IntertwinedGuGu;
import com.Not_an_UP.whatgugumod.items.ItemBase;
import com.Not_an_UP.whatgugumod.items.ItemGuGuStar;
import com.Not_an_UP.whatgugumod.items.ItemPenguins;
import com.Not_an_UP.whatgugumod.items.ItemGuGu;
import com.Not_an_UP.whatgugumod.items.SurprisedGuGu;
import com.Not_an_UP.whatgugumod.items.armor.ArmorBase;
import com.Not_an_UP.whatgugumod.items.food.FoodBase;
import com.Not_an_UP.whatgugumod.items.tools.ToolAxe;
import com.Not_an_UP.whatgugumod.items.tools.ToolHoe;
import com.Not_an_UP.whatgugumod.items.tools.ToolPickaxe;
import com.Not_an_UP.whatgugumod.items.tools.ToolSpade;
import com.Not_an_UP.whatgugumod.items.tools.ToolSword;
import com.Not_an_UP.whatgugumod.util.Reference;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	// items
	public static final Item GUGU = new ItemGuGu("gugu", Main.GUGU_TAB);
	public static final Item PIECE_OF_GUGU = new ItemBase("piece_of_gugu", Main.GUGU_TAB);
	
	public static final Item GUGU_STICK = new ItemBase("gugu_stick", Main.GUGU_TAB);
	public static final Item OCTUPLE_COMPRESSED_GUGU_STICK = new ItemBase("octuple_compressed_gugu_stick", Main.GUGU_TAB);
	
	public static final Item COAL_GUGU = new ItemBase("coal_gugu", Main.GUGU_TAB);
	public static final Item PIECE_OF_COAL_GUGU = new ItemBase("piece_of_coal_gugu", Main.GUGU_TAB);
	
	public static final Item COMPRESSED_GUGU = new ItemBase("compressed_gugu", Main.GUGU_TAB);
	public static final Item DOUBLE_COMPRESSED_GUGU = new ItemBase("double_compressed_gugu", Main.GUGU_TAB);
	public static final Item TRIPLE_COMPRESSED_GUGU = new ItemBase("triple_compressed_gugu", Main.GUGU_TAB);
	public static final Item QUADRUPLE_COMPRESSED_GUGU = new ItemBase("quadruple_compressed_gugu", Main.GUGU_TAB);
	public static final Item QUINTUPLE_COMPRESSED_GUGU = new ItemBase("quintuple_compressed_gugu", Main.GUGU_TAB);
	public static final Item SEXTUPLE_COMPRESSED_GUGU = new ItemBase("sextuple_compressed_gugu", Main.GUGU_TAB);
	public static final Item SEPTUPLE_COMPRESSED_GUGU = new ItemBase("septuple_compressed_gugu", Main.GUGU_TAB);
	public static final Item OCTUPLE_COMPRESSED_GUGU = new ItemBase("octuple_compressed_gugu", Main.GUGU_TAB);
	
	public static final Item GUGU_INGOT = new ItemBase("gugu_ingot", Main.GUGU_TAB);
	public static final Item RAW_GUGU_STEEL_INGOT = new ItemBase("raw_gugu_steel_ingot", Main.GUGU_TAB);
	public static final Item GUGU_STEEL_INGOT = new ItemBase("gugu_steel_ingot", Main.GUGU_TAB);
	
	public static final Item SURPRISED_GUGU = new SurprisedGuGu("surprised_gugu", Main.GUGU_TAB);	

	public static final Item HANDHELD_COMPOSTER = new HandheldComposter("handheld_composter", Main.GUGU_TAB);
	
	public static final Item CRAFTING_GUGU = new CraftingGuGu("crafting_gugu", Main.GUGU_TAB);
	
	public static final Item GUGU_HANDHELD_CHEST = new GuGuChest("gugu_handheld_chest", Main.GUGU_TAB, (byte)0);
	public static final Item GUGU_HANDHELD_BIGGER_CHEST = new GuGuChest("gugu_handheld_bigger_chest", Main.GUGU_TAB, (byte)1);
	public static final Item GUGU_HANDHELD_BIGGEST_CHEST = new GuGuChest("gugu_handheld_biggest_chest", Main.GUGU_TAB, (byte)2);

	public static final Item GUGU_EGG = new GuGuEgg("gugu_egg", Main.GUGU_TAB);
	public static final Item GUGU_EGG_COIN = new ItemBase("gugu_egg_coin", Main.GUGU_TAB);
	
	public static final Item CCCP_GUGU = new CCCPGuGu("cccp_gugu", Main.GUGU_TAB);  
	public static final Item TETRIS_GUGU_COIN = new ItemBase("tetris_gugu_coin", Main.GUGU_TAB);
	
	public static final Item LIGHTBLUE_PAPER = new ItemBase("lightblue_paper", Main.GUGU_TAB);
	public static final Item GUGUMUN_NOTE = new ItemBase("gugumun_note", Main.GUGU_TAB);
	public static final Item GOLD_GUGUMUN_NOTE = new ItemBase("gold_gugumun_note", Main.GUGU_TAB);
	
	public static final Item GUGU_PRIMOGEMS = new ItemBase("gugu_primogems", Main.GUGU_TAB);
	public static final Item COMPRESSED_GUGU_PRIMOGEMS = new ItemBase("compressed_gugu_primogems", Main.GUGU_TAB);
	public static final Item INTERTWINED_GUGU = new IntertwinedGuGu("intertwined_gugu", Main.GUGU_TAB);
	public static final Item COMPRESSED_INTERTWINED_GUGU = new CompressedIntertwinedGuGu("compressed_intertwined_gugu", Main.GUGU_TAB);
	public static final Item THREE_STAR_COIN = new ItemBase("three_star_coin", Main.GUGU_TAB);
	public static final Item FOUR_STAR_COIN = new ItemBase("four_star_coin", Main.GUGU_TAB);
	public static final Item FIVE_STAR_GUGU_COIN = new ItemBase("five_star_gugu_coin", Main.GUGU_TAB);
	public static final Item GUGU_ORE_COIN = new ItemBase("gugu_ore_coin", Main.GUGU_TAB);  
	public static final Item GUGU_CHEST_COIN = new ItemBase("gugu_chest_coin", Main.GUGU_TAB);
	public static final Item GUGU_SOUND_COIN = new ItemBase("gugu_sound_coin", Main.GUGU_TAB);
	public static final Item GUGU_COIN = new ItemBase("gugu_coin", Main.GUGU_TAB);
	public static final Item GOLD_GUGU_COIN = new ItemBase("gold_gugu_coin", Main.GUGU_TAB);
	public static final Item FINAL_GUGU_COIN = new ItemBase("final_gugu_coin", Main.GUGU_TAB);
	
	//food
	public static final Item PIECE_OF_RAW_TRIM = new ItemBase("piece_of_raw_trim", Main.GUGU_TAB);
	public static final ItemFood RAW_TRIM = new FoodBase("raw_trim", 3, 1.0f, true, Main.GUGU_TAB);
	public static final ItemFood COOKED_TRIM = new FoodBase("cooked_trim", 8, 1.0f, true, Main.GUGU_TAB);
	public static final ItemFood COMPRESSED_RAW_TRIM = new FoodBase("compressed_raw_trim", 27, 1.0f, true, Main.GUGU_TAB);
	public static final ItemFood COMPRESSED_COOKED_TRIM = new FoodBase("compressed_cooked_trim", 72, 1.0f, true, Main.GUGU_TAB);

	//materials
	public static final ToolMaterial MATERIAL_GUGU = EnumHelper.addToolMaterial("material_gugu", 1, 1145141919, 5.0f, 1.0f, 1);
	public static final ArmorMaterial ARMOR_MATERIAL_GUGU = EnumHelper.addArmorMaterial("armor_material_gugu", Reference.MOD_ID + ":armor_material_gugu", 11451419, new int[] {2,4,5,2}, 1, SoundEvents.ENTITY_CHICKEN_AMBIENT, 0.25f);
	
	public static final ToolMaterial MATERIAL_OCTUPLE_COMPRESSED_GUGU = EnumHelper.addToolMaterial("material_optuple_compressed_gugu", 114, 1145141919, 114514.0f, 110.0f, 100);
	
	public static final ToolMaterial MATERIAL_GUGU_STEEL = EnumHelper.addToolMaterial("material_gugu_steel", 2, 1145141919, 7.0f, 2.2f, 4);
	public static final ArmorMaterial ARMOR_MATERIAL_GUGU_STEEL = EnumHelper.addArmorMaterial("armor_material_gugu_steel", Reference.MOD_ID + ":armor_material_gugu_steel", 11451419, new int[] {3,5,6,3}, 4, SoundEvents.ENTITY_CHICKEN_AMBIENT, 0.4f);
	
	public static final ToolMaterial MATERIAL_COIN_GUGU = EnumHelper.addToolMaterial("material_coin_gugu", 3, 1145141919, 5.0f, 1.0f, 12);
	public static final ArmorMaterial ARMOR_MATERIAL_COIN_GUGU = EnumHelper.addArmorMaterial("armor_material_coin_gugu", Reference.MOD_ID + ":armor_material_coin_gugu", 11451419, new int[] {0,3,5,0}, 12, SoundEvents.ENTITY_CHICKEN_AMBIENT, 1.2f);

	public static final ToolMaterial MATERIAL_FIVE_STAR_COIN_GUGU = EnumHelper.addToolMaterial("material_five_star_coin_gugu", 3, 1145141919, 8.0f, 3.0f, 20);
	public static final ArmorMaterial ARMOR_MATERIAL_FIVE_STAR_COIN_GUGU = EnumHelper.addArmorMaterial("armor_material_five_star_coin_gugu", Reference.MOD_ID + ":armor_material_five_star_coin_gugu", 11451419, new int[] {3,6,8,3}, 20, SoundEvents.ENTITY_CHICKEN_AMBIENT, 2.0f);
	
	//tools
	public static final ItemSword GUGU_SWORD = new ToolSword("gugu_sword", Main.GUGU_TAB, MATERIAL_GUGU);
	public static final ItemAxe GUGU_AXE = new ToolAxe("gugu_axe", Main.GUGU_TAB, MATERIAL_GUGU);
	public static final ItemPickaxe GUGU_PICKAXE = new ToolPickaxe("gugu_pickaxe", Main.GUGU_TAB, MATERIAL_GUGU);
	public static final ItemSpade GUGU_SPADE = new ToolSpade("gugu_spade", Main.GUGU_TAB, MATERIAL_GUGU);
	public static final ItemHoe GUGU_HOE = new ToolHoe("gugu_hoe", Main.GUGU_TAB, MATERIAL_GUGU);
	
	public static final ItemAxe GUGU_STEEL_AXE = new ToolAxe("gugu_steel_axe", Main.GUGU_TAB, MATERIAL_GUGU_STEEL);
	public static final ItemPickaxe GUGU_STEEL_PICKAXE = new ToolPickaxe("gugu_steel_pickaxe", Main.GUGU_TAB, MATERIAL_GUGU_STEEL);  
	public static final ItemSpade GUGU_STEEL_SPADE = new ToolSpade("gugu_steel_spade", Main.GUGU_TAB, MATERIAL_GUGU_STEEL);      
	public static final ItemSword GUGU_STEEL_SWORD = new ToolSword("gugu_steel_sword", Main.GUGU_TAB, MATERIAL_GUGU_STEEL);
	public static final ItemHoe GUGU_STEEL_HOE = new ToolHoe("gugu_steel_hoe", Main.GUGU_TAB, MATERIAL_GUGU_STEEL);
	
	public static final ItemSword OCTUPLE_COMPRESSED_GUGU_SWORD = new ToolSword("octuple_compressed_gugu_sword", Main.GUGU_TAB, MATERIAL_OCTUPLE_COMPRESSED_GUGU);
	public static final ItemPickaxe OCTUPLE_COMPRESSED_GUGU_PICKAXE = new ToolPickaxe("octuple_compressed_gugu_pickaxe", Main.GUGU_TAB, MATERIAL_OCTUPLE_COMPRESSED_GUGU);
	
	//armors
	public static final Item GUGU_HELMET = new ArmorBase("gugu_helmet",Main.GUGU_TAB, ARMOR_MATERIAL_GUGU, 1, EntityEquipmentSlot.HEAD);
	public static final Item GUGU_CHESTPLATE = new ArmorBase("gugu_chestplate",Main.GUGU_TAB, ARMOR_MATERIAL_GUGU, 1, EntityEquipmentSlot.CHEST);
	public static final Item GUGU_LEGGINGS = new ArmorBase("gugu_leggings",Main.GUGU_TAB, ARMOR_MATERIAL_GUGU, 2, EntityEquipmentSlot.LEGS);
	public static final Item GUGU_BOOTS = new ArmorBase("gugu_boots",Main.GUGU_TAB, ARMOR_MATERIAL_GUGU, 1, EntityEquipmentSlot.FEET);

	public static final Item GUGU_STEEL_HELMET = new ArmorBase("gugu_steel_helmet", Main.GUGU_TAB, ARMOR_MATERIAL_GUGU_STEEL, 1, EntityEquipmentSlot.HEAD);
	public static final Item GUGU_STEEL_CHESTPLATE = new ArmorBase("gugu_steel_chestplate", Main.GUGU_TAB, ARMOR_MATERIAL_GUGU_STEEL, 1, EntityEquipmentSlot.CHEST);
	public static final Item GUGU_STEEL_LEGGINGS = new ArmorBase("gugu_steel_leggings", Main.GUGU_TAB, ARMOR_MATERIAL_GUGU_STEEL, 2, EntityEquipmentSlot.LEGS);
	public static final Item GUGU_STEEL_BOOTS = new ArmorBase("gugu_steel_boots", Main.GUGU_TAB, ARMOR_MATERIAL_GUGU_STEEL, 1, EntityEquipmentSlot.FEET);
	
	// creative
	public static final Item FIVE_STAR_GUGU = new IntertwinedGuGu("five_star_gugu", Main.GUGU_TAB_CREATIVE, 2);
	public static final Item FOUR_STAR_GUGU = new IntertwinedGuGu("four_star_gugu", Main.GUGU_TAB_CREATIVE, 1);
	public static final Item THREE_STAR_GUGU = new IntertwinedGuGu("three_star_gugu", Main.GUGU_TAB_CREATIVE, 0);
	
	//added by python
	public static final ItemAxe COIN_GUGU_AXE = new ToolAxe("coin_gugu_axe", Main.GUGU_TAB, MATERIAL_COIN_GUGU);
	public static final ItemPickaxe COIN_GUGU_PICKAXE = new ToolPickaxe("coin_gugu_pickaxe", Main.GUGU_TAB, MATERIAL_COIN_GUGU);
	public static final ItemSpade COIN_GUGU_SPADE = new ToolSpade("coin_gugu_spade", Main.GUGU_TAB, MATERIAL_COIN_GUGU);
	public static final ItemSword COIN_GUGU_SWORD = new ToolSword("coin_gugu_sword", Main.GUGU_TAB, MATERIAL_COIN_GUGU);
	public static final ItemHoe COIN_GUGU_HOE = new ToolHoe("coin_gugu_hoe", Main.GUGU_TAB, MATERIAL_COIN_GUGU);
	public static final Item COIN_GUGU_HELMET = new ArmorBase("coin_gugu_helmet", Main.GUGU_TAB, ARMOR_MATERIAL_COIN_GUGU, 1, EntityEquipmentSlot.HEAD);
	public static final Item COIN_GUGU_CHESTPLATE = new ArmorBase("coin_gugu_chestplate", Main.GUGU_TAB, ARMOR_MATERIAL_COIN_GUGU, 1, EntityEquipmentSlot.CHEST);        
	public static final Item COIN_GUGU_LEGGINGS = new ArmorBase("coin_gugu_leggings", Main.GUGU_TAB, ARMOR_MATERIAL_COIN_GUGU, 2, EntityEquipmentSlot.LEGS);
	public static final Item COIN_GUGU_BOOTS = new ArmorBase("coin_gugu_boots", Main.GUGU_TAB, ARMOR_MATERIAL_COIN_GUGU, 1, EntityEquipmentSlot.FEET);
	public static final Item FIVE_STAR_STAR = new ItemGuGuStar("five_star_star", Main.GUGU_TAB);
	public static final Item FOUR_STAR_STAR = new ItemGuGuStar("four_star_star", Main.GUGU_TAB);
	public static final Item THREE_STAR_STAR = new ItemGuGuStar("three_star_star", Main.GUGU_TAB);
	public static final ItemAxe FIVE_STAR_COIN_GUGU_AXE = new ToolAxe("five_star_coin_gugu_axe", Main.GUGU_TAB, MATERIAL_FIVE_STAR_COIN_GUGU);
	public static final ItemPickaxe FIVE_STAR_COIN_GUGU_PICKAXE = new ToolPickaxe("five_star_coin_gugu_pickaxe", Main.GUGU_TAB, MATERIAL_FIVE_STAR_COIN_GUGU);
	public static final ItemSpade FIVE_STAR_COIN_GUGU_SPADE = new ToolSpade("five_star_coin_gugu_spade", Main.GUGU_TAB, MATERIAL_FIVE_STAR_COIN_GUGU);
	public static final ItemSword FIVE_STAR_COIN_GUGU_SWORD = new ToolSword("five_star_coin_gugu_sword", Main.GUGU_TAB, MATERIAL_FIVE_STAR_COIN_GUGU);
	public static final ItemHoe FIVE_STAR_COIN_GUGU_HOE = new ToolHoe("five_star_coin_gugu_hoe", Main.GUGU_TAB, MATERIAL_FIVE_STAR_COIN_GUGU);
	public static final Item FIVE_STAR_COIN_GUGU_HELMET = new ArmorBase("five_star_coin_gugu_helmet", Main.GUGU_TAB, ARMOR_MATERIAL_FIVE_STAR_COIN_GUGU, 1, EntityEquipmentSlot.HEAD);
	public static final Item FIVE_STAR_COIN_GUGU_CHESTPLATE = new ArmorBase("five_star_coin_gugu_chestplate", Main.GUGU_TAB, ARMOR_MATERIAL_FIVE_STAR_COIN_GUGU, 1, EntityEquipmentSlot.CHEST);
	public static final Item FIVE_STAR_COIN_GUGU_LEGGINGS = new ArmorBase("five_star_coin_gugu_leggings", Main.GUGU_TAB, ARMOR_MATERIAL_FIVE_STAR_COIN_GUGU, 2, EntityEquipmentSlot.LEGS);
	public static final Item FIVE_STAR_COIN_GUGU_BOOTS = new ArmorBase("five_star_coin_gugu_boots", Main.GUGU_TAB, ARMOR_MATERIAL_FIVE_STAR_COIN_GUGU, 1, EntityEquipmentSlot.FEET);
	public static final Item ITEM_PENGUINS = new ItemPenguins("item_penguins", Main.GUGU_TAB);
	public static final Item PIECE_OF_PENGUINS = new ItemPenguins("piece_of_penguins", Main.GUGU_TAB);

}
