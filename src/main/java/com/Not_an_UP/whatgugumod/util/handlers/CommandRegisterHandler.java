package com.Not_an_UP.whatgugumod.util.handlers;

import com.Not_an_UP.whatgugumod.commands.CommandGuGu;
import com.Not_an_UP.whatgugumod.commands.CommandGuGuBook;
import com.Not_an_UP.whatgugumod.commands.CommandGuGuCipher;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandRegisterHandler {
	
	public static void init(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandGuGu());
        event.registerServerCommand(new CommandGuGuBook());
        event.registerServerCommand(new CommandGuGuCipher());
	}

}
