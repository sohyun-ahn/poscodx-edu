package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			// 1.JDBC Driver 로딩
			Class.forName("driver.MyDriver"); // 드라이버만들어서 등록
			
			String usrID = null;
			String usrPW = null;
			// facade패턴
			
			// 2. 연결하기
			String url = "jdbc:mydb://127.0.0.1:2202/webdb"; // url의 맨 앞은 프로토콜 + schema + options
			connection = DriverManager.getConnection(url, usrID, usrPW); // usrID, usrPW webdb, webdb 
			
			System.out.println("success!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}catch (SQLException e) {
			// null을 받으면 출력
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
