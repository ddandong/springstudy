package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.domain.Customer;

@EnableAutoConfiguration
public class App implements CommandLineRunner
{
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	

	public void run(String...strings) throws Exception{
		/*String sql = "SELECT 1";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer result = jdbcTemplate.queryForObject(sql, param, Integer.class);
		
		System.out.println("result = " + result);*/
		
		/*String sql = "SELECT :a + :b";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("a", 100)
				.addValue("b",200);
		Integer result = jdbcTemplate.queryForObject(sql, param, Integer.class);
		System.out.println("result : " + result);*/
		
		String sql = "SELECT id, first_name, last_name FROM customers WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("id",1);
		
		/*Customer result = jdbcTemplate.queryForObject(sql, param,
				new RowMapper<Customer>(){

			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
			
		});*/
		
		//lambda
		Customer result = jdbcTemplate.queryForObject(sql, param,
				(rs,rowNum)-> new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"))
		);
		System.out.println("result :" + result);
	}
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
