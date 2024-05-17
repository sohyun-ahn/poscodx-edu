package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectEx02 {

	public static void main(String[] args) {
		// employees DB 가서 할 것 
		search("pat");
	}
	
	public static void search(String keyword) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1.JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			String usrID = "hr";
			String usrPW = "hr";
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.64.5:3306/employees?charset=utf8"; // url의 맨 앞은 프로토콜 + DBschema(employees) + options
			conn = DriverManager.getConnection(url, usrID, usrPW); 
			
			// 3. Statement 준비하기
			String sql = "select emp_no, first_name, last_name"
					+ "  from employees"
					+ " where first_name like ?"
					+ "   and last_name like ?"; // ?: string을 통째로 바인딩시킴 = parameter
		    pstmt = conn.prepareStatement(sql);
		   
		    // 4. parameter binding
		    pstmt.setString(1,  "%" + keyword + "%"); // binding
		    pstmt.setString(2,  "%" + keyword + "%");
		    
		    // 5. binding 이후 SQL 실행
		    rs = pstmt.executeQuery(); // select문에서는 executeQuery 
		                               // ()안에 sql이 아니라 binding된 sql문을 실행시켜야하기때문에 비워놓아야함!!
		    
			// 6. 결과 처리
		    while(rs.next()) {
		    	Long empNo = rs.getLong(1);
		    	String firstName = rs.getString(2);
		    	String lastName = rs.getString(3);
		    	
		    	System.out.println(empNo + ":" + firstName + ":" + lastName);
		    }
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}catch (SQLException e) {
			// null을 받으면 출력
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				// 닫을땐 반대로 statement 먼저 닫고 connection 닫기
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			
		}
	}
}
