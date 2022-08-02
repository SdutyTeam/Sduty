package com.d108.sduty.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DailyQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dailyq_seq")
	private int seq;
	@Column(name="dailyq_admin_seq")
	private int adminSeq;
	@Column(name="dailyq_content")
	private String content;
	@Column(name="dailyq_regtime")
	@CreationTimestamp
	private LocalDateTime regtime;
}
