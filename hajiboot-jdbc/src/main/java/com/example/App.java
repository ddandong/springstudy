package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;

@EnableAutoConfiguration
@ComponentScan
public class App implements CommandLineRunner
{
	@Autowired
	CustomerRepository customerRepository;
	

	public void run(String...strings) throws Exception{
		//데이터 추가
		Customer created = customerRepository.save(new Customer(null,"Hidetoshi","Dekisugi"));
		System.out.println(created + "is created!");
	
		//데이터 표시
		//customerRepository.findAllOrderByName().forEach(System.out::println);
		
		//페이징 처리
		Pageable pageable = new PageRequest(0,3);
		Page<Customer> page = customerRepository.findAllOrderByName(pageable);
		System.out.println("한 페이지당 데이터 수  = " + page.getSize());
		System.out.println("현재 페이지  = " + page.getNumber());
		System.out.println("전체 페이지 수  = " + page.getTotalPages());
		System.out.println("전체 데이터 수  = " + page.getTotalElements());
		page.getContent().forEach(System.out::println);
	}
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
