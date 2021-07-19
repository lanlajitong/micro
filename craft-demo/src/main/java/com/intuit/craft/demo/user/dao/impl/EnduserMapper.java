package com.intuit.craft.demo.user.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.intuit.craft.demo.user.dto.EnduserDTO;

public class EnduserMapper implements RowMapper<EnduserDTO> {

	@Override
	public EnduserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		EnduserDTO o = new EnduserDTO();
		o.setId(rs.getLong("ID"));
		String name = rs.getString("NAME");
		if(name != null) {
			String[] split = name.trim().split(" ");
			o.setFirstName(split[0]);
			if(split.length > 1)
				o.setLastName(split[1]);
		}
		o.setBirthday(rs.getDate("BIRTHDAY"));
	    return o;
	}




}
