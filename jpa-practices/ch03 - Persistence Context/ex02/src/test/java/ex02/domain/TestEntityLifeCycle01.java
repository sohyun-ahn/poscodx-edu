package ex02.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 안붙이면 메소드 길이 순으로 실행이 된다!
public class TestEntityLifeCycle01 {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    @Order(0)
    void beforeAllTest() {

        // Entity 매니저 생성
        EntityManager em = emf.createEntityManager();

        // Transaction 객체 얻어오기
        EntityTransaction tx = em.getTransaction();

        // [트랜잭션 시작]
        tx.begin();

        // Entity 객체 생성, 비영속 상태
        Book book = new Book();
        book.setId("book");
        book.setTitle("Mastering JPA");

        // 영속화(1차 cache)
        em.persist(book);

        // [트랜잭션 종료]
        tx.commit();

        // Entity 매니저 종료
        em.close();

        // EMF는 지금 닫으면 안됨 다른 테스트 메소드들도 실행해야하니깐
    }

    @Test
    @Order(1)
    void testFind() {
        EntityManager em = emf.createEntityManager(); // em을 새로 생성하면, persistence context가 새로 생성

        // JPQL 확인하기1
        Book book1 = em.find(Book.class, "book"); // 여기서 JPQL이 날라간다.
        assertNotNull(book1);

        // JPQL 확인하기2 => X , 얘는 1차 캐시에서 가져옴
        Book book2 = em.find(Book.class, "book"); // 1차 캐시에서 가져온다.
        assertTrue(book1==book2);
    }

    @Test
    @Order(2)
    void testInEntityTransactionBoundary02() {
        EntityManager em = emf.createEntityManager(); // em을 새로 생성하면, persistence context가 새로 생성

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // JPQL 확인하기1
        Book book = em.find(Book.class, "book"); // 여기서 JPQL이 날라간다.
        book.setTitle("Mastering JPA");

        tx.commit();

        em.close();
    }

    @Test
    @Order(3)
    void testInEntityTransactionBoundary03() {
        EntityManager em = emf.createEntityManager(); // em을 새로 생성하면, persistence context가 새로 생성

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // JPQL 확인하기1
        Book book = em.find(Book.class, "book"); // 여기서 JPQL이 날라간다.
        em.remove(book);

        tx.commit();

        em.close();
    }
}