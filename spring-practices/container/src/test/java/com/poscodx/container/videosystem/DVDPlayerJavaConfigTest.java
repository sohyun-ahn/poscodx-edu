package com.poscodx.container.videosystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.poscodx.container.config.videosystem.mixing.DVDPlayerConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {DVDPlayerConfig.class})
public class DVDPlayerJavaConfigTest {
	@Autowired
	/* 같은 타입의 bean이 2개 이상 있는 경우
	 * 설정 클래스의 bean생성 method의 @Bean의 name(default값) 속성으로
	 * Quaifier 하기
	 */
	@Qualifier("dvdPlayer01") // 어떤 DVDPlayer를 가져와야하는지 ambiguous 한 것을 구분해주려고
	private DVDPlayer dvdPlayer01;
	//private DigitalVideoDisc dvd;
	
	@Autowired
	/* 같은 타입의 bean이 2개 이상 있는 경우
	 * 설정 클래스의 bean생성 method의 이름으로
	 * Quaifier 하기
	 */
	@Qualifier("dvdPlayer2") // 어떤 DVDPlayer를 가져와야하는지 ambiguous 한 것을 구분해주려고
	private DVDPlayer dvdPlayer02;
	
	@Autowired
	@Qualifier("dvdPlayer03")
	private DVDPlayer dvdPlayer03;
	
	@Test
	public void testDVDPlayer01NotNull() {
		assertNotNull(dvdPlayer01);
	}
	
	@Test
	public void testDVDPlayer02NotNull() {
		assertNotNull(dvdPlayer02);
	}
	
	@Test
	public void testDVDPlayer03NotNull() {
		assertNotNull(dvdPlayer03);
	}
	
	@Test
	public void testPlay() {
		assertEquals("Playing Movie Marvel's Avengers", dvdPlayer03.play()); 
	}
	
//	@Test
//	public void testDVDNotNull() {
//		assertNotNull(dvd);
//	}
}
