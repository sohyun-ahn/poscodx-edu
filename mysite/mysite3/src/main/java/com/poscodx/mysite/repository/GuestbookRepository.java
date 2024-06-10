package com.poscodx.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	private SqlSession sqlSession;
	
	public GuestbookRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(GuestbookVo vo) {
		return sqlSession.insert("guestbook.insert", vo);
//		boolean result = false;
//		try (Connection conn = getConnection();
//				PreparedStatement pstmt = conn.prepareStatement("insert into guestbook values(null, ?, ?, ?, ?)");) {
//			
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getPassword());
//			pstmt.setString(3, vo.getContent());
//			pstmt.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//
//			int count = pstmt.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력
//
//			result = count == 1;
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//
//		return result;
	}

	public int deleteByNoAndPassword(Long no, String password) {
		return sqlSession.delete("guestbook.deleteByNoAndPassword", Map.of("no", no, "password", password));
//		int result = 0;
//
//		try (Connection conn = getConnection();
//				PreparedStatement pstmt = conn
//						.prepareStatement("delete from guestbook where no = ? and password = ?");) {
//			pstmt.setLong(1, no);
//			pstmt.setString(2, password);
//
//			result = pstmt.executeUpdate(); // insert가 잘 수행시 카운트된 숫자 출력
//
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//
//		return result;

	}

	public List<GuestbookVo> findAll() {
		// myBatis sqlSession 사용한 것
		return sqlSession.selectList("guestbook.findAll");
		
//		List<GuestbookVo> result = new ArrayList<>();
//
//		try (Connection conn = getConnection();
//				PreparedStatement pstmt = conn
//						.prepareStatement("select no, name, reg_date, content from guestbook order by reg_date desc");
//				ResultSet rs = pstmt.executeQuery();
//		) {
//			while (rs.next()) {
//				Long no = rs.getLong(1);
//				String name = rs.getString(2);
//				String regDate = rs.getString(3);
//				String content = rs.getString(4);
//
//				GuestbookVo vo = new GuestbookVo();
//				vo.setNo(no);
//				vo.setName(name);
//				vo.setRegDate(regDate);
//				vo.setContent(content);
//
//				result.add(vo);
//			}
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		return result;
	}
}
