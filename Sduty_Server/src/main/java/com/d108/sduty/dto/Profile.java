package com.d108.sduty.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "Profile: 유저 프로필 정보", description = "유저 프로필 상세 정보")
public class Profile {
	
	@Id
	@Column(name="profile_user_seq")
	private int userSeq;
	@Column(name="profile_nickname")
	private String nickname;
	@Column(name="profile_birthday")
	private Date birthday;
	@Column(name="profile_public_birthday")
	private int publicBirthday;
	@Column(name="profile_short_introduce")
	private String shortIntroduce;
	@Column(name="profile_image")
	private String image;
	@Column(name="profile_job")
	private String job;
	@Column(name="profile_public_job")
	private int publicJob;
	@Column(name="profile_interest")
	private int interest;
	@Column(name="profile_public_interest")
	private int publicInterest;
	@Column(name="profile_followers")
	private int followers;
	@Column(name="profile_followees")
	private int followees;
	@Column(name="profile_main_achievement_seq")
	private int mainAchievmentSeq;
	@Column(name="profile_block_action")
	private int blockAction;
	@Column(name="profile_warning")
	private int warning;
	@Column(name="is_prohibited_user")
	private int isProhibitedUser;
	@Column(name="is_studying")
	private int isStudying;
}
