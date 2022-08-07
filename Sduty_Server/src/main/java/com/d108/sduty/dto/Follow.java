package com.d108.sduty.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor	
@IdClass(FollowPK.class)
public class Follow {
	@Id
	@Column(name = "follower_seq")
	private int followerSeq;
	@Id
	@Column(name = "followee_seq")
	private int followeeSeq;
	
	@Override
	public String toString() {
		return "Follow [followerSeq=" + followerSeq + ", followeeSeq=" + followeeSeq + "]";
	}
	
	@OneToOne
	@JoinColumn(name = "followee_seq", insertable = false, updatable = false)
	private Profile profile;
	
}
