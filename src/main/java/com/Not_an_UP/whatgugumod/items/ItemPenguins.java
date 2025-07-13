package com.Not_an_UP.whatgugumod.items;

import java.util.List;

import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.util.UsefulFunc;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPenguins extends ItemBase{

	public ItemPenguins(String name, CreativeTabs tab) {
		super(name, tab);
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if ((worldIn.isRemote | playerIn.getCooldownTracker().hasCooldown(this))) {
			return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
		}
		
		ItemStack mainHand = playerIn.getHeldItemMainhand();
		ItemStack offHand = playerIn.getHeldItemOffhand();
		
		if (handIn != EnumHand.MAIN_HAND | mainHand.getItem() != ModItems.ITEM_PENGUINS) {
			return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
			
		}
		
		if (offHand.hasTagCompound()) {
			if (offHand.getTagCompound().hasKey("inventory")) {
				playerIn.sendMessage(new TextComponentString("禁止套娃，特别是会把游戏搞崩的那种。"));
				return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
			}
		}
		
		if (offHand.getItem() instanceof ItemPenguins | offHand.getItem() instanceof GuGuChest){
			playerIn.sendMessage(new TextComponentString("禁止套娃，特别是会把游戏搞崩的那种。"));
			
		}else if (!UsefulFunc.getPenguinNBT(mainHand).isEmpty()){
			ItemStack getStack = UsefulFunc.getPenguinNBT(mainHand);
			getStack.setCount(mainHand.getCount());
			if (getStack.getCount() > getStack.getMaxStackSize()) {
				if (playerIn instanceof EntityPlayerMP) {
					PlayerAdvancements advancements = ((EntityPlayerMP)playerIn).getAdvancements();
					Advancement advancement = ((EntityPlayerMP)playerIn).mcServer.getAdvancementManager().getAdvancement(new ResourceLocation("whatgugumod", "limit_break"));
					if (advancement != null) {
				        AdvancementProgress progress = advancements.getProgress(advancement);
				        if (!progress.isDone()) {  // 防止重复触发
				            for (String criterion : progress.getRemaningCriteria()) {
				                advancements.grantCriterion(advancement, criterion);  // 完成条件
				            }
				        }
				    }
				}
				int maxSize = getStack.getMaxStackSize();
				int stackSize = getStack.getCount();
				ItemStack giveStack = getStack.copy();
				giveStack.setCount(maxSize);
				while (stackSize > maxSize) {
					worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, giveStack));
					stackSize -= maxSize;
				}
				giveStack.setCount(stackSize);
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, giveStack));
			}else {
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, getStack));
			}
			playerIn.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModItems.ITEM_PENGUINS, mainHand.getCount()));;
			playerIn.sendMessage(new TextComponentString("硬核拆解！"));
			
		}else if (mainHand.getCount() != offHand.getCount()){
			playerIn.sendMessage(new TextComponentString("主手和副手的物品数量不匹配。"));
			
		}else if (UsefulFunc.getPenguinNBT(mainHand).isEmpty()) {
			UsefulFunc.setPenguinNBT(mainHand, playerIn.getHeldItemOffhand());
			playerIn.setHeldItem(EnumHand.OFF_HAND, ItemStack.EMPTY);
			playerIn.getHeldItem(handIn).setStackDisplayName(TextFormatting.GOLD + offHand.getDisplayName() + TextFormatting.WHITE + new ItemStack(ModItems.ITEM_PENGUINS).getDisplayName());
			playerIn.sendMessage(new TextComponentString("超级拼装！"));
			
		}else {
			return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
		}
		
		playerIn.getCooldownTracker().setCooldown(this, 20);
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		if (stack.getItem() == ModItems.ITEM_PENGUINS) {
			tooltip.add(TextFormatting.GOLD+""+TextFormatting.ITALIC + UsefulFunc.getPenguinNBT(stack).getDisplayName() +TextFormatting.GRAY+TextFormatting.ITALIC+ "这一块！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "将企鹅放在主手右键使用，可以与副手的物品超级拼装！");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "前提是主手和副手的物品数量相等。");
			tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "将已经超级拼装过的企鹅放在主手右键使用，可以进行硬核拆解！");
		}else if (stack.getItem() == ModItems.PIECE_OF_PENGUINS){
			tooltip.add(TextFormatting.GOLD+""+TextFormatting.ITALIC + UsefulFunc.getPenguinNBT(stack).getDisplayName() +TextFormatting.GRAY+TextFormatting.ITALIC+ "这一小块！");
		}
	}
}
