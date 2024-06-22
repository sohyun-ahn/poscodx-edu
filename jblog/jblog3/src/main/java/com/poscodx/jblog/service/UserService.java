package com.poscodx.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogService blogService;

	@Transactional
	public void join(UserVo vo) {
		// insert user ->
		int count = userRepository.insert(vo);

		if (count == 0) {
			return;
		}

		String defaultTitle = vo.getId() + "'s Blog :)︎";
		String defaultLogo = "/assets/upload-images/buangmangom.png";

		// insert blog -> insert category => blogService
		BlogVo blogVo = new BlogVo();
		blogVo.setId(vo.getId());
		blogVo.setTitle(defaultTitle);
		blogVo.setLogo(defaultLogo);

		blogService.createBlog(blogVo);
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}

	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}

}
