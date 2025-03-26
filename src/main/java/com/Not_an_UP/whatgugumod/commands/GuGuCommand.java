package com.Not_an_UP.whatgugumod.commands;

import java.util.Random;

import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.util.UsefulFunc;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class GuGuCommand extends CommandBase {

	@Override
	public String getName() {
		return "gugu";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/gugu -> 使用后会发出咕咕叫！qwq";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        BlockPos pos = sender.getPosition();
		String message = TextFormatting.GRAY+""+TextFormatting.BOLD + sender.getName() +"在（"+ pos.getX() +","+ pos.getY() +","+ pos.getZ() +"）向全宇宙发出了咕咕叫！";
        server.getPlayerList().sendMessage(new TextComponentString(message));
        
        sender.getEntityWorld().playSound(null, sender.getPosition(), SoundEvents.ENTITY_CHICKEN_DEATH, SoundCategory.PLAYERS, 1.0f, new Random().nextFloat()/2 + 0.8f);
        
        if (sender instanceof EntityPlayer) {
        	ItemStack stack = new ItemStack(ModItems.GUGU_SOUND_COIN, UsefulFunc.randint(1, 10));
        	if (!((EntityPlayer)sender).inventory.addItemStackToInventory(stack)) {
                // 如果物品栏已满，将物品掉落到玩家脚下
        		((EntityPlayer)sender).dropItem(stack, false);
            }
        }
	}
	
	 @Override
    public int getRequiredPermissionLevel() {
        return 0; // 0 表示所有玩家都可以使用这个指令qwq
    }
}
