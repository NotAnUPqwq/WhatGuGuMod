package com.Not_an_UP.whatgugumod.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.Main;
import com.Not_an_UP.whatgugumod.util.handlers.GuiHandler;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuGuChest extends ItemBase{
	private final String[] textFormatList = {TextFormatting.GRAY+""+TextFormatting.ITALIC, TextFormatting.GOLD+""+TextFormatting.ITALIC, TextFormatting.GOLD+""+TextFormatting.ITALIC+""+TextFormatting.BOLD, TextFormatting.BLUE+""+TextFormatting.ITALIC+""+TextFormatting.BOLD};
	private final byte[] chestGuiTypeIDList = {GuiHandler.GUGU_HANDHELD_CHEST, GuiHandler.GUGU_HANDHELD_BIGGER_CHEST, GuiHandler.GUGU_HANDHELD_BIGGEST_CHEST};
	private final byte type;
	public byte getType() {return this.type;}
	
	private static final String[] playGuGuMessages = {"咕咕咕……不要拿我耍杂技……我有点……晕……", "我……我……我要吐了！", "（呕）", "我吐出来了，都你害的啦！", "（它刚和朋友分食了咕咕牌长效晕车药，现在不想和你说话）", "（药效真的很长，能持续到游戏关闭）", "（真的不想说话了）", "……"};
	private byte playGuGuMessageIndex = 0;
	public byte getPlayGuGuMessageIndex() {
		if (this.playGuGuMessageIndex < playGuGuMessages.length - 1){
			return this.playGuGuMessageIndex++;
		}else {
			return this.playGuGuMessageIndex;
		}
	}
	public String getPlayGuGuMessage(int index) {return playGuGuMessages[index];}
	
	public GuGuChest(String name, CreativeTabs tab, byte type) {
        super(name, tab);
        this.type = type;
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
    	ItemStack stack = player.getHeldItem(hand);
		world.playSound(player, player.getPosition(), SoundEvents.ENTITY_CHICKEN_AMBIENT, SoundCategory.PLAYERS, 1.0f, new Random().nextFloat()/2 + 0.8f);
    	if (!world.isRemote) { // 检测是否为客户端
        	// 传递手部信息（0=主手，1=副手）
            int handFlag = (hand == EnumHand.MAIN_HAND) ? 0 : 1;
            player.openGui(Main.instance, chestGuiTypeIDList[this.type], world, handFlag, 0, 0);
        }
    	return new ActionResult<>(EnumActionResult.SUCCESS, stack); 
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        // 使用当前物品名称动态生成tooltip
    	String displayTooltip = textFormatList[this.type+1]+stack.getDisplayName();
    	final String[] firstTooltipList = {
    			"咕咕咕！拥有九格存储空间的",
    			"咕咕咕咕咕咕咕咕咕！拥有二十七格存储空间的",
    			"超级融合进化！拥有五十四格存储空间的"
    			};
    	String firstTooltip = firstTooltipList[chestGuiTypeIDList[type]];
    	
    	while (tooltip.size()>1){
    		tooltip.remove(1);
    	}
        tooltip.add(textFormatList[this.type] + firstTooltip + displayTooltip +textFormatList[this.type]+"！");
    }
}
