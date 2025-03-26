package com.Not_an_UP.whatgugumod.entity;

import com.Not_an_UP.whatgugumod.Main;
import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuFiveStar;
import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuFourStar;
import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuThreeStar;
import com.Not_an_UP.whatgugumod.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
	public static void registerEntities() {
		registerEntity("entity_cccp_gugu", EntityCCCPGuGu.class, 100);
		registerEntity("entity_gugu_egg", EntityGuGuEgg.class, 100);
		registerEntity("entity_gugu", EntityGuGu.class, 100, 0xffffff, 0x000000);
		registerEntity("entity_intertwined_gugu_three_star", EntityIntertwinedGuGuThreeStar.class, 100, true);
		registerEntity("entity_intertwined_gugu_four_star", EntityIntertwinedGuGuFourStar.class, 100, true);
		registerEntity("entity_intertwined_gugu_five_star", EntityIntertwinedGuGuFiveStar.class, 100, true);
	}
	
	public static void registerEntity(String name, Class<? extends Entity> entity, int range, int primaryColor, int secondaryColor) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, Main.ENTITY_COUNTER++, Main.instance, range, 3, true, primaryColor, secondaryColor);
	}
	
	public static void registerEntity(String name, Class<? extends Entity> entity, int range) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, Main.ENTITY_COUNTER++, Main.instance, range, 3, true);
	}
	
	public static void registerEntity(String name, Class<? extends Entity> entity, int range, boolean isFastUpdate) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, Main.ENTITY_COUNTER++, Main.instance, range, 1, true);
	}
}
