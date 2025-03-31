package com.Not_an_UP.whatgugumod.commands;

import com.Not_an_UP.whatgugumod.util.handlers.WorldFileHandler;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandGuGuCipher extends CommandBase {
    
    @Override
    public String getName() {
        return "gugucipher";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/gugucipher <password> -> 输入咕咕密码，或许可以解锁些什么？";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        // TODO 添加输入密码的功能
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0; // 0 表示所有玩家都可以使用这个指令qwq
    }
}