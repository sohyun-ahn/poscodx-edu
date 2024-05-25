package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;

public class CartDao {

	public int insert(CartVo vo) {
		int result = 0;
		
		try (
			Connection conn = ConnectDB.getConnection();
			PreparedStatement pstmt1 = conn.prepareStatement("insert into cart values(?, ?, ?, ?)"); 
			PreparedStatement pstmt2 = conn.prepareStatement("select title from book where no = ?");
		){
			// book title을 넣어주기위해
			pstmt2.setLong(1, vo.getBookNo());
			ResultSet rs = pstmt2.executeQuery();
			vo.setBookTitle(rs.next() ? rs.getString(1) : null);
			
			pstmt1.setString(1, vo.getBookTitle());
			pstmt1.setInt(2,  vo.getQuantity());
			pstmt1.setLong(3,  vo.getUserNo());
			pstmt1.setLong(4,  vo.getBookNo());
			result = pstmt1.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}
	
	public List<CartVo> findByUserNo(Long no) {
		List<CartVo> result = new ArrayList<>();
		
		try (
			Connection conn = ConnectDB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select a.title, a.quantity, b.no, c.no from cart a, user b, book c where a.user_no = b.no and a.book_no = c.no and b.no = ? order by b.no desc");
		) {
			pstmt.setLong(1, no);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String bookTitle = rs.getString(1);
				int quantity = rs.getInt(2);
				Long userNo = rs.getLong(3);
				Long bookNo = rs.getLong(4);
				
				CartVo vo = new CartVo();
				vo.setBookTitle(bookTitle);
				vo.setQuantity(quantity);
				vo.setUserNo(userNo);
				vo.setBookNo(bookNo);
				result.add(vo);
				
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}

	public int deleteByUserNoAndBookNo(Long userNo, Long bookNo) {
		int result = 0;
		
		try(
			Connection conn = ConnectDB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from cart where user_no = ? and book_no = ?");
		){
			pstmt.setLong(1, userNo);
			pstmt.setLong(2, bookNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}
}
