package com.Not_an_UP.whatgugumod.util.handlers;

import java.lang.reflect.Field;
import java.util.Random;

import com.Not_an_UP.whatgugumod.items.books.GuGuBookHandler;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@EventBusSubscriber
public class EventHandler {

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
	public static void onPlayerLoggedIn (PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.player.getTags().contains("read_eula")) {
			event.player.inventory.addItemStackToInventory(GuGuBookHandler.getBook("eula"));
			event.player.addTag("read_eula");
		}
	}
	
	@SubscribeEvent
	public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
//		if (event.player.world.isRemote | !(event.player instanceof EntityPlayerMP)) return;  // 只在服务端执行
//
//	    EntityPlayerMP player = (EntityPlayerMP) event.player;
//	    InventoryCrafting craftMatrix = (InventoryCrafting) event.craftMatrix;  // 获取合成矩阵
//
//	    // 查找匹配的配方
//	    IRecipe recipe = CraftingManager.findMatchingRecipe(craftMatrix, player.world);
//
//	    if (recipe != null) {
//	        ResourceLocation recipeId = recipe.getRegistryName();  // 获取配方ID
//	        if (recipeId.equals(new ResourceLocation("whatgugumod", "diary_recipe"))) {
//	            // 授予成就
//	            Advancement advancement = player.world.getMinecraftServer().getAdvancementManager()
//	                .getAdvancement(new ResourceLocation("whatgugumod", "craft_diary"));
//	            if (advancement != null) {
//	                player.getAdvancements().grantCriterion(advancement, "craft_diary");
//	            }
//	        }
//	    }
	}
}
