package com.sentiment.nlp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Properties;

import org.ejml.simple.SimpleMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sentiment.model.Classification;
import com.sentiment.model.Results;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.StringUtils;

/*
 * "Very negative" = 0 "Negative" = 1 "Neutral" = 2 "Positive" = 3
 * "Very positive" = 4
 */

/*
 * Author: Siddhant Mahajani
 */

public class SentimentAnalyser {
	
	private static final Logger logger = LoggerFactory.getLogger(SentimentAnalyser.class);
	
	static StanfordCoreNLP pipeline;
	
	public static void init() {
		logger.info("Initialisation of sentimental analyses started on: {}", LocalDateTime.now(ZoneId.systemDefault()));
		final Properties properties = new Properties();
		properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		pipeline = new StanfordCoreNLP(properties);
		logger.info("Sentiment analyser initialisation ended on: {}", LocalDateTime.now(ZoneId.systemDefault()));
	}
	
	public static Results getSentimentResults(final String sentence) {
		final Results results = new Results();
		final Classification classification = new Classification();
		if (!StringUtils.isNullOrEmpty(sentence)) {
			logger.info("Annotations generated for analysis");
			final Annotation annotation = pipeline.process(sentence);
			for (final CoreMap sentenceCoreMap : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
				logger.info("Parse tree of current sentences");
				final Tree tree = sentenceCoreMap.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
				final SimpleMatrix simpleMatrix = RNNCoreAnnotations.getPredictions(tree);
				final String sentimentType = sentenceCoreMap.get(SentimentCoreAnnotations.SentimentClass.class);
				
				logger.info("Added sentiment values to Classification class.");
				classification.setVeryPositive((double)Math.round(simpleMatrix.get(4) * 100d));
				classification.setPositive((double)Math.round(simpleMatrix.get(3) * 100d));
				classification.setNeutral((double)Math.round(simpleMatrix.get(2) * 100d));
				classification.setNegative((double)Math.round(simpleMatrix.get(1) * 100d));
				classification.setVeryNegative((double)Math.round(simpleMatrix.get(0) * 100d));
				
				logger.info("Added information to Results");
				results.setSentimentScore(RNNCoreAnnotations.getPredictedClass(tree));
				results.setSentimentType(sentimentType);
				results.setSentimentClass(classification);
			}
		} else {
			logger.error("The entered string is null or empty. Please add appropriate non empty string.");
		}
		return results;
	}

}
