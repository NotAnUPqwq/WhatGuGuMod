package com.Not_an_UP.whatgugumod.gui.container;

import java.util.List;

import com.Not_an_UP.whatgugumod.items.GuGuChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerGuGuChest extends Container{
	private final int[] GuGuChestTypeList = {9,27,54};
	private final int[][] GuGuGuiTypeList = {{3,3},{3,9},{6,9}};
	private final int type;
	private final ItemStack handheldChestStack;
	public ItemStack getHandheldChestStack() {return this.handheldChestStack;}
	
	private final ItemStackHandler inventory;
	private final int lockedSlotIndex; // 新增：被锁定的槽位索引
	private final EnumHand hand;
	private final EntityPlayer player;
	private boolean needsSave = false;
	
    public ContainerGuGuChest(EntityPlayer player, int type, EnumHand hand) {
        this.player = player;
    	this.hand = hand;
    	this.type = type;
    	this.inventory = new ItemStackHandler(GuGuChestTypeList[type]);
    	if (hand == EnumHand.MAIN_HAND) {
        	this.handheldChestStack = player.getHeldItemMainhand();
        	// 主手对应快捷栏槽位 
            this.lockedSlotIndex = player.inventory.currentItem + 27 + GuGuChestTypeList[type];
    	}else {
        	this.handheldChestStack = player.getHeldItemOffhand();
        	// 副手固定槽位，虽然我也不知道为什么锁副手qwq
            this.lockedSlotIndex = 36 + GuGuChestTypeList[type];
        }
    	
        // 从NBT加载库存
        if (handheldChestStack.hasTagCompound()) {
            inventory.deserializeNBT(handheldChestStack.getTagCompound().getCompoundTag("inventory"));
        }

        // 添加存储槽位
        int guiFix = (type==0 ? 54 : 0);
        int guiFix2 = (type==0 ? 0 : 1);
        for (int row = 0; row < GuGuGuiTypeList[type][0]; row++) {
            for (int col = 0; col < GuGuGuiTypeList[type][1]; col++) {
                int index = col + row * GuGuGuiTypeList[type][1];
                addSlotToContainer(new SlotItemHandler(inventory, index, 
                    8 + col * 18 + guiFix,
                    17 + row * 18 + guiFix2
                ) {
                    @Override
                    public boolean isItemValid(ItemStack stack) {
                    	return !(stack.getItem() instanceof GuGuChest);
                    }
                });
            }
        }

        // 玩家背包
        guiFix = (type==2 ? 56 : 0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 
                    8 + j * 18, 
                    84 + i * 18 + guiFix
                ));
            }
        }

        // 快捷栏位置
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(player.inventory, i, 
                8 + i * 18, 
                142 + guiFix
            ));
        }
    }
    
    @Override
    public void onContainerClosed(EntityPlayer player) {
    	if (!player.world.isRemote & this.canInteractWith()) {
    		saveInventoryToNBT();
        }
        super.onContainerClosed(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
    	return player.getHeldItem(this.hand) == this.handheldChestStack;
    }
    
    public boolean canInteractWith() {
    	return this.player.getHeldItem(this.hand).getItem() == this.handheldChestStack.getItem();
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack transferredStack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);

        if (this.inventorySlots.get(index).getStack().getItem() instanceof GuGuChest) {
            return ItemStack.EMPTY;
        }
        
        if (slot != null && slot.getHasStack()) {
            ItemStack sourceStack = slot.getStack();
            transferredStack = sourceStack.copy();

            if (index < GuGuChestTypeList[type]) {
                // 从容器转移到玩家背包/快捷栏
                if (!this.mergeItemStack(sourceStack, GuGuChestTypeList[type], GuGuChestTypeList[type]+36, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // 从玩家背包/快捷栏转移到容器
                // 注意：这里会自动跳过不允许放入的物品（如自身）
                if (!this.mergeItemStack(sourceStack, 0, GuGuChestTypeList[type], false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (sourceStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            // 处理部分转移的情况
            if (sourceStack.getCount() == transferredStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, sourceStack);
        }
        
        if (!player.world.isRemote) {
            this.markDirty();
        }
        return transferredStack;
    }
    
    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickType, EntityPlayer player) {
    	if (!this.canInteractWith()) {
    		return ItemStack.EMPTY;
    	}
    	if (clickType == ClickType.SWAP) {
            int swappedSlot = 27 + dragType + GuGuChestTypeList[type]; // 被交换的快捷栏槽位
            // 如果目标槽位是被锁定的槽位
            if (swappedSlot == this.lockedSlotIndex || slotId == this.lockedSlotIndex) {
                return ItemStack.EMPTY;
            }
        }
    	// 阻止对锁定槽位的所有操作
        if (slotId == lockedSlotIndex) {
            return ItemStack.EMPTY;
        }
        
        if (!player.world.isRemote) {
            this.markDirty();
        }
        return super.slotClick(slotId, dragType, clickType, player);
        
    }
    
    @Override
    public void setAll(List<ItemStack> stacks) {
        for (int i = 0; i < this.inventorySlots.size(); i++) {
            if (i != lockedSlotIndex) { // 跳过锁定槽位
                super.getSlot(i).putStack(stacks.get(i));
            }
        }
    }
    
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        
        if (!this.player.world.isRemote && this.needsSave) {
            saveInventoryToNBT();
            this.needsSave = false;
        }
    }
    
    private void saveInventoryToNBT() {
        ItemStack stack = player.getHeldItem(this.hand);
        if (!stack.isEmpty()) {
            if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }
            stack.getTagCompound().setTag("inventory", inventory.serializeNBT());
        }
    }
    
    public void markDirty() {
        this.needsSave = true;
    }
    
    public EnumHand getHand() {
    	return this.hand;
    }
}
