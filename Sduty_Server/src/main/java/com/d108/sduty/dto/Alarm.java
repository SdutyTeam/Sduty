package com.d108.sduty.dto;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alarm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="alarm_seq")
	private int seq;
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="alarm_study_seq")
//	@Column(name="alarm_study_seq")
//	private Integer studySeq;
	@Column(name="alarm_time")
	private String time;
	private boolean mon;
	private boolean tue;
	private boolean wed;
	private boolean thu;
	private boolean fri;
	private boolean sat;
	private boolean sun;
	
//	@OneToOne(mappedBy = "alarm")
	@OneToOne(optional = false)
	@JoinColumn(name="alarm_study_seq")
	private Study study;
}
