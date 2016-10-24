package br.com.challenges.compressed.sequence.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Given a string with a number sequence, write a program to compress 
 * it as follows:
 * 
 * input.: 1 1 3 3 3 2 2 2 2 14 14 14 11 11 11 2
 * output: 2 1 3 3 4 2 3 14 3 11 1 2
 *
 */
public class Main {
	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 99;
	private static final int MIN_LINE_LENGTH = 0;
	private static final int MAX_LINE_LENGTH = 400;
	private static final int MIN_NUMBER_LINES = 20;
	private static final int MAX_NUMBER_LINES = 50;
	private static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			String fileName = args[0];
			File file = new File(fileName);
			logger.debug("Caminho do arquivo: " + file.getAbsolutePath());

			validateNumberLines(file);
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
		validateLineLength(lineArray.length);
		
		int lastNumber = -1;
		int totalCount = 0;
		
		for (int i = 0; i < lineArray.length; i++) {
			String actualNumber = lineArray[i];
			validateValue(actualNumber);
			
			if (lastNumber < 0) {
				lastNumber = Integer.valueOf(actualNumber);
				totalCount++;
			} else {
				if (Integer.valueOf(actualNumber).equals(lastNumber)) {
					totalCount++;
				} else {
					tempBuffer.append(totalCount + " " + lastNumber + " ");
					totalCount = 1;
				}
				lastNumber = Integer.valueOf(actualNumber);
			}
			
		}
		
		return tempBuffer.append(totalCount + " " + lastNumber).append("\n");
	}

	private static void validateValue(String valueString) {
		Integer valueAsNumber = Integer.valueOf(valueString);
		if (valueAsNumber.intValue() < MIN_VALUE || valueAsNumber.intValue() > MAX_VALUE) {
			logger.error(String.format("This value is out of range: %d", valueAsNumber));
			throw new RuntimeException("N is in a range from 0 to 99.");
		}
	}

	private static void validateNumberLines(File file) throws IOException {
		int countLines = countLines(file);
		if (countLines < MIN_NUMBER_LINES || countLines > MAX_NUMBER_LINES) {
			logger.error(String.format("Incorrect total number of lines: %d", countLines));
			throw new RuntimeException("T is in a range from 20 to 50");
		}
	}

	private static void validateLineLength(int lineLength) throws IOException {
		if (lineLength <= MIN_LINE_LENGTH || lineLength > MAX_LINE_LENGTH) {
			logger.error(String.format("Incorrect line length: %d", lineLength));
			throw new RuntimeException("L is in a range from 1 to 400.");
		}
	}

	private static int countLines(File file) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		int total = 0;
		
		while (bufferedReader.readLine() != null) total++;
		
		bufferedReader.close();

		logger.info(String.format("Total de linhas no arquivo: %d", total));
		return total;
	}
}
