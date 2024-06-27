package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.jblog.security.Auth;
import com.poscodx.jblog.security.AuthUser;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;
import com.poscodx.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets|admin).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(@PathVariable("id") String id, @PathVariable("pathNo1") Optional<Integer> pathNo1,
			@PathVariable("pathNo2") Optional<Integer> pathNo2, Model model) {
		isIdExists(id);

		// id와 일치하는 user가 있는지 없는지 확인하기
		if (!blogService.isBlogExists(id)) {
			return "redirect:/";
		}

		BlogVo vo = blogService.getBlog(id);

		List<CategoryVo> categoryList = blogService.getCategoryList(id);

		int categoryNo = 0;
		int postNo = 0;

		if (pathNo2.isPresent()) {
			// categoryNo와 postNo 둘 다 존재
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			// categoryNo만 존재
			categoryNo = pathNo1.get();
		} else {
			// categoryNo == 0일때 기본 categoryNo 세팅
			categoryNo = categoryList.get(0).getNo();
		}

		List<PostVo> postList = blogService.getPostList(categoryNo);

		if (postNo == 0 && postList.size() != 0) {
			// postNo == 0일때 기본 postNo 세팅
			postNo = postList.get(0).getNo();
		}

		PostVo postingVo = blogService.getPost(postNo);

		model.addAttribute("blogVo", vo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		model.addAttribute("postingVo", postingVo);

		return "blog/main";
	}

	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@AuthUser UserVo authUser, @PathVariable("id") String id, Model model) {
		isIdExists(id);
		checkRedirectToMain(authUser, id);

		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("blogVo", vo);
		return "blog/admin-basic";
	}

	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String adminBasic(@AuthUser UserVo authUser, @PathVariable("id") String id, BlogVo vo,
			@RequestParam String title, @RequestParam("logo-file") MultipartFile file) {
		String logo = fileUploadService.restore(file);

		vo.setTitle(title.replaceAll("'", "&#39;")); // ' 정규식 처리
		vo.setLogo(logo);

		blogService.updateBlog(vo);

		return "redirect:/" + id + "/admin/basic";
	}

	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@AuthUser UserVo authUser, @PathVariable("id") String id, Model model) {
		isIdExists(id);
		checkRedirectToMain(authUser, id);

		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("blogVo", vo);

		List<Map<String, Object>> categoryList = blogService.getCategoryListWithPostCount(id);

		model.addAttribute("categoryList", categoryList);

		return "blog/admin-category";
	}

	@Auth
	@RequestMapping("/admin/category/delete")
	public String adminCategory(@AuthUser UserVo authUser, @PathVariable("id") String id, @RequestParam("no") int no) {
		isIdExists(id);
		checkRedirectToMain(authUser, id);

		blogService.deleteCategory(no);

		return "redirect:/" + id + "/admin/category";

	}

	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String adminCategory(@AuthUser UserVo authUser, @PathVariable("id") String id, CategoryVo vo,
			@RequestParam String name, @RequestParam String desc) {

		vo.setName(name);
		vo.setDescription(desc);
		vo.setId(id);

		blogService.addCategory(vo);

		return "redirect:/" + id + "/admin/category";
	}

	@Auth
	@RequestMapping("/admin/write")
	public String adminWrite(@AuthUser UserVo authUser, @PathVariable("id") String id, Model model) {
		isIdExists(id);
		checkRedirectToMain(authUser, id);

		BlogVo vo = blogService.getBlog(id);
		List<CategoryVo> categoryList = blogService.getCategoryList(id);

		model.addAttribute("blogVo", vo);
		model.addAttribute("categoryList", categoryList);

		return "blog/admin-write";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String adminWrite(@AuthUser UserVo authUser, @PathVariable("id") String id, @RequestParam String title,
			@RequestParam String category, @RequestParam String content) {

		PostVo postVo = new PostVo();
		postVo.setCategoryNo(Integer.parseInt(category));
		postVo.setTitle(title);
		postVo.setContent(content);

		blogService.addPost(postVo);

		return "redirect:/" + id + "/" + category + "/" + postVo.getNo();
	}

	public String isIdExists(String id) {
		if ("".equals(id) || id == null) {
			return "redirect:/";
		}
		return null;
	}

	public String checkRedirectToMain(@AuthUser UserVo authUser, String id) {
		if (!id.equals(authUser.getId())) {
			return "redirect:/" + id;
		}
		return null;
	}

}
