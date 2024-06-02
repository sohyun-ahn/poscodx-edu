package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.repository.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long no = Long.parseLong(request.getParameter("no"));
		BoardDao boardDao = new BoardDao();
		BoardVo boardVo = boardDao.findByNo(no);

		// cookie 확인 후 조회수 처리
		Cookie[] cookies = request.getCookies();
		boolean hasHitsCookie = false;

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String hitsByUserNo = ""; // cookie key - 같은 브라우저용
		if (authUser != null) {
			hitsByUserNo = "_by_user" + Long.toString(authUser.getNo()); // cookie key - 로그인 유저별
		}

		String hitsOfNo = "Hits_of_no." + request.getParameter("no") + hitsByUserNo;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(hitsOfNo)) {
					hasHitsCookie = true;
				}
			}
		}
		
		// 쿠키가 없다면 쿠키 생성 후 hit update
		// hitsOfNo : "true" 로 저장
		if (!hasHitsCookie) {
			Cookie hitsCookie = new Cookie(hitsOfNo, "true");
			hitsCookie.setMaxAge(24 * 60 * 60); // 24시간 설정
			hitsCookie.setPath("/"); // 모든 URL 범위에서 전송
			response.addCookie(hitsCookie);
			boardDao.updateHit(boardVo);
		}

		request.setAttribute("boardVo", boardVo);

		request.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(request, response);
	}

}
