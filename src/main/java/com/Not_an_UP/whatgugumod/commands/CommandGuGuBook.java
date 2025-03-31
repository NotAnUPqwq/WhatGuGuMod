package com.Not_an_UP.whatgugumod.commands;

import com.Not_an_UP.whatgugumod.items.books.GuGuBookBase;
import com.Not_an_UP.whatgugumod.items.books.GuGuBookHandler;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class CommandGuGuBook extends CommandBase {

	@Override
	public String getName() {
		return "gugubook";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/gugubook <id> -> 输入书的id，获得一本咕咕图书";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 1) 
			throw new CommandException("用法: /gugubook <id>");
		
		ItemStack bookStack = GuGuBookHandler.getBook(args[0]);
		
		if (bookStack.isEmpty()) 
			throw new CommandException("咕咕图书馆还没有这种藏书哦~");
		
        if ((sender instanceof EntityPlayerMP))
        	((EntityPlayerMP)sender).inventory.addItemStackToInventory(bookStack);
        else
        	throw new CommandException("只有玩家能到图书馆借书！");
	}
	
	@Override
    public int getRequiredPermissionLevel() {
        return 1; // 0 表示所有玩家都可以使用这个指令qwq
    }
}
