package ex01.repository;

import ex01.domain.Guestbook;
import ex01.dto.GuestbookDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestJpqlGuestbookRepository {
    private static Guestbook guesbookMock = new Guestbook("고길동", "1234", "안녕", new Date()); // mock 객체 id받기, static으로 해서 공유

    @Autowired
    private JpqlGuestbookRepository guestbookRepository;

    @Test
    @Order(0)
    @Transactional
    @Rollback(false) // true로 해버리면, 다음 테스트에서 사용 불가이기 때문
    public void testSave() {
        guestbookRepository.save(guesbookMock);
        assertNotNull(guesbookMock.getId());
    }

    @Test
    @Order(1)
    public void testFindAll01() {
        List<Guestbook> list = guestbookRepository.findAll01();
        assertTrue(guestbookRepository.count() == list.size());
    }

    @Test
    @Order(2)
    public void testFindAll02() {
        List<GuestbookDto> list = guestbookRepository.findAll02();
        assertTrue(guestbookRepository.count() == list.size());
    }

    @Test
    @Order(3)
    @Transactional
    @Rollback(false)
    public void testDelete() {
        assertEquals(1, guestbookRepository.delete(guesbookMock.getId(), "1234"));
    }
}