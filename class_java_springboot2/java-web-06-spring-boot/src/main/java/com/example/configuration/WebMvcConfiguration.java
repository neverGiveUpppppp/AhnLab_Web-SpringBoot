package com.example.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	// 이 인터페이스를 구현하면 메소드가 많음
	
	/**
	 * 다국어 설정 및 메세지 프로퍼티 사용을 위한 빈 등록 
	 * 다국어처리 메세지 소스, 빈으로 등록
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = 
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:config/messages/message"); // 현재 컴파일된 폴더에 위치까지
		// 다국어 파일 경로 set : 보통 다국어지원할려면 이거 사용
		// ex) 파일명 message_en message_jp이런식으로
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	
	/**
	 * Validator 빈 등록
	 * Validator 메세지 설정을 하기 위한 빈 등록
	 */
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	
	
	@Bean
	public MappingJackson2JsonView jsonView() {
//		스프링 mvc Model,contents 다 자동으로 json으로 바꿔줌
		return new MappingJackson2JsonView();
	}

	
	
	
}
