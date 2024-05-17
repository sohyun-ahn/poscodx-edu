package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionEx {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		try {
			// 1.JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			String usrID = null;
			String usrPW = null;
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.64.5:3306/webdb?charset=utf8"; // url의 맨 앞은 프로토콜 + schema + options
			connection = DriverManager.getConnection(url, usrID, usrPW); // usrID, usrPW webdb, webdb 
			
			System.out.println("success!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
}
