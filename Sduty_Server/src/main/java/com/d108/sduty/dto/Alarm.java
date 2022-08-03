package com.d108.sduty.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.d108.sduty.utils.StudyScheduler;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ApiModel(value = "Alarm: 스터디 알림 정보", description = "캠스터디 알림 정보")
public class Alarm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="alarm_seq")
	private int seq;
	@Column(name="alarm_time")
	private String time;
	private boolean mon;
	private boolean tue;
	private boolean wed;
	private boolean thu;
	private boolean fri;
	private boolean sat;
	private boolean sun;
	
//	public void setScheduler() {
//		StudyScheduler ts = new StudyScheduler();
//		ts.changeCronSet("*/5 * * * * *");
//		ts.startScheduler(1);
//	}
}
