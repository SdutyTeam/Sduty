package com.d108.sduty.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.d108.sduty.dto.Follow;

@Repository
public interface FollowRepo {
	int insertFollow(int followerSeq, int followeeSeq);
	Follow findFollowing(int followerSeq, int followeeSeq);
	int deleteFollowing(int followerSeq, int followeeSeq);
	List<Follow> selectFollower(int seq);
	List<Follow> selectFollowee(int seq);
}
