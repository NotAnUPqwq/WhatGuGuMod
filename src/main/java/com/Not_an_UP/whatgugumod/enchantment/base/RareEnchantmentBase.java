package com.Not_an_UP.whatgugumod.enchantment.base;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class RareEnchantmentBase extends EnchantmentBase{

	public RareEnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] slot) {
        super(name, rarity, type, slot);
    }
	
	@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false; // 不能在附魔台上获得
    }
}
