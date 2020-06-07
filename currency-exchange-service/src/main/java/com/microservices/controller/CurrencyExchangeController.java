package com.microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.bean.ExchangeValue;
import com.microservices.dao.ExchangeValueRepository;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	private Environment env;
	
	@Autowired
	private ExchangeValueRepository repository;

	@GetMapping("/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		logger.info("Inside method retriveExchangeValue of Exchange Service");
		ExchangeValue exchangeValue= repository.findByFromAndTo(from, to);
				//new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
		exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		return exchangeValue;
	}
}
