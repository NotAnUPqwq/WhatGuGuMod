package com.Not_an_UP.whatgugumod.gui;

import com.Not_an_UP.whatgugumod.gui.container.ContainerGuGuChest;
import com.Not_an_UP.whatgugumod.items.GuGuChest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class GuiGuGuChest extends GuiContainer{
	private final ContainerGuGuChest container;
	private final ResourceLocation TEXTURE;
	private final EntityPlayer player;

    public GuiGuGuChest(ContainerGuGuChest container, int type, EntityPlayer player) {
        super(container);
        this.container = container;
        this.xSize = 176;
        this.ySize = type==2 ? 222 : 166;
        this.player = player;
        
        switch (type) {
        case 0:
        	this.TEXTURE = new ResourceLocation("minecraft", "textures/gui/container/dispenser.png");
        	break;
        case 1:
        	this.TEXTURE = new ResourceLocation("minecraft", "textures/gui/container/shulker_box.png");
        	break;
        case 2:
        	this.TEXTURE = new ResourceLocation("minecraft", "textures/gui/container/generic_54.png");
        	break;
        default:
        	this.TEXTURE = null;
        	throw new IllegalArgumentException(String.format("GuiGuGuChest内出现了未知的type编号！（type：%d）",type));
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        
        // 绘制主界面
        this.drawTexturedModalRect(
            this.guiLeft, 
            this.guiTop, 
            0, 0, 
            this.xSize, this.ySize
        );
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        // 从玩家当前手持物品获取名称
        EntityPlayer player = Minecraft.getMinecraft().player;
        ItemStack stack = player.getHeldItem(container.getHand());
        
        // 绘制标题
        String title = stack.getDisplayName();
        int titleWidth = this.fontRenderer.getStringWidth(title);
        int xPos = (this.xSize - titleWidth) / 2; // 水平居中
        this.fontRenderer.drawString(title, xPos, 6, 0x404040);
        
        // 绘制玩家背包标签
        this.fontRenderer.drawString(
            player.inventory.getDisplayName().getUnformattedText(),
            8, this.ySize - 96 + 2, 0x404040);
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		try{
			if (container.canInteractWith()) {
	    		super.drawScreen(mouseX, mouseY, partialTicks);  // 自动处理悬停提示
		        this.renderHoveredToolTip(mouseX, mouseY);       // 显式渲染工具提示
			}else {
				throw new Exception();
			}
		}catch(Exception e){
			if (container.getHandheldChestStack().getItem() instanceof GuGuChest) {
    			GuGuChest getChest = (GuGuChest)container.getHandheldChestStack().getItem();
    			byte getMessageIndex = getChest.getPlayGuGuMessageIndex();
    			if (getMessageIndex == 2) {
    				BlockPos pos = player.getPosition();
    				player.world.spawnEntity(new EntityItem(player.world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.DYE,1,11).setStackDisplayName("不可名状的咕咕呕吐物")));
    			}
        		player.sendMessage(new TextComponentString(container.getHandheldChestStack().getDisplayName()+"：  "+getChest.getPlayGuGuMessage(getMessageIndex)));
    		}
			this.player.closeScreen();
    	}
    }
}
