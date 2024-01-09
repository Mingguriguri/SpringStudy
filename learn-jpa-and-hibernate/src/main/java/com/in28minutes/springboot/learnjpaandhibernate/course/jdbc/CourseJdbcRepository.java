package com.in28minutes.springboot.learnjpaandhibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;

@Repository
public class CourseJdbcRepository {
	
	@Autowired
	private JdbcTemplate springJdbcTemplate;
	
	private static String INSERT_QUERY = 
			"""
			insert into course (id, name, author)
			values (?, ?, ?);
			""";
	
	private static String DELETE_QUERY = 
			"""
			delete from course
			where id = ?
			""";
	private static String SELECT_QUERY = 
			"""
			select * from course
			where id = ?
			""";
	
	
	
	public void insert(Course course) {
		springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
	}
	
	public void deleteById(long id) {
		springJdbcTemplate.update(DELETE_QUERY, id);
	}
	
	public Course findById(long id) {
		// (2) ResultSet -> Bean => Row Mapper : 각 Bean에 연결해줌 (Use BeanPropertyRowMapper)
		// (3) Id (input)
		return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
	}
}
