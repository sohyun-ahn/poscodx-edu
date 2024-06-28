package com.poscodx.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;

	public int insert(BlogVo vo) {
		return sqlSession.insert("blog.insert", vo);
	}
	public int searchById(String id) {
		return sqlSession.selectOne("blog.searchById", id);
	}

	public BlogVo findById(String id) {
		return sqlSession.selectOne("blog.findById", id);
	}

	public int update(BlogVo vo) {
		return sqlSession.update("blog.update", vo);
	}

}
