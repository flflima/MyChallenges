package br.com.challenges.lowercase.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.log4j.Logger;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		try {
			String fileName = args[0];
			File file = new File(fileName);
			logger.debug("Caminho do arquivo: " + file.getAbsolutePath());
			
			logger.info("Iniciando leitura");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

			String line;
			StringBuffer buffer = new StringBuffer();
			
			while ((line = bufferedReader.readLine()) != null) {
				String[] lineArray = line.split("\\s");
				if (lineArray.length > 0) {
					logger.debug("Lendo a linha: " + line);
					StringBuilder builder = new StringBuilder();
					for (int i = 0; i < lineArray.length; i++) {
						builder.append(lineArray[i].toLowerCase()).append(" ");
					}
					buffer.append(builder.toString().trim()).append("\n");
				}
			}
			
			buffer.replace(buffer.length() - 1, buffer.length(), "");
			
			logger.info("String em lowerCase: \n" + buffer.toString());
			
			bufferedReader.close();
		} catch (Exception e) {
			logger.debug("Erro ao manipular o arquivo: ", e);
		}
	}

}
