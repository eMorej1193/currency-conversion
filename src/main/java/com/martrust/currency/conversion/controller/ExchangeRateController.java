package com.martrust.currency.conversion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.martrust.currency.conversion.model.ExchangeResponseModel;
import com.martrust.currency.conversion.service.ExchangeRateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1")
@Api(value="Exchange Rate API")
public class ExchangeRateController {
	
	@Autowired 
	private ExchangeRateService exchangeRateService;
	
	@GetMapping("/exchange/rates/{fromCountry}/to/{toCountry}")
	@ApiOperation(value  = "")
	public ExchangeResponseModel retrieveExchangeRate(@PathVariable String fromCountry, @PathVariable String toCountry, @RequestParam double amount) throws Exception {
		return exchangeRateService.calculateExchangeRate(fromCountry, toCountry, amount);
	}
}
