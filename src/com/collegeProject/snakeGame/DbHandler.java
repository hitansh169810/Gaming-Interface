package com.collegeProject.snakeGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHandler {

	public static int hignScore ;
	
	public static Connection getConnnection() throws SQLException {
		Connection connection = null;
		//		String driverName = "org.postgresql.Driver" ;
		String dburl = "jdbc:postgresql://localhost:5432/game" ;
		String dbid = "postgres" ;
		String pass = "1234" ;
		connection = DriverManager.getConnection(dburl,dbid,pass);
		System.out.println("Connection Created ");
		return connection ;
	}

	
}
