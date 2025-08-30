package com.Not_an_UP.whatgugumod.enchantment;

import com.Not_an_UP.whatgugumod.enchantment.base.RareEnchantmentBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class CarbonizationEnchantment extends RareEnchantmentBase{
	public static final CarbonizationEnchantment INSTANCE = new CarbonizationEnchantment();
	
	public CarbonizationEnchantment() {
        super("carbonization", Rarity.VERY_RARE, EnumEnchantmentType.ALL, EntityEquipmentSlot.values());
	}
	
	@Override
	public boolean isCurse() {
		return true;
	}
	
	@Override
    public int getMaxLevel() {
        return 1;
    }
	
	@Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 0;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return 0;
    }
	
	@Override
    public boolean canApply(ItemStack stack) {
		return false;
    }
	
	@Override
    public boolean canApplyTogether(Enchantment ench) {
        // 不允许相同类型的附魔合并
        return false;
    }
}
