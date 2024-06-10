package com.poscodx.aoptest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// Advisor 생성
// aop 설정 빈이라는 것을 알게됨
@Component
@Aspect
public class MyAspect {
	
	// ()안에 pointcut 지정
	// 접근 지시자 적어주고 returntype 적어준 것 (접근지시자 생략가능)
	@Before("execution(public com.poscodx.aoptest.vo.ProductVo com.poscodx.aoptest.service.ProductService.find(String))")
	public void adviceBefore() {
		System.out.println("--- Before Advice ---");
	}
	
	@After("execution(com.poscodx.aoptest.vo.ProductVo com.poscodx.aoptest.service.ProductService.find(String))")
	public void adviceAfter() {
		System.out.println("--- After Advice ---");
	}
	
	// * : 모든 return값
	// *..*. : package 이름을 줄임
	// overloading이 되어 있는 경우, find(..)로 처리하면 편리
	@AfterReturning("execution(* com.poscodx.aoptest.service.ProductService.find(String))")
	public void adviceAfterReturning() {
		System.out.println("--- AfterReturning Advice ---");
	}
	
	// * : 모든 return값
	// *..*. : package 이름을 줄임
	// *..*.*.*(..) : 이렇게 하면 global exception handler
	@AfterThrowing(value="execution(* *..*.*.*(..))", throwing="ex")
	public void adviceAfterThrowing(Throwable ex) {
		System.out.println("--- AfterThrowing Advice " + ex + "---");
	}
	
	@Around("execution(* *..*.ProductService.*(..))")
	public Object adviceAround(ProceedingJoinPoint pjp) throws Throwable {
		/* Before */
		System.out.println("--- Around(Before) ---");
		
		/* Point Cut Method 실행 */
		// Object[] params = {"Camera"};
		// Object result = pjp.proceed(params); // 우리가 pointcut한 파라미터 name=Camera
		
		Object result = pjp.proceed(); // 이걸 안하면 메소드가 실행이 안됨. return을 지정하지 않으면 null 처리됨, name=TV 
		
		/* After */
		System.out.println("--- Around(After) ---");
		
		return result;
	}
}
