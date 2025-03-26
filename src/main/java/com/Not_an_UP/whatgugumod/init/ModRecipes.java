package com.Not_an_UP.whatgugumod.init;

import javax.annotation.Nonnull;

import com.Not_an_UP.whatgugumod.enchantment.ConstellationEnchantment;
import com.Not_an_UP.whatgugumod.items.ItemGuGuStar;
import com.Not_an_UP.whatgugumod.items.armor.ArmorBase;
import com.Not_an_UP.whatgugumod.items.tools.IGuGuTool;
import com.Not_an_UP.whatgugumod.util.UsefulFunc;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	
	public static void init() {
		
		GameRegistry.addSmelting(ModItems.RAW_TRIM, new ItemStack(ModItems.COOKED_TRIM), 0.35f);
		GameRegistry.addSmelting(ModItems.RAW_GUGU_STEEL_INGOT, new ItemStack(ModItems.GUGU_STEEL_INGOT), 0.35f);
		
	}
	
	@SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        // 创建配方实例
        IRecipe recipeBiggerChest = new BiggerChestRecipe(
	            new ResourceLocation("whatgugumod", "gugu_handheld_bigger_chest_recipe"), // 配方唯一标识
	            new ItemStack(ModItems.GUGU_HANDHELD_BIGGER_CHEST) // 合成结果
	        ).setRegistryName(new ResourceLocation("whatgugumod", "gugu_handheld_bigger_chest_recipe"));
        IRecipe recipeBiggestChest = new BiggestChestRecipe(
                new ResourceLocation("whatgugumod", "gugu_handheld_biggest_chest_recipe"), // 配方唯一标识
                new ItemStack(ModItems.GUGU_HANDHELD_BIGGEST_CHEST) // 合成结果
            ).setRegistryName(new ResourceLocation("whatgugumod", "gugu_handheld_biggest_chest_recipe"));
        IRecipe recipeConstellation = new ConstellationRecipe(
                new ResourceLocation("whatgugumod", "constellation_recipe"), // 配方唯一标识
                new ItemStack(ModItems.FIVE_STAR_GUGU_COIN) // 合成结果
            ).setRegistryName(new ResourceLocation("whatgugumod", "constellation_recipe"));
        
        // 注册配方
        event.getRegistry().register(recipeBiggerChest);
        event.getRegistry().register(recipeBiggestChest);
        event.getRegistry().register(recipeConstellation);
    	
        /* 咕咕为批量注册配方鏖战两小时，
         * 最终还是与自己和解，使用deepseek辅助 qwq...
         * 结果强如deepseek也解决不了我的问题qwq
         * 你知道我最后怎么解决的吗？
         * 用python写批量生成配方的代码啦~*/
    }
	
	private static class ConstellationRecipe extends ShapelessOreRecipe{
		public ConstellationRecipe(ResourceLocation group, ItemStack output, Object... ingredients) {
			super(group, output, ingredients);
		}
		
		@Override
        public boolean matches(InventoryCrafting inv, World world) {
			int coinCount = this.findCoin(inv);
			ItemStack stack = this.findItemStack(inv);
			int constellation = this.getConstellation(stack, coinCount);
			return ((!stack.isEmpty()) ? ((coinCount > 0) ? (constellation <= 6) : false) : false);
        }

        @Override
        public ItemStack getCraftingResult(InventoryCrafting inv) {
        	ItemStack stack = this.findItemStack(inv);
        	// 下面这个变量不要删，要先把命座数存起来再去除附魔！
        	int constellation = this.getConstellation(stack, this.findCoin(inv));
        	UsefulFunc.removeEnchantment(stack, ConstellationEnchantment.INSTANCE);
        	stack.addEnchantment(ConstellationEnchantment.INSTANCE, constellation);
            return stack;
        }
        
        public int getConstellation(ItemStack stack, int count) {
        	return count + EnchantmentHelper.getEnchantmentLevel(ConstellationEnchantment.INSTANCE, stack);
        }
        
        public int findCoin(InventoryCrafting inv) {
        	int coinCount = 0;
        	ItemGuGuStar coinType = null;
        	for (int i = 0; i < inv.getSizeInventory(); i++) {
        		ItemStack stack = inv.getStackInSlot(i);
                if (!stack.isEmpty()) {
                	if (stack.getItem() instanceof ItemGuGuStar) {
                		if (coinType == null) {
                			coinType = (ItemGuGuStar)stack.getItem();
                			switch (this.getMaterialType(this.findItemStack(inv))) {
                			case 0:
                				if (coinType != ModItems.THREE_STAR_STAR) {
                					return -1;
                				}
                				break;
                			case 1:
                				if (coinType != ModItems.FOUR_STAR_STAR) {
                					return -1;
                				}
                				break;
                			case 2:
                				if (coinType != ModItems.FIVE_STAR_STAR) {
                					return -1;
                				}
                				break;
                			}
                		}
                		if (stack.getItem() != coinType) {
                			return -1;
                		}
                		coinCount++;
                	}
                }
            }
        	return coinCount;
        }
        
        public ItemStack findItemStack(InventoryCrafting inv) {
        	int itemCount = 0;
        	ItemStack getItemStack = ItemStack.EMPTY;

            for (int i = 0; i < inv.getSizeInventory(); i++) {
                ItemStack stack = inv.getStackInSlot(i);
                if (!stack.isEmpty()) {
                	switch (this.getMaterialType(stack)) {
                	case -1:
                		if (stack.getItem() instanceof ItemGuGuStar){
                			continue;
                		}
                		return ItemStack.EMPTY;
                	default:
                		itemCount++;
                        getItemStack = stack;
                	}
                }
            }
            if (itemCount != 1){
            	return ItemStack.EMPTY;
            }
            return getItemStack.copy();
        }
        
        // 0代表工具  1代表盔甲  2代表五星咕咕装备  -1代表不匹配
        public int getMaterialType(ItemStack stack){
        	if (stack.getItem() instanceof IGuGuTool) {
        		if ( ((IGuGuTool)stack.getItem()).getMaterial() == ModItems.MATERIAL_COIN_GUGU ){
        			return 0;
        		}else if (((IGuGuTool)stack.getItem()).getMaterial() == ModItems.MATERIAL_FIVE_STAR_COIN_GUGU) {
        			return 2;
        		}
        		else {
        			return -1;
        		}
        	}else if(stack.getItem() instanceof ArmorBase) {
        		if ( ((ArmorBase)stack.getItem()).getArmorMaterial() == ModItems.ARMOR_MATERIAL_COIN_GUGU ){
        			return 1;
        		}else if ( ((ArmorBase)stack.getItem()).getArmorMaterial() == ModItems.ARMOR_MATERIAL_FIVE_STAR_COIN_GUGU ){
        			return 2;
        		}else {
        			return -1;
        		}
        	}
        	return -1;
        }        
        
        @Override
        public boolean isDynamic() {
            return true;
        }
	}
	
    private static class BiggerChestRecipe extends ShapedOreRecipe {
        public BiggerChestRecipe(ResourceLocation group, ItemStack result) {
            super(group, result,
                " G ",  // 第一行 (索引0-2)
                "CCC",  // 第二行 (索引3-5)
                " G ",  // 第三行 (索引6-8)
                'G', "ingotGold",          // 使用矿典标签
                'C', ModItems.GUGU_HANDHELD_CHEST // 直接物品引用
            );
        }

        @Override
        public boolean matches(InventoryCrafting inv, World world) {
            // 基础形状验证
            if (!super.matches(inv, world)) return false;

            // 精确槽位验证
            return matchesOreDict(inv.getStackInSlot(1), "ingotGold") &&   // 第一行中间 (索引1)
                   inv.getStackInSlot(3).getItem() == ModItems.GUGU_HANDHELD_CHEST && // 第二行左 (索引3)
                   inv.getStackInSlot(4).getItem() == ModItems.GUGU_HANDHELD_CHEST && // 第二行中 (索引4)
                   inv.getStackInSlot(5).getItem() == ModItems.GUGU_HANDHELD_CHEST && // 第二行右 (索引5)
                   matchesOreDict(inv.getStackInSlot(1), "ingotGold");     // 第三行中间 (索引7)
        }

        @Nonnull
        @Override
        public ItemStack getCraftingResult(InventoryCrafting inv) {
            ItemStack result = super.getCraftingResult(inv);
            
            // 创建27格库存
            ItemStackHandler newInventory = new ItemStackHandler(27);
            int targetSlot = 0;

            // 按合成顺序遍历输入槽位 (3→4→5)
            for (int slot : new int[]{3, 4, 5}) {
                ItemStack chestStack = inv.getStackInSlot(slot);
                if (chestStack.getItem() == ModItems.GUGU_HANDHELD_CHEST) {
                    // 读取原箱子物品
                    ItemStackHandler sourceInventory = new ItemStackHandler(9);
                    if (chestStack.hasTagCompound()) {
                        NBTTagCompound tag = chestStack.getTagCompound();
                        if (tag.hasKey("inventory")) {
                            sourceInventory.deserializeNBT(tag.getCompoundTag("inventory"));
                        }
                    }

                    // 复制有效物品
                    for (int i = 0; i < sourceInventory.getSlots(); i++) {
                        ItemStack item = sourceInventory.getStackInSlot(i);
                        if (!item.isEmpty()) {
                        	newInventory.setStackInSlot(targetSlot++, item.copy());
                        }
                    }
                }
            }

            // 设置NBT
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("inventory", newInventory.serializeNBT());
            result.setTagCompound(tag);

            return result;
        }

        // 允许显示在JEI中
        @Override
        public boolean isDynamic() {
            return false;
        }
    }
    
    private static class BiggestChestRecipe extends ShapedOreRecipe {
        public BiggestChestRecipe(ResourceLocation group, ItemStack result) {
            super(group, result,
                " G ",  // 第一行 (索引0-2)
                "CBC",  // 第二行 (索引3-5)
                " G ",  // 第三行 (索引6-8)
                'G', "gemDiamond",          
                'B', ModItems.SURPRISED_GUGU, 
                'C', ModItems.GUGU_HANDHELD_BIGGER_CHEST 
            );  // 想给key凑个“GBC”
        }

        @Override
        public boolean matches(InventoryCrafting inv, World world) {
            // 基础形状验证
            if (!super.matches(inv, world)) return false;

            // 精确槽位验证
            return matchesOreDict(inv.getStackInSlot(1), "gemDiamond") &&   // 第一行中间 (索引1)
                   inv.getStackInSlot(3).getItem() == ModItems.GUGU_HANDHELD_BIGGER_CHEST && // 第二行左 (索引3)
                   inv.getStackInSlot(4).getItem() == ModItems.SURPRISED_GUGU && // 第二行中 (索引4)
                   inv.getStackInSlot(5).getItem() == ModItems.GUGU_HANDHELD_BIGGER_CHEST && // 第二行右 (索引5)
                   matchesOreDict(inv.getStackInSlot(1), "gemDiamond");     // 第三行中间 (索引7)
        }

        @Nonnull
        @Override
        public ItemStack getCraftingResult(InventoryCrafting inv) {
            ItemStack result = super.getCraftingResult(inv);
            
            // 创建54格库存
            ItemStackHandler newInventory = new ItemStackHandler(54);
            int targetSlot = 0;

            // 按合成顺序遍历输入槽位 (3→5)
            for (int slot : new int[]{3, 5}) {
                ItemStack chestStack = inv.getStackInSlot(slot);
                if (chestStack.getItem() == ModItems.GUGU_HANDHELD_BIGGER_CHEST) {
                    // 读取原箱子物品
                    ItemStackHandler sourceInventory = new ItemStackHandler(27);
                    if (chestStack.hasTagCompound()) {
                        NBTTagCompound tag = chestStack.getTagCompound();
                        if (tag.hasKey("inventory")) {
                            sourceInventory.deserializeNBT(tag.getCompoundTag("inventory"));
                        }
                    }

                    // 复制有效物品
                    for (int i = 0; i < sourceInventory.getSlots(); i++) {
                        ItemStack item = sourceInventory.getStackInSlot(i);
                        if (!item.isEmpty()) {
                        	newInventory.setStackInSlot(targetSlot++, item.copy());
                        }
                    }
                }
            }

            // 设置NBT
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("inventory", newInventory.serializeNBT());
            result.setTagCompound(tag);

            return result;
        }

        // 允许显示在JEI中
        @Override
        public boolean isDynamic() {
            return false;
        }
    }
    
    private static boolean matchesOreDict(ItemStack stack, String oreDict) {
        if (stack.isEmpty()) return false;
        
        // 获取物品的所有矿典条目ID
        int[] oreIDs = OreDictionary.getOreIDs(stack);
        int targetID = OreDictionary.getOreID(oreDict);
        
        // 遍历检查匹配
        for (int id : oreIDs) {
            if (id == targetID) {
                return true;
            }
        }
        return false;
    }
}
