package com.poscodx.jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscodx.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}
	
	public UserVo findByIdAndPassword(String id, String password) {
		return sqlSession.selectOne("user.findByIdAndPassword",
				Map.of("id", id, "password", password));
	}
	
	public <R> R findById(String id, Class<R> resultType) {
		FindByIdResultHandler<R> findByIdResultHandler = new FindByIdResultHandler<R>(resultType);
		
		sqlSession.select("user.findById", id, findByIdResultHandler);
		
		return findByIdResultHandler.result;
	}
	
	private class FindByIdResultHandler<R> implements ResultHandler<Map<String, Object>> {
		private R result;
		private Class<R> resultType;
		
		FindByIdResultHandler(Class<R> resultType) {
			this.resultType = resultType;
		}
		
		@Override
		public void handleResult(ResultContext<? extends Map<String, Object>> resultContext) {
			Map<String, Object> resultMap = resultContext.getResultObject();
			result =  new ObjectMapper().convertValue(resultMap, resultType);
			// Map<String, Object> resultMap를 Class<R> resultType으로 변경해야하고 이 결과를 R result로 반환하기
		}
	}
}
