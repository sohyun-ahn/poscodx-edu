package com.poscodx.container.videosystem;

import java.util.List;

public class BlankDisc implements DigitalVideoDisc {
	private String title;
	private String studio;
	private List<String> actors;

	public BlankDisc() {
	}
	
	public BlankDisc(String title, String studio) {
		// bean생성을 생성자로 하겠다는 의미
		this.title = title;
		this.studio = studio;
	}
	
	public BlankDisc(String title, String studio, List<String> actors) {
		// bean생성을 생성자로 하겠다는 의미
		this.title = title;
		this.studio = studio;
		this.actors = actors;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}
	
	@Override
	public String play() {
		return "Playing Movie " + studio + "'s " + title;
	}

}
