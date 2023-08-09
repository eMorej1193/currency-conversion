package com.martrust.currency.conversion.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchangeExternalApi", url = "http://api.exchangeratesapi.io/")
public interface ExchangeExternalApi {

	@GetMapping({ "v1/convert" })
	String getConversion(@RequestParam("access_key") String access_key,
			@RequestParam("from") String from,
			@RequestParam("to") String to,
			@RequestParam("amount") int amount);
	
	@GetMapping({ "v1/latest" })
	String getLatestRates(@RequestParam("access_key") String access_key,
			@RequestParam("base") String base);
	
}
