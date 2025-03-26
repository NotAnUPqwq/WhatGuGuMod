package com.Not_an_UP.whatgugumod.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.Main;
import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorBase extends ItemArmor implements IHasModel{
	public ArmorBase(String name, CreativeTabs tab, ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot equipmentSlot) {
		super(armorMaterial, renderIndex, equipmentSlot);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		String myTooltip = null;
		switch(this.getEquipmentSlot()) {
		case HEAD:
			myTooltip = ("头盔");
			break;
		case CHEST:
			myTooltip = ("胸甲");
			break;
		case LEGS:
			myTooltip = ("护腿");
			break;
		case FEET:
			myTooltip = ("靴子");
			break;
		default:
			break;
		}
		
		if (this.getArmorMaterial() == ModItems.ARMOR_MATERIAL_GUGU |
			this.getArmorMaterial() == ModItems.ARMOR_MATERIAL_GUGU_STEEL ) {
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕为这件"+myTooltip+"注入了不毁的力量！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "代价是它在附魔台的附魔比其他"+myTooltip+"都烂。");
		}else if (this.getArmorMaterial() == ModItems.ARMOR_MATERIAL_COIN_GUGU) {
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕为这件"+myTooltip+"注入了不毁的力量！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "代价是没有命座的情况下比其他"+myTooltip+"都烂。");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "但是你可以通过在工作台上与四星命星合成获得命座！");
		}else if (this.getArmorMaterial() == ModItems.ARMOR_MATERIAL_FIVE_STAR_COIN_GUGU) {
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕为这件"+myTooltip+"注入了不毁的力量！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "代价是它的命座特别贵，需要一个神秘的咕咕币。");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "你可以通过在工作台上与五星命星合成获得命座！");
		}
	}
	
	@Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		if (!stack.hasTagCompound()) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("Unbreakable", new NBTTagInt(1));
            stack.setTagCompound(nbt);
        }
    }
	
	public int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
        return EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
    }
}
