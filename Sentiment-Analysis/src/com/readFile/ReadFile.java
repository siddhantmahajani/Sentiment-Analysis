package com.readFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Author: Siddhant Mahajani
 */

public class ReadFile {
	
	private static final Logger logger = LoggerFactory.getLogger(ReadFile.class);
	
	public static String readFile(final String fileName) {
		String data = null;
		try {
			logger.info("Reading of file started on: {}", LocalDateTime.now(ZoneId.systemDefault()));
			data = new String(Files.readAllBytes(Paths.get(fileName)));
			logger.info("Reading of file ended on: {}", LocalDateTime.now(ZoneId.systemDefault()));
		} catch (final Exception ex) {
			logger.error("Exception thrown while reading file: {}", ex.getMessage());
			ex.printStackTrace();
		}
		return data;
	}
}
