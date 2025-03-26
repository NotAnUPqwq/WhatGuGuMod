package com.Not_an_UP.whatgugumod.enchantment;

import com.Not_an_UP.whatgugumod.enchantment.base.RareEnchantmentBase;
import com.Not_an_UP.whatgugumod.items.armor.ArmorBase;
import com.Not_an_UP.whatgugumod.items.tools.IGuGuTool;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ConstellationEnchantment extends RareEnchantmentBase{
	public static final ConstellationEnchantment INSTANCE = new ConstellationEnchantment();
	
	public ConstellationEnchantment() {
        super("constellation", Rarity.VERY_RARE, EnumEnchantmentType.ALL, EntityEquipmentSlot.values());
    }
	
	@Override
    public int getMaxLevel() {
        return 6;
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
		Item get = stack.getItem();
        return (get instanceof IGuGuTool) ? true : (get instanceof ArmorBase);
    }
	
	@Override
    public boolean canApplyTogether(Enchantment ench) {
        // 允许相同类型的附魔合并
        if (ench instanceof ConstellationEnchantment) {
        	ConstellationEnchantment other = (ConstellationEnchantment)ench;
            return this.getMaxLevel() >= other.getMaxLevel(); // 确保合并后的等级不超过最大等级
        }
        return super.canApplyTogether(ench);
    }
	
	@Override
    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType) {
        switch(level) {
        case 0: return 0.0f;
        case 1: return 0.1f;
        case 2: return 1.0f;
        case 3: return 1.1f;
        case 4: return 2.0f;
        case 5: return 2.1f;
        case 6: return 3.0f;
        default: return level * 0.5f;
        }
    }
}
