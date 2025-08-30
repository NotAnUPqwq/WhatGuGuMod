package com.Not_an_UP.whatgugumod.util.handlers;

import com.Not_an_UP.whatgugumod.enchantment.CarbonizationEnchantment;
import com.Not_an_UP.whatgugumod.enchantment.ConstellationEnchantment;
import com.Not_an_UP.whatgugumod.entity.EntityInit;
import com.Not_an_UP.whatgugumod.init.ModBlocks;
import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.init.ModRecipes;
import com.Not_an_UP.whatgugumod.items.books.GuGuBookHandler;
import com.Not_an_UP.whatgugumod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
		
		GuGuBookHandler.init();
		
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		
		for(Item item : ModItems.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block: ModBlocks.BLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
	}
	
	@SubscribeEvent
    public static void onRecipeRegister(RegistryEvent.Register<IRecipe> event) {
        ModRecipes.registerRecipes(event);
    }
	
	@SubscribeEvent
	public static void onEntityRegister(RegistryEvent.Register<EntityEntry> event) {
		EntityInit.registerEntities();
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
    public static void onRegisterRenderers(RegistryEvent.Register<EntityEntry> event) {
        RenderHandler.registerEntityRenders();
    }
	
	@SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().register(ConstellationEnchantment.INSTANCE);
        event.getRegistry().register(CarbonizationEnchantment.INSTANCE);
    }
}
