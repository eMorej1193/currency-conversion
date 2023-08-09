package com.martrust.currency.conversion.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.martrust.currency.conversion.api.ExchangeExternalApi;
import com.martrust.currency.conversion.model.ExchangeResponseModel;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class ExchangeRateServiceTest {
	
	private String applicationJson;
	
	@Mock 
	private ExchangeExternalApi exchangeExternalApi;
	
	@Mock
	private ObjectMapper objectMapper;
	
	@InjectMocks
	@Spy
	private ExchangeRateService exchangeRateService;
	
	public Configuration configuration;
	
	private DocumentContext appDc;
	
	private List<String> countries = new ArrayList<String>();
	
	
	@Before
	public void setup() throws IOException {
		countries.add("EUR");
		ClassPathResource applicationResource = new ClassPathResource("examples/latestRates.json");
		applicationJson = IOUtils.toString(applicationResource.getInputStream(), StandardCharsets.UTF_8.name());
		ReflectionTestUtils.setField(exchangeRateService, "accessKey", "ddf7ee6268302e7b992d58e1c4ce0f49");
		ReflectionTestUtils.setField(exchangeRateService, "activeCountries", countries);
		
		objectMapper = new ObjectMapper()
				.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
				.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.disable(SerializationFeature.INDENT_OUTPUT)
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		configuration = com.jayway.jsonpath.Configuration.builder()
				.jsonProvider(new JacksonJsonProvider(objectMapper))
				.mappingProvider(new JacksonMappingProvider(objectMapper)).build()
				.addOptions(Option.SUPPRESS_EXCEPTIONS)
				.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
		
		appDc = JsonPath.using(configuration).parse(applicationJson);
	}
	
	@Test
	public void calculateExchangeRateTest() throws Exception {
		when(exchangeExternalApi.getLatestRates(any(), any())).thenReturn(applicationJson);
		ExchangeResponseModel model =  exchangeRateService.calculateExchangeRate("EUR", "JPY", 25);
		assertNotNull(model);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calculateExchangeRateTest_Null() throws Exception {
		when(exchangeExternalApi.getLatestRates(any(), any())).thenReturn(null);
		ExchangeResponseModel model =  exchangeRateService.calculateExchangeRate("EUR", "JPY", 25);
	}

	
	

}
