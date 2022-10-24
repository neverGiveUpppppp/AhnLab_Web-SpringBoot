package com.example.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String form() {
		return "/member/form";
	}
	
	
	
	
	/**
	 * 회원가입
	 * @param form
	 * @return
	 */
	@PostMapping("/join")
	@ResponseBody
	public HttpEntity<Boolean> join(@Validated @RequestBody MemberSaveForm form) { // 멤버조인폼에서 json으로 던지기 때문에 @리퀘스트바디 추가 
		// 계정 중복체크
		boolean isUseAccount = memberService.
			selectMemberAccountCount(form.getAccount()) > 0;
		Assert.state(!isUseAccount, "이미 사용중인 계정 입니다.");
		// 회원가입 정보 DB에 등록
		memberService.insertMember(form);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	
	
	/**
	 * 가입완료 화면
	 * @return
	 */
	@GetMapping("/join-complete")
	public String joinComplete() {
		return "/member/join-complete";
	}
	
}
