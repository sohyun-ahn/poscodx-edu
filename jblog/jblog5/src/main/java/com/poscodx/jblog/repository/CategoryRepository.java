package com.poscodx.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;

	public int insert(CategoryVo vo) {
		return sqlSession.insert("category.insert", vo);
	}
	
	public List<CategoryVo> findAllById(String id) {
		return sqlSession.selectList("category.findAllById", id);
	}
	
	public List<Map<String, Object>> findAllByIdWithPostCount(String id) {
		return sqlSession.selectList("category.findAllByIdWithPostCount", id);
	}
	
	public int deleteByNo(int no) {
		return sqlSession.delete("category.deleteByNo", no);
	}
}
