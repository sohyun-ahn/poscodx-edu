package guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import guestbook.vo.GuestbookVo;

public class GuestbookDao {
	
	public boolean insert(GuestbookVo vo) {
		boolean result = false;
		Connection conn = connectDB();
		PreparedStatement pstmt = null;
		try {
			// Statement 준비하기
			String sql = "insert into guestbook values(null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
		   
			// binding  
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getRegDate());
			
		    // SQL 실행
		    int count = pstmt.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력
		    
			// 결과 처리
		    result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
	

	public boolean deleteByNoAndPassword(Long no, String password) {
		boolean result = false;
		Connection conn = connectDB();
		PreparedStatement pstmt = null;
		
		try {
			// 3. Statement 준비
			String sql = "delete from guestbook where no = ? and password = ?"; // JDBC안에는 ;를 쓰면 안됨, 쓰면 쿼리 2개로 인식
		    pstmt = conn.prepareStatement(sql);
		   
		    // 4. binding
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
		    
		    // 5. SQL 실행
		    int count = pstmt.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력
			
			// 6. 결과 처리
		    result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
		
		return result;
		
	}
	
	public List<GuestbookVo> findAll() {
 		List<GuestbookVo> result = new ArrayList<>();
 		Connection conn = connectDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = connectDB();
			
			//3. Statement 준비
			String sql =
				"select no, name, date_format(reg_date, '%Y-%m-%d'), content" + 
			    " from guestbook" + 
				" order by reg_date desc";
			pstmt = conn.prepareStatement(sql);

			//4. binding

			//5. SQL 실행
			rs = pstmt.executeQuery();

			//6. 결과 처리
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String regDate = rs.getString(3);
				String content = rs.getString(4);

				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setRegDate(regDate);
				vo.setContent(content);

				result.add(vo);
			}
		} catch (SQLException e) {
				System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}

				if(pstmt != null) {
					pstmt.close();
				}

				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	private Connection connectDB() {
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
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		return conn;
	}
}
