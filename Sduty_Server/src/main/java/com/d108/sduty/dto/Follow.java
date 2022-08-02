package com.d108.sduty.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor	
public class Follow {
	private int followerSeq;
	private int followeeSeq;
	
	@Override
	public String toString() {
		return "Follow [followerSeq=" + followerSeq + ", followeeSeq=" + followeeSeq + "]";
	}
}
