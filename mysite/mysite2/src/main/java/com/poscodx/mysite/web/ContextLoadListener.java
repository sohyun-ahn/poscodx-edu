package com.poscodx.mysite.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce)  { 
		// 우리의 관심대상
		// Listener는 Context Parameter(전역파라미터)만 읽을 수 있음
		ServletContext sc = sce.getServletContext();
		String contextConfigLocation = sc.getInitParameter("contextConfigLocation");
		
		System.out.println("Application[Mysites2] starts..." + contextConfigLocation);
		
	}

    public void contextDestroyed(ServletContextEvent sce)  { 
         // 톰캣이 내려갈때 실행되는 곳
    }
	
}
