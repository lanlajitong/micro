package com.intuit.craft.demo.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EnduserMapper implements RowMapper<Enduser> {

	@Override
	public Enduser mapRow(ResultSet rs, int rowNum) throws SQLException {
		Enduser o = new Enduser();
		o.setId(rs.getLong("ID"));
		o.setName(rs.getString("NAME"));
		o.setBirthday(rs.getDate("BIRTHDAY"));
	    return o;
	}




}
