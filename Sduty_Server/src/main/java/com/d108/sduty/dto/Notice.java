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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notice {
	@Id
	@Column(name="notice_seq")
	int seq;
	@Column(name="notice_content")
	String content;
	@Column(name="notice_writer_seq")
	int writerSeq;
	@Column(name="notice_regtime")
	@CreationTimestamp
	LocalDateTime regtime;
}
