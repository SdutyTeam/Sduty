package com.d108.sduty.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@ApiModel(value = "User: 유저 정보", description = "유저 상세 정보")
public class User {
	@Id
	@ApiModelProperty(value = "아이디")
	@Column(name = "user_seq")
	private int seq;
	@Column(name = "user_id")
	private String id;
	@Column(name = "user_password")
	private String pass;
	@Column(name = "user_name")
	private String name;
	@Column(name = "user_tel")
	private String tel;
	@Column(name = "user_email")
	private String email;
	@Column(name = "user_fcm_token")
	private String fcmToken;
	@Column(name = "user_regtime", updatable = false)
	private Date regtime;
	@Column(name = "user_public")
	private int userPublic;

	@Override
	public String toString() {
		return "User [seq=" + seq + ", id=" + id + ", pass=" + pass + ", name=" + name + ", tel=" + tel + ", email="
				+ email + ", fcmToken=" + fcmToken + ", regtime=" + regtime + ", userPublic=" + userPublic + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + seq;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (seq != other.seq)
			return false;
		return true;
	}

	@OneToMany(mappedBy = "masterSeq", fetch = FetchType.EAGER)
	private Set<Study> masterStudies = new HashSet<Study>();

	@ManyToMany(mappedBy = "participants", fetch = FetchType.EAGER)
	private Set<Study> studies = new HashSet<Study>();
}