package com.Not_an_UP.whatgugumod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Not_an_UP.whatgugumod.commands.GuGuCommand;
import com.Not_an_UP.whatgugumod.init.ModRecipes;
import com.Not_an_UP.whatgugumod.proxy.CommonProxy;
import com.Not_an_UP.whatgugumod.tabs.GuGuTab;
import com.Not_an_UP.whatgugumod.tabs.GuGuTabCreative;
import com.Not_an_UP.whatgugumod.util.Reference;
import com.Not_an_UP.whatgugumod.util.handlers.EnchantmentHandler;
import com.Not_an_UP.whatgugumod.util.handlers.GuiHandler;
import com.Not_an_UP.whatgugumod.util.handlers.OreDictHandler;
import com.Not_an_UP.whatgugumod.util.handlers.TooltipHandler;
import com.Not_an_UP.whatgugumod.world.ModWorldGen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	@Instance
	public static Main instance;
	
	public static int ENTITY_COUNTER = 114;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final Logger LOGGER = LogManager.getLogger("whatgugumod");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		OreDictHandler.init();
		ModRecipes.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new EnchantmentHandler());
		MinecraftForge.EVENT_BUS.register(new TooltipHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@EventHandler // 注册了一个咕咕指令
    public static void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new GuGuCommand());
    }
	
	public static CreativeTabs GUGU_TAB = new GuGuTab();
	public static CreativeTabs GUGU_TAB_CREATIVE = new GuGuTabCreative();
}
