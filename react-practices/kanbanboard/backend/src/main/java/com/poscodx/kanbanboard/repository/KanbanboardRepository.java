package com.poscodx.kanbanboard.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.kanbanboard.vo.CardVo;
import com.poscodx.kanbanboard.vo.TaskVo;

@Repository
public class KanbanboardRepository {
	
	@Autowired
	private SqlSession sqlSession;

	// Card [R]
	public List<CardVo> findAll() {
		return sqlSession.selectList("kanbanboard.findAllCards");
	}
	
	// Task [C]
	public int insert(TaskVo vo) {
		return sqlSession.insert("kanbanboard.insert", vo);
	}
	
	// Task [R]
	public List<TaskVo> findAll(Long cardNo){
		return sqlSession.selectList("kanbanboard.findAllTasks", cardNo);
	}

	// Task [U]
	public int update(TaskVo vo) {
		return sqlSession.update("kanbanboard.updateByNo", vo);
	}
	
	// Task [D]
	public int delete(Long no, Long cardNo) {
		return sqlSession.delete("kanbanboard.deleteByNo", Map.of("no", no, "cardNo", cardNo));
	}
	
	

	

}
