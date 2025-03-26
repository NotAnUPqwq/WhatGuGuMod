package com.Not_an_UP.whatgugumod.util.handlers;

import java.lang.reflect.Field;
import java.util.Random;

import com.Not_an_UP.whatgugumod.enchantment.ConstellationEnchantment;
import com.Not_an_UP.whatgugumod.entity.EntityInit;
import com.Not_an_UP.whatgugumod.init.ModBlocks;
import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.init.ModRecipes;
import com.Not_an_UP.whatgugumod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class RegistryHandler {
	private static boolean triedToReplace = false;
	
	@SubscribeEvent
	public static void onGuiRegister(GuiScreenEvent.InitGuiEvent.Post event) {
		if (triedToReplace){
        	return;
        }
		if (event.getGui() instanceof GuiMainMenu) {
			float randomFloat = new Random().nextFloat();
			String changeMessage = null;
			if ((0.01 < randomFloat) & (randomFloat < 0.1)) {
                changeMessage = "咕咕咕！";
            } else if(0.01 > randomFloat){
            	changeMessage = "你有9%的概率看到“咕咕咕！”标语！（这条是1%）";
            }
            if (changeMessage != null) {
            	try {
                    GuiMainMenu mainMenu = (GuiMainMenu) event.getGui();
                    Field splashField = GuiMainMenu.class.getDeclaredField("splashText");
                    splashField.setAccessible(true);
                    splashField.set(mainMenu, changeMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            triedToReplace = true;
        }
	}
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
		
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
	
	private static final String[] deathMessageList = {"%s被%s用%s打得倒头就睡",
													  "咕咕日报 ： %s被%s用%s杀掉了",
													  "%s被%s用%s咕掉了，咕咕咕"};
	@SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        EntityLivingBase entity = event.getEntityLiving();

        // 检查是否是玩家死亡
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            DamageSource source = event.getSource();

            // 检查是否是被生物杀死
            if (source.getTrueSource() instanceof EntityLivingBase) {
                EntityLivingBase killer = (EntityLivingBase) source.getTrueSource();
                
                ItemStack heldItem = killer.getHeldItemMainhand();
                String itemName = heldItem.isEmpty() ? "赤手空拳" : heldItem.getDisplayName(); // 如果手持物品为空，显示“空手”

                // 有概率替换死亡信息
                if (Math.random() < 0.4) { // 40% 的概率
                    String customDeathMessage = String.format(deathMessageList[new Random().nextInt(deathMessageList.length)], player.getName(), killer.getName(), itemName);
                    event.setCanceled(true); // 取消原版死亡信息
                    player.world.getMinecraftServer().getPlayerList().sendMessage(new TextComponentString(customDeathMessage));
                }
            }
        }
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
    }
}
