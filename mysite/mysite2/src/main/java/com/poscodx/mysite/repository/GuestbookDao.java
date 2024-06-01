package com.poscodx.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscodx.mysite.vo.GuestbookVo;

public class GuestbookDao {
	
	public boolean insert(GuestbookVo vo) {
		boolean result = false;
		
		try(
			Connection conn = DBConnection.get();
			PreparedStatement pstmt = conn.prepareStatement("insert into guestbook values(null, ?, ?, ?, ?)");
		) {
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
		}
		
		return result;
	}
	

	public boolean deleteByNoAndPassword(Long no, String password) {
		boolean result = false;
		
		try (
			Connection conn = DBConnection.get();
			PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where no = ? and password = ?");
		){
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
	
	public List<GuestbookVo> findAll() {
 		List<GuestbookVo> result = new ArrayList<>();
	
		try (
			Connection conn = DBConnection.get();
			PreparedStatement pstmt = conn.prepareStatement("select no, name, reg_date, content from guestbook order by reg_date desc");
			ResultSet rs = pstmt.executeQuery();
		){
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
		} 

		return result;
	}
	
}
