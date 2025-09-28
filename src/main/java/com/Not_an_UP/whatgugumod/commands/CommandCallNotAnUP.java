package com.Not_an_UP.whatgugumod.commands;

import com.Not_an_UP.whatgugumod.util.handlers.EventHandler;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandCallNotAnUP extends CommandBase{

	@Override
	public String getName() {
		return "Not_an_UP";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return null;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (sender instanceof EntityPlayer)
			EventHandler.sendSingleMessageLater((EntityPlayer)sender, 100, "<Not_an_UP> 哪有这种指令qwq你想多了", true);
		
		throw new CommandException(I18n.format("commands.generic.notFound"));
	}
	
}
