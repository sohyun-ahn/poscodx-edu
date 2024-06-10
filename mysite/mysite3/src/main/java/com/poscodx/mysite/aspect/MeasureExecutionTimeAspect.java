package com.poscodx.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MeasureExecutionTimeAspect {
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..))") // pointcut 지정. public생략가능, *..* :com.poscodx.mysite // 모든 레포지토리로 다 걸어둠.. 서비스도 추가
	public Object adviceAround(ProceedingJoinPoint pjp) throws Throwable {
		// before
		StopWatch sw = new StopWatch();
		sw.start();

		// 코드들이 삽일될 위치
		Object result = pjp.proceed();
		
		// after
		sw.stop();
		
		long totalTime = sw.getTotalTimeMillis();
		String className = pjp.getTarget().getClass().getName(); // 실행되는 bean의 클래스네임
		String methodName = pjp.getSignature().getName(); // method이름
		String taskName = className + "." + methodName;
		
		System.out.println("[Execution Time][" + taskName + "]" + totalTime + "millis");
		
		return result;

	}
}
