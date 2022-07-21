package com.d108.sduty.dto;

import java.util.Date;

public class AuthInfo {
	private int id;
	private String authcode;
	private String phone;
	private Date expireTime;
	public AuthInfo() {
		super();
	}
	
	public AuthInfo(int id, String authcode, String phone) {
		super();
		this.id = id;
		this.authcode = authcode;
		this.phone = phone;
	}

	public AuthInfo(int id, String authcode, String phone, Date expire_time) {
		super();
		this.id = id;
		this.authcode = authcode;
		this.phone = phone;
		this.expireTime = expire_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getExpire_time() {
		return expireTime;
	}
	public void setExpire_time(Date expire_time) {
		this.expireTime = expire_time;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthCode [id=");
		builder.append(id);
		builder.append(", authcode=");
		builder.append(authcode);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", expire_time=");
		builder.append(expireTime);
		builder.append("]");
		return builder.toString();
	}
	
	

}
