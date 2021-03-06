package com.intuit.craft.feed.exchange.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ExchangeValueMapper implements RowMapper<ExchangeValue> {

	@Override
	public ExchangeValue mapRow(ResultSet rs, int rowNum) throws SQLException {
		ExchangeValue ev = new ExchangeValue();
	        ev.setId(rs.getLong("ID"));
	        ev.setFrom(rs.getString("FROM_CURRENCY"));
	        ev.setTo(rs.getString("TO_CURRENCY"));
	        ev.setConversionMultiple(rs.getBigDecimal("CONVERSION_MULTIPLE"));
	        return ev;
	}



}
