package com.choa.aspect;

import org.springframework.stereotype.Component;

@Component //이 메서드를 쓰면 객체를 만들어 줌
//핵심로직
public class Transport {

	// 핵심 로직 Bus 를 타는 것
	public void bus() {
		System.out.println("자리 확보");
		System.out.println("옆사람 핸드폰 보기");
		System.out.println("BBBBBBBBBBBBBBBBBBBBBBB");
	}
	
	public void subway(){
		System.out.println("두리번 둘번");
		System.out.println("리니지 M");
		System.out.println("SSSSSSSSSSSSSSSSSSSSS");
	}

}
