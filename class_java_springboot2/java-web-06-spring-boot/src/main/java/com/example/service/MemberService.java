package com.example.service;

import org.mybatis.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.controller.form.MemberSaveForm;
import com.example.domain.Member;
import com.example.mapper.MemberMapper;
import com.example.security.userdetails.SecurityUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
	// 클래스명인 MemberService 빨간줄 명령어 add unimplemented method 실행
	// @Override
	// public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// 생성함
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final MemberMapper memberMapper;
	
	public int selectMemberAccountCount(String account) {
		return memberMapper.selectMemberAccountCount(account);
	}
	
	public void insertMember(MemberSaveForm form) {
		memberMapper.insertMember(form);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("loadUserByUsername : {}", username);
		Member member = memberMapper.selectMemberAccount(username);
		if(member == null) {
			throw new UsernameNotFoundException("회원이 존재하지 않습니다.");
		}
		logger.info("member : {}", member);
		return SecurityUserDetails.builder()
				.memberSeq(member.getMemberSeq())
				.nickname(member.getNickname())
				.username(username)
				.password(member.getPassword())
				.build();
	}
	
	
	
	
}
