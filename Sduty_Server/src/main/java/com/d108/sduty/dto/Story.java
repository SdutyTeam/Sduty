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
public class Story {
	@Id
	@Column(name="story_seq")
	private int seq;
	@Column(name="story_writer_seq")
	private int writerSeq;
	@Column(name="story_image_source")
	private String imageSource;
	@Column(name="story_thumbnail")
	private String thumbnail;
	@Column(name="story_hashtag")
	private int hashtag;
	@Column(name="story_regtime")
	private Date regtime;
	@Column(name="story_public")
	private int storyPublic;
	@Column(name="story_warning")
	private int warning;

	@Override
	public String toString() {
		return "Story [seq=" + seq + ", writerSeq=" + writerSeq + ", imageSource=" + imageSource + ", thumbnail="
				+ thumbnail + ", hashtag=" + hashtag + ", regtime=" + regtime + ", storyPublic=" + storyPublic
				+ ", warning=" + warning + "]";
	}
}
