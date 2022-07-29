package com.d108.sduty.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Profile: 유저 프로필 정보", description = "유저 프로필 상세 정보")
public class Profile {
	
	@Id
	private int userSeq;
	private String nickname;
	private Date birthday;
	private String shortIntroduce;
	private String image;
	private String job;
	private int publicJob;
	private int interest;
	private int publicInterest;
	private int followers;
	private int followees;
	private int mainAchievmentSeq;
	private int blockAction;
	private int warning;
	private int isProhibitedUser;

}
