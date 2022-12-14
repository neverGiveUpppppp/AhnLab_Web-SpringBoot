package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//			http.authorizeRequests()
//			// 해당 url 패턴은 로그인 권한 없어도 접근 되도록
//			.antMatchers("/public/**", "/member/form", "/member/join**")
//			.permitAll()
//			// 나머지 요청은 로그인을 해야 접근 되도록
//			.anyRequest().hasRole("USER").and()
//			// .csrf().disable() : ?
//			.formLogin()
//			.permitAll();
//			
//		return http.build();
//	}
//	
//	/**
//	 * 비밀번호 인코더 등록
//	 * 등록안하면 There is no PasswordEncoder mapped for the id "null"에러가 발생함
//	 * @return
//	 */
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
	
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			// 해당 url 패턴은 로그인 권한없어도 접근되게
			.antMatchers("/public/**", "/member/form", "/member/join**")
			.permitAll()
			// 나머지 요청은 로그인을 해야 접근되게
			.anyRequest().hasRole("USER").and()
			//.csrf().disable()
			.formLogin()
			.defaultSuccessUrl("/home")
			.permitAll();
		return http.build();
	}
	
	/**
	 * 비밀번호 인코더 등록
	 * 등록안하면 There is no PasswordEncoder mapped for the id "null" 에러가 발생
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
