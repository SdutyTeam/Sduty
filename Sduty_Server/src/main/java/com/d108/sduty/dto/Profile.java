package com.d108.sduty.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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
