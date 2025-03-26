package com.Not_an_UP.whatgugumod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerGuGuWorkbench extends ContainerWorkbench{
	
	public ContainerGuGuWorkbench(InventoryPlayer inventoryPlayer, World world) {
		super(inventoryPlayer, world, BlockPos.ORIGIN);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}
