package com.sentiment.model;

/*
 * Author: Siddhant Mahajani
 */

public class Results {
	
	double sentimentScore;
	String sentimentType;
	Classification sentimentClass;
	
	public double getSentimentScore() {
		return sentimentScore;
	}
	
	public void setSentimentScore(final double sentimentScore) {
		this.sentimentScore = sentimentScore;
	}
	
	public String getSentimentType() {
		return sentimentType;
	}
	
	public void setSentimentType(final String sentimentType) {
		this.sentimentType = sentimentType;
	}
	
	public Classification getSentimentClass() {
		return sentimentClass;
	}
	
	public void setSentimentClass(final Classification sentimentClass) {
		this.sentimentClass = sentimentClass;
	}

}
