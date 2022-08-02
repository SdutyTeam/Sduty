package com.d108.sduty.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Qna {
	@Id
	@Column(name="qna_seq")
	private int seq;
	@Column(name="ques_title")
	private String title;
	@Column(name="ques_content")
	private String content;
	@Column(name="ques_category")
	private String category;
	@Column(name="ques_writer")
	private int writerSeq;
	@Column(name="ques_regtime")
	@CreationTimestamp
	private LocalDateTime regtime;
	@Column(name="ans_content")
	private String answer;
	@Column(name="ans_writer")
	private Integer adminSeq;
	@Column(name="ans_regtime")
	private String answerRegtime;
}
