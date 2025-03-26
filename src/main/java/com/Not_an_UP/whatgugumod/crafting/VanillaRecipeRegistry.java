package com.Not_an_UP.whatgugumod.crafting;

import com.Not_an_UP.whatgugumod.init.ModBlocks;
import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.util.Reference;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class VanillaRecipeRegistry {
	
	@SubscribeEvent
    public static void getBurnTime(FurnaceFuelBurnTimeEvent event)
    {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() == ModItems.COAL_GUGU) {
            event.setBurnTime(900);
            
        }else if (stack.getItem() == ModItems.PIECE_OF_COAL_GUGU){
        	event.setBurnTime(100);
        	
        }else if (stack.getItem() == Item.getItemFromBlock(ModBlocks.COAL_GUGU_BLOCK)) {
        	event.setBurnTime(3600);
        }
    }

}
