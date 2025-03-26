package com.Not_an_UP.whatgugumod.util.handlers;

import java.text.DecimalFormat;
import java.util.List;

import com.Not_an_UP.whatgugumod.enchantment.ConstellationEnchantment;
import com.Not_an_UP.whatgugumod.items.tools.IGuGuTool;
import com.Not_an_UP.whatgugumod.util.UsefulFunc;

import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TooltipHandler {

	@SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof ItemArmor) {
            ItemArmor armor = (ItemArmor)stack.getItem();

            // 获取附魔等级
            int level = EnchantmentHelper.getEnchantmentLevel(ConstellationEnchantment.INSTANCE, stack);
            if (level > 0) {
                // 获取原版护甲值
                int getArmor = armor.getArmorMaterial().getDamageReductionAmount(armor.armorType);
                float getArmorToughness = armor.getArmorMaterial().getToughness();
                int indexArmorToughness = 0;
                boolean containArmorTooltip = false;
                // 计算调整后的护甲值
                getArmor += level;
                getArmorToughness = (getArmorToughness + level * 0.2f);
                // 遍历工具提示，找到原版护甲值并替换
                List<String> tooltip = event.getToolTip();
                for (int i = 0; i < tooltip.size(); i++) {
                    // 查找包含 "盔甲" 的行
                	String line = tooltip.get(i);
                	if (line.contains(I18n.format("attribute.name.generic.armorToughness"))) {
                		// 替换
                        tooltip.set(i, TextFormatting.BLUE + String.format(" +%.1f ", getArmorToughness) + I18n.format("attribute.name.generic.armorToughness"));
                        indexArmorToughness = i;
                        continue;
                	}else if (line.contains(I18n.format("attribute.name.generic.armor"))) {
                        // 替换
                		containArmorTooltip = true;
                        tooltip.set(i, TextFormatting.BLUE + " +" + getArmor + " " + I18n.format("attribute.name.generic.armor"));
                        break;
                	}
                }
                if (!containArmorTooltip) {
                	tooltip.add(indexArmorToughness+1, TextFormatting.BLUE + " +" + getArmor + " " + I18n.format("attribute.name.generic.armor"));
                }
            }
        }else if (stack.getItem() instanceof IGuGuTool) {
        	// 获取附魔等级
            int level = EnchantmentHelper.getEnchantmentLevel(ConstellationEnchantment.INSTANCE, stack);
            if (level > 0) {
            	float trueDamage = UsefulFunc.getTrueDamage(stack);
            	
                List<String> tooltip = event.getToolTip();
                for (int i = 0; i < tooltip.size(); i++) {
                	if (tooltip.get(i).contains(I18n.format("attribute.name.generic.attackDamage"))) {
                		// 插入
                		DecimalFormat df = new DecimalFormat(" #.## ");
                        tooltip.add(i+1, " +" + level*20 + "% " + I18n.format("tooltip.whatgugumod.extra_speed"));
                        if (trueDamage > 0) {
                        	tooltip.add(i+1, df.format(trueDamage) + I18n.format("tooltip.whatgugumod.true_damage"));
                        }
                        break;
	                }
                }
            }
        }
    }
}
