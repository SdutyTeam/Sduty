package com.d108.sduty.dto;

import java.util.Date;

public class AuthInfo {
	private int id;
	private String tel;
	private String code;
	private Date expire;
	public AuthInfo() {
		super();
	}
	
	public AuthInfo(String tel, String code, Date expire) {
		super();
		this.tel = tel;
		this.code = code;
		this.expire = expire;
	}
	
	public AuthInfo(String tel, String code) {
		super();
		this.tel = tel;
		this.code = code;
	}

	public AuthInfo(int id, String tel, String code, Date expire) {
		super();
		this.id = id;
		this.tel = tel;
		this.code = code;
		this.expire = expire;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthInfo [id=");
		builder.append(id);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", code=");
		builder.append(code);
		builder.append(", expire=");
		builder.append(expire);
		builder.append("]");
		return builder.toString();
	}
	
	
}