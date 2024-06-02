package com.poscodx.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscodx.mysite.vo.BoardVo;

public class BoardDao {

	public Long getLength() {
		// ListAction
		Long result = null;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn.prepareStatement("select count(*) from board");
				ResultSet rs = pstmt.executeQuery();) {
			result = rs.next() ? rs.getLong(1) : null;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}
	
	public Long getLengthByKWD(String kwd) {
		// ListAction + Search By Keyword
		Long result = null;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn.prepareStatement("select count(*) from board where title like ? or content like ?");
				) {
			String keyword = kwd != null ? "%" + kwd + "%" : null;
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			
			ResultSet rs = pstmt.executeQuery();
			result = rs.next() ? rs.getLong(1) : null;
			
			rs.close();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}

	public List<BoardVo> findPerPageByItemSize(Long pageNo, Long itemSize) {
		// ListAction
		List<BoardVo> result = new ArrayList<>();
		
		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn.prepareStatement(
						"select a.no, a.title, a.content, a.hit, a.reg_date, a.g_no, a.o_no, a.depth, a.user_no, b.name from board a, user b where a.user_no = b.no order by g_no DESC, o_no ASC limit ? , ?");) {
			pstmt.setLong(1, (pageNo - 1) * itemSize);
			pstmt.setLong(2, itemSize);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long hit = rs.getLong(4);
				String regDate = rs.getString(5);
				Long groupNo = rs.getLong(6);
				Long orderNo = rs.getLong(7);
				Long depth = rs.getLong(8);
				Long userNo = rs.getLong(9);
				String userName = rs.getString(10);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				
				result.add(vo);
			}
			rs.close();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}
	
	public List<BoardVo> findPerPageByItemSizeByKWD(Long pageNo, String kwd, Long itemSize) {
		// ListAction + Search By Keyword
		List<BoardVo> result = new ArrayList<>();

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn.prepareStatement(
						"select a.no, a.title, a.content, a.hit, a.reg_date, a.g_no, a.o_no, a.depth, a.user_no, b.name from board a, user b where a.user_no = b.no and ( title like ? or content like ?) order by g_no DESC, o_no ASC limit ? , ?");) {
			String keyword = kwd != null ? "%" + kwd + "%" : null;

			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setLong(3, (pageNo - 1) * itemSize);
			pstmt.setLong(4, itemSize);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long hit = rs.getLong(4);
				String regDate = rs.getString(5);
				Long groupNo = rs.getLong(6);
				Long orderNo = rs.getLong(7);
				Long depth = rs.getLong(8);
				Long userNo = rs.getLong(9);
				String userName = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);

				result.add(vo);
			}
			rs.close();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}

	public int insert(BoardVo vo) {
		// WriteAction
		// 새글
		int result = 0;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn.prepareStatement("select Max(g_no) from board");
				PreparedStatement pstmt1 = conn
						.prepareStatement("insert into board values(null, ?, ?, ?, ?, ?, ?, ?, ?)");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
				ResultSet rs = pstmt.executeQuery();) {
			Long groupNo = rs.next() ? rs.getLong(1) : 1;

			// binding
			pstmt1.setString(1, vo.getTitle());
			pstmt1.setString(2, vo.getContent());
			pstmt1.setLong(3, vo.getHit());
			pstmt1.setString(4, vo.getRegDate());
			pstmt1.setLong(5, groupNo + 1);
			pstmt1.setLong(6, vo.getOrderNo());
			pstmt1.setLong(7, vo.getDepth());
			pstmt1.setLong(8, vo.getUserNo());

			vo.setGroupNo(groupNo);

			result = pstmt1.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력

			// select No
			ResultSet rs2 = pstmt2.executeQuery();
			vo.setNo(rs2.next() ? rs2.getLong(1) : null);

			rs2.close();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;

	}

	public int insertReply(BoardVo vo) {
		// WriteAction
		// 답글
		int result = 0;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt1 = conn
						.prepareStatement("insert into board values(null, ?, ?, ?, ?, ?, ?, ?, ?)");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");

		) {
			// binding
			pstmt1.setString(1, vo.getTitle());
			pstmt1.setString(2, vo.getContent());
			pstmt1.setLong(3, vo.getHit());
			pstmt1.setString(4, vo.getRegDate());
			pstmt1.setLong(5, vo.getGroupNo());
			pstmt1.setLong(6, vo.getOrderNo());
			pstmt1.setLong(7, vo.getDepth());
			pstmt1.setLong(8, vo.getUserNo());

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

	public int updateByGroupNo(BoardVo boardVo) {
		// WriteAction
		// 업데이트
		int result = 0;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn
						.prepareStatement("update board set o_no = o_no + 1 where g_no = ? and o_no >= ?");) {
			pstmt.setLong(1, boardVo.getGroupNo());
			pstmt.setLong(2, boardVo.getOrderNo());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;

	}

	public int updateHit(BoardVo boardVo) {
		// ViewAction
		int result = 0;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn.prepareStatement("update board set hit = ? where no = ?");) {
			pstmt.setLong(1, boardVo.getHit() + 1);
			pstmt.setLong(2, boardVo.getNo());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;

	}

	public int update(BoardVo boardVo) {
		// ModifyAction
		int result = 0;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn
						.prepareStatement("update board set title = ?, content = ?, reg_date = ? where no = ?");) {
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setString(3, boardVo.getRegDate());
			pstmt.setLong(4, boardVo.getNo());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;

	}

	public BoardVo findByNo(Long no) {
		// ViewAction, ModifyFormAction
		BoardVo result = null;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn.prepareStatement(
						"select title, content, user_no, g_no, o_no, depth, hit from board a, user b where a.user_no = b.no and a.no = ?");) {
			pstmt.setLong(1, no);

			ResultSet rs = pstmt.executeQuery();
			result = new BoardVo();
			if (rs.next()) {
				String title = rs.getString(1);
				String content = rs.getString(2);
				Long userNo = rs.getLong(3);
				Long groupNo = rs.getLong(4);
				Long orderNo = rs.getLong(5);
				Long depth = rs.getLong(6);
				Long hit = rs.getLong(7);

				result = new BoardVo();
				result.setTitle(title);
				result.setContent(content);
				result.setUserNo(userNo);
				result.setGroupNo(groupNo);
				result.setOrderNo(orderNo);
				result.setDepth(depth);
				result.setNo(no);
				result.setHit(hit);
			}
			rs.close();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}

	public BoardVo findByNoAndUserNo(Long no, Long userNo) {
		// ModifyFormAction
		BoardVo result = null;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn.prepareStatement(
						"select a.title, a.content from board a, user b where a.user_no = b.no and a.no = ? and b.no = ?");) {
			pstmt.setLong(1, no);
			pstmt.setLong(2, userNo);

			ResultSet rs = pstmt.executeQuery();
			result = new BoardVo();
			if (rs.next()) {
				String title = rs.getString(1);
				String content = rs.getString(2);

				result = new BoardVo();
				result.setNo(no);
				result.setUserNo(userNo);
				result.setTitle(title);
				result.setContent(content);
			}
			rs.close();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}

	public int deleteByNo(Long no) {
		// DeleteAction
		int result = 0;

		try (Connection conn = DBConnection.get();
				PreparedStatement pstmt = conn.prepareStatement("delete from board where no = ?");) {
			pstmt.setLong(1, no);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;

	}

}
