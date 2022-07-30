package com.d108.sduty.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter, toString 등
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Study: 스터디 정보", description = "스터디 이름, 소개, 인원 등의 정보")
@Entity
public class Study {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "study_seq")
	private Integer seq;
	@Column(name = "study_master_seq")
	private int masterSeq;
	@Column(name = "study_name")
	private String name;
	@Column(name = "study_introduce")
	private String introduce;
	@Column(name = "study_category")
	private String category;
	@Column(name = "study_limit_Number")
	private int limitNumber;
	@Column(name = "study_join_Number")
	private int joinNumber;
	@Column(name = "study_password")
	private String password;
	@Column(name = "study_is_camstudy")
	private boolean isCamstudy;// is로 시작하는건 getter setter가 is떼고 만듦..
	@Column(name = "study_regtime")
	@CreationTimestamp
	private LocalDateTime regtime;
	@Column(name = "study_notice")
	private String notice;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="participation",
			joinColumns = @JoinColumn(name="participation_study_seq"),
			inverseJoinColumns = @JoinColumn(name="participation_user_seq"))
	private Set<User> participation = new HashSet<User>();
}
