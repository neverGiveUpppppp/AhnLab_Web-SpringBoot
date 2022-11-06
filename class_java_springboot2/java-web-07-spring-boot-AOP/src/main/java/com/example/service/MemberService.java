package com.example.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.controller.form.MemberSaveForm;
import com.example.domain.Member;
import com.example.mapper.MemberMapper;
import com.example.security.userdetails.SecurityUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
//public class MemberService {
public class MemberService implements UserDetailsService {
	// 클래스명인 MemberService 빨간줄 명령어 add unimplemented method 실행
	// @Override
	// public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// 생성함
	
//	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final PasswordEncoder passwordEncoder;
	private final MemberMapper memberMapper;

	@Value("${file.root-path}")
	private String rootPath;

	
	public int selectMemberAccountCount(String account) {
		return memberMapper.selectMemberAccountCount(account);
	}
	
//	public void insertMember(MemberSaveForm form) {
//		memberMapper.insertMember(form);
//	}
	
	
	public void insertMember(MemberSaveForm form) {
		MultipartFile profileImage = form.getProfileImage();
		String originalFilename = profileImage.getOriginalFilename();
		String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, 
				originalFilename.length());
		String randomFilename = UUID.randomUUID().toString() + "." + ext; 
		String addPath = "/" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
		// 저장경로
		String savePath = new StringBuilder(rootPath).append(addPath).toString();
		String imagePath = addPath + "/" + randomFilename;
		File saveDir = new File(savePath);
		log.info("originalFilename : {}", originalFilename);
		log.info("ext : {}", ext);
		log.info("randomFilename : {}", randomFilename);
		// 폴더가 없는경우 
		if (!saveDir.isDirectory()) {
			// 폴더 생성
			saveDir.mkdirs();
		}
		// 폴더가 없는경우 
		if (!saveDir.isDirectory()) {
			// 폴더 생성
			saveDir.mkdirs();
		}
		
		
		File out = new File(saveDir, randomFilename);
		try {
			FileCopyUtils.copy(profileImage.getInputStream(), new FileOutputStream(out));
		} catch (IOException e) {
			log.error("fileCopy", e);
			throw new RuntimeException("파일을 저장하는 과정에 오류가 발생하였습니다.");
		}
		
		
		Member member = new Member();

		member.setAccount(form.getAccount());
		String encodePassword = passwordEncoder.encode(form.getPassword());
		log.info("encodePassword : {}", encodePassword);
		member.setPassword(encodePassword);
//		member.setPassword(form.getPassword());
		member.setNickname(form.getNickname());
		member.setProfileImagePath(imagePath);
		member.setProfileImageName(originalFilename);
		
		memberMapper.insertMember(member);
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername : {}", username);
		Member member = memberMapper.selectMemberAccount(username);
		if(member == null) {
			throw new UsernameNotFoundException("회원이 존재하지 않습니다.");
		}
		log.info("member : {}", member);
		return SecurityUserDetails.builder()
				.memberSeq(member.getMemberSeq())
				.nickname(member.getNickname())
				.username(username)
				.password(member.getPassword())
				.build();
	}
	
	
	
	
}
