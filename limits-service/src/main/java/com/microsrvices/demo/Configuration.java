package com.microsrvices.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
public class Configuration {

	private int minimum;
	private int maxiimum;
	
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaxiimum() {
		return maxiimum;
	}
	public void setMaxiimum(int maxiimum) {
		this.maxiimum = maxiimum;
	}

	
}
