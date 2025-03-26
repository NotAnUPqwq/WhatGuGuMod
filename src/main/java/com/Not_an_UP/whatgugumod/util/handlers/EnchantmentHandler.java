package com.Not_an_UP.whatgugumod.util.handlers;

import com.Not_an_UP.whatgugumod.enchantment.ConstellationEnchantment;
import com.Not_an_UP.whatgugumod.util.UsefulFunc;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentHandler {
	
	@SubscribeEvent
    public void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        if (event.getEntityLiving() instanceof EntityLivingBase) {
        	EntityLivingBase entity = (EntityLivingBase)event.getEntityLiving();

            int extraArmor = 0;
            float extraToughness = 0;
            for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
                if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR) {
                    ItemStack armorStack = entity.getItemStackFromSlot(slot);
                    if (!armorStack.isEmpty()) {
                        // 获取附魔等级
                        int level = EnchantmentHelper.getEnchantmentLevel(ConstellationEnchantment.INSTANCE, armorStack);
                        extraArmor += level; // 每级附魔增加1点护甲值
                    }
                }
            }
            extraToughness += 0.2 * extraArmor;
            
            entity.getEntityAttribute(SharedMonsterAttributes.ARMOR)
                  .setBaseValue(extraArmor);

            entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS)
                  .setBaseValue(extraToughness);
        }
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack heldItem = player.getHeldItemMainhand();

        // 获取附魔等级
        int level = EnchantmentHelper.getEnchantmentLevel(ConstellationEnchantment.INSTANCE, heldItem);
        if (level > 0) {
            // 每级附魔增加20%挖掘速度
            float speedMultiplier = 1.0f + level * 0.2f;
            event.setNewSpeed(event.getOriginalSpeed() * speedMultiplier);
        }
    }
    
    // 用于存储冷却时间进度的键
    private static final String COOLDOWN_PROGRESS_KEY = "gugu_cooldown_progress";
    
    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {
        // 检查攻击者是否为玩家
        if (event.getEntityPlayer() != null) {
        	EntityPlayer attacker = event.getEntityPlayer();

            // 获取攻击者的攻击冷却时间进度（0.0到1.0）
            float cooldownProgress = attacker.getCooledAttackStrength(0.5f);
            // 将冷却时间进度存储到攻击者的 NBT 数据中
            NBTTagCompound nbt = attacker.getEntityData();
            nbt.setFloat(COOLDOWN_PROGRESS_KEY, cooldownProgress);
        }
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        // 检查攻击者是否为实体
        if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
            ItemStack heldItem = attacker.getHeldItemMainhand();

            // 获取附魔等级
            if (EnchantmentHelper.getEnchantmentLevel(ConstellationEnchantment.INSTANCE, heldItem) > 0) {
            	// 计算真实伤害
        		float trueDamage = UsefulFunc.getTrueDamage(heldItem);
        		
            	if (attacker instanceof EntityPlayer) {
            		// 从攻击者的 NBT 数据中读取冷却时间进度
	                NBTTagCompound nbt = attacker.getEntityData();
	                float cooldownProgress = nbt.getFloat(COOLDOWN_PROGRESS_KEY);
	                // 根据攻击冷却时间进度调整真实伤害
	                trueDamage *= 0.2 + 0.8 * cooldownProgress * cooldownProgress;
	                
	                nbt.removeTag(COOLDOWN_PROGRESS_KEY);
	            }
                // 直接增加伤害值
                event.setAmount(event.getAmount() + trueDamage);
            }
        }
    }
}
