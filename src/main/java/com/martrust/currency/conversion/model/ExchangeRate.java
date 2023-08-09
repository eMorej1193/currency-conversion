package com.martrust.currency.conversion.model;


public class ExchangeRate {
	
	private String fromCurrency;
	private String toCurrency;
	private double amount;
	private double exchangeAmount;
	private double rate;

	public ExchangeRate(String fromCurrency, String toCurrency, double amount, double exchangeAmount, double rate) {
		super();
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.amount = amount;
		this.exchangeAmount = exchangeAmount;
		this.rate = rate;
	}
	
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getFromCurrency() {
		return fromCurrency;
	}
	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	public String getToCurrency() {
		return toCurrency;
	}
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getExchangeAmount() {
		return exchangeAmount;
	}
	public void setExchangeAmount(double exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}
}
