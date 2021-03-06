package com.intuit.craft.feed.exchange.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment env;
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		String query = "SELECT * FROM exchange_value WHERE from_currency = ? and to_currency = ?";
		ExchangeValue ev = jdbcTemplate.queryForObject(
				query, new ExchangeValueMapper(), from, to);
		ev.setPort(env.getProperty("server.port"));
		 
		return ev;
	}
}
