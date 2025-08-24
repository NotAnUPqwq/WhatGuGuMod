package com.Not_an_UP.whatgugumod.entity;

import java.util.Set;

import com.Not_an_UP.whatgugumod.Main;
import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuFiveStar;
import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuFourStar;
import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuThreeStar;
import com.Not_an_UP.whatgugumod.entity.particle.EntityAngryParticle;
import com.Not_an_UP.whatgugumod.entity.particle.EntityHeartParticle;
import com.Not_an_UP.whatgugumod.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
	public static void registerEntities() {
		registerEntity("entity_cccp_gugu", EntityCCCPGuGu.class, 100);
		registerEntity("entity_gugu_egg", EntityGuGuEgg.class, 100);
		registerEntity("entity_gugu", EntityGuGu.class, 100, 0xffffff, 0x000000);
		registerEntity("entity_dark_gugu", EntityDarkGuGu.class, 100, 0x222222, 0x666666);
		registerEntity("entity_intertwined_gugu_three_star", EntityIntertwinedGuGuThreeStar.class, 100, true);
		registerEntity("entity_intertwined_gugu_four_star", EntityIntertwinedGuGuFourStar.class, 100, true);
		registerEntity("entity_intertwined_gugu_five_star", EntityIntertwinedGuGuFiveStar.class, 100, true);
		registerEntity("entity_heart_particle", EntityHeartParticle.class, 100);
		registerEntity("entity_angry_particle", EntityAngryParticle.class, 100);
		registerEntity("entity_fake_gugu", EntityFakeGuGu.class, 100, 0x999999, 0x000000);
		
		registerSpawn();
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
	
	private static void registerSpawn() {
        // 在所有主世界生物群系中注册生成
		Set<Biome> overworld = BiomeDictionary.getBiomes(BiomeDictionary.Type.getType("OVERWORLD", BiomeDictionary.Type.PLAINS));
        
        // 以后一定会有用的参数说明：
        // 1. 实体类
        // 2. 生成权重 (概率)
        // 3. 最小生成数量
        // 4. 最大生成数量
        // 5. 生成类型 (比如MONSTER表示夜晚生成)
        // 6. 适用的生物群系数组
		
        EntityRegistry.addSpawn(EntityDarkGuGu.class, 40, 1, 3, EnumCreatureType.MONSTER, 
            overworld.toArray(new Biome[0]));
        EntityRegistry.addSpawn(EntityGuGu.class, 60, 2, 4, EnumCreatureType.CREATURE, 
                overworld.toArray(new Biome[0]));
    }
}
