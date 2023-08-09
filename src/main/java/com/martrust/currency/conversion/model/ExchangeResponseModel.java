package com.martrust.currency.conversion.model;

import java.util.List;
import java.util.Map;


public class ExchangeResponseModel {
	
	private List<Map<String, Object>> countryRate;
	private String base;
	private List<Map<String, Object>> symbols; 
	private ExchangeRate exchangeRate;


	public List<Map<String, Object>> getCountryRate() {
		return countryRate;
	}
	public void setCountryRate(List<Map<String, Object>> countryRate) {
		this.countryRate = countryRate;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public List<Map<String, Object>> getSymbols() {
		return symbols;
	}
	public void setSymbols(List<Map<String, Object>> symbols) {
		this.symbols = symbols;
	}
	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
}
