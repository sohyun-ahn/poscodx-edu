package com.poscodx.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	public int insert(PostVo vo) {
		return sqlSession.insert("post.insert", vo);
	}
	
	public List<PostVo> findAllByNo(int categoryNo) {
		return sqlSession.selectList("post.findAllByNo", categoryNo);
	}
	
	public PostVo findByPostNo(int postNo) {
		return sqlSession.selectOne("post.findByPostNo", postNo);
	}
}
