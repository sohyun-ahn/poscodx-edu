package com.poscodx.guestbook.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.poscodx.guestbook.vo.GuestbookVo;

@Repository
public class GuestbookRepositoryWithJdbcTemplate {
	private JdbcTemplate jdbcTemplate;

	public GuestbookRepositoryWithJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<GuestbookVo> findAll() {
		return jdbcTemplate.query("select no, name, reg_date, content from guestbook order by reg_date desc",
				new RowMapper<GuestbookVo>() {

					@Override
					public GuestbookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
						GuestbookVo vo = new GuestbookVo();
						vo.setNo(rs.getLong(1));
						vo.setName(rs.getString(2));
						vo.setRegDate(rs.getString(3));
						vo.setContent(rs.getString(4));
						return vo;
					}
				});
	}

	public int insert(GuestbookVo vo) {
		return jdbcTemplate.update("insert into guestbook values(null, ?, ?, ?, ?)", new Object[] { vo.getName(),
				vo.getPassword(), vo.getContent(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) });

	}

	public int deleteByNoAndPassword(Long no, String password) {
		return jdbcTemplate.update("delete from guestbook where no = ? and password = ?",
				new Object[] { no, password });
		
	}

}
