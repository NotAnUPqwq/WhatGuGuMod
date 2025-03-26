package com.Not_an_UP.whatgugumod.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.Main;
import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolHoe extends ItemHoe implements IHasModel,IGuGuTool{
	public ToolHoe(String name, CreativeTabs tab, ToolMaterial toolMaterial) {
		
		super(toolMaterial);
		
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
		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕为这把锄头注入了不毁的力量！");
		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "代价是……额……代价……");
		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "哎嘿~☆");
    }
	
	@Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (!stack.hasTagCompound()) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("Unbreakable", new NBTTagInt(1));
            stack.setTagCompound(nbt);
        }
    }
	
	public ToolMaterial getMaterial() {
		return this.toolMaterial;
	}
	
	public float getAttackDamage() {
		return 0;
	}
}
