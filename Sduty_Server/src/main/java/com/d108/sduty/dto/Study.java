package com.d108.sduty.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter 등
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Study: 스터디 정보", description = "스터디 이름, 소개, 인원 등의 정보")
public class Study {
	private int study_seq;
	private int study_master_seq;
	private String study_name;
	private String study_introduce;
	private String study_category;
	private int study_limit_number;
	private int study_join_number;
	private String study_password;
	private boolean study_is_camstudy;
	private String study_regtime;
}
