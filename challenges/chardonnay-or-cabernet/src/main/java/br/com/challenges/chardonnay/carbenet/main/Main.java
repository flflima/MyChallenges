package br.com.challenges.chardonnay.carbenet.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * 
 *
 */
public class Main {
	private static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			String fileName = args[0];
			File file = new File(fileName);
			logger.debug("Caminho do arquivo: " + file.getAbsolutePath());

			StringBuffer buffer = readLine(file);
			
			System.out.print(buffer.toString());
		} catch (Exception e) {
			logger.error("Erro ao manipular o arquivo: ", e);
		}
	}
	
	private static StringBuffer readLine(File file) throws IOException {
		logger.info("Iniciando leitura");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		
		String line;
		StringBuffer buffer = new StringBuffer();
		
		while ((line = bufferedReader.readLine()) != null) {
			buffer.append(buildOutputLine(line));
		}
		
		buffer.replace(buffer.length() - 1, buffer.length(), "");
		bufferedReader.close();
		return buffer;
	}

	private static StringBuffer buildOutputLine(String line) throws IOException {
		StringBuffer tempBuffer = new StringBuffer();
		logger.info("Lendo a linha: " + line);

		String[] lineArray = line.split("\\s");
		
		for (int i = 0; i < lineArray.length; i++) {
			
		}
		
		return tempBuffer.append("").append("\n");
	}

}
