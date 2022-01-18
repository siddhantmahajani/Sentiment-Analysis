package com.sentiment.model;

/*
 * Author: Siddhant Mahajani
 */

public class Classification {
		
	double veryPositive;
	double positive;
	double neutral;
	double negative;
	double veryNegative;
	
	public double getVeryPositive() {
		return veryPositive;
	}
	
	public void setVeryPositive(final double veryPositive) {
		this.veryPositive = veryPositive;
	}
	
	public double getPositive() {
		return positive;
	}
	
	public void setPositive(final double positive) {
		this.positive = positive;
	}
	
	public double getNeutral() {
		return neutral;
	}
	
	public void setNeutral(final double neutral) {
		this.neutral = neutral;
	}
	
	public double getNegative() {
		return negative;
	}
	
	public void setNegative(final double negative) {
		this.negative = negative;
	}
	
	public double getVeryNegative() {
		return veryNegative;
	}
	
	public void setVeryNegative(final double veryNegative) {
		this.veryNegative = veryNegative;
	}
	
}
