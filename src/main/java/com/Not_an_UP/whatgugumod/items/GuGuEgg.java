package com.Not_an_UP.whatgugumod.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.Not_an_UP.whatgugumod.entity.EntityGuGuEgg;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuGuEgg extends ItemBase{
	public GuGuEgg(String name, CreativeTabs tab) {
        super(name, tab);
        setMaxStackSize(16);
    }

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_SNOWMAN_SHOOT, SoundCategory.NEUTRAL, 1.0f, new Random().nextFloat()/2 + 0.8f);
		ItemStack itemstack = playerIn.getHeldItem(handIn);
        
        if (!playerIn.capabilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        if (!worldIn.isRemote) {
            EntityGuGuEgg snowball = new EntityGuGuEgg(worldIn, playerIn);
            snowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(snowball);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "咕咕蛋！有概率砸出一只咕咕！");
		tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC + "（有概率双黄蛋，三黄蛋也有可能，还能四黄蛋）");
	}
	
	@Override
	public Item getContainerItem() {
		return Items.EGG;
	}
	
	@Override
	public boolean hasContainerItem() {
		return true;
	}
}
