package com.d108.sduty.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User: 유저 정보", description = "유저 상세 정보")
public class User {
	@ApiModelProperty(value = "아이디")
	private int user_seq;
	private String user_id;
	private String user_pass;
	private String user_name;
	private String user_tel;	
	private String user_email;
	private String user_fcm_token;
	private Date user_regtime;
	private int user_user_public;
	
	public User(int user_seq, String user_id, String user_pass, String user_name, String user_tel, String user_email,
			String user_fcm_token, Date user_regtime, int user_user_public) {
		super();
		this.user_seq = user_seq;
		this.user_id = user_id;
		this.user_pass = user_pass;
		this.user_name = user_name;
		this.user_tel = user_tel;
		this.user_email = user_email;
		this.user_fcm_token = user_fcm_token;
		this.user_regtime = user_regtime;
		this.user_user_public = user_user_public;
	}
	
	public User(String user_id, String user_pass, String user_name, String user_email) {
		super();
		this.user_id = user_id;
		this.user_pass = user_pass;
		this.user_name = user_name;
		this.user_email = user_email;
	}

	public User(String user_id, String user_pass, String user_name, String user_tel, String user_email) {
		super();
		this.user_id = user_id;
		this.user_pass = user_pass;
		this.user_name = user_name;
		this.user_tel = user_tel;
		this.user_email = user_email;
	}
	
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_fcm_token() {
		return user_fcm_token;
	}
	public void setUser_fcm_token(String user_fcm_token) {
		this.user_fcm_token = user_fcm_token;
	}
	public Date getUser_regtime() {
		return user_regtime;
	}
	public void setUser_regtime(Date user_regtime) {
		this.user_regtime = user_regtime;
	}
	public int isUser_user_public() {
		return user_user_public;
	}
	public void setUser_user_public(int user_user_public) {
		this.user_user_public = user_user_public;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [user_seq=");
		builder.append(user_seq);
		builder.append(", user_id=");
		builder.append(user_id);
		builder.append(", user_pass=");
		builder.append(user_pass);
		builder.append(", user_name=");
		builder.append(user_name);
		builder.append(", user_tel=");
		builder.append(user_tel);
		builder.append(", user_email=");
		builder.append(user_email);
		builder.append(", user_fcm_token=");
		builder.append(user_fcm_token);
		builder.append(", user_regtime=");
		builder.append(user_regtime);
		builder.append(", user_user_public=");
		builder.append(user_user_public);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}