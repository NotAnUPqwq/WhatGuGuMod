package com.Not_an_UP.whatgugumod.items.books;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class GuGuBookBase {
	public static final GuGuBookBase INSTANCE = new GuGuBookBase("empty", new String[] {});
	
	protected String id;
	protected String title;
	protected String author;
	protected String[] pages;
	
	public GuGuBookBase(String id, String[] pages) {
		this.id = id;
		this.title = I18n.format("book.whatgugumod."+ id +".title");
		this.author = I18n.format("book.whatgugumod."+ id +".author");
		this.pages = pages;
	}
	
	protected static String[] build(String... args) {
		List<String> output = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		
		for (String i : args) {
			if (i == "<page>") {
				output.add(builder.toString());
				builder.setLength(0);
			} else if (builder.length() != 0){
				builder.append("\\n" + i);
			} else {
				builder.append(i);
			}
		}
		
		return output.toArray(new String[output.size()]);
	}
	
	public ItemStack getBook() {
		if (this.id == "empty")
			return ItemStack.EMPTY;
		
		ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
		NBTTagCompound tag = new NBTTagCompound();
	    
	    // 标题+作者
	    tag.setString("title", this.title);
	    tag.setString("author", this.author);
	    tag.setByte("resolved", (byte)1);
	    
	    NBTTagList pages = new NBTTagList();
	    for (String content : this.pages) {
	        // 使用JSON文本格式，确保换行和格式正确
	        String pageJson = "{\"text\":\"" + content.replace("\"", "\\\"") + "\"}";
	        pages.appendTag(new NBTTagString(pageJson));
	    }
	    
	    tag.setTag("pages", pages);
	    book.setTagCompound(tag);
	    
		return book;
	}
	
	public String getId() {
		return this.id;
	}
}
