package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectDB {
	
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String usrID = "bookmall";
			String usrPW = "bookmall";
			
			String url = "jdbc:mariadb://192.168.64.5:3306/bookmall?charset=utf8"; // url의 맨 앞은 프로토콜 + DBschema(employees) + options
			conn = DriverManager.getConnection(url, usrID, usrPW); // SQLException 처리 필요하지만 바깥의 다른 것들이 처리하므로 throws~
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		
		return conn;
	}
}
