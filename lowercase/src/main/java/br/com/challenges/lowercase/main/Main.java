package br.com.challenges.lowercase.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.log4j.Logger;

public class Main {
	//TODO verificar o logger
	private static final Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		try {
			String fileName = args[0];
			logger.info("teste");
			File file = new File(fileName);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
