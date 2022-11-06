package com.example.controller.form;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.validation.ValidationSteps;

import lombok.Data;

@Data
@GroupSequence({
	// 유효성 검사 실행순서 지정한 것 : step1-5
	// step5를 맨 위로 올리면 step5부터 시작함
	BoardSaveForm.class,		  // 이 클래스로 할꺼다 설정
	ValidationSteps.Step1.class,  // 검증하고 싶은 첫번째
	ValidationSteps.Step2.class,  // 검증하고 싶은 두번째
	ValidationSteps.Step3.class,  //  ...
	ValidationSteps.Step4.class,
	ValidationSteps.Step5.class
})
public class BoardSaveForm {

	// 메세지를 프로퍼티(외부) 파일로 관리하는 코드
//	private int boardSeq;
//	
//	@NotEmpty(message = "{BoardSaveForm.boardType.notEmpty}")
//	private String boardType;
//	
//	@NotEmpty(message = "{BoardSaveForm.userName.notEmpty}")
//	private String userName;
//	
//	@NotEmpty(message = "{BoardSaveForm.title.notEmpty}")
//	@Length(groups = ValidationSteps.Step4.class, min=5, max=20, message = "{제목는 길이 최소5~최대20까지 가능합니다}")
//	private String title;
//	
//	@NotEmpty(message = "{BoardSaveForm.contents.notEmpty}")
//	private String contents;
	
	
	// Validation을 하나하나 순서데로 적용되기 위함
	// 인풋이 그루핑 될 수 있음. 입력값을 10개 10개
	private int boardSeq;
	
//	@NotEmpty(groups = ValidationSteps.Step1.class, message = "게시판 종류 적어주세요!")
	@NotNull(groups = ValidationSteps.Step1.class, message = "게시판 종류 적어주세요!") // import javax.validation.constraints.NotNull;

	private String boardType;

	// AOP 적용하면서 userName 삭제
//	@NotEmpty(groups = ValidationSteps.Step2.class, message = "회원명을 넣어주세요!")
//	private String userName;
	
	@NotEmpty(groups = ValidationSteps.Step3.class, message = "제목을 적어주세요!")
	@Length(groups = ValidationSteps.Step4.class, min=5, max=20, message = "제목는 길이 최소5~최대20까지 가능합니다!")
	private String title;
	
	@NotEmpty(groups = ValidationSteps.Step5.class, message = "내용을 입력해주세요!")
	private String contents;
	

	
}
