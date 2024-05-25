package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVo;

public class CategoryDao {
	
	public int insert(CategoryVo vo) {
		int result = 0;
		
		try (
			Connection conn = ConnectDB.getConnection();
			PreparedStatement pstmt1 = conn.prepareStatement("insert into category(name) values(?)"); // SQLException 처리 필요
			PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
		){
			pstmt1.setString(1,  vo.getName());
			result = pstmt1.executeUpdate();
			
			ResultSet rs = pstmt2.executeQuery(); 
			vo.setNo(rs.next() ? rs.getLong(1) : null);
			rs.close();
		
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}
	
	
	public List<CategoryVo> findAll() {
		List<CategoryVo> result = new ArrayList<>();
		
		try (
			Connection conn = ConnectDB.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement("select no, name from category order by no desc"); 
			ResultSet rs = pstmt.executeQuery(); 
		) {
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				CategoryVo vo = new CategoryVo(name);
				vo.setNo(no);
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}

	public Integer deleteByNo(Long no) {
		int result = 0;
		
		try (
			Connection conn = ConnectDB.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement("delete from category where no = ?"); 
		) {
			pstmt.setLong(1,  no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}
}
