package com.poscodx.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poscodx.mysite.vo.UserVo;

public class UserDao {

	public int insert(UserVo vo) {
		int result = 0;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt1 = conn
						.prepareStatement("insert into user values(null, ?, ?, password(?), ?, current_date())");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");) {
			// binding
			pstmt1.setString(1, vo.getName());
			pstmt1.setString(2, vo.getEmail());
			pstmt1.setString(3, vo.getPassword());
			pstmt1.setString(4, vo.getGender());
			pstmt1.setString(5, vo.getJoinDate());

			result = pstmt1.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력

			// select No
			ResultSet rs = pstmt2.executeQuery();
			vo.setNo(rs.next() ? rs.getLong(1) : null);
			rs.close();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}

	public boolean deleteByNoAndPassword(Long no, String password) {
		boolean result = false;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn.prepareStatement("delete from user where no = ? and password = ?");) {
			// 4. binding
			pstmt.setLong(1, no);
			pstmt.setString(2, password);

			// 5. SQL 실행
			int count = pstmt.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력

			// 6. 결과 처리
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;

	}

	public UserVo findByNoAndPassword(String email, String password) {
		UserVo result = null;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn
						.prepareStatement("select no, name from user where email = ? and password=password(?)");) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				result = new UserVo();
				result.setNo(no);
				result.setName(name);
			}
			rs.close();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}

	public UserVo findByNo(Long no) {
		UserVo result = null;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn
						.prepareStatement("select name, email, password, gender from user where no = ?");) {
			pstmt.setLong(1, no);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString(1);
				String email = rs.getString(2);
				String password = rs.getString(3);
				String gender = rs.getString(4);

				result = new UserVo();
				result.setName(name);
				result.setEmail(email);
				result.setPassword(password);
				result.setGender(gender);
			}
			rs.close();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}

	public Integer update(UserVo userVo) {
		int result = 0;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn.prepareStatement("update user set name = ?, gender = ? where no = ?");
				PreparedStatement pstmt2 = conn
						.prepareStatement("update user set name = ?, password = password(?), gender = ? where no = ?");

		) {
			if ("".equals(userVo.getPassword())) {
				pstmt.setString(1, userVo.getName());
				pstmt.setString(2, userVo.getGender());
				pstmt.setLong(3, userVo.getNo());
				result = pstmt.executeUpdate();
			} else {
				pstmt2.setString(1, userVo.getName());
				pstmt2.setString(2, userVo.getPassword());
				pstmt2.setString(3, userVo.getGender());
				pstmt2.setLong(4, userVo.getNo());
				result = pstmt2.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}
}
