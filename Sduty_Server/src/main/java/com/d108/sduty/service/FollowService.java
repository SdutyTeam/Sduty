package com.d108.sduty.service;

import java.util.List;

import com.d108.sduty.dto.Follow;

public interface FollowService {
	int insertFollow(int followerSeq, int followeeSeq);
	boolean findFollowing(int followerSeq, int followeeSeq);
	int deleteFollow(int followerSeq, int followeeSeq);
	List<Follow> selectFollower(int seq);
	List<Follow> selectFollowee(int seq);
}
