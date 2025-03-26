package com.Not_an_UP.whatgugumod.util.handlers;

import com.Not_an_UP.whatgugumod.gui.GuiGuGuChest;
import com.Not_an_UP.whatgugumod.gui.GuiGuGuWorkbench;
import com.Not_an_UP.whatgugumod.gui.container.ContainerGuGuChest;
import com.Not_an_UP.whatgugumod.gui.container.ContainerGuGuWorkbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	public static final int GUGU_WORKBENCH = 5;
	
	public static final int GUGU_HANDHELD_CHEST = 0;
	public static final int GUGU_HANDHELD_BIGGER_CHEST = 1;
	public static final int GUGU_HANDHELD_BIGGEST_CHEST = 2;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		EnumHand hand = (x==0) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
		if (ID == GUGU_WORKBENCH) {
			return new ContainerGuGuWorkbench(player.inventory, world);
		}
		if (ID == GUGU_HANDHELD_CHEST) {
			return new ContainerGuGuChest(player, 0, hand);
        } else if (ID == GUGU_HANDHELD_BIGGER_CHEST) {
            return new ContainerGuGuChest(player, 1, hand);
        } else if (ID == GUGU_HANDHELD_BIGGEST_CHEST) {
			return new ContainerGuGuChest(player, 2, hand);
        }
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		EnumHand hand = (x==0) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
		if (ID == GUGU_WORKBENCH) {
			return new GuiGuGuWorkbench(player.inventory, world);
		}
		if (ID == GUGU_HANDHELD_CHEST) {
            return new GuiGuGuChest(new ContainerGuGuChest(player, 0, hand), 0, player);
        } else if (ID == GUGU_HANDHELD_BIGGER_CHEST) {
			return new GuiGuGuChest(new ContainerGuGuChest(player, 1, hand), 1, player);
        } else if (ID == GUGU_HANDHELD_BIGGEST_CHEST) {
			return new GuiGuGuChest(new ContainerGuGuChest(player, 2, hand), 2, player);
        }
		return null;
	}
}
