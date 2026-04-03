package com.green;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration : 이 클래스는 스프링부트의 설정파일임을 나타냄
// WebMvcConfigurer 인터페이스를 상속받아 스프링 MVC의 기능을 확장한다.
@Configuration 
public class WebConfig implements WebMvcConfigurer {
	
	// addResourceHandlers : 정적 리소스(이미지, css, js 등)를 관리하는 메서드
	// 외부의 물리적인 경로를 웹에서 사용하는 URL 주소로 매핑하는 설정을 담당한다.
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// file:///c:/upload/ => 실제로 파일이 저장되는 물리적인 경로이다.
		registry.addResourceHandler("/img/**")
				.addResourceLocations("file:///C:/Spring_Boot/com.green_MyBatis/frontend/public/img/");
	}
}
