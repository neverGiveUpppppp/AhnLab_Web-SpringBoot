package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.annotation.RequestConfig;
import com.example.security.userdetails.SecurityUserDetails;

@Controller
public class HomeController {

	final Logger logger = LoggerFactory.getLogger(getClass());

//	// Spring Security 적용 전
//	@GetMapping("/home")
//	public void home() {
//		logger.info("this is home");
//	}
	
//	// Spring Security 적용 후
//	@GetMapping(value = { "/", "/home" })
//	@RequestConfig(menu = "HOME")
//	public String home(Model model, Authentication authentication) {
//		logger.info("authentication : {}", authentication);
//		SecurityUserDetails details = (SecurityUserDetails) authentication.getPrincipal();
//		model.addAttribute("details", details);
//		return "/home";
//	}
	
	
	@GetMapping(value = { "/", "/home"})
	@RequestConfig(menu = "HOME")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 익명사용자인경우
		if (authentication instanceof AnonymousAuthenticationToken) {
			logger.info("익명사용자 접근");
		} else {
			SecurityUserDetails details = (SecurityUserDetails) authentication.getPrincipal();
			model.addAttribute("details", details);
		}
		return "home";
	}

	
}
