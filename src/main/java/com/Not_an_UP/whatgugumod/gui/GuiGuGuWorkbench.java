package com.Not_an_UP.whatgugumod.gui;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiGuGuWorkbench extends GuiCrafting{
	public GuiGuGuWorkbench(InventoryPlayer inventoryPlayer, World world) {
		super(inventoryPlayer, world, BlockPos.ORIGIN);
	}
}
