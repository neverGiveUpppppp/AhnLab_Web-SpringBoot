package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.annotation.RequestConfig;
import com.example.controller.form.MemberSaveForm;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	
	
	/**
	 * 정보입력 화면
	 * @return
	 */
	@GetMapping("/form")
	@RequestConfig(menu = "MEMBER") // 멤버 대문자
	public String form() {
		return "member/form";
	}
	
	
	
	
	/**
	 * 회원가입
	 * @param form
	 * @return
	 */
	@PostMapping("/join")
	@ResponseBody
	public HttpEntity<Boolean> join(@Validated MemberSaveForm form) { // 멤버조인폼에서 json으로 던지기 때문에 @리퀘스트바디 추가 // 파일 멀티파트파일 추가 후 다시 삭제 
		// 계정 중복체크
		boolean isUseAccount = memberService.
			selectMemberAccountCount(form.getAccount()) > 0;
		Assert.state(!isUseAccount, "이미 사용중인 계정 입니다.");
		// 회원가입 정보 DB에 등록
		memberService.insertMember(form);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);

//		private final PasswordEncoder passwordEncoder;
//		
//		String password = passwordEncoder.encode(form.getPassword());
//		// 암호화된 비밀번호로 저장
//		form.setPassword(password);
		
	}

	/**
	 * 본인인증 콜백이 오는경우 처리
	 * @param model
	 * @return
	 */
	@GetMapping("/realname/callback")
	@RequestConfig(menu = "MEMBER")
	public HttpEntity<?> realnameCallback(Model model, HttpServletRequest request) {
		// 실제 기능구현에는 요청이온 파라메터 값을 체크해서 성공여부를 해야함
		request.getSession().setAttribute("realnameCheck", true);
		return ResponseEntity.ok().build();
	}

	
	/**
	 * 가입완료 화면
	 * @return
	 */
	@GetMapping("/join-complete")
	@RequestConfig(menu = "MEMBER", realname= true) // 멤버 대문자
	public String joinComplete() {
		return "member/join-complete";
	}
	
	
	/**
	 * 회원 로그아웃
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/logout")
	@RequestConfig(menu = "MEMBER")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	
}
