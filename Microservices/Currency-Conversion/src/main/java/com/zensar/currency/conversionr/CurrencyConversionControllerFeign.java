package com.zensar.currency.conversionr;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionControllerFeign {

	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable ("from") String from,
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
		
		return new CurrencyConversionBean(response.getId(), from,to,response.getConversionMultiple(),
				quantity,
				quantity.multiply(response.getConversionMultiple()),response.getPort());
	}
}
