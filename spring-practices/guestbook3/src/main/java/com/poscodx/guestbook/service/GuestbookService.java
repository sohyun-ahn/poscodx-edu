package com.poscodx.guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.poscodx.guestbook.repository.GuestbookLogRepository;
import com.poscodx.guestbook.repository.GuestbookRepository;
import com.poscodx.guestbook.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private PlatformTransactionManager transactionManager; // 이걸 가지고 경계를 잡아본다

	@Autowired
	private GuestbookRepository guestbookRepository;

	@Autowired
	private GuestbookLogRepository guestbookLogRepository; // 주입받기

	public List<GuestbookVo> getContentList() {
		return guestbookRepository.findAll();
	}

	public void deleteContent(Long no, String password) {
		// TX:BEGIN /////////////
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			guestbookLogRepository.update(no);
			guestbookRepository.deleteByNoAndPassword(no, password); // 실패시 runtimeException 발생

			// TX:END(SUCCESS) /////////
			transactionManager.commit(status);
		} catch (Throwable e) {
			// TX:END(FAIL) ////////////
			transactionManager.rollback(status);
		}
	}

	public void addContent(GuestbookVo vo) {
		// 트랜잭션 매니저로 코드를 숨긴다.
		// 템플릿 콜백패턴
		// 트랜잭션 동기(connection) 초기화
		TransactionSynchronizationManager.initSynchronization(); // static method, 초기화 시켜둠 /

		Connection conn = DataSourceUtils.getConnection(dataSource); // 후에 template callback을 써서 감추고, 트랜잭션 매니저(이 안으로 넣어둘

		try {
			// TX:BEGIN /////////////
			conn.setAutoCommit(false);

			int count = guestbookLogRepository.update();

			if (count == 0) {
				guestbookLogRepository.insert();
			}

			guestbookRepository.insert(vo);

			// TX:END(SUCCESS) /////////
			conn.commit();
		} catch (Throwable e) {
			// TX:END(FAIL) ////////////
			try {
				conn.rollback(); // error를 출력하고 싶으면 여기서 처리하기
			} catch (SQLException ignored) {
			}
		} finally {
			if (conn != null) {
				DataSourceUtils.releaseConnection(conn, dataSource);
			}
		}
	}

}
