package com.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.bean.CurrencyConversionBean;
import com.microservices.feign.CurrencyExchangeServiceProxy;


@RestController
@RequestMapping("currency-converter")
public class CurrencyConverterController {
	private Logger logger = LoggerFactory.getLogger(CurrencyConverterController.class);
	
	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean retriveExchangeConversionValue(@PathVariable String from, 
			@PathVariable String to, 
			@PathVariable BigDecimal quantity) {
		
		logger.info("Inside method retriveExchangeConversionValue of Conversion Service");
		
		Map<String, String> uriVeriable = new HashMap<>();
		uriVeriable.put("from", from);
		uriVeriable.put("to", to);
		ResponseEntity<CurrencyConversionBean> responseEntity =  new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVeriable);
		CurrencyConversionBean response = responseEntity.getBody();
		
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple() , quantity.multiply(response.getConversionMultiple()), quantity, response.getPort());
	}
	
	@GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean retriveExchangeConversionValueFeign(@PathVariable String from, 
			@PathVariable String to, 
			@PathVariable BigDecimal quantity) {
		logger.info("Inside method retriveExchangeConversionValueFeign of Conversion Service");
		CurrencyConversionBean response = currencyExchangeServiceProxy.retriveExchangeValue(from, to);
		
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple() , quantity.multiply(response.getConversionMultiple()), quantity, response.getPort());
	}

}
