package com.poscodx.emaillist.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poscodx.emaillist.vo.EmaillistVo;

@Repository
public class EmaillistRepository {

	public boolean insert(EmaillistVo vo) {
		boolean result = false;

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("insert into emaillist values(null, ?, ?, ?)");) {
			// binding // lastName, firstName,
			pstmt.setString(1, vo.getLastName());
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getEmail());

			// SQL 실행
			int count = pstmt.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력

			// 결과 처리
			result = count == 1;

		} catch (SQLException e) {
			// null을 받으면 출력
			System.out.println("error:" + e);
		}

		return result;
	}

	public boolean deleteByEmail(String email) {
		boolean result = false;

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from emaillist where email = ?");) {
			// binding
			pstmt.setString(1, email);

			// SQL 실행
			int count = pstmt.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력

			// 결과 처리
			result = count == 1;

		} catch (SQLException e) {
			// null을 받으면 출력
			System.out.println("error:" + e);
		}

		return result;

	}

	public List<EmaillistVo> findAll() {
		List<EmaillistVo> result = new ArrayList<>();

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("select no, first_name, last_name, email from emaillist order by no desc");
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);

				EmaillistVo vo = new EmaillistVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String usrID = "webdb";
			String usrPW = "webdb";

			String url = "jdbc:mariadb://192.168.64.5:3306/webdb?charset=utf8"; // url의 맨 앞은 프로토콜 + DBschema(employees)
																				// + options
			conn = DriverManager.getConnection(url, usrID, usrPW); // SQLException 처리 필요하지만 바깥의 다른 것들이 처리하므로 throws~

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}

}
