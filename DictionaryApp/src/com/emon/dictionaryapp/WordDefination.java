package com.emon.dictionaryapp;

import java.util.ArrayList;

public class WordDefination {
    String word,defination;
    
    public WordDefination(String word,ArrayList<String> alldefination) {
		this.word=word;
		
		StringBuilder stringBuilder=new StringBuilder();
		for (String string : alldefination) {
			stringBuilder.append(string);
		}
		this.defination=stringBuilder.toString();
	}
    
     public WordDefination(String word,String alldefination) {
		this.word=word;
		this.defination=alldefination.toString();
	}
    
}
