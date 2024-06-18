package com.poscodx.container.config.videosystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.poscodx.container.videosystem.Avengers;
import com.poscodx.container.videosystem.DVDPlayer;
import com.poscodx.container.videosystem.DigitalVideoDisc;

@Configuration
public class DVDPlayerConfig {
	
	@Bean
	public DigitalVideoDisc avengers() {
		return new Avengers();
	}
	
	// 주입(injection)하기 1
	// Bean 생성 메소드를 직접 호출하는 방법
	// 생성자 주입
	@Bean("dvdPlayer01") // (): bean의 default name(id) 설정
	public DVDPlayer dvdPlayer1() {  // dvdPlayer1 : @Bean()안에 id를 명시하지 않으면, dvdPlayer1을 bean의 id 처럼 사용한다!!
		// DVDPlayer dvdPlayer = new DVDPlayer(); // new DVDPlayer(new Avengers()); 라고 하면 안된다
		// dvdPlayer.setDvd(); // 명시적으로 해야하기 때문에 setter를 사용하든지 생성자를 사용하든지 하는 주입하는 코드가 필요하다.
		// return dvdPlayer;
		
		return new DVDPlayer(avengers()); // 생성자 주입
	}
	
	// 주입(injection)하기 2
	// Parameter로 bean을 전달하는 방법
	// 생성자 주입
	@Bean
	public DVDPlayer dvdPlayer2(DigitalVideoDisc dvd) {
		return new DVDPlayer(dvd); // 생성자 주입
	}
	
	// 주입(injection)하기 3
	// Parameter로 bean을 전달하는 방법
	// setter주입
	@Bean("dvdPlayer03")
	public DVDPlayer dvdPlayer3(DigitalVideoDisc dvd) {
		DVDPlayer dvdPlayer = new DVDPlayer(dvd); 
		dvdPlayer.setDvd(dvd);
		
		return dvdPlayer;
	}
	
}
