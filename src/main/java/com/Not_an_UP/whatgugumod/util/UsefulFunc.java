package com.Not_an_UP.whatgugumod.util;

import java.util.Random;

import com.Not_an_UP.whatgugumod.enchantment.ConstellationEnchantment;
import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.items.tools.IGuGuTool;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class UsefulFunc {
	public static float rand(World world) {
		return world.rand.nextFloat();
	}
	
	// 注意nextint提供的数字范围为 0 ~ (bound-1)
	public static int randint(World world, int bound) {
		return world.rand.nextInt(bound);
	}
	
	public static int randint(Random rand, int bound) {
		return rand.nextInt(bound);
	}
	
	public static int randint(World world,int min, int max) {
		return randint(world.rand, min, max);
	}
	
	public static int randint(Random rand, int min, int max) {
		if (max >= min){
			return rand.nextInt(max - min + 1) + min;
		}
		throw new IllegalArgumentException(String.format("The min value : %i , the max value : %i", min, max));
	}
	
	public static void removeEnchantment(ItemStack stack, Enchantment enchantment) {
	    if (stack.isEmpty() || !stack.hasTagCompound()) {
	        return; // 如果物品为空或没有 NBT 数据，直接返回
	    }

	    // 获取物品的附魔列表
	    NBTTagList enchantments = stack.getEnchantmentTagList();
	    if (enchantments == null) {
	        return; // 如果没有附魔列表，直接返回
	    }

	    // 遍历附魔列表，查找目标附魔
	    for (int i = 0; i < enchantments.tagCount(); i++) {
	        NBTTagCompound enchantmentTag = enchantments.getCompoundTagAt(i);
	        if (enchantmentTag.getShort("id") == Enchantment.getEnchantmentID(enchantment)) {
	            // 找到目标附魔，从列表中移除
	            enchantments.removeTag(i);
	            break; // 移除后退出循环
	        }
	    }

	    // 如果附魔列表为空，移除整个附魔标签
	    if (enchantments.tagCount() == 0) {
	        stack.getTagCompound().removeTag("ench"); // 移除附魔列表
	    }

	    // 如果 NBT 数据为空，移除整个 NBT 标签
	    if (stack.getTagCompound().hasNoTags()) {
	        stack.setTagCompound(null); // 移除 NBT 数据
	    }
	}
	
	public static boolean isFiveStar(ItemStack stack) {
		if (stack.getItem() instanceof IGuGuTool) {
        	return ((IGuGuTool)stack.getItem()).getMaterial() == ModItems.MATERIAL_FIVE_STAR_COIN_GUGU;
        }else {
        	return false;
        }
	}
	
	public static float getTrueDamage(ItemStack stack) {
		
		int level = EnchantmentHelper.getEnchantmentLevel(ConstellationEnchantment.INSTANCE, stack);
        boolean isFiveStar = isFiveStar(stack);
        
		float trueDamage = 0f;
		if (isFiveStar){
			trueDamage = ((level == 0) ? 0.0f : 
						 ((level <= 1) ? 0.1f : 
						 ((level <= 3) ? 1.0f : 
						 ((level <= 5) ? 1.5f : 
					                     2.0f)) ) );
			
		}else if (level >= 4) {
			trueDamage = (level >= 6) ? 1.0f : 0.1f;
		}
		return trueDamage;
	}
	
	public static ItemStack getPenguinNBT(ItemStack stack) {
	    if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("penguin")) {
	        return ItemStack.EMPTY; // 如果没有NBT或penguin键，返回空ItemStack
	    }
	    
	    // 从 NBT 读取存储的 ItemStack
	    NBTTagCompound tag = stack.getTagCompound().getCompoundTag("penguin");
	    return new ItemStack(tag);
	}
	
	public static void setPenguinNBT(ItemStack stack, ItemStack stackSaved) {
		/* 把第二个ItemStack存储到第一个ItemStack的"Penguin"NBT中 */
		if (!stack.hasTagCompound()) {
	        stack.setTagCompound(new NBTTagCompound());
	    }
		
		ItemStack getStack = stackSaved.copy();
		getStack.setCount(1);
		NBTTagCompound tag = getStack.writeToNBT(new NBTTagCompound());
	
		stack.getTagCompound().setTag("penguin", tag);
	}
	
	public static int getCount(InventoryCrafting inv) {
		/* 获取一个InventoryCrafting内的物品个数 */
		int count = 0;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (!stack.isEmpty()){
				count++;
			}
		}
		return count;
	}
}
