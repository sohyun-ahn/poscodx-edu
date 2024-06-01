package com.poscodx.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {

	public static Connection get() throws SQLException{
		Connection conn = null;
		
		//1. JDBC Driver 로딩
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String usrName = "webdb";
			String usrPW = "webdb";

			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.5:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, usrName, usrPW);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}
}
