package com.choa.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Component
@Aspect //어노테이션을 이용해서 이클래스가 Aspect 입니다 라고 알려줌
public class CardCheck {//공통 로직
	@Before("execution(* com.choa.aspect..Trip.*(..))")
	public void reservation(){
		System.out.println("예약 완료");
		System.out.println("======================");
	}
	@Around("execution(* com.choa.aspect..Transport.*(..))")
	//around 쓴 메서드에 매개변수 추가
	public Object check(ProceedingJoinPoint join){ //join 에 버스가 들어온다. 
		System.out.println("카드 인");
		Object obj =  null;
		try {
			obj = join.proceed();
		} catch (Throwable e) {

			e.printStackTrace();
		}
		
		System.out.println("카드 아웃");
		return obj;
	}
}
