package com.Not_an_UP.whatgugumod.util.handlers;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.lang3.tuple.Pair;

import com.Not_an_UP.whatgugumod.entity.EntityFakeGuGu;
import com.Not_an_UP.whatgugumod.gui.GuiJumpScare;
import com.Not_an_UP.whatgugumod.util.UsefulFunc;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler {
	@SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
		if (event.phase != TickEvent.Phase.END) return;

		EntityPlayer getPlayer = Minecraft.getMinecraft().player;
		
        Iterator<Map.Entry<EntityPlayer, Queue<Pair<String, Integer>>>> iterator = EventHandler.messageQueues.entrySet().iterator();
        while (iterator.hasNext()) {
        	Map.Entry<EntityPlayer, Queue<Pair<String, Integer>>> entry = iterator.next();
            EntityPlayer player = entry.getKey();
            
            try {
	            if (!getPlayer.equals(player)) {
	            	continue;
	            }
	        }catch (NullPointerException e) {
	        	continue;
	        }
            
            Queue<Pair<String, Integer>> messages = entry.getValue();

            // 检查是否已等待20秒（20秒 = 20*20 ticks）
            if (player.world.getTotalWorldTime() - EventHandler.playerLoginTimes.get(player) < 0)
            	continue;
                
        	// 检查距离上条消息是否超过5秒（100 ticks）
            if (player.world.getTotalWorldTime() < EventHandler.nextMessageTimes.get(player)) 
            	continue;
                
        	if (!messages.isEmpty()) {
        		Pair<String, Integer> messagePair = messages.poll();
                player.sendMessage(new TextComponentString(messagePair.getLeft()));
                EventHandler.nextMessageTimes.put(player, player.world.getTotalWorldTime() + messagePair.getRight());
            } else {
                iterator.remove(); // 清理垃圾这一块
                EventHandler.playerLoginTimes.remove(player);
                EventHandler.nextMessageTimes.remove(player); 
            }
        }
        
        if (EventHandler.isSendingMessage(getPlayer))
	        	return;
        try {
	        if (UsefulFunc.myGuGuRandom.nextFloat() < 0.00005) {
	        	switch (UsefulFunc.myGuGuRandom.nextInt(4)) {
		        	case 0:
		        		EventHandler.sendSingleMessageLater(getPlayer, 100, "<Not_an_UP> 不知道说什么，还是发个qwq吧", false);
		        		break;
		        	case 1:
		        		EventHandler.sendSomeMessageLater(getPlayer, 100, Pair.of("<Not_an_UP> 现在写1.12.2模组的教程实在是太少了qwq", 80),
		        				                                          Pair.of("<Not_an_UP> 但是AI实在是太好用了你知道吗", 30),
		        				                                          Pair.of("<Not_an_UP> qwq", 0));
		        		break;
		        	case 2:
		        		EventHandler.sendSomeMessageLater(getPlayer, 60, Pair.of("<Not_an_UP> 你喜欢末影人吗qwq", 60),
		        				                                          Pair.of("<Not_an_UP> 我觉得他挺可爱的", 30),
		        				                                          Pair.of("<Not_an_UP> qwq", 0));
		        		break;
		        	default:
		        		EventHandler.sendSingleMessageLater(getPlayer, 40, "<Not_an_UP> qwq咕咕咕", false);
		        		break;
	        	}
	        }
        }catch (NullPointerException e){
        	
        }
    }
	
	@SubscribeEvent
    public static void onEntityRightClick(PlayerInteractEvent.EntityInteract event) {
	    if (event.getEntityPlayer().getHeldItemMainhand().isEmpty() & event.getTarget() instanceof EntityFakeGuGu) {
            GuiJumpScare.trigger(); 
        }
    }
}
