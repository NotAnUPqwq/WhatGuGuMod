package com.Not_an_UP.whatgugumod.items.books;

import net.minecraft.util.text.TextFormatting;

public class GuGuGuide extends GuGuBookBase{
	public static final GuGuGuide INSTANCE = new GuGuGuide();
	
	public GuGuGuide() {
		super("guide", build(
		
	"如果你没装jei，就来看看这本书吧，这本书内含部分"+TextFormatting.DARK_RED+TextFormatting.ITALIC+"什么咕咕模组"+TextFormatting.BLACK+"中常用的配方",
	"但其实我还是更推荐装一个jei一起玩qwq",
	"<page>",
	"咕咕锭",
	"一种简单的材料，只需要用一个咕咕和一个铁锭就能合成，用咕咕锭合成出的装备不会损坏，但用起来也只是石制工具的水平",
	"<page>",
	"咕咕钢",
	"一种会咕咕叫的神奇钢材，可以像普通钢一样使用（矿物辞典这一块）",
	"想要制造这种钢材，需要七个碳化咕咕碎片、一个咕咕锭和一个咕咕蛋壳，放在工作台里无序合成",
	"合成出来的东西放到熔炉里烧一下，就是咕咕钢啦！",
	"至于为什么需要蛋壳，大抵是和某款游戏有关吧",
	"<page>",
	"咕咕蛋",
	"要是找不到野生咕咕，可以用一个咕咕（物品）和一颗普通鸡蛋合成出咕咕蛋！",
	"<page>"
		));
	}
}
