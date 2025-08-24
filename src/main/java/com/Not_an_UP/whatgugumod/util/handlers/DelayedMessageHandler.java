package com.Not_an_UP.whatgugumod.util.handlers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import net.minecraft.entity.player.EntityPlayer;

public class DelayedMessageHandler {
    // 存储玩家及其待发送的消息队列（格式：<玩家, List<[消息, 剩余延迟ticks]>）
    private static final Map<EntityPlayer, Queue<Object[]>> messageQueue = new HashMap<>();

    // 添加延迟消息到队列
    public static void addDelayedMessage(EntityPlayer player, String message, int delayTicks) {
        messageQueue.computeIfAbsent(player, k -> new LinkedList<>())
                   .add(new Object[]{message, delayTicks});
    }
}
