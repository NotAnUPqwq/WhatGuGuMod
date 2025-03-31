package com.Not_an_UP.whatgugumod.items.books;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public class GuGuBookHandler {
	public static final HashMap<String, GuGuBookBase> BookMap = new HashMap<String, GuGuBookBase>();
	
	public static void init() {
		register(BaiNianGuDu.INSTANCE);
		register(GuGuEULA.INSTANCE);
	}

	private static void register(GuGuBookBase book) {
		BookMap.put(book.getId(), book);
	}
	
	public static ItemStack getBook(String id) {
		return BookMap.getOrDefault(id, GuGuBookBase.INSTANCE).getBook();
	}
}
