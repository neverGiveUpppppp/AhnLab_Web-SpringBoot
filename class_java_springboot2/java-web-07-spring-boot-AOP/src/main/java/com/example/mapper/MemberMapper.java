package com.example.mapper;

import com.example.controller.form.MemberSaveForm;
import com.example.domain.Member;

public interface MemberMapper {

	int selectMemberAccountCount(String account);
	
//	void insertMember(MemberSaveForm form);
	void insertMember(Member member); 
//	member,memberSaveForm
//	이제 db로 인아웃을 할 때는 ..강의 25:00 10.29 3시36분

	Member selectMemberAccount(String username);
	
	
}
