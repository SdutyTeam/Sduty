package com.d108.sduty.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	@Id
	@Column(name="reply_seq", updatable = false)
	private int seq;
	@Column(name="reply_user_seq", updatable = false)
	private int userSeq;
	@Column(name="reply_story_seq", updatable = false)
	private int storySeq;
	@Column(name="reply_content")
	private String content;
	@Column(name="reply_regtime", updatable = false)
	private Date regtime;
}
