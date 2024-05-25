package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class OrderDao {
	
	public int insert(OrderVo vo) {
		int result = 0;
		
		try (
			Connection conn = ConnectDB.getConnection();
			PreparedStatement pstmt1 = conn.prepareStatement(
					"insert into orders(user_name, status, price, address, user_no) values(?, ?, ?, ?, ?)"
				); 
			PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
		){
			pstmt1.setString(1,  vo.getNumber());
			pstmt1.setString(2,  vo.getStatus());
			pstmt1.setInt(3,  vo.getPayment());
			pstmt1.setString(4,  vo.getShipping());
			pstmt1.setLong(5, vo.getUserNo());
			result = pstmt1.executeUpdate();
			
			ResultSet rs = pstmt2.executeQuery(); 
			vo.setNo(rs.next() ? rs.getLong(1) : null);
			rs.close();
		
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}

	public Integer deleteByNo(Long no) {
		int result = 0;
		
		try (
			Connection conn = ConnectDB.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement("delete from orders where no = ?"); 
		) {
			pstmt.setLong(1,  no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
		
	}
	
	public List<OrderVo> findAll() {
		List<OrderVo> result = new ArrayList<>();
		
		try (
			Connection conn = ConnectDB.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement("select a.no, a.user_name, a.status, a.price, a.address, b.no from orders a, user b where a.no = b.no order by a.no desc"); 
			ResultSet rs = pstmt.executeQuery(); 
		) {
			while(rs.next()) {
				Long no = rs.getLong(1);
				String number = rs.getString(2);
				String status = rs.getString(3);
				int price = rs.getInt(4);
				String address = rs.getString(5);
				Long userNo = rs.getLong(6);
				
				OrderVo vo = new OrderVo();
				vo.setNo(no);
				vo.setNumber(number);
				vo.setStatus(status);
				vo.setPayment(price);
				vo.setShipping(address);
				vo.setUserNo(userNo);
				result.add(vo);
				
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}

	public OrderVo findByNoAndUserNo(Long orderNo, Long userNo) {
		OrderVo vo = null;
		
		try (
			Connection conn = ConnectDB.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement("select a.user_name, a.status, a.price, a.address from orders a, user b where a.user_no = b.no and a.no = ? and b.no = ? order by a.no desc"); 
		) {
			pstmt.setLong(1, orderNo);
			pstmt.setLong(2, userNo);
			ResultSet rs = pstmt.executeQuery(); 

			if(rs.next()) {
				String orderNumber = rs.getString(1);
				String status = rs.getString(2);
				int paymentPrice = rs.getInt(3);
				String shippingAddress = rs.getString(4);

				vo = new OrderVo();
				vo.setNo(orderNo);
				vo.setNumber(orderNumber);
				vo.setStatus(status);
				vo.setPayment(paymentPrice);
				vo.setShipping(shippingAddress);
				vo.setUserNo(userNo);	
				
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return vo;
		
	}

	public int insertBook(OrderBookVo vo) {
		int result = 0;
		
		try (
			Connection conn = ConnectDB.getConnection();
			PreparedStatement pstmt1 = conn.prepareStatement(
					"insert into orders_book(title, quantity, price, orders_no, book_no) values(?, ?, ?, ? ,?)"
				); 
			PreparedStatement pstmt2 = conn.prepareStatement("select title from book where no = ?");
		){
			// book title 가져오기
			pstmt2.setLong(1, vo.getBookNo());
			ResultSet rs = pstmt2.executeQuery();
			vo.setBookTitle(rs.next() ? rs.getString(1) : null);
			
			pstmt1.setString(1,  vo.getBookTitle());
			pstmt1.setInt(2,  vo.getQuantity());
			pstmt1.setInt(3,  vo.getPrice());
			pstmt1.setLong(4, vo.getOrderNo());
			pstmt1.setLong(5, vo.getBookNo());
			result = pstmt1.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}

	public List<OrderBookVo> findBooksByNoAndUserNo(Long orderNo, Long userNo) {
		List<OrderBookVo> result = new ArrayList<>();
		
		try (
				Connection conn = ConnectDB.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("select a.title, a.quantity, a.price, c.no from orders_book a, orders b, book c where a.orders_no = b.no and a.book_no = c.no and b.no = ? and b.user_no = ? order by b.no desc"); 
			) {
				pstmt.setLong(1, orderNo);
				pstmt.setLong(2, userNo);
				ResultSet rs = pstmt.executeQuery(); 
				
				while(rs.next()) {
					String title = rs.getString(1);
					int quantity = rs.getInt(2);
					int price = rs.getInt(3);
					Long bookNo = rs.getLong(4);
					
					OrderBookVo vo = new OrderBookVo();
					vo.setBookTitle(title);
					vo.setQuantity(quantity);
					vo.setPrice(price);
					vo.setOrderNo(orderNo);
					vo.setBookNo(bookNo);
					result.add(vo);
					
				}
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		
		return result;
	}

	public Integer deleteBooksByNo(Long no) {
		int result = 0;
		
		try (
			Connection conn = ConnectDB.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement("delete from orders_book where orders_no = ?"); 
		) {
			pstmt.setLong(1,  no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}
}
