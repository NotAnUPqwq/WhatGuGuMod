package com.Not_an_UP.whatgugumod.util.handlers;

import com.Not_an_UP.whatgugumod.entity.EntityCCCPGuGu;
import com.Not_an_UP.whatgugumod.entity.EntityDarkGuGu;
import com.Not_an_UP.whatgugumod.entity.EntityGuGu;
import com.Not_an_UP.whatgugumod.entity.EntityGuGuEgg;
import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuFiveStar;
import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuFourStar;
import com.Not_an_UP.whatgugumod.entity.intertwined_gugu.EntityIntertwinedGuGuThreeStar;
import com.Not_an_UP.whatgugumod.entity.render.RenderEntityCCCPGuGu;
import com.Not_an_UP.whatgugumod.entity.render.RenderEntityDarkGuGu;
import com.Not_an_UP.whatgugumod.entity.render.RenderEntityGuGu;
import com.Not_an_UP.whatgugumod.entity.render.RenderEntityGuGuEgg;
import com.Not_an_UP.whatgugumod.entity.render.intertwined_gugu.RenderEntityIntertwinedFiveStarGuGu;
import com.Not_an_UP.whatgugumod.entity.render.intertwined_gugu.RenderEntityIntertwinedFourStarGuGu;
import com.Not_an_UP.whatgugumod.entity.render.intertwined_gugu.RenderEntityIntertwinedThreeStarGuGu;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCCCPGuGu.class, RenderEntityCCCPGuGu::new); 
		RenderingRegistry.registerEntityRenderingHandler(EntityGuGuEgg.class, RenderEntityGuGuEgg::new); 
		RenderingRegistry.registerEntityRenderingHandler(EntityGuGu.class, RenderEntityGuGu::new); 
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkGuGu.class, RenderEntityDarkGuGu::new); 
		RenderingRegistry.registerEntityRenderingHandler(EntityIntertwinedGuGuThreeStar.class, RenderEntityIntertwinedThreeStarGuGu::new); 
		RenderingRegistry.registerEntityRenderingHandler(EntityIntertwinedGuGuFourStar.class, RenderEntityIntertwinedFourStarGuGu::new); 
		RenderingRegistry.registerEntityRenderingHandler(EntityIntertwinedGuGuFiveStar.class, RenderEntityIntertwinedFiveStarGuGu::new); 
	}
}
