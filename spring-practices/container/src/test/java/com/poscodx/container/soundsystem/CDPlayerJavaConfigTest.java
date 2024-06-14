package com.poscodx.container.soundsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.poscodx.container.config.soundsystem.CDPlayerConfig;

@ExtendWith(SpringExtension.class) // jUnit통합환경용, spring에서 test할때 붙이는 annotation
@ContextConfiguration(classes= {CDPlayerConfig.class}) // 오타가 있으면 import 및 실행자체가 안되니깐 이런 부분이 장점
public class CDPlayerJavaConfigTest { 
	
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
