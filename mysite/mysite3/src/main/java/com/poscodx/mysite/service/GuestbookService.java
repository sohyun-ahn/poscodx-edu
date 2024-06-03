package com.poscodx.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.GuestbookRepository;
import com.poscodx.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVo> getContentList() {
		return guestbookRepository.findAll();
	}

	public void deleteContent(Long no, String password) {
		guestbookRepository.deleteByNoAndPassword(no, password);
	}

	public void addContent(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}

}
