package com.todoschedule.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

class mysqlTest {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&allowPublicKeyRetrieval=true";
	private static final String USER = "root";
	private static final String PW = "1q2w3e4r!";
	
	@Test
	void test() throws Exception {
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println("성공");
			System.out.println(con);
		} catch (Exception e) {
			System.out.println("에러 발생");
			e.printStackTrace();
		}
	}

}
