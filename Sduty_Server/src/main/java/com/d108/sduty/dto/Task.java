package com.d108.sduty.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Task: report's task 정보", description = "리포트의 task 상세정보")
public class Task {
	private int seq;
	private String title;
	private String content;
	private String startTime;
	private String endTime;
	private String durationTime;
	
}
