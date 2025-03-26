package com.Not_an_UP.whatgugumod.enchantment.base;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentBase extends Enchantment{

	public EnchantmentBase(String name, Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] slot) {
        super(rarity, type, slot);
        
        this.setName(name);
        this.setRegistryName(name);
    }
}
