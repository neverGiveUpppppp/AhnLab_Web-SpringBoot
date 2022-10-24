package com.example.domain;

import javax.validation.GroupSequence;

import lombok.Data;

@Data
public class Member {
	
	private int memberSeq;
	private String account;
	private String password;
	private String nickname;
	private String joinDate;
}
