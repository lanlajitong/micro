package com.intuit.craft.demo.user;

import java.sql.SQLDataException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOService implements UserDAO {
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	private final String SQL_INSERT = "insert into Enduser(name, birthday) values(:name, :birthday)";
	private final String SQL_UPDATE = "update Enduser set name = :name, birthday = :birthday where id = :id";
	private final String SQL_DELETE = "delete from Enduser where id = :id";
	@Override
	public List<Enduser> findUsersIDLessThan(long upper, int pageNo, int pageSize) {
		String query = "SELECT id, name FROM Enduser WHERE id < :id ORDER BY id asc  LIMIT :limit OFFSET :offset";
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", upper)
				.addValue("limit", pageSize).addValue("offset", pageSize*pageNo);
		List<Enduser> users = jdbcTemplate.query(
				query, namedParameters, new EnduserMapper());
		return users;
	}

	@Override
	public boolean createUser(Enduser user) {
		return jdbcTemplate.update(SQL_INSERT, new BeanPropertySqlParameterSource(user)) > 0;
	}

	@Override
	@Transactional
	public boolean updateUser(Enduser user) {
		user.setName("bbb");
		jdbcTemplate.update(SQL_UPDATE, new BeanPropertySqlParameterSource(user));
		return jdbcTemplate.update("SQL_UPDATE", new BeanPropertySqlParameterSource(user)) > 0;

	}

	@Override
	public boolean deleteUser(long id) {
		return jdbcTemplate.update(SQL_DELETE, new MapSqlParameterSource().addValue("id", id)) > 0;

	}
}
