package com.microsrvices.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsrvices.bean.LimitsConfiguration;

@RestController
public class LimitsConfigController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitsConfiguration retriveLimitsConfiguration() {
		return new LimitsConfiguration(configuration.getMaxiimum(), configuration.getMinimum());
	}
}
