package ex01.repository;

import ex01.domain.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// 실행시, proxy bean이 만들어진다
public interface JpaGuestbookRepository extends JpaRepository<Guestbook, Integer> {
	List<Guestbook> findAllByOrderByRegDateDesc();

	int deleteByIdAndPassword(Integer id, String password);
}