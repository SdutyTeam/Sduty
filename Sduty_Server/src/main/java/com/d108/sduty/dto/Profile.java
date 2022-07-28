package com.d108.sduty.dto;

import java.util.Date;

public class Profile {
	
	private int profile_user_seq;
	private String profile_nickname;
	private Date profile_birthday;
	private String profile_short_introduce;
	private String profile_image;
	private String profile_job;
	private int profile_public_job;
	private int profile_interest;
	private int profile_public_interest;
	private int profile_followers;
	private int profile_followees;
	private int profile_main_achievment_seq;
	private int profile_block_action;
	private int profile_warning;
	private int is_prohibited_user;
	
	public Profile(int profile_user_seq, String profile_nickname, Date profile_birthday, String profile_short_introduce,
			String profile_image, String profile_job, int profile_public_job, int profile_interest,
			int profile_public_interest, int profile_followers, int profile_followees, int profile_main_achievment_seq,
			int profile_block_action, int profile_warning, int is_prohibited_user) {
		super();
		this.profile_user_seq = profile_user_seq;
		this.profile_nickname = profile_nickname;
		this.profile_birthday = profile_birthday;
		this.profile_short_introduce = profile_short_introduce;
		this.profile_image = profile_image;
		this.profile_job = profile_job;
		this.profile_public_job = profile_public_job;
		this.profile_interest = profile_interest;
		this.profile_public_interest = profile_public_interest;
		this.profile_followers = profile_followers;
		this.profile_followees = profile_followees;
		this.profile_main_achievment_seq = profile_main_achievment_seq;
		this.profile_block_action = profile_block_action;
		this.profile_warning = profile_warning;
		this.is_prohibited_user = is_prohibited_user;
	}

	public Profile(String profile_nickname, Date profile_birthday, String profile_short_introduce, String profile_image,
			String profile_job, int profile_public_job, int profile_interest, int profile_public_interest,
			int profile_main_achievment_seq) {
		super();
		this.profile_nickname = profile_nickname;
		this.profile_birthday = profile_birthday;
		this.profile_short_introduce = profile_short_introduce;
		this.profile_image = profile_image;
		this.profile_job = profile_job;
		this.profile_public_job = profile_public_job;
		this.profile_interest = profile_interest;
		this.profile_public_interest = profile_public_interest;
		this.profile_main_achievment_seq = profile_main_achievment_seq;
	}

	public int getProfile_user_seq() {
		return profile_user_seq;
	}

	public void setProfile_user_seq(int profile_user_seq) {
		this.profile_user_seq = profile_user_seq;
	}

	public String getProfile_nickname() {
		return profile_nickname;
	}

	public void setProfile_nickname(String profile_nickname) {
		this.profile_nickname = profile_nickname;
	}

	public Date getProfile_birthday() {
		return profile_birthday;
	}

	public void setProfile_birthday(Date profile_birthday) {
		this.profile_birthday = profile_birthday;
	}

	public String getProfile_short_introduce() {
		return profile_short_introduce;
	}

	public void setProfile_short_introduce(String profile_short_introduce) {
		this.profile_short_introduce = profile_short_introduce;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getProfile_job() {
		return profile_job;
	}

	public void setProfile_job(String profile_job) {
		this.profile_job = profile_job;
	}

	public int getProfile_public_job() {
		return profile_public_job;
	}

	public void setProfile_public_job(int profile_public_job) {
		this.profile_public_job = profile_public_job;
	}

	public int getProfile_interest() {
		return profile_interest;
	}

	public void setProfile_interest(int profile_interest) {
		this.profile_interest = profile_interest;
	}

	public int getProfile_public_interest() {
		return profile_public_interest;
	}

	public void setProfile_public_interest(int profile_public_interest) {
		this.profile_public_interest = profile_public_interest;
	}

	public int getProfile_followers() {
		return profile_followers;
	}

	public void setProfile_followers(int profile_followers) {
		this.profile_followers = profile_followers;
	}

	public int getProfile_followees() {
		return profile_followees;
	}

	public void setProfile_followees(int profile_followees) {
		this.profile_followees = profile_followees;
	}

	public int getProfile_main_achievment_seq() {
		return profile_main_achievment_seq;
	}

	public void setProfile_main_achievment_seq(int profile_main_achievment_seq) {
		this.profile_main_achievment_seq = profile_main_achievment_seq;
	}

	public int getProfile_block_action() {
		return profile_block_action;
	}

	public void setProfile_block_action(int profile_block_action) {
		this.profile_block_action = profile_block_action;
	}

	public int getProfile_warning() {
		return profile_warning;
	}

	public void setProfile_warning(int profile_warning) {
		this.profile_warning = profile_warning;
	}

	public int getIs_prohibited_user() {
		return is_prohibited_user;
	}

	public void setIs_prohibited_user(int is_prohibited_user) {
		this.is_prohibited_user = is_prohibited_user;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Profile [profile_user_seq=");
		builder.append(profile_user_seq);
		builder.append(", profile_nickname=");
		builder.append(profile_nickname);
		builder.append(", profile_birthday=");
		builder.append(profile_birthday);
		builder.append(", profile_short_introduce=");
		builder.append(profile_short_introduce);
		builder.append(", profile_image=");
		builder.append(profile_image);
		builder.append(", profile_job=");
		builder.append(profile_job);
		builder.append(", profile_public_job=");
		builder.append(profile_public_job);
		builder.append(", profile_interest=");
		builder.append(profile_interest);
		builder.append(", profile_public_interest=");
		builder.append(profile_public_interest);
		builder.append(", profile_followers=");
		builder.append(profile_followers);
		builder.append(", profile_followees=");
		builder.append(profile_followees);
		builder.append(", profile_main_achievment_seq=");
		builder.append(profile_main_achievment_seq);
		builder.append(", profile_block_action=");
		builder.append(profile_block_action);
		builder.append(", profile_warning=");
		builder.append(profile_warning);
		builder.append(", is_prohibited_user=");
		builder.append(is_prohibited_user);
		builder.append("]");
		return builder.toString();
	}
	

}
