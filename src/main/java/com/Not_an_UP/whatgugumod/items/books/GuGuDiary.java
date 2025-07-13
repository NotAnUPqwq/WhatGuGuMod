package com.Not_an_UP.whatgugumod.items.books;

public class GuGuDiary extends GuGuBookBase {
	public static final GuGuDiary INSTANCE = new GuGuDiary();
	
	public GuGuDiary() {
		super("diary", build(
				
		  "正经人谁写日记啊？",
		  "<page>"
		
		));
	}
}
