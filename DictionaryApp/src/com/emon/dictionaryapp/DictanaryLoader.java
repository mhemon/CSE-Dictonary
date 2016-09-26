package com.emon.dictionaryapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;



public class DictanaryLoader {
	
	public static void loadData(BufferedReader bufferedReader,DictanaryDataBaseHelper dictanaryDataBaseHelper) {
		
		ArrayList<WordDefination> allWords = new ArrayList<WordDefination>();

		try {

			BufferedReader fileReader = bufferedReader;

			try {

				int c = 17;
				c = fileReader.read();
				while (c != (-1)) {

					StringBuilder stringBuilder = new StringBuilder();

					while ((char) c != '\n' && c != -1) {
						try {
							stringBuilder.append((char) c);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println(stringBuilder.length());
							// e.printStackTrace();
						}
						c = fileReader.read();
						if (c == -1) {
							return;
						}
					}

					String wordString = stringBuilder.toString();

					ArrayList<String> definition = new ArrayList<String>();
					while (c == '\n' || c == '\t') {
						c = fileReader.read();
						if (c == '\n' || c == '\t' || c == '\r') {
							StringBuilder stringBuilder2 = new StringBuilder();
							while (c != '\n') {
								stringBuilder2.append((char) c);
								c = fileReader.read();
							}
							String definitionString = stringBuilder2.toString();
							definition.add(definitionString);
						} else {
							break;
						}

					}

					wordString = wordString.trim();
					// Logger.log("word Loaded: "+(++counter)+" :"+wordString);
					allWords.add(new WordDefination(wordString, definition));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				dictanaryDataBaseHelper
						.initializeDatabaseFortheFirstTime(allWords);
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
