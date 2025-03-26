package com.Not_an_UP.whatgugumod.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.Main;
import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolSword extends ItemSword implements IHasModel,IGuGuTool{
	private ToolMaterial material;
	private float attackDamage;
	public ToolSword(String name, CreativeTabs tab, ToolMaterial toolMaterial) {
		super(toolMaterial);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		ModItems.ITEMS.add(this);
		
		this.material = toolMaterial;
		this.attackDamage = 3.0F + material.getAttackDamage();
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		switch(this.getUnlocalizedName()) {
		case ("item.gugu_sword"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕为这把剑注入了不毁的力量！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "代价是它在附魔台的附魔比其他剑都烂。");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "而且伤害也非常一般。");
			break;
		case ("item.gugu_steel_sword"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕为这把剑注入了不毁的力量！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "代价是它在附魔台的附魔比其他剑都烂。");
			break;
		case ("item.octuple_compressed_gugu_sword"):
			tooltip.add(TextFormatting.DARK_RED+""+TextFormatting.ITALIC + "这把剑注入了不毁之力。");
			tooltip.add(TextFormatting.DARK_RED+""+TextFormatting.ITALIC + "代价是需要96855122.25只咕咕。");
			break;
		case ("item.coin_gugu_sword"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕为这把剑注入了不毁的力量！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "代价是没有命座的情况下比其他剑都烂。");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "但是你可以通过在工作台上与三星命星合成获得命座！");
			break;
		case ("item.five_star_coin_gugu_sword"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕为这把剑注入了不毁的力量！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "代价是它的命座特别贵，需要一个神秘的咕咕币。");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "你可以通过在工作台上与五星命星合成获得命座！");
			break;
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
	
	public ToolMaterial getMaterial() {
		return this.material;
	}
	
	public float getAttackDamage() {
		return this.attackDamage;
	}
}
