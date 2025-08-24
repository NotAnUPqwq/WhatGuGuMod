package com.Not_an_UP.whatgugumod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class GuiJumpScare extends Gui {
    private static final ResourceLocation JUMPSCARE_TEXTURE = new ResourceLocation("whatgugumod:textures/gui/coal_gugu.png");
    private static long showTime = 0; // 显示开始时间
    private static int duration = 500; // 显示时长（毫秒，默认2秒）
    private static float alpha = 0.0f; // 透明度（0.0~1.0）
    
    // 触发 Jump Scare
    public static void trigger() {
        showTime = System.currentTimeMillis();
        alpha = 1.0f; // 初始完全不透明
    }

    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent.Post event) {
    	if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return; // 只在渲染整个屏幕时触发
        if (showTime == 0) return; // 未触发时不渲染

        Minecraft mc = Minecraft.getMinecraft();
        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - showTime;
        
        if (elapsed >= duration) {
            showTime = 0; // 重置
            return;
        }

        // 计算淡出效果（最后 0.5 秒淡出）
        if (elapsed >= duration - 500) {
            alpha = 1.0f - ((elapsed - (duration - 500)) / 500.0f);
        }

        // 渲染图片（全屏）
        mc.renderEngine.bindTexture(JUMPSCARE_TEXTURE);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, alpha); // 设置透明度

        // 绘制图片（全屏适配）
        int screenWidth = event.getResolution().getScaledWidth();
        int screenHeight = event.getResolution().getScaledHeight();
        drawScaledCustomSizeModalRect(
            0, 0, // 绘制位置 (x, y)
            0, 0, // 纹理起始坐标 (u, v)
            1920, 1080, // 原始纹理尺寸（假设图片是1920x1080）
            screenWidth, screenHeight, // 目标尺寸（适配屏幕）
            1920, 1080 // 纹理尺寸（防止拉！伸！）
        );

        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f); // 重置颜色
    }
}
