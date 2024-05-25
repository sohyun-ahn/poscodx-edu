package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;

public class BookDao {

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String usrID = "webdb";
			String usrPW = "webdb";
			
			String url = "jdbc:mariadb://192.168.64.5:3306/webdb?charset=utf8"; // url의 맨 앞은 프로토콜 + DBschema(employees) + options
			conn = DriverManager.getConnection(url, usrID, usrPW); // SQLException 처리 필요하지만 바깥의 다른 것들이 처리하므로 throws~
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		
		return conn;
	}
	
	
	public int insert(BookVo vo) {
		int result = 0;
		
		// JDBC 코드 넣기
		try (
			// assign 구문 중에 왼쪽부분이 close가 있는 경우에만 들어올 수 있음
			Connection conn = getConnection(); // 중복코드는 함수로 만듦, 이 ()안에는 assign함수만 들어올 수 있음, 자동으로 닫아주기때문에 자원정리도 안해도 됨
			PreparedStatement pstmt1 = conn.prepareStatement("insert into book(title, author_no) values(?, ?)"); // SQLException 처리 필요
			PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
		) {
			pstmt1.setString(1,  vo.getTitle());
			pstmt1.setLong(2,  vo.getAuthorNo());
			result = pstmt1.executeUpdate();
			
			ResultSet rs = pstmt2.executeQuery(); 
			
			vo.setNo(rs.next() ? rs.getLong(1) : null);
			rs.close();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}


	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		
		try (
			// assign 구문 중에 왼쪽부분이 close가 있는 경우에만 들어올 수 있음
			Connection conn = getConnection(); // 중복코드는 함수로 만듦, 이 ()안에는 assign함수만 들어올 수 있음, 자동으로 닫아주기때문에 자원정리도 안해도 됨
			PreparedStatement pstmt = conn.prepareStatement("select a.no, a.title, a.status, b.name from book a, author b where a.author_no = b.no order by no desc"); // SQLException 처리 필요
			ResultSet rs = pstmt.executeQuery(); 
		) {
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String status = rs.getString(3);
				String authorName = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setStatus(status);
				vo.setAuthorName(authorName);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}


	public Integer deleteByNo(Long no) {
		int result = 0;
		
		// JDBC 코드 넣기
		try (
			// assign 구문 중에 왼쪽부분이 close가 있는 경우에만 들어올 수 있음
			Connection conn = getConnection(); // 중복코드는 함수로 만듦, 이 ()안에는 assign함수만 들어올 수 있음, 자동으로 닫아주기때문에 자원정리도 안해도 됨
			PreparedStatement pstmt = conn.prepareStatement("delete from book where no = ?"); // SQLException 처리 필요
			
		) {
			pstmt.setLong(1,  no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		return result;
		
	}


	public Integer update(Long no, String status) {
		int result = 0;
		
		// JDBC 코드 넣기
		try (
			// assign 구문 중에 왼쪽부분이 close가 있는 경우에만 들어올 수 있음
			Connection conn = getConnection(); // 중복코드는 함수로 만듦, 이 ()안에는 assign함수만 들어올 수 있음, 자동으로 닫아주기때문에 자원정리도 안해도 됨
			PreparedStatement pstmt = conn.prepareStatement("update book set status = ? where no = ?"); // SQLException 처리 필요
			
		) {
			pstmt.setString(1,  status);
			pstmt.setLong(2,  no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}


	public int deleteAll() {
		int result = 0;

		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from book");
		) {
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
		
	}

}
