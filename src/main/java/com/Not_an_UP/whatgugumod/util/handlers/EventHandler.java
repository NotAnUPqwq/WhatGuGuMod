package com.Not_an_UP.whatgugumod.util.handlers;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import com.Not_an_UP.whatgugumod.entity.EntityFakeGuGu;
import com.Not_an_UP.whatgugumod.gui.GuiJumpScare;
import com.Not_an_UP.whatgugumod.items.books.GuGuBookHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
	
	public static final Map<EntityPlayer, Queue<Pair<String, Integer>>> messageQueues = new HashMap<>(); //new 消息+延迟
	public static final Map<EntityPlayer, Long> playerLoginTimes = new HashMap<>();
	public static final Map<EntityPlayer, Long> nextMessageTimes = new HashMap<>();
	
	public static boolean isSendingMessage(EntityPlayer player) {
		return nextMessageTimes.get(player) != null;
	}
	
    public static void sendSingleMessageLater(EntityPlayer player, int startTime, String message) {
    	Queue<Pair<String, Integer>> messages = new LinkedList<>(Arrays.asList(
    			Pair.of(message, 0)
    			));
    	sendMessageLater(player, startTime, messages);
    }
    
    @SafeVarargs
	public static void sendSomeMessageLater(EntityPlayer player, int startTime, Pair<String, Integer>... pairs) {
    	Queue<Pair<String, Integer>> messages = new LinkedList<>();
    	for (Pair<String, Integer> pair : pairs) {
    		messages.add(pair);
    	}
    	sendMessageLater(player, startTime, messages);
    }
    
    public static void sendMessageLater (EntityPlayer player, int startTime, Queue<Pair<String, Integer>> messages) {
    	/* 使用方法参见下方函数onPlayerLoggedIn中messages的构造
           startTime 指多少tick以后开始向玩家发送讯息，建议写成20*<秒数>的形式 */
    	playerLoginTimes.put(player, player.world.getTotalWorldTime() + startTime);
    	messageQueues.put(player, messages);
        nextMessageTimes.put(player, player.world.getTotalWorldTime());
    }
    
    @SubscribeEvent
	public static void onPlayerLoggedIn (PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.player.getTags().contains("read_eula") & !event.player.world.isRemote) {
			event.player.inventory.addItemStackToInventory(GuGuBookHandler.getBook("eula"));
			event.player.inventory.addItemStackToInventory(GuGuBookHandler.getBook("guide"));
			
			/* 延迟也是用tick做单位（20tick = 1s）
			       格式是<讯息，延迟>，注意填入的是到下一个讯息的延迟
			       加新讯息的时候记得改延迟*/
			Queue<Pair<String, Integer>> messages = new LinkedList<>(Arrays.asList(
		            Pair.of(TextFormatting.YELLOW + "Not_an_UP加入了游戏", 100),      
		            Pair.of("<Not_an_UP> 哈喽，感谢你游玩我的咕咕模组qwq", 80),      
		            Pair.of("<Not_an_UP> 咕咕推荐单人游玩本模组qwq", 120),      
		            Pair.of("<Not_an_UP> 主要是因为我还没测试多人模式会有哪些bug", 30),
		            Pair.of("<Not_an_UP> qwq", 110),
		            Pair.of("<Not_an_UP> 你手里应该有本书，随便看看就得了", 80),
		            Pair.of("<Not_an_UP> nobody cares", 30),
		            Pair.of("<Not_an_UP> qwq", 80),
		            Pair.of("<Not_an_UP> 祝你玩得开心qwq", 300),
		            Pair.of("<Not_an_UP> 沃日，谁把我退出键扣掉了", 30),
		            Pair.of("<Not_an_UP> qwq", 300),
		            Pair.of("<Not_an_UP> 算了，我自己去玩吧qwq", 120),
		            Pair.of("<Not_an_UP> bro你就不用和我聊天了，我只会发qwq", 30),
		            Pair.of("<Not_an_UP> qwq", 0)
		        ));
			
			sendMessageLater(event.player, 20*20, messages);

			event.player.addTag("read_eula");
		}
	}

    // 玩家退出时清理多余数据
    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        EntityPlayer player = (EntityPlayer) event.player;
        messageQueues.remove(player);
        playerLoginTimes.remove(player);
        nextMessageTimes.remove(player);
    }
	
    @SubscribeEvent
    public static void onPlayerChat(ServerChatEvent event) {
        if (isSendingMessage(event.getPlayer()))
        	return;
    	
    	String message = event.getMessage();
        
        boolean isSinglePlayer = Minecraft.getMinecraft().isSingleplayer() && 
                !Minecraft.getMinecraft().getIntegratedServer().getPublic();
        
        // 示例：检测特定关键词
        if ((message.contains("我喜欢你") | message.contains("我爱你")) & event.getPlayer().getTags().contains("love_gugu_qwq")) {
        	sendSingleMessageLater(event.getPlayer(), 100, "<Not_an_UP> 哦，qwq");
        }else if ((message.contains("我喜欢你")| message.contains("我爱你")) & isSinglePlayer) {
            sendMessageLater(event.getPlayer(), 200, new LinkedList<>(Arrays.asList(
            		Pair.of("<Not_an_UP> 什么，竟然是在这种时候吗qwq", 120),
            		Pair.of("<Not_an_UP> 明明我才刚来这个地方qwq就要说出这种话", 100),
            		Pair.of("<Not_an_UP> qwq你不会只是想知道我会说什么吧", 0))));
            event.getPlayer().addTag("love_gugu_qwq");
        }else if (message.contains("qwq")){
        	sendSingleMessageLater(event.getPlayer(), 20, "<Not_an_UP> qwq");
        }else {
        	sendSingleMessageLater(event.getPlayer(), 60, "<Not_an_UP> qwq");
        }
    }
}
