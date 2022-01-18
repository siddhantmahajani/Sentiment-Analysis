package com.sentiment.nlp;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.readFile.ReadFile;
import com.sentiment.model.Results;

/*
 * Author: Siddhant Mahajani
 * Sentimental analysis response:
 * "Very negative" = 0 "Negative" = 1 "Neutral" = 2 "Positive" = 3
 * "Very positive" = 4
 */

public class Main {
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		logger.info("Sentimental Analysis started on: {}", LocalDateTime.now(ZoneId.systemDefault()));
		try {
			final String filePath = "/Users/siddhantmahajani/Downloads/Test/test1.txt";
			final String sentences = ReadFile.readFile(filePath);
			logger.info("Data in file: {}", sentences);
			SentimentAnalyser.init();
			logger.info("Sentiment analyser intialised.");
			final Results results = SentimentAnalyser.getSentimentResults(sentences);
			logger.info("Sentiment score: {}", results.getSentimentScore());
			logger.info("Sentiment type: {}", results.getSentimentType());
			logger.info("Very positive: {}%", results.getSentimentClass().getVeryPositive());
			logger.info("Positive: {}%", results.getSentimentClass().getPositive());
			logger.info("Neutral: {}%", results.getSentimentClass().getNeutral());
			logger.info("Negative: {}%", results.getSentimentClass().getNegative());
			logger.info("Very negative: {}%", results.getSentimentClass().getVeryNegative());
			logger.info("Sentiment Analysis ended on: {}", LocalDateTime.now(ZoneId.systemDefault()));
		} catch (final Exception ex) {
			logger.info("Exception thrown while performing sentimental analysis: {}", ex.getMessage());
			ex.printStackTrace();
		}
	}

}
