package com.poscodx.guestbook.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcContext {
	private DataSource dataSource;

	public JdbcContext(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
//  구현 팁
//	public <T> T executeQueryForObject(String sql) {
//		return null;
//	}
//
//	public <T> List<T> executeQueryForObject(String sql, Object[] parameter) {
//		return null;
//	}

	public int update(String sql) {
		return executeUpdateWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makeStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				return pstmt;
			}
		});
	}
	
	public int update(String sql, Object[] parameters) {
		return executeUpdateWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makeStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				for(int i = 0; i < parameters.length; i++) {
					pstmt.setObject(i+1, parameters[i]);
				}
				return pstmt;
			}
		});
	}
	
	private int executeUpdateWithStatementStrategy(StatementStrategy statementStrategy) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DataSourceUtils.getConnection(dataSource);
			pstmt = statementStrategy.makeStatement(conn);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e); // 이렇게 해야 try-catch 안되어있어서 service까지 올라가서 rollback 가능해진다. // sqlsession template에 되어있음. 
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					DataSourceUtils.releaseConnection(conn,dataSource);
				}
			} catch(SQLException ignored) {
			}
		}
		
		return result;		
	}

	// Generic type으로 설정하여 query()를 여러군데서 사용될 수 있게 
	public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
		// <T> List<T> 단점
		// list 길이가 하나일 경우에도 리스트로 리턴되어진다.
		// 쿼리가 파라미터가 있으면 안됨
		return executeQueryWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makeStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				return pstmt;
			}
		}, rowMapper);		
	}

	private <E> List<E> executeQueryWithStatementStrategy(StatementStrategy statementStrategy, RowMapper<E> rowMapper) {
		List<E> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DataSourceUtils.getConnection(dataSource);
			
			pstmt = statementStrategy.makeStatement(conn);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				E e = rowMapper.mapRow(rs, rs.getRow()); // rs, row의 번호 
				result.add(e);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					DataSourceUtils.releaseConnection(conn,dataSource);
				}
			} catch (SQLException ignored) {
			}
		}
		return result;
	}
}
