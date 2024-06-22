package com.poscodx.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.repository.PostRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private PostRepository postRepository;

	// main/index.jsp (create Blog)
	@Transactional
	public void createBlog(BlogVo vo) {
		// insert blog ->
		int count = blogRepository.insert(vo);

		if (count == 0) {
			return;
		}
		
		String defaultCategoryName = vo.getId() + "'s HOME";
		String defaultCategoryDescrition = "기본 카테고리입니다.";

		// insert category
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setId(vo.getId());
		categoryVo.setName(defaultCategoryName); // default category name : #{id}'s HOME
		categoryVo.setDescription(defaultCategoryDescrition);

		addCategory(categoryVo);

	}
	
	// main.jsp
	public boolean isBlogExists(String id) {
		return 1 == blogRepository.searchById(id);
	}

	// main.jsp
	public BlogVo getBlog(String id) {
		return blogRepository.findById(id);
	}

	// main.jsp
	public List<CategoryVo> getCategoryList(String id) {
		return categoryRepository.findAllById(id);
	}

	// main.jsp
	public List<PostVo> getPostList(int categoryNo) {
		return postRepository.findAllByNo(categoryNo);
	}

	// main.jsp
	public PostVo getPost(int PostNo) {
		return postRepository.findByPostNo(PostNo);
	}

	// admin-basic.jsp
	public void updateBlog(BlogVo vo) {
		blogRepository.update(vo);
	}

	// admin-category.jsp
	public List<Map<String, Object>> getCategoryListWithPostCount(String id) {
		return categoryRepository.findAllByIdWithPostCount(id);
	}

	// admin-category.jsp
	public int addCategory(CategoryVo categoryVo) {
		return categoryRepository.insert(categoryVo);
	}

	// admin-category.jsp
	public int deleteCategory(int no) {
		return categoryRepository.deleteByNo(no);
	}
	
	// admin-write.jsp
	public int addPost(PostVo postVo) {
		return postRepository.insert(postVo);
	}

}
