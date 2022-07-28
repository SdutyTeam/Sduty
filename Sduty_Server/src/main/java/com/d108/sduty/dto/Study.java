package com.d108.sduty.dto;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter 등
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Study: 스터디 정보", description = "스터디 이름, 소개, 인원 등의 정보")
@Entity
public class Study {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="study_seq")
	private Integer seq;
	@Column(name="study_master_seq")
	private int masterSeq;
	@Column(name="study_name")
	private String name;
	@Column(name="study_introduce")
	private String introduce;
	@Column(name="study_category")
	private String category;
	@Column(name="study_limit_Number")
	private int limitNumber;
	@Column(name="study_join_Number")
	private int joinNumber;
	@Column(name="study_password")
	private String password;
	@Column(name="study_is_camstudy")
	private boolean isCamstudy;//is로 시작하는건 getter setter가 is떼고 만듦..
	@Column(name="study_regtime")
	@CreationTimestamp
	private LocalDateTime regtime;
	@Column(name="study_notice")
	private String notice;
	
	//1:0,1관계
	//Alarm이 연관관계의 주인(외래키 가진 쪽)
//	@OneToOne(optional =true)
//	private Alarm alarm;
}
