package com.example.mapper;

import com.example.controller.form.MemberSaveForm;
import com.example.domain.Member;

public interface MemberMapper {

	int selectMemberAccountCount(String account);
	
	void insertMember(MemberSaveForm form);

	Member selectMemberAccount(String account); // account? username?
	
	
}
