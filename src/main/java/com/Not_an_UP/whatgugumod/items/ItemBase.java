package com.Not_an_UP.whatgugumod.items;

import java.util.List;

import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.Main;
import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item implements IHasModel{
	
	public ItemBase(String name, CreativeTabs tab) {
		
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
		switch(this.getUnlocalizedName()) {
		case ("item.coal_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "这只咕咕可以作为燃料，提供燃烧4.5个物品的燃烧时间。");
			break;
		case ("item.piece_of_coal_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "这撮咕咕可以作为燃料，提供燃烧0.5个物品的燃烧时间。");
			break;
		case ("item.gugu_stick"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "棒！咕咕！棒！");
			break;
		case ("item.piece_of_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "（它发出的咕咕声很小，很难听见。）");
			break;
		case ("item.piece_of_raw_trim"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "这一片太小了，还是不要吃它了。");
			break;
		case ("item.compressed_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕咕！");
			break;
		case ("item.double_compressed_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "更多的咕咕！融合进化！");
			break;
		case ("item.triple_compressed_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "看来你是真的闲啊，咕~");
			break;
		case ("item.quadruple_compressed_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "啊，咕咕好多，全都占在一个格子，好挤……");
			break;
		case ("item.quintuple_compressed_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "救命……好闷……好热……羽毛好多……");
			break;
		case ("item.sextuple_compressed_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "救……命……");
			break;
		case ("item.septuple_compressed_gugu"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "……");
			break;
		case ("item.octuple_compressed_gugu"):
			tooltip.add(TextFormatting.DARK_RED+""+TextFormatting.ITALIC + "好像有什么呼救声，");
			tooltip.add(TextFormatting.DARK_RED+""+TextFormatting.ITALIC + "大概是幻听吧。");	
			break;
		case ("item.octuple_compressed_gugu_stick"):
			tooltip.add(TextFormatting.DARK_RED+""+TextFormatting.ITALIC + "*幽邃深远的咕咕声*");
			break;
		case ("item.gugu_ingot"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕锭！可以做工具！");
			break;
		case ("item.raw_gugu_steel_ingot"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "也许烤一下会更强？");
			break;
		case ("item.gugu_steel_ingot"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕钢！变得更强了！");
			break;
		case ("item.tetris_gugu_coin"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "玩俄罗斯咕块的奖励！");
			break;
		case ("item.gugu_primogems"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "稀有货币，将总共等价于160或160整数倍的咕咕原石的物品放入工作台可以合成出抽卡道具！");
			break;
		case ("item.compressed_gugu_primogems"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "压缩稀有货币，内含9咕咕原石！");
			break;
		case ("item.four_star_coin"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "四星币，有些用处！");
			break;
		case ("item.three_star_coin"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "三星堆，三星堆！");
			break;
		case ("item.five_star_gugu_coin"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "玩咕咕抽卡的五星级奖励！");
			break;
		case ("item.lightblue_paper"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "淡蓝色的纸，要不印点货币？");
			break;
		case ("item.gugumun_note"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "目前广泛流通的咕咕币，使咕咕世界的商业复兴成为可能。");
			break;
		case ("item.gugu_egg_coin"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "饲养咕咕的奖励！");
			break;
		case ("item.gugu_ore_coin"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "挖咕咕模组里矿的奖励！");
			break;
		case ("item.gugu_chest_coin"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "开咕咕宝箱的奖励！");
			break;
		case ("item.gugu_sound_coin"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "向宇宙发出咕咕叫的奖励！");
			break;
		case ("item.gugu_coin"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕抚摸部专员的奖励（工钱）！");
			break;
		case ("item.gold_gugu_coin"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "给家里有矿的奖励！");
			break;
		case ("item.final_gugu_coin"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "给什么咕咕模组资深玩家的奖励！");
			break;
		case ("item.gold_gugumun_note"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "你这纸是金子做的还是颜料是金子做的？");
			break;
		case ("item.gugu_heart"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "一颗咕咕心，材质逼真得令人发指。");
			break;
		case ("item.gugu_egg_shell"):
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "一片洁白的蛋壳，可以用来炼咕咕钢（提升钢里的咕咕浓度）。");
			break;
		}
    }
}
