package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertEx02 {
	
	public static void main(String[] args) {
		System.out.println(insert("기획1팀"));
		System.out.println(insert("기획2팀"));
	}
	
	
	public static boolean insert(String deptName) {
		boolean result = false;
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			// 1.JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			String usrID = "webdb";
			String usrPW = "webdb";
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.64.5:3306/webdb?charset=utf8"; // url의 맨 앞은 프로토콜 + schema + options
			conn = DriverManager.getConnection(url, usrID, usrPW); 
			
			// 3. Statement 준비하기
			String sql = "insert into dept values(null, ?)"; // JDBC안에는 ;를 쓰면 안됨, 쓰면 쿼리 2개로 인식
			pstmt = conn.prepareStatement(sql);
		   
			// 4. binding
			pstmt.setString(1, deptName);
			
		    // 5. SQL 실행
		    int count = pstmt.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력
			
			// 6. 결과 처리
		    result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}catch (SQLException e) {
			// null을 받으면 출력
			System.out.println("error:" + e);
		} finally {
			try {
				// 닫을떈 반대로 statement 먼저 닫고 connection 닫기
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
		
		return result;
	}
}
