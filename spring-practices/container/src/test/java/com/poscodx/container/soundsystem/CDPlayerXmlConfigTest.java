package com.poscodx.container.soundsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // jUnit통합환경용, spring에서 test할때 붙이는 annotation
@ContextConfiguration(locations= {"classpath:com/poscodx/container/config/soundsystem/applicationContext.xml"}) // spring test가 컨테이너를 하나 만들고 cdplay랑 highschool 을 만들고, testrunner가 cdplayxmlconfigtest 빈으로 만들어 테스트를 진행한다.
public class CDPlayerXmlConfigTest { 
	
	@Autowired
	private CDPlayer cdPlayer;

	@Test
	public void testCDPlayNotNull() {
		assertNotNull(cdPlayer);
	}
	
	@Test
	public void testPlay() {
		assertEquals("Playing 붕붕 by 김하온", cdPlayer.play());
	}
}
