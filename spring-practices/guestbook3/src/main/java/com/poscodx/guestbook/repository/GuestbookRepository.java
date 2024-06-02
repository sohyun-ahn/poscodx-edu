package com.poscodx.guestbook.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poscodx.guestbook.vo.GuestbookVo;

@Repository
public class GuestbookRepository {

	public boolean insert(GuestbookVo vo) {
		boolean result = false;
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("insert into guestbook values(null, ?, ?, ?, ?)");) {
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

			int count = pstmt.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}

	public boolean deleteByNoAndPassword(Long no, String password) {
		boolean result = false;

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("delete from guestbook where no = ? and password = ?");) {
			pstmt.setLong(1, no);
			pstmt.setString(2, password);

			int count = pstmt.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;

	}

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = new ArrayList<>();

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("select no, name, reg_date, content from guestbook order by reg_date desc");
				ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
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
