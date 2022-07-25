package com.d108.sduty.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User: 유저 정보", description = "유저 상세 정보")
public class User {
	@ApiModelProperty(value = "아이디")
	private String id;
	private String pass;
	private String name;
	private String tel;	
	private String email;
	private String fcm_token;
	private Date regtime;
	private boolean user_public;
	
	public User() {
		super();
	}

	
	public User(String id, String pass, String name, String email) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
	}


	public User(String id, String pass, String name, String tel, String email) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.tel = tel;
		this.email = email;
	}

	public User(String id, String pass, String name, String tel, String email, String fcm_token, Date regtime,
			boolean user_public) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.fcm_token = fcm_token;
		this.regtime = regtime;
		this.user_public = user_public;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFcm_token() {
		return fcm_token;
	}

	public void setFcm_token(String fcm_token) {
		this.fcm_token = fcm_token;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public boolean isUser_public() {
		return user_public;
	}

	public void setUser_public(boolean user_public) {
		this.user_public = user_public;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", pass=");
		builder.append(pass);
		builder.append(", name=");
		builder.append(name);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", email=");
		builder.append(email);
		builder.append(", fcm_token=");
		builder.append(fcm_token);
		builder.append(", regtime=");
		builder.append(regtime);
		builder.append(", user_public=");
		builder.append(user_public);
		builder.append("]");
		return builder.toString();
	}
	
	
}
