package com.martrust.currency.conversion.service;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.martrust.currency.conversion.api.ExchangeExternalApi;
import com.martrust.currency.conversion.exception.MartrustRuntimeExcpetion;
import com.martrust.currency.conversion.model.ExchangeRate;
import com.martrust.currency.conversion.model.ExchangeResponseModel;

@Service
public class ExchangeRateService {
	
	
	@Autowired 
	ExchangeExternalApi exchangeExternalApi;
	
	@Value("${api.externalKey}")
	private String accessKey;
	
	@Value("${active.country.conversion}")
	private List<String> activeCountries;
	
    private Configuration conf = Configuration.builder()
            .options(Option.SUPPRESS_EXCEPTIONS).build();
    
	
	public ExchangeResponseModel calculateExchangeRate(String fromCountry, String toCountry, double amount) throws Exception {
		
		if(!activeCountries.contains(fromCountry)) {
			throw new MartrustRuntimeExcpetion("NOT SUPPORTED COUNTRY");
		}

		String result = exchangeExternalApi.getLatestRates(accessKey, fromCountry);
		DocumentContext dc = JsonPath.using(conf).parse(result);
		String error = dc.read("$.error");
		
		if(null != error) {
			throw new MartrustRuntimeExcpetion("ERROR IN EXTERNAL API");
		}
		
		Map<String,Double > map = dc.read("$.rates", Map.class);
		double rate = map.get(toCountry);
		double exchangeAmount = rate * amount;
		
		ExchangeResponseModel responseModel = new ExchangeResponseModel();
		responseModel.setExchangeRate(new ExchangeRate(fromCountry, toCountry, amount,exchangeAmount, rate));
			
		return responseModel;
		
	}
	

}
